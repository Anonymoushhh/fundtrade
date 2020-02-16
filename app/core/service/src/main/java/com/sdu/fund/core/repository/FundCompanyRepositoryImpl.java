package com.sdu.fund.core.repository;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundCompanyMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.util.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundCompanyConverter;
import com.sdu.fund.core.model.bo.FundCompany;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


/**
 * @program: fundproduct
 * @description: 基金公司仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class FundCompanyRepositoryImpl implements FundCompanyRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundCompanyRepositoryImpl.class);

    @Autowired
    private FundCompanyMapper fundCompanyMapper;

    @Override
    public FundCompany get(String fundCompanyCode) {
        return FundCompanyConverter.FundCompanyDoconvert2FundCompany(fundCompanyMapper.selectByPrimaryKey(fundCompanyCode));
    }

    @Override
    public Result add(FundCompany fundCompany) {
        // 预校验
        boolean check = preCheck(fundCompany);
        if (!check) {
            LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = fundCompanyMapper.insert(FundCompanyConverter.FundCompanyconvert2FundCompanyDo(fundCompany));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(FundCompany fundCompany) {
        // 预校验
        boolean check = preCheck(fundCompany);
        if (!check) {
            LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                fundCompanyMapper.updateByPrimaryKeySelective(FundCompanyConverter.FundCompanyconvert2FundCompanyDo(fundCompany));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(String fundCompanyCode) {
        try {
            int count = fundCompanyMapper.deleteByPrimaryKey(fundCompanyCode);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金公司信息删除失败，fundCompanyCode={},errCode={}", fundCompanyCode,
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息删除失败，fundCompanyCode={},errCode={}", fundCompanyCode,
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金公司信息删除失败，fundCompanyCode={},errCode={}", fundCompanyCode,
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private boolean preCheck(FundCompany fundCompany) {
        return Validator.notNull(fundCompany) && Validator.notNull(fundCompany.getFundCompanyCode());
    }
}
