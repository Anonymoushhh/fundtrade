package com.sdu.fund.core.serviceImpl;

import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.repository.FundDataRepository;
import com.sdu.fund.core.repository.TradeOrderRepository;
import com.sdu.fund.core.service.PurchaseCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseCoreServiceImpl implements PurchaseCoreService {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseCoreServiceImpl.class);

    @Autowired
    private TradeOrderRepository fundDataRepository;

    @Override
    public TradeOrder apply(TradeOrder tradeOrder) {
        Result result = fundDataRepository.add(tradeOrder);
        if(result!=null&&result.isSuccess()){
            return tradeOrder;
        }else{
            throw new CommonException("创建订单失败");
        }
    }

    @Override
    public TradeOrder pay(TradeOrder tradeOrder) {
        return null;
    }

    @Override
    public TradeOrder confirm(TradeOrder tradeOrder) {
        return null;
    }

    @Override
    public TradeOrder cancel(TradeOrder tradeOrder) {
        return null;
    }
}
