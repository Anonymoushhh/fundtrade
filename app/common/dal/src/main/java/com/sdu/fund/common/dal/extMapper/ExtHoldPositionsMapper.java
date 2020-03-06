package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.HoldPositionDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtHoldPositionsMapper {

    /**
     * 行级锁
     */
    HoldPositionDo lockByPrimaryKey(Long id);

    /**
     * 如果主键冲突则忽略
     */
    int insertSelectiveIgnore(HoldPositionDo record);

    /**
     * 根据userId查
     */
    List<HoldPositionDo> selectByUserId(Long userId);

    /**
     * 根据userId,fundCode查
     */
    List<HoldPositionDo> selectByUserIdAndFundCode(@Param("userId") Long userId, @Param("fundCode") String fundCode);

    /**
     * 使非法
     */
    int makeInValid(Long id);

    /**
     * 使合法
     */
    int makeValid(Long id);
}