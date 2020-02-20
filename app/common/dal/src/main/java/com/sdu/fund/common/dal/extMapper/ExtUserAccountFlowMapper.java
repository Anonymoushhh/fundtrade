package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.UserAccountFlowDo;

public interface ExtUserAccountFlowMapper {
    /**
     行级锁
     */
    UserAccountFlowDo lockByPrimaryKey(String flowId);
}