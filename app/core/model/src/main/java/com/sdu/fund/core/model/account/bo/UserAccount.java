package com.sdu.fund.core.model.account.bo;

import com.google.common.collect.Maps;
import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.GenderEnum;
import com.sdu.fund.core.model.account.enums.UserStatusEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountStatusEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountTypeEnum;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

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
    private BigDecimal totolAccount;

    /**
     * 可用余额
     */
    private BigDecimal availAccount;

    /**
     * 冻结金额
     */
    private BigDecimal freezeAccount;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotolAccount() {
        return totolAccount;
    }

    public void setTotolAccount(BigDecimal totolAccount) {
        this.totolAccount = totolAccount;
    }

    public BigDecimal getAvailAccount() {
        return availAccount;
    }

    public void setAvailAccount(BigDecimal availAccount) {
        this.availAccount = availAccount;
    }

    public BigDecimal getFreezeAccount() {
        return freezeAccount;
    }

    public void setFreezeAccount(BigDecimal freezeAccount) {
        this.freezeAccount = freezeAccount;
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
