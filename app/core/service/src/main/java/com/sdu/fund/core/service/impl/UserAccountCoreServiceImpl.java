package com.sdu.fund.core.service.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.request.AccountFlowRequest;
import com.sdu.fund.core.model.account.bo.UserAccountFlow;
import com.sdu.fund.core.model.trade.enums.UserAccountChangeDirectionEnum;
import com.sdu.fund.core.repository.UserAccountFlowRepository;
import com.sdu.fund.core.repository.UserAccountRepository;
import com.sdu.fund.core.service.UserAccountCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/19 20:23
 **/
public class UserAccountCoreServiceImpl implements UserAccountCoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountCoreServiceImpl.class);

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountFlowRepository userAccountFlowRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public Result accountPay(AccountFlowRequest accountFlowRequest) {
        try {
            // 支付事务是requirednew的，为了模拟真实支付场景
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);//设置为RequiresNew传播级别
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    // 1.锁
                    UserAccount userAccount = userAccountRepository.lock(accountFlowRequest.getPayment().getUserId());
                    // 2.创建用户账户流水
                    UserAccountFlow userAccountFlow = UserAccountFlow.createFlow(accountFlowRequest.getPayment(),
                            accountFlowRequest.getRelatedFlowId(),
                            accountFlowRequest.getChangeDirection(), accountFlowRequest.getType(), accountFlowRequest.getInitiator(),
                            userAccount.getTotolAmount(), userAccount.getAvailAmount(),
                            userAccount.getFreezeAmount(),
                            accountFlowRequest.getFreezeAmount());
                    userAccountFlowRepository.add(userAccountFlow);
                    // 3.出账
                    userAccountRepository.accountOut(accountFlowRequest.getPayment().getUserId(),
                            accountFlowRequest.getPayment().getOrderAmount(),accountFlowRequest.getFreezeAmount());
                    LOGGER.info("用户账户出账成功，payOrderId={},userId={}", accountFlowRequest.getPayment().getPayOrderId(),
                            accountFlowRequest.getPayment().getUserId());
                }
            });
            return ResultUtil.buildSuccessResult();
        } catch (Exception e) {
            LOGGER.info("用户账户出账失败，payOrderId={},userId={}", accountFlowRequest.getPayment().getPayOrderId(),
                    accountFlowRequest.getPayment().getUserId());
            return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public Result accountReceive() {
        return null;
    }
}
