package com.sdu.fund.core.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdu.fund.common.dal.entity.FundDataDo;
import com.sdu.fund.core.model.bo.FundData;
import com.sdu.fund.core.model.bo.Rate;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @program: fundtrade
 * @description: 基金数据对象转换类
 * @author: anonymous
 * @create: 2019-12-09 22:22
 **/
public class FundDataConverter {

    public static FundData FundDataDoconvert2FundData(FundDataDo fundDataDo){
        if(fundDataDo == null){
            return null;
        }
        FundData fundData = new FundData();
        BeanUtils.copyProperties(fundDataDo,fundData,"purchaseRate","subscribeRate","redeemRate");
        fundData.setPurchaseRate(JSON.parseArray(fundDataDo.getPurchaseRate(),Rate.class));
        fundData.setSubscribeRate(JSON.parseArray(fundDataDo.getSubscribeRate(),Rate.class));
        fundData.setRedeemRate(JSON.parseArray(fundDataDo.getRedeemRate(),Rate.class));

        return fundData;
    }

    public static FundDataDo FundDataconvert2FundDataDo(FundData fundData){
        if(fundData == null){
            return null;
        }
        FundDataDo fundDataDo = new FundDataDo();
        BeanUtils.copyProperties(fundData,fundDataDo,"purchaseRate","subscribeRate","redeemRate");
        fundDataDo.setPurchaseRate(JSON.toJSONString(fundData.getPurchaseRate()));
        fundDataDo.setSubscribeRate(JSON.toJSONString(fundData.getSubscribeRate()));
        fundDataDo.setRedeemRate(JSON.toJSONString(fundData.getRedeemRate()));

        return fundDataDo;
    }
}
