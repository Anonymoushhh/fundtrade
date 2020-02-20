package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.TradeOrderDo;

public interface ExtTradeOrderMapper {

    /**
     行级锁
     */
    TradeOrderDo lockByPrimaryKey(String orderId);
}