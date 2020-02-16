package com.sdu.fund.core.repository;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.FundManagerMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.util.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.FundManagerConverter;
import com.sdu.fund.core.model.bo.FundManager;
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
public class FundManagerRepositoryImpl implements FundManagerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundManagerRepositoryImpl.class);

    @Autowired
    private FundManagerMapper fundManagerMapper;

    public FundManager get(String managerId) {
        return FundManagerConverter.FundManagerDoconvert2FundManager(fundManagerMapper.selectByPrimaryKey(managerId));
    }

    public Result add(FundManager fundManager) {
        // 预校验
        boolean check = preCheck(fundManager);
        if (!check) {
            LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = fundManagerMapper.insert(FundManagerConverter.FundManagerconvert2FundManagerDo(fundManager));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金经理信息插入失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    public Result update(FundManager fundManager) {
        // 预校验
        boolean check = preCheck(fundManager);
        if (!check) {
            LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                fundManagerMapper.updateByPrimaryKeySelective(FundManagerConverter.FundManagerconvert2FundManagerDo(fundManager));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金经理信息更新失败，managerId={},errCode={}", fundManager.getManagerId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    public Result delete(String managerId) {
        try {
            int count = fundManagerMapper.deleteByPrimaryKey(managerId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("基金经理信息删除失败，managerId={},errCode={}", managerId,
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("基金经理信息删除失败，managerId={},errCode={}", managerId,
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("基金经理信息删除失败，managerId={},errCode={}", managerId,
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
    private boolean preCheck(FundManager fundManager) {
        return Validator.notNull(fundManager) && Validator.notNull(fundManager.getManagerId());
    }
}
