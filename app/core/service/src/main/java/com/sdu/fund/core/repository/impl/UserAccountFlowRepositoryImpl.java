package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtUserAccountFlowMapper;
import com.sdu.fund.common.dal.mapper.UserAccountFlowMapper;
import com.sdu.fund.common.exception.CommonException;
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

    @Autowired
    private ExtUserAccountFlowMapper extUserAccountFlowMapper;

    @Override
    public UserAccountFlow get(Long flowId) {
        Validator.notNull(flowId);
        try {
            UserAccountFlow userAccountFlow =
                    UserAccountFlowConverter.UserAccountFlowDoconvert2UserAccountFlow(userAccountFlowMapper.selectByPrimaryKey(flowId));
            return userAccountFlow;
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水查询失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户流水查询失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户流水查询失败，flowId={},errCode={}", flowId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户流水查询失败");
        }
    }

    @Override
    public void add(UserAccountFlow userAccountFlow) {
        try {
            preCheck(userAccountFlow);
            int id =
                    userAccountFlowMapper.insertSelective(UserAccountFlowConverter.UserAccountFlowconvert2UserAccountFlowDo(userAccountFlow));
            if (id <= 0) {
                LOGGER.error("用户账户流水插入失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户流水插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水插入失败，flowId={},errCode={},msg={}", userAccountFlow.getUserId(),
                    ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("用户账户流水插入失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户流水插入失败，flowId={},errCode={},msg={}", userAccountFlow.getUserId(),
                    ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("用户账户流水插入失败");
        }
    }

    @Override
    public void update(UserAccountFlow userAccountFlow) {
        try {
            preCheck(userAccountFlow);
            int count =
                    userAccountFlowMapper.updateByPrimaryKeySelective(UserAccountFlowConverter.UserAccountFlowconvert2UserAccountFlowDo(userAccountFlow));
            if (count <= 0) {
                LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户流水更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户流水更新失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户流水更新失败，flowId={},errCode={}", userAccountFlow.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户流水更新失败");
        }
    }

    @Override
    public void delete(Long flowId) {
        try {
            int count = userAccountFlowMapper.deleteByPrimaryKey(flowId);
            if (count <= 0) {
                LOGGER.error("用户账户流水删除失败，flowId={},errCode={}", flowId,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户流水删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水删除失败，flowId={},errCode={}", flowId,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户流水删除失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户流水删除失败，flowId={},errCode={}", flowId,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户流水删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(UserAccountFlow userAccountFlow) {
        Validator.notNull(userAccountFlow);
        Validator.notNull(userAccountFlow.getFlowId());
    }

    @Override
    public UserAccountFlow lock(Long flowId) {
        Validator.notNull(flowId);
        try {
            UserAccountFlow userAccountFlow =
                    UserAccountFlowConverter.UserAccountFlowDoconvert2UserAccountFlow(extUserAccountFlowMapper.lockByPrimaryKey(flowId));
            return userAccountFlow;
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户流水锁定失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户流水锁定失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户流水锁定失败，flowId={},errCode={}", flowId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户流水锁定失败");
        }
    }

    @Override
    public void makeInValid(Long flowId) {
        try {
            int count =
                    extUserAccountFlowMapper.makeInValid(flowId);
            if (count <= 0) {
                LOGGER.error("使账户流水非法失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("使账户流水非法失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("使账户流水非法失败，flowId={},errCode={},msg={}", flowId, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("使账户流水非法失败");
        } catch (Exception e2) {
            LOGGER.error("使账户流水非法失败，flowId={},errCode={},msg={}", flowId, ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("使账户流水非法失败");
        }
    }
}
