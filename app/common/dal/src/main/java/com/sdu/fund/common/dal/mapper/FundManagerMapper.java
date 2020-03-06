package com.sdu.fund.common.dal.mapper;

import com.sdu.fund.common.dal.entity.FundManagerDo;

public interface FundManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fund_manager
     *
     * @mbg.generated Wed Mar 04 13:17:20 CST 2020
     */
    int deleteByPrimaryKey(String managerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fund_manager
     *
     * @mbg.generated Wed Mar 04 13:17:20 CST 2020
     */
    int insert(FundManagerDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fund_manager
     *
     * @mbg.generated Wed Mar 04 13:17:20 CST 2020
     */
    int insertSelective(FundManagerDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fund_manager
     *
     * @mbg.generated Wed Mar 04 13:17:20 CST 2020
     */
    FundManagerDo selectByPrimaryKey(String managerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fund_manager
     *
     * @mbg.generated Wed Mar 04 13:17:20 CST 2020
     */
    int updateByPrimaryKeySelective(FundManagerDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fund_manager
     *
     * @mbg.generated Wed Mar 04 13:17:20 CST 2020
     */
    int updateByPrimaryKey(FundManagerDo record);
}