package com.sdu.fund.core.model.bo;

import com.sdu.fund.core.model.enums.OrderStatusEnum;
import com.sdu.fund.core.model.enums.OrderTypeEnum;
import com.sdu.fund.core.model.event.OrderEvent;
import com.sdu.fund.core.model.factory.OrderStatusMachineFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description: 基金订单模型
 * @author: anonymous
 * @create: 2020/2/13 19:13
 **/
public class Order {

    private String orderId;

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
    private BigDecimal orderAmount;

    /**
     * 费率扣的金额
     */
    private BigDecimal fee;

    /**
     * 扣过手续费的金额
     */
    private BigDecimal realAmount;

    /**
     * 订单类型
     */
    private OrderTypeEnum orderType;

    /**
     * 订单状态
     */
    private OrderStatusEnum orderStatus;

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
    private OrderStatusMachineFactory orderStatusMachineFactory;

    /**
     * 推进订单状态
     */
    public void pushStatus(Order order, OrderEvent orderEvent) {
        order.setOrderStatus(
                orderStatusMachineFactory
                        .getStatusMachine(order.getOrderType()) // 获取状态机
                        .getNextStatus(order.getOrderStatus(), orderEvent)); // 获取下一个状态
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

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
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
