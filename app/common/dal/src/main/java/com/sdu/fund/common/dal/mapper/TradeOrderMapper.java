package com.sdu.fund.common.dal.mapper;

import com.sdu.fund.common.dal.entity.TradeOrderDo;

public interface TradeOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_order
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    int deleteByPrimaryKey(String orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_order
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    int insert(TradeOrderDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_order
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    int insertSelective(TradeOrderDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_order
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    TradeOrderDo selectByPrimaryKey(String orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_order
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    int updateByPrimaryKeySelective(TradeOrderDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trade_order
     *
     * @mbg.generated Thu Feb 20 20:41:19 CST 2020
     */
    int updateByPrimaryKey(TradeOrderDo record);
}