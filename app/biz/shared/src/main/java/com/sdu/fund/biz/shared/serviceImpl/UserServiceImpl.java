package com.sdu.fund.biz.shared.serviceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.service.UserAccountService;
import com.sdu.fund.biz.shared.vo.UserLoginVo;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.model.account.bo.WeChatSessionInfo;
import com.sdu.fund.core.service.WeChatApiService;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public class UserAccountServiceImpl implements UserAccountService {

    @SofaReference
    private WeChatApiService weChatApiService;

    @Override
    public UserLoginVo weChatLogin(String code, UserAccount userAccount) {
        // 1.获取三方平台session信息
        WeChatSessionInfo weChatSessionInfo = weChatApiService.getSessionInfo(code);
        // 2.根据用户唯一标识 OpenID查询用户信息

        // 3.1查询到用户则执行登录动作
        // 3.2未查到执行注册动作
        // 4.生成token
//        return new UserLoginVo();
        return null;
    }
}
