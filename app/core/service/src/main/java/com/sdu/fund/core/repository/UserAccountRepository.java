package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.account.bo.UserAccount;

import java.math.BigDecimal;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface UserAccountRepository extends Repository<UserAccount,Long> {

    public UserAccount lock(Long userId);

    public void accountIn(Long userId, BigDecimal amount, BigDecimal freezeAmount);

    public void accountOut(Long userId, BigDecimal amount, BigDecimal freezeAmount);

}
