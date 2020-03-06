package com.sdu.fund.biz.shared.vo;


import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.common.utils.NumberUtil;
import com.sdu.fund.core.model.trade.bo.TradeOrder;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/15 21:21
 **/
public class PurchaseApplyVO {

    private String orderId;

    private String fundName;

    private String purchaseTradeOrderType;

    // 下单金额
    private String purchaseAmount;

    // 费率扣的金额
    private String feeAmount;

    // 最终支付金额
    private String realPayAmount;

    // 预计确认时间
    private String expectComfirmDate;

    // 预计查看收益时间
    private String expectProfitViewDate;

    public static PurchaseApplyVO convert(TradeOrder tradeOrder) {
        PurchaseApplyVO purchaseApplyVO = new PurchaseApplyVO();
        if (tradeOrder == null) {
            return null;
        }
        purchaseApplyVO.setOrderId(tradeOrder.getOrderId());
        purchaseApplyVO.setFundName(tradeOrder.getFundName());
        purchaseApplyVO.setPurchaseAmount(NumberUtil.getBigDecimal_to2_is45(tradeOrder.getOrderAmount()).toString());
        purchaseApplyVO.setFeeAmount(NumberUtil.getBigDecimal_to2_is45(tradeOrder.getFeeAmount()).toString());
        purchaseApplyVO.setRealPayAmount(NumberUtil.getBigDecimal_to2_is45(tradeOrder.getRealPayAmount()).toString());
        purchaseApplyVO.setPurchaseTradeOrderType(tradeOrder.getOrderType().getCode());
        purchaseApplyVO.setExpectComfirmDate(DateUtil.dateToStr(tradeOrder.getPurchaseConfirmDay(), DateUtil.FMT_YMD3));
        purchaseApplyVO.setExpectProfitViewDate(DateUtil.dateToStr(tradeOrder.getPurchaseConfirmDay(),
                DateUtil.FMT_YMD3) + "收益更新后");
        return purchaseApplyVO;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
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
