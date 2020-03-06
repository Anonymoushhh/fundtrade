package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtUserAccountMapper;
import com.sdu.fund.common.dal.mapper.UserAccountMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.TradeOrderConverter;
import com.sdu.fund.core.converter.UserAccountConverter;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;


/**
 * @program: fundproduct
 * @description: 用户账户仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class UserAccountRepositoryImpl implements UserAccountRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountRepositoryImpl.class);

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private ExtUserAccountMapper extUserAccountMapper;


    @Override
    public UserAccount get(Long userId) {
        Validator.notNull(userId);
        try {
            UserAccount userAccount =
                    UserAccountConverter.UserAccountDoconvert2UserAccount(userAccountMapper.selectByPrimaryKey(userId));
            return userAccount;
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户查询失败，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户插入失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户查询失败，userId={},errCode={}", userId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户插入失败");
        }
    }

    @Override
    public void add(UserAccount userAccount) {
        try {
            preCheck(userAccount);
            int id =
                    userAccountMapper.insertSelective(UserAccountConverter.UserAccountconvert2UserAccountDo(userAccount));
            if (id <= 0) {
                LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户插入失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户插入失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户插入失败");
        }
    }

    @Override
    public void update(UserAccount userAccount) {
        try {
            preCheck(userAccount);
            int count =
                    userAccountMapper.updateByPrimaryKeySelective(UserAccountConverter.UserAccountconvert2UserAccountDo(userAccount));
            if (count <= 0) {
                LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户更新失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户更新失败，userId={},errCode={}", userAccount.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户更新失败");
        }
    }

    @Override
    public void delete(Long userId) {
        try {
            int count = userAccountMapper.deleteByPrimaryKey(userId);
            if (count <= 0) {
                LOGGER.error("用户账户删除失败，userId={},errCode={}", userId,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户删除失败，userId={},errCode={}", userId,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户删除失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户删除失败，userId={},errCode={}", userId,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(UserAccount userAccount) {
        Validator.notNull(userAccount);
        Validator.notNull(userAccount.getUserId());
    }

    @Override
    public UserAccount lock(Long userId) {
        Validator.notNull(userId);
        try {
            return UserAccountConverter.UserAccountDoconvert2UserAccount(extUserAccountMapper.lockByPrimaryKey(userId));
        } catch (DataAccessException e1) {
            LOGGER.error("账户锁定失败，userId={},errCode={},msg={}", userId, ResultCode.DATABASE_EXCEPTION, e1.getMessage());
            throw new CommonException("账户锁定失败");
        } catch (Exception e2) {
            LOGGER.error("账户锁定失败，userId={},errCode={},msg={}", userId, ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("账户锁定失败");
        }
    }

    @Override
    public void accountIn(Long userId, BigDecimal amount, BigDecimal freezeAmount) {
        try {
            int count = extUserAccountMapper.accountIn(userId, amount, freezeAmount);
            if (count <= 0) {
                LOGGER.error("用户账户入账失败，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户入账失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户入账失败，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户入账失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户入账失败，userId={},errCode={}", userId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户入账失败");
        }
    }

    @Override
    public void accountOut(Long userId, BigDecimal amount, BigDecimal freezeAmount) {
        try {
            int count = extUserAccountMapper.accountOut(userId, amount, freezeAmount);
            if (count <= 0) {
                LOGGER.error("用户账户出账失败，余额不足，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户账户余额不足");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户账户出账失败，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户账户出账失败");
        } catch (Exception e2) {
            LOGGER.error("用户账户出账失败，userId={},errCode={}", userId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户账户出账失败");
        }
    }
}
