package com.sdu.fund.biz.shared.factory;

import com.sdu.fund.biz.shared.enums.OrderTypeEnum;
import com.sdu.fund.biz.shared.request.BaseOrderRequest;
import com.sdu.fund.biz.shared.request.WeChatPayOrderRequest;
import com.sdu.fund.biz.shared.request.WeChatPurchaseApplyOrderRequest;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.common.utils.PayOrderIdUtil;
import com.sdu.fund.common.utils.TradeOrderIdUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.trade.bo.BaseOrder;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.enums.PayChannelEnum;
import com.sdu.fund.core.model.trade.enums.PayStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;

import java.math.BigDecimal;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:07
 **/
public class OrderCreateFactory {

    public BaseOrder getOrder(BaseOrderRequest request, long userId) {
        OrderTypeEnum typeEnum = OrderTypeEnum.getEnumByCode(request.getOrderType());
        Validator.notNull(typeEnum);
        switch (typeEnum) {
            case PURCHASE:
                return getPurchaseOrder(request, userId);
            case PAY:
                return getPamyentOrder(request, userId);
            case REDEEM:
            case FIXED:
            default:
                throw new CommonException("没有该状态机");
        }
    }

    private TradeOrder getPurchaseOrder(BaseOrderRequest request, long userId) {
        WeChatPurchaseApplyOrderRequest weChatPurchaseApplyRequest = (WeChatPurchaseApplyOrderRequest)request;

        TradeOrder tradeOrder = new TradeOrder();
        long now = System.currentTimeMillis();
        tradeOrder.setTradeOrderId(TradeOrderIdUtil.genTradeOrderId(tradeOrder.getUserId(), now,
                tradeOrder.getTradeOrderChannel().getCode()));
        tradeOrder.setUserId(userId);
        tradeOrder.setFundCode(weChatPurchaseApplyRequest.getFundCode());
        tradeOrder.setFundName(weChatPurchaseApplyRequest.getFundName());
        tradeOrder.setTradeOrderType(TradeOrderTypeEnum.PURCHASE);
        tradeOrder.setTradeOrderAmount(new BigDecimal(weChatPurchaseApplyRequest.getTradeOrderAmount()));
        tradeOrder.setFee(new BigDecimal(weChatPurchaseApplyRequest.getFee()));
        tradeOrder.setFeeAmount(new BigDecimal(weChatPurchaseApplyRequest.getFeeAmount()));
        tradeOrder.setRealPayAmount(new BigDecimal(weChatPurchaseApplyRequest.getRealPayAmount()));
        tradeOrder.setTradeOrderTime(DateUtil.unixToDate(now));
        tradeOrder.setTradeOrderStatus(TradeOrderStatusEnum.INIT);
        return tradeOrder;
    }

    private Payment getPamyentOrder(BaseOrderRequest request, long userId) {
        WeChatPayOrderRequest weChatPayOrderRequest = (WeChatPayOrderRequest)request;

        Payment payment = new Payment();
        long now = System.currentTimeMillis();
        payment.setPayOrderId(PayOrderIdUtil.genPayOrderId(weChatPayOrderRequest.getTradeOrderId(),now,
                weChatPayOrderRequest.getPayChannel()));
        payment.setOrderId(weChatPayOrderRequest.getTradeOrderId());
        payment.setUserId(userId);
        payment.setOrderAmount(new BigDecimal(weChatPayOrderRequest.getOrderAmount()));
        payment.setPayChannel(PayChannelEnum.getEnumByCode(weChatPayOrderRequest.getPayChannel()));
        payment.setPayStatus(PayStatusEnum.PAYING);
        return payment;
    }
}
