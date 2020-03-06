package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.account.bo.User;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface UserRepository extends Repository<User,Long> {

    public User getByOpenId(String openId);
}
