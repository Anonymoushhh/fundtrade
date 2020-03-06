package com.sdu.fund.biz.shared.vo;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/15 21:21
 **/
public class PurchaseApplyCheckVO {

    private String fundCode;

    // 预购买金额
    private String orderAmount;

    // 前端提示的错误信息
    private String errorMsg;

    // 费率
    private String fee;

    // 费率扣的金额
    private String feeAmount;

    // 最终支付金额
    private String realPayAmount;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOrderAmount() {
        return orderAmount;
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
}
