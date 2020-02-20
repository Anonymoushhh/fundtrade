package com.sdu.fund.common.dal.extMapper;


import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface ExtUserAccountMapper {

    /**
     入账
     */
    int accountIn(@Param("userId")Long userId, @Param("amount")BigDecimal amount, @Param("freezeAmount")BigDecimal freezeAmount);

    /**
     出账,乐观锁
     */
    int accountOut(@Param("userId")Long userId, @Param("amount")BigDecimal amount);
}