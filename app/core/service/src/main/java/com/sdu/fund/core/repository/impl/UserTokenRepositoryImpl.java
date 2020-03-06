package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtUserTokenMapper;
import com.sdu.fund.common.dal.mapper.UserTokenMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.UserTokenConverter;
import com.sdu.fund.core.model.account.bo.UserToken;
import com.sdu.fund.core.repository.UserTokenRepository;
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
public class UserTokenRepositoryImpl implements UserTokenRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenRepositoryImpl.class);

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private ExtUserTokenMapper extUserTokenMapper;

    @Override
    public UserToken get(Long tokenId) {
        try {
            Validator.notNull(tokenId);
            UserToken userToken =
                    UserTokenConverter.UserTokenDoconvert2UserToken(userTokenMapper.selectByPrimaryKey(tokenId));
            return userToken;
        } catch (DataAccessException e1) {
            LOGGER.error("用户登录Token查询失败，tokenId={},errCode={}", tokenId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户登录Token查询失败");
        } catch (Exception e2) {
            LOGGER.error("用户登录Token查询失败，tokenId={},errCode={}", tokenId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户登录Token查询失败");
        }
    }

    @Override
    public Long getUserIdByToken(String token) {
        Validator.notNull(token);
        try {
            Long userId = extUserTokenMapper.selectUserIdByToken(token);
            return userId;
        } catch (DataAccessException e1) {
            LOGGER.error("用户id查询失败，token={},errCode={}", token, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户id查询失败");
        } catch (Exception e2) {
            LOGGER.error("用户id查询失败，token={},errCode={}", token, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户id查询失败");
        }
    }

    @Override
    public void add(UserToken userToken) {
        try {
            preCheck(userToken);
            int id = userTokenMapper.insertSelective(UserTokenConverter.UserTokenconvert2UserTokenDo(userToken));
            if (id <= 0) {
                LOGGER.error("用户登录Token插入失败，tokenId={},errCode={}", userToken.getTokenId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户登录Token插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户登录Token插入失败，tokenId={},errCode={}", userToken.getTokenId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户登录Token插入失败");
        } catch (Exception e2) {
            LOGGER.error("用户登录Token插入失败，tokenId={},errCode={}", userToken.getTokenId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户登录Token插入失败");
        }
    }

    @Override
    public void update(UserToken userToken) {
        try {
            preCheck(userToken);
            int count =
                    userTokenMapper.updateByPrimaryKeySelective(UserTokenConverter.UserTokenconvert2UserTokenDo(userToken));
            if (count <= 0) {
                LOGGER.error("用户登录Token更新失败，tokenId={},errCode={}", userToken.getTokenId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户登录Token更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户登录Token更新失败，tokenId={},errCode={}", userToken.getTokenId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户登录Token更新失败");
        } catch (Exception e2) {
            LOGGER.error("用户登录Token更新失败，tokenId={},errCode={}", userToken.getTokenId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户登录Token更新失败");
        }
    }

    @Override
    public void delete(Long tokenId) {
        try {
            int count = userTokenMapper.deleteByPrimaryKey(tokenId);
            if (count <= 0) {
                LOGGER.error("用户登录Token删除失败，tokenId={},errCode={}", tokenId,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("用户登录Token删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("用户登录Token删除失败，tokenId={},errCode={}", tokenId,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("用户登录Token删除失败");
        } catch (Exception e2) {
            LOGGER.error("用户登录Token删除失败，tokenId={},errCode={}", tokenId,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("用户登录Token删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(UserToken userToken) {
        Validator.notNull(userToken);
        Validator.notNull(userToken.getTokenId());
    }

}
