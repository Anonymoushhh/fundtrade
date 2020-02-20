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

    private String purchaseAmount;

    // 预计确认时间
    private String expectComfirmDate;

    // 预计查看收益时间
    private String expectProfitViewDate;

    public static PurchaseApplyVO convert(TradeOrder tradeOrder){
        PurchaseApplyVO purchaseApplyVO = new PurchaseApplyVO();
        if(tradeOrder == null){
            return purchaseApplyVO;
        }
        purchaseApplyVO.setOrderId(tradeOrder.getTradeOrderId());
        purchaseApplyVO.setFundName(tradeOrder.getFundName());
        purchaseApplyVO.setPurchaseAmount(NumberUtil.getBigDecimal_to2_is45(tradeOrder.getTradeOrderAmount()).toString());
        purchaseApplyVO.setPurchaseTradeOrderType(tradeOrder.getTradeOrderType().getCode());
        purchaseApplyVO.setExpectComfirmDate(DateUtil.dateToStr(tradeOrder.getPurchaseConfirmDay(),DateUtil.FMT_YMD3));
        purchaseApplyVO.setExpectComfirmDate(purchaseApplyVO.getExpectComfirmDate());
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
