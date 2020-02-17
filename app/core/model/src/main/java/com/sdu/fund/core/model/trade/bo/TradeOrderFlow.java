package com.sdu.fund.core.model.trade.bo;

import com.sdu.fund.core.model.trade.enums.FlowInitiatorEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderFlowTypeEnum;

import java.util.Date;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/17 12:25
 **/
public class TradeOrderFlow {

    private String flowId;

    /**
     *
     */
    private String orderId;

    /**
     *
     */
    private String payOrderId;

    /**
     *
     */
    private Long userId;

    /**
     * 流水类型
     */
    private TradeOrderFlowTypeEnum type;

    /**
     * 发起者
     */
    private FlowInitiatorEnum initiator;

    /**
     * 流水时间
     */
    private Date flowTime;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TradeOrderFlowTypeEnum getType() {
        return type;
    }

    public void setType(TradeOrderFlowTypeEnum type) {
        this.type = type;
    }

    public FlowInitiatorEnum getInitiator() {
        return initiator;
    }

    public void setInitiator(FlowInitiatorEnum initiator) {
        this.initiator = initiator;
    }

    public Date getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(Date flowTime) {
        this.flowTime = flowTime;
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
