package com.sdu.fund.core.model.trade.bo;

import com.sdu.fund.core.model.trade.enums.PayChannelEnum;
import com.sdu.fund.core.model.trade.enums.PayStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description: 支付单
 * @author: anonymous
 * @create: 2020/2/17 12:26
 **/
public class Payment extends BaseOrder{

    private String payOrderId;

    /**
     *
     */
    private String orderId;

    /**
     *
     */
    private Long userId;

    /**
     * 支付金额
     */
    private BigDecimal orderAmount;

    /**
     * 支付渠道
     */
    private PayChannelEnum payChannel;

    /**
     * 支付状态
     */
    private PayStatusEnum payStatus;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public PayChannelEnum getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(PayChannelEnum payChannel) {
        this.payChannel = payChannel;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
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
}
