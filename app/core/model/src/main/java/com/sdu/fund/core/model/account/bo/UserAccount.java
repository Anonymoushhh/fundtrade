package com.sdu.fund.core.model.account.bo;

import com.sdu.fund.core.model.trade.enums.UserAccountStatusEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:17
 **/
public class UserAccount {

    private Long userId;

    /**
     * 总金额
     */
    private BigDecimal totolAmount;

    /**
     * 可用余额
     */
    private BigDecimal availAmount;

    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;

    /**
     * 账户类型
     */
    private UserAccountTypeEnum type;

    /**
     * 账户状态
     */
    private UserAccountStatusEnum status;

    /**
     * 校验码
     */
    private String checkValue;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    public static UserAccount createUserAccount(Long userId, BigDecimal totolAmount, BigDecimal availAmount,
                                                BigDecimal freezeAmount,
                                                UserAccountTypeEnum type, UserAccountStatusEnum status) {
        UserAccount userAccount = new UserAccount();
        userAccount.userId = userId;
        userAccount.totolAmount = totolAmount;
        userAccount.availAmount = availAmount;
        userAccount.freezeAmount= freezeAmount;
        userAccount.type = type;
        userAccount.status = status;
        return userAccount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotolAmount() {
        return totolAmount;
    }

    public void setTotolAmount(BigDecimal totolAmount) {
        this.totolAmount = totolAmount;
    }

    public BigDecimal getAvailAmount() {
        return availAmount;
    }

    public void setAvailAmount(BigDecimal availAmount) {
        this.availAmount = availAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public UserAccountTypeEnum getType() {
        return type;
    }

    public void setType(UserAccountTypeEnum type) {
        this.type = type;
    }

    public UserAccountStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserAccountStatusEnum status) {
        this.status = status;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
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
