package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.TradeOrderFlowDo;

public interface ExtTradeOrderFlowMapper {

    /**
     行级锁
     */
    TradeOrderFlowDo lockByPrimaryKey(Long flowId);

    /**
     通过幂等号查询订单流水
     */
    public TradeOrderFlowDo selectByIdempotentId(String idempotentId);

    /**
     使流水非法
     */
    int makeInValid(Long flowId);
}