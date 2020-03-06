package com.sdu.fund.biz.shared.request;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:14
 **/
public class WeChatPurchaseApplyCheckRequest extends BaseOrderRequest {

    private String fundCode;

    // 预购买金额
    private String orderAmount;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}
