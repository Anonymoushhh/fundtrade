package com.sdu.fund.common.dal.extMapper;


public interface ExtUserTokenMapper {

    /**
     *
     */
    Long selectUserIdByToken(String token);
}