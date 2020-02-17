package com.sdu.fund.core.model.trade.machine;

import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.event.TradeOrderEvent;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:42
 **/
public interface TradeOrderStatusMachine {
    /**
     *@params status 当前状态
     *@params event 审批意见
     *@return 下一个状态
     **/
    public TradeOrderStatusEnum getNextStatus(TradeOrderStatusEnum status, TradeOrderEvent event);
}
