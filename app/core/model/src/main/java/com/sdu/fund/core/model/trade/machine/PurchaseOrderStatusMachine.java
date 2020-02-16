package com.sdu.fund.core.model.trade.machine;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.event.TradeOrderEvent;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:09
 **/
public class PurchaseTradeOrderStatusMachine implements TradeOrderStatusMachine{

    /**
     * 订单状态机
     * INIT ---> PAIED ----------> WAIT_CONFIRM_SHARE
     *   |         ^                       |
     *   |         |                       |                            WAIT_CONFIRM_ACTION
     *   |         |                       |
     *    ---> WAIT_PAY   ---> CANCEL <---   --->  WAIT_ARRIVAL --> FINISHED
     *
     */

    public TradeOrderStatusEnum getNextStatus(TradeOrderStatusEnum status, TradeOrderEvent event) {
        switch (status) {
            case INIT:
            case WAIT_PAY:
                return getPaidStatus(event);
            case PAIED:
                return TradeOrderStatusEnum.WAIT_CONFIRM_SHARE;
            case WAIT_CONFIRM_SHARE:
                return getConfirmShareStatus(event);
            case WAIT_ARRIVAL:
                return TradeOrderStatusEnum.FINISHED;
            case WAIT_CONFIRM_ACTION:
            default:
                throw new CommonException("没有该流程");
        }
    }

    private TradeOrderStatusEnum getPaidStatus(TradeOrderEvent event) {
        switch (event) {
            case NO_PAY:
                return TradeOrderStatusEnum.WAIT_PAY;
            case PAY_SUCCESS:
                return TradeOrderStatusEnum.PAIED;
            case PAY_FAILURE:
                return TradeOrderStatusEnum.CANCEL;
            default:
                throw new CommonException("不支持该Event");
        }
    }

    private TradeOrderStatusEnum getConfirmShareStatus(TradeOrderEvent event) {
        switch (event) {
            case CONFIRM_SHARE_SUCCESS:
                return TradeOrderStatusEnum.WAIT_ARRIVAL;
            case CONFIRM_SHARE_FAILURE:
                return TradeOrderStatusEnum.CANCEL;
            default:
                throw new RuntimeException("不支持该Event");
        }
    }

}
