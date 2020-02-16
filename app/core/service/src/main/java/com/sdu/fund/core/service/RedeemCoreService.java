package com.sdu.fund.core.service;

import com.sdu.fund.core.model.trade.bo.TradeOrder;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 17:46
 **/
public interface RedeemCoreService {

    public TradeOrder apply(TradeOrder tradeOrder);

    public TradeOrder confirm(TradeOrder tradeOrder);

    public TradeOrder cancel(TradeOrder tradeOrder);

}
