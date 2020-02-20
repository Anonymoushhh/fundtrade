package com.sdu.fund.core.model.trade.bo;

import com.sdu.fund.common.utils.SnowflakeIdUtil;
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

    private Long flowId;

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
     * 流水合法性，默认合法
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

    public static TradeOrderFlow createFlow(TradeOrder tradeOrder, TradeOrderFlowTypeEnum tradeOrderFlowType) {
        TradeOrderFlow tradeOrderFlow = new TradeOrderFlow();
        tradeOrderFlow.setFlowId(SnowflakeIdUtil.getInstance().nextId());
        tradeOrderFlow.setOrderId(tradeOrder.getTradeOrderId());
        tradeOrderFlow.setUserId(tradeOrder.getUserId());
        tradeOrderFlow.setType(tradeOrderFlowType);
        switch (tradeOrderFlowType) {
            case USER_ORDER:
            case USER_PAY:
            case USER_NO_PAY:
            case USER_CANCEL_OR_TIMEOUT:
            case USER_CACEL:
            case USER_DELAY_PAY:
                tradeOrderFlow.setInitiator(FlowInitiatorEnum.USER);
            case SYSTEM_RECEIVE_ORDER:
            case SYSTEM_CONFIRM:
            case SYSTEM_FINFISH:
            default:
                tradeOrderFlow.setInitiator(FlowInitiatorEnum.SYSTEM);
        }
        tradeOrderFlow.setFlowTime(new Date());
        return tradeOrderFlow;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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
