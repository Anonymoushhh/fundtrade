package com.sdu.fund.core.model.machine;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.core.model.enums.OrderStatusEnum;
import com.sdu.fund.core.model.event.OrderEvent;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:09
 **/
public class PurchaseOrderStatusMachine implements OrderStatusMachine{

    /**
     * 订单状态机
     * INIT ---> PAIED ----------> WAIT_CONFIRM_SHARE
     *   |         ^                       |
     *   |         |                       |                            WAIT_CONFIRM_ACTION
     *   |         |                       |
     *    ---> WAIT_PAY   ---> CANCEL <---   --->  WAIT_ARRIVAL --> FINISHED
     *
     */

    public OrderStatusEnum getNextStatus(OrderStatusEnum status, OrderEvent event) {
        switch (status) {
            case INIT:
            case WAIT_PAY:
                return getPaidStatus(event);
            case PAIED:
                return OrderStatusEnum.WAIT_CONFIRM_SHARE;
            case WAIT_CONFIRM_SHARE:
                return getConfirmShareStatus(event);
            case WAIT_ARRIVAL:
                return OrderStatusEnum.FINISHED;
            case WAIT_CONFIRM_ACTION:
            default:
                throw new CommonException("没有该流程");
        }
    }

    private OrderStatusEnum getPaidStatus(OrderEvent event) {
        switch (event) {
            case NO_PAY:
                return OrderStatusEnum.WAIT_PAY;
            case PAY_SUCCESS:
                return OrderStatusEnum.PAIED;
            case PAY_FAILURE:
                return OrderStatusEnum.CANCEL;
            default:
                throw new CommonException("不支持该Event");
        }
    }

    private OrderStatusEnum getConfirmShareStatus(OrderEvent event) {
        switch (event) {
            case CONFIRM_SHARE_SUCCESS:
                return OrderStatusEnum.WAIT_ARRIVAL;
            case CONFIRM_SHARE_FAILURE:
                return OrderStatusEnum.CANCEL;
            default:
                throw new RuntimeException("不支持该Event");
        }
    }

}
