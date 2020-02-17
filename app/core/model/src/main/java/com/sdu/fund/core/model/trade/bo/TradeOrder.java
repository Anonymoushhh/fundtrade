package com.sdu.fund.core.model.trade.bo;

import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.common.utils.TradeOrderIdUtil;
import com.sdu.fund.core.model.trade.enums.TradeOrderChannelEnum;
import com.sdu.fund.core.model.trade.enums.PayChannelEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import com.sdu.fund.core.model.trade.event.TradeOrderEvent;
import com.sdu.fund.core.model.trade.factory.TradeOrderStatusMachineFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description: 基金订单模型
 * @author: anonymous
 * @create: 2020/2/13 19:13
 **/
public class TradeOrder {

    private String tradeOrderId;

    /**
     *
     */
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
     * 下单金额
     */
    private BigDecimal tradeOrderAmount;

    /**
     * 费率扣的金额
     */
    private BigDecimal feeAmount;

    /**
     * 费率
     */
    private BigDecimal fee;

    /**
     * 最终支付的金额
     */
    private BigDecimal realPayAmount;

    /**
     * 订单类型
     */
    private TradeOrderTypeEnum tradeOrderType;

    /**
     * 订单状态
     */
    private TradeOrderStatusEnum tradeOrderStatus;

    /**
     * 下单渠道
     */
    private TradeOrderChannelEnum tradeOrderChannel;

    /**
     * 支付渠道
     */
    private PayChannelEnum payChannel;

    /**
     * 订单份额
     */
    private BigDecimal tradeOrderShare;

    /**
     * 订单时间
     */
    private Date tradeOrderTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 份额确认时间
     */
    private Date shareConfirmTime;

    /**
     * 到账时间
     */
    private Date arrivalTime;

    /**
     * 撤单时间
     */
    private Date canelTime;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    /**
     * 订单状态机工厂
     */
    @Autowired
    private TradeOrderStatusMachineFactory tradeOrderStatusMachineFactory;

    /**
     * 推进订单状态
     */
    public void pushStatus(TradeOrder tradeOrder, TradeOrderEvent tradeOrderEvent) {
        tradeOrder.setTradeOrderStatus(
                tradeOrderStatusMachineFactory
                        .getStatusMachine(tradeOrder.getTradeOrderType()) // 获取状态机
                        .getNextStatus(tradeOrder.getTradeOrderStatus(), tradeOrderEvent)); // 获取下一个状态
    }

    /**
     * 创建订单
     */
    public void createTradeOrder() {
        switch (this.getTradeOrderType()){
            case PURCHASE:
                long now = System.currentTimeMillis();
                this.setTradeOrderId(TradeOrderIdUtil.genTradeOrderId(this.getUserId(),now,this.getTradeOrderChannel().getCode()));
                this.setTradeOrderTime(DateUtil.unixToDate(now));
                this.setTradeOrderStatus(TradeOrderStatusEnum.INIT);
                break;
            case REDEEM:
            case FIXED:
                break;
        }
    }

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
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

    public BigDecimal getTradeOrderAmount() {
        return tradeOrderAmount;
    }

    public void setTradeOrderAmount(BigDecimal tradeOrderAmount) {
        this.tradeOrderAmount = tradeOrderAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
    }

    public TradeOrderTypeEnum getTradeOrderType() {
        return tradeOrderType;
    }

    public void setTradeOrderType(TradeOrderTypeEnum tradeOrderType) {
        this.tradeOrderType = tradeOrderType;
    }

    public TradeOrderChannelEnum getTradeOrderChannel() {
        return tradeOrderChannel;
    }

    public void setTradeOrderChannel(TradeOrderChannelEnum tradeOrderChannel) {
        this.tradeOrderChannel = tradeOrderChannel;
    }

    public PayChannelEnum getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(PayChannelEnum payChannel) {
        this.payChannel = payChannel;
    }

    public TradeOrderStatusEnum getTradeOrderStatus() {
        return tradeOrderStatus;
    }

    public void setTradeOrderStatus(TradeOrderStatusEnum tradeOrderStatus) {
        this.tradeOrderStatus = tradeOrderStatus;
    }

    public BigDecimal getTradeOrderShare() {
        return tradeOrderShare;
    }

    public void setTradeOrderShare(BigDecimal tradeOrderShare) {
        this.tradeOrderShare = tradeOrderShare;
    }

    public Date getTradeOrderTime() {
        return tradeOrderTime;
    }

    public void setTradeOrderTime(Date tradeOrderTime) {
        this.tradeOrderTime = tradeOrderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getShareConfirmTime() {
        return shareConfirmTime;
    }

    public void setShareConfirmTime(Date shareConfirmTime) {
        this.shareConfirmTime = shareConfirmTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getCanelTime() {
        return canelTime;
    }

    public void setCanelTime(Date canelTime) {
        this.canelTime = canelTime;
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
