package com.sdu.fund.biz.shared.vo;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/15 21:21
 **/
public class PurchasePreApplyVO {

    private String fundCode;

    private String fundName;

    // 幂等号
    private String idempotentId;

    private String minPurchaseAmount;

    private PurchaseStepsVO purchaseStepsVO;

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

    public PurchaseStepsVO getPurchaseStepsVO() {
        return purchaseStepsVO;
    }

    public void setPurchaseStepsVO(PurchaseStepsVO purchaseStepsVO) {
        this.purchaseStepsVO = purchaseStepsVO;
    }

    public String getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(String minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }
}
