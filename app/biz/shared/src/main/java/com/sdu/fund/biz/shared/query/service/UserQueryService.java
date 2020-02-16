package com.sdu.fund.biz.shared.query.service;

import com.sdu.fund.core.model.account.bo.User;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public interface UserQueryService {

    public User queryLoginUser(String token);

}
