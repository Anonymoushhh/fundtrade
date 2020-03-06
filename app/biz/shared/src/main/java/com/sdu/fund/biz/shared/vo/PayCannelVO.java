package com.sdu.fund.biz.shared.vo;


import com.sdu.fund.common.utils.NumberUtil;
import com.sdu.fund.core.model.trade.bo.TradeOrder;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/18 17:59
 **/
public class PayCannelVO {

    private String orderId;

    // 剩余支付时间 ex:30 * 60 * 1000(默认30分钟关单)
    private String timeRemaining = "30 * 60 * 1000";

    // 付款金额
    private String payAmount;

    public static PayCannelVO buildSuccessPayCannelVO(TradeOrder tradeOrder){
        PayCannelVO payCannelVO = new PayCannelVO();
        payCannelVO.setOrderId(tradeOrder.getOrderId());
        payCannelVO.setPayAmount(NumberUtil.getBigDecimal_to2_no45(tradeOrder.getRealPayAmount()).toString());
        return payCannelVO;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
