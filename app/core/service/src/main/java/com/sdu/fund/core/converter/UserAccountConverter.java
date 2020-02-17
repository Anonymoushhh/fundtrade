package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.UserAccountDo;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.model.trade.enums.UserAccountStatusEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class UserAccountConverter {

    public static UserAccount UserAccountDoconvert2UserAccount(UserAccountDo userAccountDo){
        if(userAccountDo == null){
            return null;
        }
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userAccountDo,userAccount,"type", "status");
        userAccount.setType(UserAccountTypeEnum.getEnumByCode(userAccountDo.getType()));
        userAccount.setStatus(UserAccountStatusEnum.getEnumByCode(userAccountDo.getStatus()));

        return userAccount;
    }

    public static UserAccountDo UserAccountconvert2UserAccountDo(UserAccount userAccount){
        if(userAccount == null){
            return null;
        }
        UserAccountDo userAccountDo = new UserAccountDo();
        BeanUtils.copyProperties(userAccount,userAccountDo,"type", "status");
        userAccountDo.setType(userAccount.getType().getCode());
        userAccountDo.setStatus(userAccount.getStatus().getCode());

        return userAccountDo;
    }
}
