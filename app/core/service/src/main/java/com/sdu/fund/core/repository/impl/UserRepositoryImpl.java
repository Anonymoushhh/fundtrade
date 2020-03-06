package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtUserMapper;
import com.sdu.fund.common.dal.mapper.UserMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.UserConverter;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


/**
 * @program: fundproduct
 * @description: 用户仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExtUserMapper extUserMapper;

    @Override
    public User get(Long userId) {
        Validator.notNull(userId);
        try {
            User user = UserConverter.UserDoconvert2User(userMapper.selectByPrimaryKey(userId));
            return user;
        } catch (DataAccessException e1) {
            LOGGER.error("用户查询失败，userId={},errCode={}", userId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户查询失败");
        } catch (Exception e2) {
            LOGGER.error("用户查询失败，userId={},errCode={}", userId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户查询失败");
        }
    }

    @Override
    public void add(User user) {
        try {
            preCheck(user);
            int id = userMapper.insertSelective(UserConverter.Userconvert2UserDo(user));
            if (id <= 0) {
                LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户信息插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户信息插入失败");
        } catch (Exception e2) {
            LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户信息插入失败");
        }
    }

    @Override
    public void update(User user) {
        try {
            preCheck(user);
            int count =
                    userMapper.updateByPrimaryKeySelective(UserConverter.Userconvert2UserDo(user));
            if (count <= 0) {
                LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户信息更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户信息更新失败");
        } catch (Exception e2) {
            LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户信息更新失败");
        }
    }

    @Override
    public void delete(Long userId) {
        try {
            int count = userMapper.deleteByPrimaryKey(userId);
            if (count <= 0) {
                LOGGER.error("用户信息删除失败，userId={},errCode={}", userId,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户信息删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息删除失败，userId={},errCode={}", userId,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户信息删除失败");
        } catch (Exception e2) {
            LOGGER.error("用户信息删除失败，userId={},errCode={}", userId,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户信息删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(User user) {
        Validator.notNull(user);
        Validator.notNull(user.getUserId());
    }

    @Override
    public User getByOpenId(String openId) {
        try {
            User user = UserConverter.UserDoconvert2User(extUserMapper.selectByOpenId(openId));
            return user;
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息查询失败，openId={},errCode={}", openId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户信息查询失败");
        } catch (Exception e2) {
            LOGGER.error("用户信息查询失败，openId={},errCode={}", openId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户信息查询失败");
        }
    }
}
