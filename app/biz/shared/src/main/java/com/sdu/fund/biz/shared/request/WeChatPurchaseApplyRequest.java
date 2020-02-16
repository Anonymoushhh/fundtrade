package com.sdu.fund.request;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:14
 **/
public class WeChatPurchaseApplyRequest {


    private String fundCode;

    // 付款金额
    private String orderAmount;

    // 订单类型
    private String orderType;

    // 费率
    private Double fee;

    // 费率扣的金额
    private Double feeAmount;

    // 红包
    private Double couponId;

    // 红包金额
    private Double couponAmount;

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Double getCouponId() {
        return couponId;
    }

    public void setCouponId(Double couponId) {
        this.couponId = couponId;
    }

    public Double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }
}
