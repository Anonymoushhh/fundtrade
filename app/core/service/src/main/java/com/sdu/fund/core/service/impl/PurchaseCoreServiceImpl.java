package com.sdu.fund.core.service.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.utils.SnowflakeIdUtil;
import com.sdu.fund.core.model.trade.bo.HoldPosition;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.bo.TradeOrderFlow;
import com.sdu.fund.core.model.trade.enums.*;
import com.sdu.fund.core.model.trade.event.TradeOrderEvent;
import com.sdu.fund.core.repository.PaymentRepository;
import com.sdu.fund.core.repository.TradeOrderFlowRepository;
import com.sdu.fund.core.repository.TradeOrderRepository;
import com.sdu.fund.core.request.AccountFlowRequest;
import com.sdu.fund.core.request.AsyncUpdateHoldPositonRequest;
import com.sdu.fund.core.service.AsyncService;
import com.sdu.fund.core.service.PurchaseCoreService;
import com.sdu.fund.core.service.UserAccountCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

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

    @Autowired
    private UserAccountCoreService userAccountCoreService;

    @Override
    public Result<TradeOrder> apply(TradeOrder tradeOrder) {
        try {
            // 初步幂等控制
            TradeOrder tradeOrderExist =
                    tradeOrderRepository.getByIdempotentId(tradeOrder.getIdempotentId());
            if (tradeOrderExist != null) {
                return ResultUtil.buildSuccessResult(tradeOrderExist);
            }
            // 初始化流水
            TradeOrderFlow tradeOrderFlow = TradeOrderFlow.createFlow(tradeOrder.getOrderId(), tradeOrder.getUserId(),
                    TradeOrderFlowTypeEnum.USER_ORDER, null);

            tradeOrderFlowRepository.add(tradeOrderFlow);
            try {
                // 兜底有orderId,idempotentId唯一索引
                tradeOrderRepository.add(tradeOrder);
                LOGGER.info("基金申购申请创单成功，tradeOrderId={},userId={}", tradeOrder.getOrderId(),
                        tradeOrder.getUserId());
                return ResultUtil.buildSuccessResult(tradeOrder);
            } catch (Exception e) {
                // 创建订单失败，流水状态需要更新
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        // 1.锁流水
                        TradeOrderFlow flowExist =
                                tradeOrderFlowRepository.lock(tradeOrderFlow.getFlowId());
                        // 2.判
                        if (flowExist != null && !flowExist.getValid()) {
                            return;
                        }
                        // 3.更新状态为失败
                        // 更新失败等待定时任务关闭
                        tradeOrderFlowRepository.makeInValid(flowExist.getFlowId());
                    }
                });
                if (e instanceof DuplicateKeyException) {
                    // 重复下单
                    return ResultUtil.buildSuccessResult(tradeOrder);
                }
                throw new CommonException("基金申购申请创单失败");
            }
        } catch (Exception e) {
            LOGGER.error("基金申购申请创单失败，tradeOrderId={},userId={}", tradeOrder.getOrderId(),
                    tradeOrder.getUserId());
            return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public Result<Payment> pay(Payment payment) {
        try {
            // 1.幂等控制，有订单直接返回
            Payment paymentExist = paymentRepository.getByOrderId(payment.getOrderId());
            if (paymentExist != null) {
                return ResultUtil.buildSuccessResult(paymentExist);
            }
            // 2.初始化流水
            TradeOrderFlow tradeOrderFlow = TradeOrderFlow.createFlow(payment.getOrderId(), payment.getUserId(),
                    TradeOrderFlowTypeEnum.USER_PAY, payment.getPayOrderId());
            TradeOrderFlow flowExist = tradeOrderFlowRepository.get(tradeOrderFlow.getFlowId());
            if (flowExist != null) {
                // 幂等第一层控制，有订单流水直接返回
                return ResultUtil.buildSuccessResult(payment);
            }
            tradeOrderFlowRepository.add(tradeOrderFlow);
            // 3.支付事务
            Result transactionResult = transactionTemplate.execute(new TransactionCallback<Result>() {
                @Override
                public Result doInTransaction(TransactionStatus transactionStatus) {
                    // 3.1.初始化支付单
                    paymentRepository.add(payment);
                    // 3.2.锁订单
                    TradeOrder tradeOrder = tradeOrderRepository.lock(payment.getOrderId());
                    if (tradeOrder == null) {
                        transactionStatus.setRollbackOnly();
                        LOGGER.error("支付未找到订单，payOrderId={},tradeOrderId={},userId={}",
                                payment.getPayOrderId(), tradeOrder.getOrderId(),
                                tradeOrder.getUserId());
                        return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
                    }
                    // 3.3.判状态
                    if (tradeOrder.getOrderStatus() != TradeOrderStatusEnum.INIT && tradeOrder.getOrderStatus() != TradeOrderStatusEnum.WAIT_PAY) {
                        // 该订单可能已被支付或取消
                        transactionStatus.setRollbackOnly();
                        return ResultUtil.buildFailedResult(ResultCode.ORDER_PAID_OR_CANNCELED);
                    }
                    // 3.4.调用支付微服务
                    AccountFlowRequest accountFlowRequest = new AccountFlowRequest();
                    accountFlowRequest.setPayment(payment);
                    accountFlowRequest.setRelatedFlowId(tradeOrderFlow.getFlowId());
                    accountFlowRequest.setType(UserAccountFlowTypeEnum.PURCHASE);
                    accountFlowRequest.setInitiator(FlowInitiatorEnum.USER);
                    accountFlowRequest.setChangeDirection(UserAccountChangeDirectionEnum.OUT);
                    // 设置冻结金额,该笔资金将会被登记为在途资产,冻结金额应该是购买基金支付的全部费用，不扣除手续费，
                    // 在基金份额确认时再扣除相关费用，不然基金份额确认失败无法退款
                    accountFlowRequest.setFreezeAmount(payment.getOrderAmount());

                    Result result = userAccountCoreService.accountPay(accountFlowRequest);
                    if (result != null && result.isSuccess()) {
                        // 不能在该处更新订单状态，若此处逻辑抛异常，创建的支付单会回滚掉
                        // 基金模型的支付单和余额支付结果是强关联关系，订单状态异步更新
                        return ResultUtil.buildSuccessResult();
                    } else {
                        transactionStatus.setRollbackOnly();
                        LOGGER.error("支付微服务失败，payOrderId={},tradeOrderId={},userId={}",
                                payment.getPayOrderId(), tradeOrder.getOrderId(),
                                tradeOrder.getUserId());
                        return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
                    }
                }
            });
            if (transactionResult != null && transactionResult.isSuccess()) {
                // 支付事务是requirednew的，为了模拟真实支付场景
                // 可能出现支付成功但是下面逻辑抛出异常导致订单状态未更新
                try {
                    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                        @Override
                        protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                            // 4.1.锁订单，支付单
                            TradeOrder tradeOrder = tradeOrderRepository.lock(payment.getOrderId());
                            Payment paymentExist = paymentRepository.lock(payment.getPayOrderId());
                            // 4.2.判状态（按道理这里可以不会出现并发问题，一笔订单值会有一个请求支付成功，这里仅做兜底）
                            if (tradeOrder.getOrderStatus() == TradeOrderStatusEnum.PAIED || tradeOrder.getOrderStatus() == TradeOrderStatusEnum.CANCEL) {
                                // 该订单可能已被支付或取消
                                return;
                            }
                            if (paymentExist.getPayStatus() != PayStatusEnum.PAYING) {
                                // 该订单已被支付
                                return;
                            }
                            // 4.3.更新订单,支付单
                            tradeOrder.pushStatus(TradeOrderEvent.PAY_SUCCESS);
                            tradeOrder.setPayOrderId(paymentExist.getPayOrderId());
                            tradeOrder.setPayTime(paymentExist.getGmtCreate());
                            tradeOrderRepository.update(tradeOrder);
                            paymentExist.setPayStatus(PayStatusEnum.PAID);
                            paymentRepository.update(paymentExist);

                            LOGGER.info("用户支付成功，payOrderId={},tradeOrderId={},userId={},orderAmount={}",
                                    payment.getPayOrderId(), payment.getOrderId(),
                                    payment.getUserId(), payment.getOrderAmount());
                        }
                    });
                } catch (Exception e) {
                    // catch住异常，此时用户支付已经成功，但是订单状态更新失败,等恢复（此情况理论上极少发生，待压测）
                    LOGGER.error("用户支付成功，但订单状态更新失败，payOrderId={},tradeOrderId={},userId={},orderAmount={}",
                            payment.getPayOrderId(), payment.getOrderId(),
                            payment.getUserId(), payment.getOrderAmount());
                }
                return ResultUtil.buildSuccessResult(payment);
            } else {
                LOGGER.error("用户支付失败，payOrderId={},tradeOrderId={},userId={},orderAmount={}",
                        payment.getPayOrderId(), payment.getOrderId(),
                        payment.getUserId(), payment.getOrderAmount());
                // 支付失败，订正流水状态
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        // 1.锁流水
                        TradeOrderFlow tradeOrderFlowExist =
                                tradeOrderFlowRepository.lock(tradeOrderFlow.getFlowId());
                        // 2.判
                        if (tradeOrderFlowExist != null && !tradeOrderFlowExist.getValid()) {
                            return;
                        }
                        // 3.更新状态为失败
                        // 若更新失败等待定时任务关闭
                        tradeOrderFlowRepository.makeInValid(tradeOrderFlowExist.getFlowId());
                    }
                });
                return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
            }
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                // 重复支付
                return ResultUtil.buildSuccessResult(payment);
            }
            LOGGER.error("用户支付失败，payOrderId={},tradeOrderId={},userId={},orderAmount={}",
                    payment.getPayOrderId(), payment.getOrderId(),
                    payment.getUserId(), payment.getOrderAmount());
            return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public Result<TradeOrder> payCannel(TradeOrder tradeOrder) throws Exception {
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    // 1.锁订单
                    TradeOrder tradeOrderExist = tradeOrderRepository.lock(tradeOrder.getOrderId());
                    // 2.判状态
                    if (tradeOrderExist.getOrderStatus() == TradeOrderStatusEnum.WAIT_PAY) {
                        return;
                    }
                    // 3.更新订单
                    tradeOrderExist.pushStatus(TradeOrderEvent.NO_PAY);
                    tradeOrderRepository.update(tradeOrderExist);
                    tradeOrder.setRealPayAmount(tradeOrderExist.getRealPayAmount());
                    LOGGER.info("用户支付取消，tradeOrderId={},userId={}", tradeOrderExist.getOrderId(),
                            tradeOrderExist.getUserId());
                }

            });
        } catch (Exception e) {
            LOGGER.error("用户支付取消失败，tradeOrderId={},userId={},msg={}", tradeOrder.getOrderId(),
                    tradeOrder.getUserId(),e.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SYSTEM_EXCEPTION);
        }
        return ResultUtil.buildSuccessResult(tradeOrder);
    }

    @Override
    public Result confirm(TradeOrder tradeOrder) {
        return null;
    }

    @Override
    public Result cancel(TradeOrder tradeOrder) {
        return null;
    }
}
