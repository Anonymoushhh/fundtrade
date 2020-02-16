package com.sdu.fund.core.repository;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtUserMapper;
import com.sdu.fund.common.dal.mapper.UserMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.UserConverter;
import com.sdu.fund.core.model.account.bo.User;
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
        return UserConverter.UserDoconvert2User(userMapper.selectByPrimaryKey(userId));
    }

    @Override
    public Result add(User user) {
        // 预校验
        boolean check = preCheck(user);
        if (!check) {
            LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = userMapper.insert(UserConverter.Userconvert2UserDo(user));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户信息插入失败，userId={},errCode={}", user.getUserId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(User user) {
        // 预校验
        boolean check = preCheck(user);
        if (!check) {
            LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                userMapper.updateByPrimaryKeySelective(UserConverter.Userconvert2UserDo(user));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户信息更新失败，userId={},errCode={}", user.getUserId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(Long userId) {
        try {
            int count = userMapper.deleteByPrimaryKey(userId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("用户信息删除失败，userId={},errCode={}", userId,
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户信息删除失败，userId={},errCode={}", userId,
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("用户信息删除失败，userId={},errCode={}", userId,
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
    private boolean preCheck(User user) {
        return Validator.notNull(user) && Validator.notNull(user.getUserId());
    }

    @Override
    public Result<User> getByOpenId(String openId) {
        User user = UserConverter.UserDoconvert2User(extUserMapper.selectByOpenId(openId));
        if(user == null){
            ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
        return ResultUtil.buildSuccessResult(user);
    }
}
