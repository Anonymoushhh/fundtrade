package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.trade.bo.TradeOrderFlow;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface TradeOrderFlowRepository extends Repository<TradeOrderFlow,Long> {

    public TradeOrderFlow lock(Long flowId);
}
