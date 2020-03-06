package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.UserDo;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.GenderEnum;
import com.sdu.fund.core.model.account.enums.UserStatusEnum;
import com.sdu.fund.core.model.trade.enums.UserTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class UserConverter {

    public static User UserDoconvert2User(UserDo userDo) {
        if (userDo == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userDo, user, "gender", "authority", "type", "status");
        user.setAuthority(AuthorityEnum.getEnumByCode(userDo.getAuthority()));
        user.setGender(GenderEnum.getEnumByCode(userDo.getGender()));
        user.setStatus(UserStatusEnum.getEnumByCode(userDo.getStatus()));
//        user.setType(UserTypeEnum.getEnumByCode(userDo));

        return user;
    }

    public static UserDo Userconvert2UserDo(User user) {
        if (user == null) {
            return null;
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(user, userDo, "gender", "authority", "status");
        userDo.setAuthority(user.getAuthority() != null ? user.getAuthority().getCode() : null);
        userDo.setGender(user.getGender() != null ? user.getGender().getCode() : null);
        userDo.setStatus(user.getStatus() != null ? user.getStatus().getCode() : null);

        return userDo;
    }
}
