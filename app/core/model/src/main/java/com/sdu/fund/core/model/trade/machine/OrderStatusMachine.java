package com.sdu.fund.core.model.machine;

import com.sdu.fund.core.model.enums.OrderStatusEnum;
import com.sdu.fund.core.model.event.OrderEvent;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/13 20:42
 **/
public interface OrderStatusMachine {
    /**
     *@params status 当前状态
     *@params event 审批意见
     *@return 下一个状态
     **/
    public OrderStatusEnum getNextStatus(OrderStatusEnum status, OrderEvent event);
}
