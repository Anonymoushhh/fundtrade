package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.TradeOrderFlowDo;
import com.sdu.fund.core.model.trade.bo.TradeOrderFlow;
import com.sdu.fund.core.model.trade.enums.FlowInitiatorEnum;
import com.sdu.fund.core.model.trade.enums.TradeOrderFlowTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class TradeOrderFlowConverter {

    public static TradeOrderFlow TradeOrderFlowDoconvert2TradeOrderFlow(TradeOrderFlowDo tradeOrderFlowDo){
        if(tradeOrderFlowDo == null){
            return null;
        }
        TradeOrderFlow tradeOrderFlow = new TradeOrderFlow();
        BeanUtils.copyProperties(tradeOrderFlowDo,tradeOrderFlow,"type", "initiator");
        tradeOrderFlow.setType(TradeOrderFlowTypeEnum.getEnumByCode(tradeOrderFlowDo.getType()));
        tradeOrderFlow.setInitiator(FlowInitiatorEnum.getEnumByCode(tradeOrderFlowDo.getInitiator()));

        return tradeOrderFlow;
    }

    public static TradeOrderFlowDo TradeOrderFlowconvert2TradeOrderFlowDo(TradeOrderFlow tradeOrderFlow){
        if(tradeOrderFlow == null){
            return null;
        }
        TradeOrderFlowDo tradeOrderFlowDo = new TradeOrderFlowDo();
        BeanUtils.copyProperties(tradeOrderFlow,tradeOrderFlowDo,"type", "initiator");
        tradeOrderFlowDo.setType(tradeOrderFlow.getType().getCode());
        tradeOrderFlowDo.setInitiator(tradeOrderFlow.getInitiator().getCode());

        return tradeOrderFlowDo;
    }
}
