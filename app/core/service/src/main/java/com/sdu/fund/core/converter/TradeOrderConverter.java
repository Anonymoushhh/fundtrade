package com.sdu.fund.core.converter;

import com.alibaba.fastjson.JSON;
import com.sdu.fund.common.dal.entity.TradeOrderDo;
import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.GenderEnum;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import org.springframework.beans.BeanUtils;

import java.util.Map;

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
        BeanUtils.copyProperties(tradeOrderDo,tradeOrder,"tradeOrderType", "tradeOrderStatus", "extInfo");
        tradeOrder.setTradeOrderType(TradeOrderTypeEnum.getEnumByCode(tradeOrderDo.getOrderType()));
        tradeOrder.setTradeOrderStatus(TradeOrderStatusEnum.getEnumByCode(tradeOrderDo.getOrderStatus()));
        tradeOrder.setExtInfo(JSON.parseObject(tradeOrderDo.getExtInfo(), Map.class));

        return tradeOrder;
    }

    public static TradeOrderDo TradeOrderconvert2TradeOrderDo(TradeOrder tradeOrder){
        if(tradeOrder == null){
            return null;
        }
        TradeOrderDo tradeOrderDo = new TradeOrderDo();
        BeanUtils.copyProperties(tradeOrder,tradeOrderDo,"tradeOrderType", "tradeOrderStatus");
        tradeOrderDo.setOrderType(tradeOrder.getTradeOrderType().getCode());
        tradeOrderDo.setOrderStatus(tradeOrder.getTradeOrderStatus().getCode());
        tradeOrderDo.setExtInfo(JSON.toJSONString(tradeOrder.getExtInfo()));

        return tradeOrderDo;
    }

    public static void main(String[] args) {
        System.out.println(JSON.parseObject(null, Map.class));;
    }
}
