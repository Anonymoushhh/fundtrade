package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.trade.bo.HoldPosition;

import java.util.List;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface HoldPositionRepository extends Repository<HoldPosition,Long> {

    public HoldPosition lock(Long id);

    /**
     * 根据userId查
     */
    List<HoldPosition> getByUserId(Long userId);

    /**
     * 根据userId,fundCode查
     */
    List<HoldPosition> getByUserIdAndFundCode(Long userId, String fundCode);

    /**
     * 如果主键冲突则忽略
     */
    void addIgnore(HoldPosition holdPosition);

    /**
     * 使合法
     */
    void makeValid(Long id);

    /**
     * 使非法
     */
    public void makeInValid(Long id);
}
