package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.UserAccountFlowDo;
import com.sdu.fund.core.model.account.bo.UserAccountFlow;
import com.sdu.fund.core.model.trade.enums.FlowInitiatorEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountChangeDirectionEnum;
import com.sdu.fund.core.model.trade.enums.UserAccountFlowTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class UserAccountFlowConverter {

    public static UserAccountFlow UserAccountFlowDoconvert2UserAccountFlow(UserAccountFlowDo userAccountFlowDo){
        if(userAccountFlowDo == null){
            return null;
        }
        UserAccountFlow userAccountFlow = new UserAccountFlow();
        BeanUtils.copyProperties(userAccountFlowDo,userAccountFlow,"type", "changeDirection","initiator");
        userAccountFlow.setType(UserAccountFlowTypeEnum.getEnumByCode(userAccountFlowDo.getType()));
        userAccountFlow.setChangeDirection(UserAccountChangeDirectionEnum.getEnumByCode(userAccountFlowDo.getChangeDirection()));
        userAccountFlow.setInitiator(FlowInitiatorEnum.getEnumByCode(userAccountFlowDo.getInitiator()));

        return userAccountFlow;
    }

    public static UserAccountFlowDo UserAccountFlowconvert2UserAccountFlowDo(UserAccountFlow userAccountFlow){
        if(userAccountFlow == null){
            return null;
        }
        UserAccountFlowDo userAccountFlowDo = new UserAccountFlowDo();
        BeanUtils.copyProperties(userAccountFlow,userAccountFlowDo,"type", "changeDirection","initiator");
        userAccountFlowDo.setType(userAccountFlow.getType().getCode());
        userAccountFlowDo.setChangeDirection(userAccountFlow.getChangeDirection().getCode());
        userAccountFlowDo.setInitiator(userAccountFlow.getInitiator().getCode());

        return userAccountFlowDo;
    }
}
