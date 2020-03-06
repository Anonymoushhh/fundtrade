package com.sdu.fund.core.repository;

import com.sdu.fund.common.result.Result;
import com.sdu.fund.core.model.account.bo.UserToken;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface UserTokenRepository extends Repository<UserToken,Long> {

    public Long getUserIdByToken(String token);
}
