package com.sdu.fund.biz.shared.service;

import com.sdu.fund.biz.shared.vo.UserLoginVO;
import com.sdu.fund.core.model.account.bo.User;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public interface UserService {

    public UserLoginVO weChatLogin(String code, User user) throws Exception;
}
