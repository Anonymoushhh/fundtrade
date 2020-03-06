package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundArchiveMapper;
import com.sdu.fund.common.dal.mapper.FundManagerMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundArchiveConverter;
import com.sdu.fund.core.model.trade.bo.FundArchive;
import com.sdu.fund.core.repository.FundArchiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 * @program: fundproduct
 * @description: 基金档案仓储类
 * @author: anonymous
 * @create: 2019-12-09 18:52
 **/
public class FundArchiveRepositoryImpl implements FundArchiveRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundArchiveRepositoryImpl.class);

    @Autowired
    private FundArchiveMapper fundArchiveMapper;

    @Autowired
    private FundManagerMapper fundManagerMapper;

    @Override
    public FundArchive get(String fundCode) {
        Validator.notNull(fundCode);
        try {
            FundArchive fundArchive =
                    FundArchiveConverter.FundArchiveDoconvert2FundArchive(fundArchiveMapper.selectByPrimaryKey(fundCode));
            return fundArchive;
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息查询失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金档案信息查询失败");
        } catch (Exception e2) {
            LOGGER.error("基金档案信息查询失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金档案信息查询失败");
        }
    }

    @Override
    public void add(FundArchive fundArchive) {
        try {
            // 预校验
            preCheck(fundArchive);
            int id =
                    fundArchiveMapper.insertSelective(FundArchiveConverter.FundArchiveconvert2FundArchiveDo(fundArchive));
            if (id <= 0) {
                LOGGER.error("基金档案信息插入失败，fundCode={},errCode={}", fundArchive.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金档案信息插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息插入失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金档案信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("基金档案信息插入失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金档案信息插入失败");
        }
    }

    @Override
    public void update(FundArchive fundArchive) {
        try {
            // 预校验
            preCheck(fundArchive);
            int id =
                    fundArchiveMapper.updateByPrimaryKeySelective(FundArchiveConverter.FundArchiveconvert2FundArchiveDo(fundArchive));
            if (id <= 0) {
                LOGGER.error("基金档案信息更新失败，fundCode={},errCode={}", fundArchive.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金档案信息更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息更新失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金档案信息更新失败");
        } catch (Exception e2) {
            LOGGER.error("基金档案信息更新失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金档案信息更新失败");
        }
    }

    @Override
    public void delete(String fundCode) {
        try {
            int count = fundArchiveMapper.deleteByPrimaryKey(fundCode);
            if (count <= 0) {
                LOGGER.error("基金档案信息删除失败，fundCode={},errCode={}", fundCode,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("基金档案信息删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("基金档案信息删除失败");
        } catch (Exception e2) {
            LOGGER.error("基金档案信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("基金档案信息删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundArchive]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(FundArchive fundArchive) {
        Validator.notNull(fundArchive);
        Validator.notNull(fundArchive.getFundCode());
    }
}
