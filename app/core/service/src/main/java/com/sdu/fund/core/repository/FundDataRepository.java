package com.sdu.fund.core.repository;

import com.sdu.fund.common.result.Result;
import com.sdu.fund.core.model.bo.FundData;

import java.util.List;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/6 20:59
 **/
public interface FundDataRepository extends Repository<FundData>{

    public Result<List<FundData>> getFundList(Integer fundType, String gainType, Integer curIndex,
                                              Integer pageSize);
}
