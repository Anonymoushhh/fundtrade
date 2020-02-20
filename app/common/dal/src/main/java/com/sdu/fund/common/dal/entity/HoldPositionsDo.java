package com.sdu.fund.common.dal.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HoldPositionsDo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.user_id
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.fund_code
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private String fundCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.fund_name
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private String fundName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.hold_amount
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal holdAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.hold_share
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal holdShare;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.hold_profit
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal holdProfit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.position_cost_price
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal positionCostPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.earning_rate
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private Double earningRate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.wait_confirm_amount
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal waitConfirmAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.profit_last_day
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal profitLastDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.gain_last_day
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private BigDecimal gainLastDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.gmt_create
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hold_positions.gmt_modified
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.user_id
     *
     * @return the value of hold_positions.user_id
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.user_id
     *
     * @param userId the value for hold_positions.user_id
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.fund_code
     *
     * @return the value of hold_positions.fund_code
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.fund_code
     *
     * @param fundCode the value for hold_positions.fund_code
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.fund_name
     *
     * @return the value of hold_positions.fund_name
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.fund_name
     *
     * @param fundName the value for hold_positions.fund_name
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setFundName(String fundName) {
        this.fundName = fundName == null ? null : fundName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.hold_amount
     *
     * @return the value of hold_positions.hold_amount
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getHoldAmount() {
        return holdAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.hold_amount
     *
     * @param holdAmount the value for hold_positions.hold_amount
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setHoldAmount(BigDecimal holdAmount) {
        this.holdAmount = holdAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.hold_share
     *
     * @return the value of hold_positions.hold_share
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getHoldShare() {
        return holdShare;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.hold_share
     *
     * @param holdShare the value for hold_positions.hold_share
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setHoldShare(BigDecimal holdShare) {
        this.holdShare = holdShare;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.hold_profit
     *
     * @return the value of hold_positions.hold_profit
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getHoldProfit() {
        return holdProfit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.hold_profit
     *
     * @param holdProfit the value for hold_positions.hold_profit
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setHoldProfit(BigDecimal holdProfit) {
        this.holdProfit = holdProfit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.position_cost_price
     *
     * @return the value of hold_positions.position_cost_price
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getPositionCostPrice() {
        return positionCostPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.position_cost_price
     *
     * @param positionCostPrice the value for hold_positions.position_cost_price
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setPositionCostPrice(BigDecimal positionCostPrice) {
        this.positionCostPrice = positionCostPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.earning_rate
     *
     * @return the value of hold_positions.earning_rate
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public Double getEarningRate() {
        return earningRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.earning_rate
     *
     * @param earningRate the value for hold_positions.earning_rate
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setEarningRate(Double earningRate) {
        this.earningRate = earningRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.wait_confirm_amount
     *
     * @return the value of hold_positions.wait_confirm_amount
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getWaitConfirmAmount() {
        return waitConfirmAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.wait_confirm_amount
     *
     * @param waitConfirmAmount the value for hold_positions.wait_confirm_amount
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setWaitConfirmAmount(BigDecimal waitConfirmAmount) {
        this.waitConfirmAmount = waitConfirmAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.profit_last_day
     *
     * @return the value of hold_positions.profit_last_day
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getProfitLastDay() {
        return profitLastDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.profit_last_day
     *
     * @param profitLastDay the value for hold_positions.profit_last_day
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setProfitLastDay(BigDecimal profitLastDay) {
        this.profitLastDay = profitLastDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.gain_last_day
     *
     * @return the value of hold_positions.gain_last_day
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public BigDecimal getGainLastDay() {
        return gainLastDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.gain_last_day
     *
     * @param gainLastDay the value for hold_positions.gain_last_day
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setGainLastDay(BigDecimal gainLastDay) {
        this.gainLastDay = gainLastDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.gmt_create
     *
     * @return the value of hold_positions.gmt_create
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.gmt_create
     *
     * @param gmtCreate the value for hold_positions.gmt_create
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hold_positions.gmt_modified
     *
     * @return the value of hold_positions.gmt_modified
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hold_positions.gmt_modified
     *
     * @param gmtModified the value for hold_positions.gmt_modified
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}