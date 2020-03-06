package com.sdu.fund.biz.shared.request;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:14
 **/
public class WeChatPurchasePreApplyOrderRequest extends BaseOrderRequest {

    private String fundCode;

    private String fundName;

    // 下单渠道
    private String orderChannel;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

}
