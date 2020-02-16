package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.TradeOrderDo;
import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.GenderEnum;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class TradeOrderConverter {

    public static TradeOrder TradeOrderDoconvert2TradeOrder(TradeOrderDo tradeOrderDo){
        if(tradeOrderDo == null){
            return null;
        }
        TradeOrder tradeOrder = new TradeOrder();
        BeanUtils.copyProperties(tradeOrderDo,tradeOrder,"tradeOrderType", "tradeOrderStatus");
        tradeOrder.setTradeOrderType(TradeOrderTypeEnum.getEnumByCode(tradeOrderDo.getTradeOrderType()));
        tradeOrder.setTradeOrderStatus(TradeOrderStatusEnum.getEnumByCode(tradeOrderDo.getTradeOrderStatus()));

        return tradeOrder;
    }

    public static TradeOrderDo TradeOrderconvert2TradeOrderDo(TradeOrder tradeOrder){
        if(tradeOrder == null){
            return null;
        }
        TradeOrderDo tradeOrderDo = new TradeOrderDo();
        BeanUtils.copyProperties(tradeOrder,tradeOrderDo,"tradeOrderType", "tradeOrderStatus");
        tradeOrderDo.setTradeOrderType(tradeOrder.getTradeOrderType().getCode());
        tradeOrderDo.setTradeOrderStatus(tradeOrder.getTradeOrderStatus().getCode());

        return tradeOrderDo;
    }
}
