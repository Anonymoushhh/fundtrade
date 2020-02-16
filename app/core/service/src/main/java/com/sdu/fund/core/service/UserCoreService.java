package com.sdu.fund.core.service;

import com.sdu.fund.core.model.account.bo.User;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 17:46
 **/
public interface UserCoreService {

    public User login(User user);

    public User register(User user);

}
