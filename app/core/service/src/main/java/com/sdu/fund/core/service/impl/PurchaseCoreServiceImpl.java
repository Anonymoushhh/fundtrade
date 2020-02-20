package com.sdu.fund.core.service.impl;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.core.model.account.bo.UserAccountFlow;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.bo.TradeOrderFlow;
import com.sdu.fund.core.model.trade.enums.TradeOrderFlowTypeEnum;
import com.sdu.fund.core.repository.PaymentRepository;
import com.sdu.fund.core.repository.TradeOrderFlowRepository;
import com.sdu.fund.core.repository.TradeOrderRepository;
import com.sdu.fund.core.service.PurchaseCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class PurchaseCoreServiceImpl implements PurchaseCoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseCoreServiceImpl.class);

    @Autowired
    private TradeOrderRepository tradeOrderRepository;

    @Autowired
    private TradeOrderFlowRepository tradeOrderFlowRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public TradeOrder apply(TradeOrder tradeOrder) {
        // 初始化流水
        TradeOrderFlow tradeOrderFlow = TradeOrderFlow.createFlow(tradeOrder, TradeOrderFlowTypeEnum.USER_ORDER);

        Result<TradeOrderFlow> flowExistResult = tradeOrderFlowRepository.get(tradeOrderFlow.getFlowId());
        if (flowExistResult != null && flowExistResult.isSuccess() && flowExistResult.getData() != null) {
            // 幂等第一层控制，有订单流水直接返回
            return tradeOrder;
        }
        // flowId有唯一索引进行兜底
        Result flowResult = tradeOrderFlowRepository.add(tradeOrderFlow);
        if (flowResult != null && flowResult.isSuccess()) {
            // 流水幂等已经控制，理论上此段逻辑不会存在并发问题，兜底有orderId唯一索引
            Result result = tradeOrderRepository.add(tradeOrder);
            if (result != null && result.isSuccess()) {
                LOGGER.info("基金申购申请创单成功，tradeOrderId={},userId={}", tradeOrder.getTradeOrderId(),
                        tradeOrder.getUserId());
                return tradeOrder;
            } else {
                // 创建订单失败，流水状态需要更新
                Result updateResult = transactionTemplate.execute(new TransactionCallback<Result>() {
                    @Override
                    public Result doInTransaction(TransactionStatus transactionStatus) {
                        // 1.锁流水
                        TradeOrderFlow flowExist =
                                tradeOrderFlowRepository.lock(tradeOrderFlow.getFlowId());
                        // 2.判
                        if (flowExist != null && !flowExist.getValid()) {
                            return;
                        }
                        // 3.更新状态为失败
                        flowExist.setValid(false);
                        return tradeOrderFlowRepository.update(tradeOrderFlow);
                    }
                });
                if (!(updateResult != null && updateResult.isSuccess())) {
                    // 更新流水状态失败，待定时任务关闭
                }
                throw new CommonException("创建订单失败");
            }
        } else {
            throw new CommonException("创建订单流水失败");
        }


    }

    @Override
    public Payment pay(Payment payment) {
        // 1.幂等控制，有订单直接返回
        Result<Payment> paymentExistResult = paymentRepository.get(payRequest.getPayment().getPayOrderId());
        if (paymentExistResult != null && paymentExistResult.isSuccess() && paymentExistResult.getData() != null) {
            return ResultUtil.buildSuccessResult();
        }

        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                // 锁订单
                TradeOrder tradeOrder = tradeOrderRepository.lock(payment.getOrderId());
                if(tradeOrder == null){
                    throw new CommonException("支付未找到订单");
                }
                // 初始化流水
                TradeOrderFlow tradeOrderFlow = TradeOrderFlow.createFlow(tradeOrder, TradeOrderFlowTypeEnum.USER_PAY);
                // 调用支付微服务
                return null;
            }
        });
        return null;
    }

    @Override
    public TradeOrder confirm(TradeOrder tradeOrder) {
        return null;
    }

    @Override
    public TradeOrder cancel(TradeOrder tradeOrder) {
        return null;
    }
}
