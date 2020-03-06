package com.sdu.fund.core.model.trade.bo;

import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.common.utils.TradeOrderIdUtil;
import com.sdu.fund.core.model.trade.constants.TradeOrderExtKey;
import com.sdu.fund.core.model.trade.enums.TradeOrderChannelEnum;
import com.sdu.fund.core.model.trade.enums.PayChannelEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import com.sdu.fund.core.model.trade.event.TradeOrderEvent;
import com.sdu.fund.core.model.trade.factory.TradeOrderStatusMachineFactory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description: 基金订单模型
 * @author: anonymous
 * @create: 2020/2/13 19:13
 **/
public class TradeOrder extends BaseOrder {

    private String orderId;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private String idempotentId;

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
    private BigDecimal orderAmount;

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
    private TradeOrderTypeEnum orderType;

    /**
     * 订单状态
     */
    private TradeOrderStatusEnum orderStatus;

    /**
     * 下单渠道
     */
    private TradeOrderChannelEnum orderChannel;

    /**
     * 支付渠道
     */
    private PayChannelEnum payChannel;

    /**
     * 订单份额
     */
    private BigDecimal orderShare;

    /**
     * 订单时间
     */
    private Date orderTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付单
     */
    private String payOrderId;

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
    private TradeOrderStatusMachineFactory tradeOrderStatusMachineFactory = new TradeOrderStatusMachineFactory();

    /**
     * 推进订单状态
     */
    public void pushStatus(TradeOrderEvent tradeOrderEvent) {
        this.setOrderStatus(
                tradeOrderStatusMachineFactory
                        .getStatusMachine(this.getOrderType()) // 获取状态机
                        .getNextStatus(this.getOrderStatus(), tradeOrderEvent)); // 获取下一个状态
    }

    public static void main(String[] args) {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setOrderType(TradeOrderTypeEnum.PURCHASE);
        tradeOrder.setOrderStatus(TradeOrderStatusEnum.INIT);
        tradeOrder.pushStatus(TradeOrderEvent.PAY_SUCCESS);
        System.out.println(tradeOrder.getOrderStatus());
    }

    /**
     * 创建订单
     */
    public void createTradeOrder() {
        switch (this.getOrderType()) {
            case PURCHASE:
                long now = System.currentTimeMillis();
                this.setOrderId(TradeOrderIdUtil.genTradeOrderId(this.getUserId(), now,
                        this.getOrderChannel().getCode()));
                this.setOrderTime(DateUtil.unixToDate(now));
                this.setOrderStatus(TradeOrderStatusEnum.INIT);
                break;
            case REDEEM:
            case FIXED:
                break;
        }
    }

    public void setPurchaseConfirmDay(Date date) {
        extInfo.put(TradeOrderExtKey.PURCHASECONFIRMDAY, date);
    }

    public Date getPurchaseConfirmDay() {
        if (extInfo.get(TradeOrderExtKey.PURCHASECONFIRMDAY) == null) {
            return null;
        }
        return (Date) extInfo.get(TradeOrderExtKey.PURCHASECONFIRMDAY);
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getIdempotentId() {
        return idempotentId;
    }

    public void setIdempotentId(String idempotentId) {
        this.idempotentId = idempotentId;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
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

    public TradeOrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(TradeOrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public TradeOrderChannelEnum getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(TradeOrderChannelEnum orderChannel) {
        this.orderChannel = orderChannel;
    }

    public PayChannelEnum getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(PayChannelEnum payChannel) {
        this.payChannel = payChannel;
    }

    public TradeOrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(TradeOrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderShare() {
        return orderShare;
    }

    public void setOrderShare(BigDecimal orderShare) {
        this.orderShare = orderShare;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
