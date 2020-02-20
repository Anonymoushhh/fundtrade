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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountFlowRepository userAccountFlowRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public Result accountOut(AccountFlowRequest accountFlowRequest) {

        // 1.创建用户账户流水
        UserAccountFlow userAccountFlow = UserAccountFlow.createFlow(accountFlowRequest.getPayment(),
                accountFlowRequest.getRelatedFlowId(),
                UserAccountChangeDirectionEnum.OUT, accountFlowRequest.getType(), accountFlowRequest.getInitiator(),
                accountFlowRequest.getPreTotolAmount(), accountFlowRequest.getPreAvailAmount(), accountFlowRequest.getPreFreezeAmount(),
                accountFlowRequest.getFreezeAmount());
        Result userAccountFlowAddResult = userAccountFlowRepository.add(userAccountFlow);
        if (userAccountFlowAddResult != null && userAccountFlowAddResult.isSuccess()) {
            // 2.出账
            Result accountOutResult = userAccountRepository.accountOut(accountFlowRequest.getPayment().getUserId(),
                    accountFlowRequest.getPayment().getOrderAmount());
            if(accountOutResult != null && accountOutResult.isSuccess()){
                return ResultUtil.buildSuccessResult();
            }else{
                // 出账失败更新流水
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        // 1.锁流水
                        UserAccountFlow userAccountFlowExist =
                                userAccountFlowRepository.lock(userAccountFlow.getFlowId());
                        // 2.判
                        if(userAccountFlowExist!=null&&!userAccountFlowExist.getValid()){
                            return;
                        }
                        // 3.更新状态为失败
                        userAccountFlowExist.setValid(false);
                        userAccountFlowRepository.update(userAccountFlowExist);
                    }
                });
                return ResultUtil.buildFailedResult(accountOutResult!=null?accountOutResult.getCode(): ResultCode.SYSTEM_EXCEPTION);
            }
        }else{
            return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public Result accountIn() {
        return null;
    }
}
