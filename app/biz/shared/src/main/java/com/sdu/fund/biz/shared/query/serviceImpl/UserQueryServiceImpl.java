package com.sdu.fund.biz.shared.query.serviceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.query.service.UserQueryService;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.repository.UserRepository;
import com.sdu.fund.core.repository.UserTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public class UserQueryServiceImpl implements UserQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserQueryServiceImpl.class);

    @SofaReference
    private UserRepository userRepository;

    @SofaReference
    private UserTokenRepository userTokenRepository;


    @Override
    public User queryLoginUser(String token) {
        Result<Long> result = userTokenRepository.getUserIdByToken(token);
        if (result != null && result.isSuccess()) {
            Long userId = result.getData();
            return userRepository.get(userId).getData();
        }
        return null;
    }
}
