package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundCompanyMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundCompanyConverter;
import com.sdu.fund.core.model.trade.bo.FundCompany;
import com.sdu.fund.core.repository.FundCompanyRepository;
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
        Validator.notNull(fundCompanyCode);
        try {
            FundCompany fundCompany =
                    FundCompanyConverter.FundCompanyDoconvert2FundCompany(fundCompanyMapper.selectByPrimaryKey(fundCompanyCode));
            return fundCompany;
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息查询失败，fundCompanyCode={},errCode={}", fundCompanyCode, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金公司信息查询失败");
        } catch (Exception e2) {
            LOGGER.error("基金公司信息查询失败，fundCompanyCode={},errCode={}", fundCompanyCode, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金公司信息查询失败");
        }
    }

    @Override
    public void add(FundCompany fundCompany) {
        try {
            preCheck(fundCompany);
            int id =
                    fundCompanyMapper.insertSelective(FundCompanyConverter.FundCompanyconvert2FundCompanyDo(fundCompany));
            if (id <= 0) {
                LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金公司信息插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金公司信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("基金公司信息插入失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金公司信息插入失败");
        }
    }

    @Override
    public void update(FundCompany fundCompany) {
        try {
            preCheck(fundCompany);
            int count =
                    fundCompanyMapper.updateByPrimaryKeySelective(FundCompanyConverter.FundCompanyconvert2FundCompanyDo(fundCompany));
            if (count <= 0) {
                LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金公司信息更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金公司信息更新失败");
        } catch (Exception e2) {
            LOGGER.error("基金公司信息更新失败，fundCompanyCode={},errCode={}", fundCompany.getFundCompanyCode(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金公司信息更新失败");
        }
    }

    @Override
    public void delete(String fundCompanyCode) {
        try {
            int count = fundCompanyMapper.deleteByPrimaryKey(fundCompanyCode);
            if (count <= 0) {
                LOGGER.error("基金公司信息删除失败，fundCompanyCode={},errCode={}", fundCompanyCode,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金公司信息删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金公司信息删除失败，fundCompanyCode={},errCode={}", fundCompanyCode,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金公司信息删除失败");
        } catch (Exception e2) {
            LOGGER.error("基金公司信息删除失败，fundCompanyCode={},errCode={}", fundCompanyCode,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金公司信息删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(FundCompany fundCompany) {
        Validator.notNull(fundCompany);
        Validator.notNull(fundCompany.getFundCompanyCode());
    }
}
