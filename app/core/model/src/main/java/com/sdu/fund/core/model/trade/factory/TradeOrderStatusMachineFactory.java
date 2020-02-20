package com.sdu.fund.core.model.trade.factory;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import com.sdu.fund.core.model.trade.machine.TradeOrderStatusMachine;
import com.sdu.fund.core.model.trade.machine.PurchaseTradeOrderStatusMachine;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:07
 **/
public class TradeOrderStatusMachineFactory {

    public TradeOrderStatusMachine getStatusMachine(TradeOrderTypeEnum type) {
        switch (type) {
            case PURCHASE:
                return new PurchaseTradeOrderStatusMachine();
            case REDEEM:
            case FIXED:
            default:
                throw new CommonException("没有该状态机");
        }
    }
}
