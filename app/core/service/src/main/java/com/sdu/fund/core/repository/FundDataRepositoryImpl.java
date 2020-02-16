package com.sdu.fund.core.repository;

import com.google.common.collect.Lists;
import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.entity.FundDataDo;
import com.sdu.fund.common.dal.mapper.FundDataMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.util.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundDataConverter;
import com.sdu.fund.core.model.bo.FundData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @program: fundproduct
 * @description: 基金数值仓储类
 * @author: anonymous
 * @create: 2019-12-09 18:52
 **/
public class FundDataRepositoryImpl implements FundDataRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundDataRepositoryImpl.class);

    @Autowired
    private FundDataMapper fundDataMapper;

    @Override
    public FundData get(String fundCode) {
        return FundDataConverter.FundDataDoconvert2FundData(fundDataMapper.selectByPrimaryKey(fundCode));
    }

    @Override
    public Result<List<FundData>> getFundList(Integer fundType, String gainType, Integer curIndex,
                                              Integer pageSize) {
        List<FundData> fundDatas = Lists.newArrayList();

        try {
            List<FundDataDo> fundDataDos = fundDataMapper.selectFundList(fundType, gainType, curIndex, pageSize);
            for (FundDataDo fundDataDo : fundDataDos) {
                fundDatas.add(FundDataConverter.FundDataDoconvert2FundData(fundDataDo));
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金列表查询失败，fundType={},errCode={},msg={}", fundType, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金列表查询失败，fundType={},errCode={},msg={}", fundType, ResultCode.SERVER_EXCEPTION,
                    e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
        return ResultUtil.buildSuccessResult(fundDatas);

    }

    @Override
    public Result add(FundData fundData) {
        // 预校验
        boolean check = preCheck(fundData);
        if (!check) {
            LOGGER.error("基金数值信息插入失败，fundCode={},errCode={}", fundData.getFundCode(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = fundDataMapper.insert(FundDataConverter.FundDataconvert2FundDataDo(fundData));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金数值信息插入失败，fundode={},errCode={}", fundData.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息插入失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金数值信息插入失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(FundData fundData) {
        // 预校验
        boolean check = preCheck(fundData);
        if (!check) {
            LOGGER.error("基金数值信息更新失败，fundCode={},errCode={}", fundData.getFundCode(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id =
                    fundDataMapper.updateByPrimaryKeySelective(FundDataConverter.FundDataconvert2FundDataDo(fundData));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金数值信息更新失败，fundCode={},errCode={}", fundData.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息更新失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金数值信息更新失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(String fundCode) {
        try {
            int count = fundDataMapper.deleteByPrimaryKey(fundCode);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金数值信息删除失败，fundCode={},errCode={}", fundCode,
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金数值信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    /*
     * @description 预校验
     * @param [fundData]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private boolean preCheck(FundData fundData) {
        return Validator.notNull(fundData) && Validator.notNull(fundData.getFundCode());
    }
}
