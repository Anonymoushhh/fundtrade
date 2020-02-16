package com.sdu.fund.core.model.factory;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.core.model.enums.OrderTypeEnum;
import com.sdu.fund.core.model.machine.OrderStatusMachine;
import com.sdu.fund.core.model.machine.PurchaseOrderStatusMachine;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:07
 **/
public class OrderStatusMachineFactory {

    public OrderStatusMachine getStatusMachine(OrderTypeEnum type) {
        switch (type) {
            case PURCHASE:
                return new PurchaseOrderStatusMachine();
            case REDEEM:
            case FIXED:
            default:
                throw new CommonException("没有该状态机");
        }
    }
}
