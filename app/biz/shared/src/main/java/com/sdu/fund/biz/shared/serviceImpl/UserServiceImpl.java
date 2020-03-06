package com.sdu.fund.biz.shared.serviceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.service.UserService;
import com.sdu.fund.biz.shared.vo.UserLoginVO;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.account.bo.UserToken;
import com.sdu.fund.core.model.account.bo.WeChatSessionInfo;
import com.sdu.fund.core.model.account.constant.WeChatAppInfo;
import com.sdu.fund.core.repository.UserRepository;
import com.sdu.fund.core.repository.UserTokenRepository;
import com.sdu.fund.core.service.UserCoreService;
import com.sdu.fund.core.service.WeChatApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @SofaReference
    private WeChatApiService weChatApiService;

    @SofaReference
    private UserCoreService userCoreService;

    @SofaReference
    private UserRepository userRepository;

    @SofaReference
    private UserTokenRepository userTokenRepository;

    @Override
    public UserLoginVO weChatLogin(String code, User user) throws Exception {
        User currentUser = user;

        // 1.获取三方平台session信息
        WeChatSessionInfo weChatSessionInfo = weChatApiService.getSessionInfo(code);
        // 2.根据用户唯一标识 OpenID查询用户信息
        User existUser = userRepository.getByOpenId(weChatSessionInfo.getOpenId());
        if (existUser != null) {
            // 3.1查询到用户则执行登录动作
            // 3.1.1比较用户微信信息有无更新
            if (!user.equals(existUser, "nickName", "gender", "city", "province", "country")) {
                user.setUserId(existUser.getUserId());
                userRepository.update(user);
            }
            currentUser = userCoreService.login(existUser);
        } else {
            // 3.2未查询到用户
            // 3.2.1绑定用户openId
            user.setOpenId(weChatSessionInfo.getOpenId());
            // 3.2.2执行注册动作
            User registeredUser = userCoreService.register(user);
            currentUser = userCoreService.login(registeredUser);
        }

        // 4.生成并存储token
        UserToken userToken = new UserToken();
        Validator.notNull(currentUser.getUserId());
        userToken.setUserId(currentUser.getUserId());
        // appId按道理应该前端传过来，目前只有一个端，暂时写死
        userToken.setAppId(WeChatAppInfo.APP_ID);
        userToken.init(user.getOpenId(), user.getUserId());
        userTokenRepository.add(userToken);
        return new UserLoginVO(currentUser.getUserId(), userToken.getToken(), currentUser.getAuthority().getCode());
    }
}
