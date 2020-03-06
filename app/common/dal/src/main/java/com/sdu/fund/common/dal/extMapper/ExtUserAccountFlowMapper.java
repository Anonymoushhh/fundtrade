package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.UserAccountFlowDo;

public interface ExtUserAccountFlowMapper {

    /**
     行级锁
     */
    UserAccountFlowDo lockByPrimaryKey(Long flowId);

    /**
     使流水非法
     */
    int makeInValid(Long flowId);
}