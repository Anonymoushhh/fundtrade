package com.sdu.fund.core.model.trade.bo;

import com.sdu.fund.core.model.trade.enums.FundTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/3/3 18:15
 **/
public class HoldPosition {

    private Long id;

    private Long userId;

    /**
     *
     */
    private String fundCode;

    /**
     *
     */
    private String fundName;

    /**
     *
     */
    private FundTypeEnum fundType;

    /**
     * 持有金额
     */
    private BigDecimal holdAmount;

    /**
     * 持有份额
     */
    private BigDecimal holdShare;

    /**
     * 持有盈亏
     */
    private BigDecimal holdProfit;

    /**
     * 持仓成本
     */
    private BigDecimal positionCostPrice;

    /**
     * 收益率
     */
    private Double earningRate;

    /**
     * 待确认金额
     */
    private BigDecimal waitConfirmAmount;

    /**
     * 昨日收益
     */
    private BigDecimal profitLastDay;

    /**
     * 昨日收益率
     */
    private BigDecimal gainLastDay;

    /**
     * 历史收益
     */
    private BigDecimal historyProfit;

    /**
     * 合法性(用户不再持有该基金时置为false)
     */
    private Boolean valid;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public FundTypeEnum getFundType() {
        return fundType;
    }

    public void setFundType(FundTypeEnum fundType) {
        this.fundType = fundType;
    }

    public BigDecimal getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(BigDecimal holdAmount) {
        this.holdAmount = holdAmount;
    }

    public BigDecimal getHoldShare() {
        return holdShare;
    }

    public void setHoldShare(BigDecimal holdShare) {
        this.holdShare = holdShare;
    }

    public BigDecimal getHoldProfit() {
        return holdProfit;
    }

    public void setHoldProfit(BigDecimal holdProfit) {
        this.holdProfit = holdProfit;
    }

    public BigDecimal getPositionCostPrice() {
        return positionCostPrice;
    }

    public void setPositionCostPrice(BigDecimal positionCostPrice) {
        this.positionCostPrice = positionCostPrice;
    }

    public Double getEarningRate() {
        return earningRate;
    }

    public void setEarningRate(Double earningRate) {
        this.earningRate = earningRate;
    }

    public BigDecimal getWaitConfirmAmount() {
        return waitConfirmAmount;
    }

    public void setWaitConfirmAmount(BigDecimal waitConfirmAmount) {
        this.waitConfirmAmount = waitConfirmAmount;
    }

    public BigDecimal getProfitLastDay() {
        return profitLastDay;
    }

    public void setProfitLastDay(BigDecimal profitLastDay) {
        this.profitLastDay = profitLastDay;
    }

    public BigDecimal getGainLastDay() {
        return gainLastDay;
    }

    public void setGainLastDay(BigDecimal gainLastDay) {
        this.gainLastDay = gainLastDay;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public BigDecimal getHistoryProfit() {
        return historyProfit;
    }

    public void setHistoryProfit(BigDecimal historyProfit) {
        this.historyProfit = historyProfit;
    }
}
