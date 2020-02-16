package com.sdu.fund.core.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sdu.fund.common.dal.entity.FundDataDo;
import com.sdu.fund.core.model.bo.FundData;
import com.sdu.fund.core.model.bo.Rate;
import com.sdu.fund.core.model.enums.ConfirmDayEnum;
import com.sdu.fund.core.model.enums.FundTypeEnum;
import com.sdu.fund.core.model.enums.PurchaseStatusEnum;
import com.sdu.fund.core.model.enums.RedeemStatusEnum;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @program: fundproduct
 * @description: 基金数据对象转换类
 * @author: anonymous
 * @create: 2019-12-09 22:22
 **/
public class FundDataConverter {

    public static FundData FundDataDoconvert2FundData(FundDataDo fundDataDo) {
        if (fundDataDo == null) {
            return null;
        }
        FundData fundData = new FundData();
        BeanUtils.copyProperties(fundDataDo, fundData, "purchaseRate", "subscribeRate", "redeemRate", "fundType",
                "purchaseStatus", "redeemStatus", "buyConfirmDay", "redeemConfirmDay");
        fundData.setFundType(FundTypeEnum.getEnumByCode(fundDataDo.getFundType()));
        fundData.setPurchaseStatus(PurchaseStatusEnum.getEnumByCode(fundDataDo.getPurchaseStatus()));
        fundData.setRedeemStatus(RedeemStatusEnum.getEnumByCode(fundDataDo.getRedeemStatus()));
        fundData.setBuyConfirmDay(ConfirmDayEnum.getEnumByCode(fundDataDo.getBuyConfirmDay()));
        fundData.setRedeemConfirmDay(ConfirmDayEnum.getEnumByCode(fundDataDo.getRedeemConfirmDay()));
        fundData.setPurchaseRate(JSON.parseArray(fundDataDo.getPurchaseRate(), Rate.class));
        fundData.setSubscribeRate(JSON.parseArray(fundDataDo.getSubscribeRate(), Rate.class));
        fundData.setRedeemRate(JSON.parseArray(fundDataDo.getRedeemRate(), Rate.class));

        return fundData;
    }

    public static FundDataDo FundDataconvert2FundDataDo(FundData fundData) {
        if (fundData == null) {
            return null;
        }
        FundDataDo fundDataDo = new FundDataDo();
        BeanUtils.copyProperties(fundData, fundDataDo, "purchaseRate", "subscribeRate", "redeemRate", "fundType",
                "purchaseStatus", "redeemStatus", "buyConfirmDay", "redeemConfirmDay");
        fundDataDo.setFundType(fundData.getFundType().getCode());
        fundDataDo.setPurchaseStatus(fundData.getPurchaseStatus().getCode());
        fundDataDo.setRedeemStatus(fundData.getRedeemStatus().getCode());
        fundDataDo.setBuyConfirmDay(fundData.getBuyConfirmDay().getCode());
        fundDataDo.setRedeemConfirmDay(fundData.getRedeemConfirmDay().getCode());
        fundDataDo.setPurchaseRate(JSON.toJSONString(fundData.getPurchaseRate()));
        fundDataDo.setSubscribeRate(JSON.toJSONString(fundData.getSubscribeRate()));
        fundDataDo.setRedeemRate(JSON.toJSONString(fundData.getRedeemRate()));

        return fundDataDo;
    }
}
