package com.sdu.fund.core.service;

import com.sdu.fund.common.result.Result;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.bo.TradeOrder;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 17:46
 **/
public interface PurchaseCoreService {

    public Result<TradeOrder> apply(TradeOrder tradeOrder);

    public Result<Payment> pay(Payment payment);

    public Result<TradeOrder> payCannel(TradeOrder tradeOrder) throws Exception;

    public Result confirm(TradeOrder tradeOrder);

    public Result cancel(TradeOrder tradeOrder);

}
