package com.sdu.fund.core.repository;

import com.google.common.collect.Lists;
import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundArchiveMapper;
import com.sdu.fund.common.dal.mapper.FundManagerMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.util.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundArchiveConverter;
import com.sdu.fund.core.converter.FundManagerConverter;
import com.sdu.fund.core.model.bo.FundArchive;
import com.sdu.fund.core.model.bo.FundManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

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
        FundArchive fundArchive;
        try {
            fundArchive =
                    FundArchiveConverter.FundArchiveDoconvert2FundArchive(fundArchiveMapper.selectByPrimaryKey(fundCode));
            if (fundArchive != null) {
                List<String> managerNames = Lists.newArrayList();
                for (String managerId : fundArchive.getManagerIds()) {
                    FundManager fundManager =
                            FundManagerConverter.FundManagerDoconvert2FundManager(fundManagerMapper.selectByPrimaryKey(managerId));
                    if(fundManager!=null){
                        managerNames.add(fundManager.getManagerName());
                    }
                }
                fundArchive.setManagerNames(managerNames);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息查询失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return null;
        } catch (Exception e2) {
            LOGGER.error("基金档案信息查询失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return null;
        }
        return fundArchive;
    }

    @Override
    public Result add(FundArchive fundArchive) {
        // 预校验
        boolean check = preCheck(fundArchive);
        if (!check) {
            LOGGER.error("基金档案信息插入失败，fundCode={},errCode={}", fundArchive.getFundCode(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = fundArchiveMapper.insert(FundArchiveConverter.FundArchiveconvert2FundArchiveDo(fundArchive));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金档案信息插入失败，fundode={},errCode={}", fundArchive.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息插入失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金档案信息插入失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(FundArchive fundArchive) {
        // 预校验
        boolean check = preCheck(fundArchive);
        if (!check) {
            LOGGER.error("基金档案信息更新失败，fundCode={},errCode={}", fundArchive.getFundCode(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id =
                    fundArchiveMapper.updateByPrimaryKeySelective(FundArchiveConverter.FundArchiveconvert2FundArchiveDo(fundArchive));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金档案信息更新失败，fundCode={},errCode={}", fundArchive.getFundCode(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息更新失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金档案信息更新失败，fundCode={},errCode={},msg={}", fundArchive.getFundCode(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(String fundCode) {
        try {
            int count = fundArchiveMapper.deleteByPrimaryKey(fundCode);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金档案信息删除失败，fundCode={},errCode={}", fundCode,
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金档案信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金档案信息删除失败，fundCode={},errCode={},msg={}", fundCode,
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    /*
     * @description 预校验
     * @param [fundArchive]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private boolean preCheck(FundArchive fundArchive) {
        return Validator.notNull(fundArchive) && Validator.notNull(fundArchive.getFundCode());
    }
}
