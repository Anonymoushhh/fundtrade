package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.UserAccountFlowMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.UserAccountFlowConverter;
import com.sdu.fund.core.model.account.bo.UserAccountFlow;
import com.sdu.fund.core.repository.UserAccountFlowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


/**
 * @program: fundproduct
 * @description: 用户账户流水流水仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class UserAccountFlowRepositoryImpl implements UserAccountFlowRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountFlowRepositoryImpl.class);

    @Autowired
    private UserAccountFlowMapper userAccountFlowMapper;
    
    @Override
    public Result<UserAccountFlow> get(Long flowId) {
        Validator.notNull(flowId);
        try {
            UserAccountFlow userAccountFlow =
                    UserAccountFlowConverter.UserAccountFlowDoconvert2UserAccountFlow(userAccountFlowMapper.selectByPrimaryKey(flowId));
            return ResultUtil.buildSuccessResult(userAccountFlow);
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水查询失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户流水查询失败，flowId={},errCode={}", flowId, ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result add(UserAccountFlow userAccountFlow) {
        // 预校验
        boolean check = preCheck(userAccountFlow);
        if (!check) {
            LOGGER.error("用户账户流水插入失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id =
                    userAccountFlowMapper.insertSelective(UserAccountFlowConverter.UserAccountFlowconvert2UserAccountFlowDo(userAccountFlow));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户账户流水插入失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水插入失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户流水插入失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(UserAccountFlow userAccountFlow) {
        // 预校验
        boolean check = preCheck(userAccountFlow);
        if (!check) {
            LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                    userAccountFlowMapper.updateByPrimaryKeySelective(UserAccountFlowConverter.UserAccountFlowconvert2UserAccountFlowDo(userAccountFlow));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(Long flowId) {
        try {
            int count = userAccountFlowMapper.deleteByPrimaryKey(flowId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户账户流水删除失败，flowId={},errCode={}", flowId,
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水删除失败，flowId={},errCode={}", flowId,
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户流水删除失败，flowId={},errCode={}", flowId,
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
    private boolean preCheck(UserAccountFlow userAccountFlow) {
        return Validator.notNull(userAccountFlow) && Validator.notNull(userAccountFlow.getFlowId());
    }

    @Override
    public UserAccountFlow lock(Long flowId) {
        return null;
    }
}
