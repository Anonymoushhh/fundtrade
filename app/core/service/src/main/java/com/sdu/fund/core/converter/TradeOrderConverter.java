package com.sdu.fund.core.converter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sdu.fund.common.dal.entity.TradeOrderDo;
import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.core.model.trade.constants.TradeOrderExtKey;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Map;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class TradeOrderConverter {

    public static TradeOrder TradeOrderDoconvert2TradeOrder(TradeOrderDo tradeOrderDo) {
        if (tradeOrderDo == null) {
            return null;
        }
        TradeOrder tradeOrder = new TradeOrder();
        BeanUtils.copyProperties(tradeOrderDo, tradeOrder, "tradeOrderType", "tradeOrderStatus", "extInfo");
        tradeOrder.setOrderType(TradeOrderTypeEnum.getEnumByCode(tradeOrderDo.getOrderType()));
        tradeOrder.setOrderStatus(TradeOrderStatusEnum.getEnumByCode(tradeOrderDo.getOrderStatus()));
        Map<String, Object> extInfo = JSON.parseObject(tradeOrderDo.getExtInfo(), Map.class);
        tradeOrder.setExtInfo(extInfo);
        if (extInfo.get(TradeOrderExtKey.PURCHASECONFIRMDAY) != null) {
            Date purchaseConfirmDay = DateUtil.strToDate((String) extInfo.get(TradeOrderExtKey.PURCHASECONFIRMDAY),
                    DateUtil.FMT_YMD1);
            tradeOrder.setPurchaseConfirmDay(purchaseConfirmDay);
        }

        return tradeOrder;
    }

    public static TradeOrderDo TradeOrderconvert2TradeOrderDo(TradeOrder tradeOrder) {
        if (tradeOrder == null) {
            return null;
        }
        TradeOrderDo tradeOrderDo = new TradeOrderDo();
        BeanUtils.copyProperties(tradeOrder, tradeOrderDo, "tradeOrderType", "tradeOrderStatus", "extInfo");
        tradeOrderDo.setOrderType(tradeOrder.getOrderType() != null ? tradeOrder.getOrderType().getCode() : null);
        tradeOrderDo.setOrderStatus(tradeOrder.getOrderStatus() != null ? tradeOrder.getOrderStatus().getCode() : null);
        Map<String, Object> extInfo = Maps.newHashMap(tradeOrder.getExtInfo());
        if (tradeOrder.getPurchaseConfirmDay() != null) {
            extInfo.put(TradeOrderExtKey.PURCHASECONFIRMDAY, DateUtil.dateToStr(tradeOrder.getPurchaseConfirmDay(),
                    DateUtil.FMT_YMD1));
        }
        tradeOrderDo.setExtInfo(JSON.toJSONString(extInfo));

        return tradeOrderDo;
    }

    public static void main(String[] args) {
        System.out.println(JSON.parseObject(null, Map.class));
        ;
    }
}
