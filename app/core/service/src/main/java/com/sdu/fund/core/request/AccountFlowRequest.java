package com.sdu.fund.core.request;

import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.enums.FlowInitiatorEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountChangeDirectionEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountFlowTypeEnum;

import java.math.BigDecimal;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/20 12:30
 **/
public class AccountFlowRequest {

    private Payment payment;
    private Long relatedFlowId;
    private UserAccountChangeDirectionEnum changeDirection;
    private UserAccountFlowTypeEnum type;
    private FlowInitiatorEnum initiator;
    private BigDecimal freezeAmount;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getRelatedFlowId() {
        return relatedFlowId;
    }

    public void setRelatedFlowId(Long relatedFlowId) {
        this.relatedFlowId = relatedFlowId;
    }

    public UserAccountChangeDirectionEnum getChangeDirection() {
        return changeDirection;
    }

    public void setChangeDirection(UserAccountChangeDirectionEnum changeDirection) {
        this.changeDirection = changeDirection;
    }

    public UserAccountFlowTypeEnum getType() {
        return type;
    }

    public void setType(UserAccountFlowTypeEnum type) {
        this.type = type;
    }

    public FlowInitiatorEnum getInitiator() {
        return initiator;
    }

    public void setInitiator(FlowInitiatorEnum initiator) {
        this.initiator = initiator;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }
}
