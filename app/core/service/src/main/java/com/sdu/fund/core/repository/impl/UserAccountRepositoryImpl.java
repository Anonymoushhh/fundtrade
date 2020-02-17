package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.UserAccountMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.UserAccountConverter;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


/**
 * @program: fundproduct
 * @description: 登录token仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class UserAccountRepositoryImpl implements UserAccountRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountRepositoryImpl.class);

    @Autowired
    private UserAccountMapper userAccountMapper;


    @Override
    public Result<UserAccount> get(Long userId) {
        Validator.notNull(userId);
        try {
            UserAccount userAccount =
                    UserAccountConverter.UserAccountDoconvert2UserAccount(userAccountMapper.selectByPrimaryKey(userId));
            return ResultUtil.buildSuccessResult(userAccount);
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户查询失败，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户查询失败，userId={},errCode={}", userId, ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result add(UserAccount userAccount) {
        // 预校验
        boolean check = preCheck(userAccount);
        if (!check) {
            LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id =
                    userAccountMapper.insertSelective(UserAccountConverter.UserAccountconvert2UserAccountDo(userAccount));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(UserAccount userAccount) {
        // 预校验
        boolean check = preCheck(userAccount);
        if (!check) {
            LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                    userAccountMapper.updateByPrimaryKeySelective(UserAccountConverter.UserAccountconvert2UserAccountDo(userAccount));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(Long userId) {
        try {
            int count = userAccountMapper.deleteByPrimaryKey(userId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户账户删除失败，userId={},errCode={}", userId,
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户删除失败，userId={},errCode={}", userId,
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户账户删除失败，userId={},errCode={}", userId,
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
    private boolean preCheck(UserAccount userAccount) {
        return Validator.notNull(userAccount) && Validator.notNull(userAccount.getUserId());
    }

}
