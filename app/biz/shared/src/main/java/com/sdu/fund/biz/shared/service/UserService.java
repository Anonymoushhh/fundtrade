package com.sdu.fund.biz.shared.service;

import com.sdu.fund.biz.shared.vo.UserLoginVo;
import com.sdu.fund.core.model.account.bo.UserAccount;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public interface UserAccountService {

    public UserLoginVo weChatLogin(String code, UserAccount userAccount);
}
