package com.sdu.fund.core.repository;

import com.sdu.fund.common.result.Result;
import com.sdu.fund.core.model.account.bo.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface UserAccountRepository extends Repository<UserAccount,Long> {

    public Result accountIn(Long userId, BigDecimal amount, BigDecimal freezeAmount);

    public Result accountOut(Long userId, BigDecimal amount);

}
