package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.UserDo;

public interface ExtUserMapper {

    /**
     *
     */
    UserDo selectByOpenId(String openId);
}