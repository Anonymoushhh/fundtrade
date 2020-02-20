package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.trade.bo.TradeOrder;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface TradeOrderRepository extends Repository<TradeOrder,String> {

    public TradeOrder lock(String orderId);

}
