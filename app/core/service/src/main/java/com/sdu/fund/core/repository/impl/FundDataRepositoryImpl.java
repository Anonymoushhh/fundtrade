package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundDataMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundDataConverter;
import com.sdu.fund.core.model.trade.bo.FundData;
import com.sdu.fund.core.repository.FundDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

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
        Validator.notNull(fundCode);
        try {
            FundData fundData =
                    FundDataConverter.FundDataDoconvert2FundData(fundDataMapper.selectByPrimaryKey(fundCode));
            return fundData;
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息查询失败，fundCode={},errCode={}", fundCode, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("基金数值信息查询失败");
        } catch (Exception e2) {
            LOGGER.error("基金数值信息查询失败，fundCode={},errCode={}", fundCode, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("基金数值信息查询失败");
        }
    }


    @Override
    public void add(FundData fundData) {
        try {
            preCheck(fundData);
            int id = fundDataMapper.insertSelective(FundDataConverter.FundDataconvert2FundDataDo(fundData));
            if (id <= 0) {
                LOGGER.error("基金数值信息插入失败，fundode={},errCode={}", fundData.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金数值信息插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息插入失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金数值信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("基金数值信息插入失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金数值信息插入失败");
        }
    }

    @Override
    public void update(FundData fundData) {
        try {
            preCheck(fundData);
            int id =
                    fundDataMapper.updateByPrimaryKeySelective(FundDataConverter.FundDataconvert2FundDataDo(fundData));
            if (id <= 0) {
                LOGGER.error("基金数值信息更新失败，fundCode={},errCode={}", fundData.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金数值信息更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息更新失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金数值信息更新失败");
        } catch (Exception e2) {
            LOGGER.error("基金数值信息更新失败，fundCode={},errCode={},msg={}", fundData.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金数值信息更新失败");
        }
    }

    @Override
    public void delete(String fundCode) {
        try {
            int count = fundDataMapper.deleteByPrimaryKey(fundCode);
            if (count <= 0) {
                LOGGER.error("基金数值信息删除失败，fundCode={},errCode={}", fundCode,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金数值信息删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金数值信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金数值信息删除失败");
        } catch (Exception e2) {
            LOGGER.error("基金数值信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金数值信息删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundData]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(FundData fundData) {
        Validator.notNull(fundData);
        Validator.notNull(fundData.getFundCode());
    }
}
