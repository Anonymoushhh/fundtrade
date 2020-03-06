package com.sdu.fund.core.model.account.bo;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.utils.SnowflakeIdUtil;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.enums.FlowInitiatorEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderFlowTypeEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountChangeDirectionEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountFlowTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/17 12:24
 **/
public class UserAccountFlow {

    private Long flowId;

    /**
     *
     */
    private Long userId;

    /**
     * 关联订单号(非必选)
     */
    private String relatedOrderId;

    /**
     * 关联的流水id(非必选)
     */
    private String relatedFlowId;

    /**
     * 账务变动方向
     */
    private UserAccountChangeDirectionEnum changeDirection;

    /**
     * 流水类型
     */
    private UserAccountFlowTypeEnum type;

    /**
     * 发起者
     */
    private FlowInitiatorEnum initiator;

    /**
     * 变动前总金额
     */
    private BigDecimal preTotolAmount;

    /**
     * 变动前可用金额
     */
    private BigDecimal preAvailAmount;

    /**
     * 变动前冻结金额
     */
    private BigDecimal preFreezeAmount;

    /**
     * 变动后总金额
     */
    private BigDecimal postTotolAmount;

    /**
     * 变动后可用金额
     */
    private BigDecimal postAvailAmount;

    /**
     * 变动后冻结金额
     */
    private BigDecimal postFreezeAmount;

    /**
     * 是否合法
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

    /**
     * 创建流水
     */
    public static UserAccountFlow createFlow(Payment payment, Long relatedFlowId,
                                             UserAccountChangeDirectionEnum changeDirection,
                                             UserAccountFlowTypeEnum type, FlowInitiatorEnum initiator,
                                             BigDecimal preTotolAmount, BigDecimal preAvailAmount,
                                             BigDecimal preFreezeAmount, BigDecimal freezeAmount) {
        UserAccountFlow userAccountFlow = new UserAccountFlow();
        userAccountFlow.setFlowId(SnowflakeIdUtil.getInstance().nextId());
        userAccountFlow.setUserId(payment.getUserId());
        userAccountFlow.setRelatedOrderId(payment.getOrderId());
        userAccountFlow.setRelatedFlowId(String.valueOf(relatedFlowId));
        userAccountFlow.setChangeDirection(changeDirection);
        userAccountFlow.setType(type);
        userAccountFlow.setInitiator(initiator);
        userAccountFlow.setPreTotolAmount(preTotolAmount);
        userAccountFlow.setPreAvailAmount(preAvailAmount);
        userAccountFlow.setPreFreezeAmount(preFreezeAmount);
        /**
         * 入账：总金额 += 订单总额
         *      可用金额 += 订单总额-订单冻结金额
         *      冻结金额 += 订单冻结金额
         */
        if (changeDirection == UserAccountChangeDirectionEnum.IN) {
            userAccountFlow.setPostTotolAmount(preTotolAmount.add(payment.getOrderAmount()));
            userAccountFlow.setPostAvailAmount(preAvailAmount.add(payment.getOrderAmount().subtract(freezeAmount)));
            userAccountFlow.setPostFreezeAmount(preFreezeAmount.add(freezeAmount));
        } else if (changeDirection == UserAccountChangeDirectionEnum.OUT) {
            /**
             * 出账：总金额 -= 总金额 - 订单总额 + 冻结金额
             *      可用金额 -= 订单总额
             *      冻结金额 += 订单冻结金额
             */
            userAccountFlow.setPostTotolAmount(preTotolAmount.subtract(payment.getOrderAmount()).add(freezeAmount));
            userAccountFlow.setPostAvailAmount(preAvailAmount.subtract(payment.getOrderAmount()));
            userAccountFlow.setPostFreezeAmount(preFreezeAmount.add(freezeAmount));
        } else {
            throw new CommonException("不支持的账务方向");
        }
        return userAccountFlow;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getRelatedOrderId() {
        return relatedOrderId;
    }

    public void setRelatedOrderId(String relatedOrderId) {
        this.relatedOrderId = relatedOrderId;
    }

    public String getRelatedFlowId() {
        return relatedFlowId;
    }

    public void setRelatedFlowId(String relatedFlowId) {
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

    public BigDecimal getPreTotolAmount() {
        return preTotolAmount;
    }

    public void setPreTotolAmount(BigDecimal preTotolAmount) {
        this.preTotolAmount = preTotolAmount;
    }

    public BigDecimal getPreAvailAmount() {
        return preAvailAmount;
    }

    public void setPreAvailAmount(BigDecimal preAvailAmount) {
        this.preAvailAmount = preAvailAmount;
    }

    public BigDecimal getPreFreezeAmount() {
        return preFreezeAmount;
    }

    public void setPreFreezeAmount(BigDecimal preFreezeAmount) {
        this.preFreezeAmount = preFreezeAmount;
    }

    public BigDecimal getPostTotolAmount() {
        return postTotolAmount;
    }

    public void setPostTotolAmount(BigDecimal postTotolAmount) {
        this.postTotolAmount = postTotolAmount;
    }

    public BigDecimal getPostAvailAmount() {
        return postAvailAmount;
    }

    public void setPostAvailAmount(BigDecimal postAvailAmount) {
        this.postAvailAmount = postAvailAmount;
    }

    public BigDecimal getPostFreezeAmount() {
        return postFreezeAmount;
    }

    public void setPostFreezeAmount(BigDecimal postFreezeAmount) {
        this.postFreezeAmount = postFreezeAmount;
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
