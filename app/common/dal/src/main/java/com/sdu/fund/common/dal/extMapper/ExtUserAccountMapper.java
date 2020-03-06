package com.sdu.fund.common.dal.extMapper;


import com.sdu.fund.common.dal.entity.UserAccountDo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface ExtUserAccountMapper {

    /**
     行级锁
     */
    UserAccountDo lockByPrimaryKey(Long userId);

    /**
     入账
     */
    int accountIn(@Param("userId")Long userId, @Param("amount")BigDecimal amount, @Param("freezeAmount")BigDecimal freezeAmount);

    /**
     出账,乐观锁
     */
    int accountOut(@Param("userId")Long userId, @Param("amount")BigDecimal amount, @Param("freezeAmount")BigDecimal freezeAmount);
}