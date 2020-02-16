package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.UserTokenDo;
import com.sdu.fund.core.model.account.bo.UserToken;
import com.sdu.fund.core.model.account.enums.TokenTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class UserTokenConverter {

    public static UserToken UserTokenDoconvert2UserToken(UserTokenDo userTokenDo){
        if(userTokenDo == null){
            return null;
        }
        UserToken userToken = new UserToken();
        BeanUtils.copyProperties(userTokenDo,userToken,"type");
        userToken.setType(TokenTypeEnum.getEnumByCode(userTokenDo.getType()));

        return userToken;
    }

    public static UserTokenDo UserTokenconvert2UserTokenDo(UserToken userToken){
        if(userToken == null){
            return null;
        }
        UserTokenDo userTokenDo = new UserTokenDo();
        BeanUtils.copyProperties(userToken,userTokenDo,"type");
        userTokenDo.setType(userToken.getType().getCode());

        return userTokenDo;
    }
}
