package com.sdu.fund.biz.shared.vo;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/15 21:21
 **/
public class PurchaseApplyVO {

    private String fundName;

    private String purchaseTradeOrderType;

    private String purchaseAmount;

    // 预计确认时间
    private String expectComfirmDate;

    // 预计查看收益时间
    private String expectProfitViewDate;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getPurchaseTradeOrderType() {
        return purchaseTradeOrderType;
    }

    public void setPurchaseTradeOrderType(String purchaseTradeOrderType) {
        this.purchaseTradeOrderType = purchaseTradeOrderType;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getExpectComfirmDate() {
        return expectComfirmDate;
    }

    public void setExpectComfirmDate(String expectComfirmDate) {
        this.expectComfirmDate = expectComfirmDate;
    }

    public String getExpectProfitViewDate() {
        return expectProfitViewDate;
    }

    public void setExpectProfitViewDate(String expectProfitViewDate) {
        this.expectProfitViewDate = expectProfitViewDate;
    }
}
