package com.sdu.fund.biz.shared.request;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:14
 **/
public class WeChatPurchaseApplyOrderRequest extends BaseOrderRequest {

    private String fundCode;

    private String fundName;

    // 幂等号
    private String idempotentId;

    // 预购买金额
    private String orderAmount;

    // 下单渠道
    private String orderChannel;

    // 费率
    private String fee;

    // 费率扣的金额
    private String feeAmount;

    // 最终支付金额
    private String realPayAmount;

    // 红包
    private String couponId;

    // 红包金额
    private String couponAmount;

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

    public String getIdempotentId() {
        return idempotentId;
    }

    public void setIdempotentId(String idempotentId) {
        this.idempotentId = idempotentId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(String realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }
}
