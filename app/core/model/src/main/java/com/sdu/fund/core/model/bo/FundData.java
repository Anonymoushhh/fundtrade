package com.sdu.fund.core.model.bo;

import java.util.Date;
import java.util.List;

/**
 * @program: fundtrade
 * @description: 基金数据业务对象
 * @author: anonymous
 * @create: 2019-12-16 22:34
 **/
public class FundData {

    /* 基金id*/
    private String fundCode;

    /* 基金名称*/
    private String fundName;

    /* 更新日期*/
    private Date date;

    /* 单位净值*/
    private Double unitNet;

    /* 累计净值*/
    private Double accumulatedNet;

    /* 日增长率*/
    private Double growthRate;

    /* 近1周收益率*/
    private Double earningRate1w;

    /* 近1月收益率*/
    private Double earningRate1m;

    /* 近3月收益率*/
    private Double earningRate3m;

    /* 近6月收益率*/
    private Double earningRate6m;

    /* 近1年收益率*/
    private Double earningRate1y;

    /* 近2年收益率*/
    private Double earningRate2y;

    /* 近3年收益率*/
    private Double earningRate3y;

    /* 今年收益率*/
    private Double earningRateThisYear;

    /* 成立以来收益率*/
    private Double earningRateFromEstablish;

    /* 申购费率*/
    private List<Rate> purchaseRate;

    /* 认购费率*/
    private List<Rate> subscribeRate;

    /* 赎回费率*/
    private List<Rate> redeemRate;

    /* 管理费*/
    private Double managerRate;

    /* 托管费*/
    private Double trusteeRate;

    /* 销服费*/
    private Double serviceRate;

    /* 首次购买最小金额*/
    private Double firstBuyAmount;

    /* 日累计申购限额*/
    private Double purchaseLimitOneDay;

    /* 买入确认日*/
    private String buyComfirmDay;

    /* 卖出确认日*/
    private String redeemComfirmDay;

    /* 追加购买最小金额*/
    private Double supplementAmount;

    /* 持仓限额*/
    private Double positionsLimit;

    /* 部分赎回最低保留份额*/
    private Double minRemainingShare;

    /* 最小申购金额*/
    private Double minPurchaseAmount;

    /* 最小赎回份额*/
    private Double minRedeemShare;

    /* 扩展字段*/
    private String extInfo;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getUnitNet() {
        return unitNet;
    }

    public void setUnitNet(Double unitNet) {
        this.unitNet = unitNet;
    }

    public Double getAccumulatedNet() {
        return accumulatedNet;
    }

    public void setAccumulatedNet(Double accumulatedNet) {
        this.accumulatedNet = accumulatedNet;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }

    public Double getEarningRate1w() {
        return earningRate1w;
    }

    public void setEarningRate1w(Double earningRate1w) {
        this.earningRate1w = earningRate1w;
    }

    public Double getEarningRate1m() {
        return earningRate1m;
    }

    public void setEarningRate1m(Double earningRate1m) {
        this.earningRate1m = earningRate1m;
    }

    public Double getEarningRate3m() {
        return earningRate3m;
    }

    public void setEarningRate3m(Double earningRate3m) {
        this.earningRate3m = earningRate3m;
    }

    public Double getEarningRate6m() {
        return earningRate6m;
    }

    public void setEarningRate6m(Double earningRate6m) {
        this.earningRate6m = earningRate6m;
    }

    public Double getEarningRate1y() {
        return earningRate1y;
    }

    public void setEarningRate1y(Double earningRate1y) {
        this.earningRate1y = earningRate1y;
    }

    public Double getEarningRate2y() {
        return earningRate2y;
    }

    public void setEarningRate2y(Double earningRate2y) {
        this.earningRate2y = earningRate2y;
    }

    public Double getEarningRate3y() {
        return earningRate3y;
    }

    public void setEarningRate3y(Double earningRate3y) {
        this.earningRate3y = earningRate3y;
    }

    public Double getEarningRateThisYear() {
        return earningRateThisYear;
    }

    public void setEarningRateThisYear(Double earningRateThisYear) {
        this.earningRateThisYear = earningRateThisYear;
    }

    public Double getEarningRateFromEstablish() {
        return earningRateFromEstablish;
    }

    public void setEarningRateFromEstablish(Double earningRateFromEstablish) {
        this.earningRateFromEstablish = earningRateFromEstablish;
    }

    public List<Rate> getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(List<Rate> purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public List<Rate> getSubscribeRate() {
        return subscribeRate;
    }

    public void setSubscribeRate(List<Rate> subscribeRate) {
        this.subscribeRate = subscribeRate;
    }

    public List<Rate> getRedeemRate() {
        return redeemRate;
    }

    public void setRedeemRate(List<Rate> redeemRate) {
        this.redeemRate = redeemRate;
    }

    public Double getManagerRate() {
        return managerRate;
    }

    public void setManagerRate(Double managerRate) {
        this.managerRate = managerRate;
    }

    public Double getTrusteeRate() {
        return trusteeRate;
    }

    public void setTrusteeRate(Double trusteeRate) {
        this.trusteeRate = trusteeRate;
    }

    public Double getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(Double serviceRate) {
        this.serviceRate = serviceRate;
    }

    public Double getFirstBuyAmount() {
        return firstBuyAmount;
    }

    public void setFirstBuyAmount(Double firstBuyAmount) {
        this.firstBuyAmount = firstBuyAmount;
    }

    public Double getPurchaseLimitOneDay() {
        return purchaseLimitOneDay;
    }

    public void setPurchaseLimitOneDay(Double purchaseLimitOneDay) {
        this.purchaseLimitOneDay = purchaseLimitOneDay;
    }

    public String getBuyComfirmDay() {
        return buyComfirmDay;
    }

    public void setBuyComfirmDay(String buyComfirmDay) {
        this.buyComfirmDay = buyComfirmDay;
    }

    public String getRedeemComfirmDay() {
        return redeemComfirmDay;
    }

    public void setRedeemComfirmDay(String redeemComfirmDay) {
        this.redeemComfirmDay = redeemComfirmDay;
    }

    public Double getSupplementAmount() {
        return supplementAmount;
    }

    public void setSupplementAmount(Double supplementAmount) {
        this.supplementAmount = supplementAmount;
    }

    public Double getPositionsLimit() {
        return positionsLimit;
    }

    public void setPositionsLimit(Double positionsLimit) {
        this.positionsLimit = positionsLimit;
    }

    public Double getMinRemainingShare() {
        return minRemainingShare;
    }

    public void setMinRemainingShare(Double minRemainingShare) {
        this.minRemainingShare = minRemainingShare;
    }

    public Double getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(Double minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public Double getMinRedeemShare() {
        return minRedeemShare;
    }

    public void setMinRedeemShare(Double minRedeemShare) {
        this.minRedeemShare = minRedeemShare;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

}
