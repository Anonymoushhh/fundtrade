package com.sdu.fund.core.converter;

import com.alibaba.fastjson.JSON;
import com.sdu.fund.common.dal.entity.HoldPositionDo;
import com.sdu.fund.core.model.trade.bo.HoldPosition;
import com.sdu.fund.core.model.trade.enums.FundTypeEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/3/3 18:40
 **/
public class HoldPositionConverter {

    public static HoldPosition HoldPositionDoconvert2HoldPosition(HoldPositionDo holdPositionDo){
        if(holdPositionDo == null){
            return null;
        }
        HoldPosition holdPosition = new HoldPosition();
        BeanUtils.copyProperties(holdPositionDo,holdPosition,"fundType");
        holdPosition.setFundType(FundTypeEnum.getEnumByCode(holdPositionDo.getFundType()));

        return holdPosition;
    }

    public static HoldPositionDo HoldPositionconvert2HoldPositionDo(HoldPosition holdPosition){
        if(holdPosition == null){
            return null;
        }
        HoldPositionDo holdPositionDo = new HoldPositionDo();
        BeanUtils.copyProperties(holdPosition,holdPositionDo,"fundType");
        holdPositionDo.setFundType(holdPosition.getFundType().getCode());

        return holdPositionDo;
    }
}
