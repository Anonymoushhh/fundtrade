package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.TradeOrderFlowDo;

public interface ExtTradeOrderFlowMapper {

    /**
     行级锁
     */
    TradeOrderFlowDo lockByPrimaryKey(Long flowId);
}