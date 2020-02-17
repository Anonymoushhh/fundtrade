package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum TradeOrderTypeEnum {


    PURCHASE("PURCHASE", "申购单"),
    REDEEM("REDEEM", "赎回单"),
    FIXED("FIXED", "定投单");

    private final String code;
    private final String msg;
    TradeOrderTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static TradeOrderTypeEnum getEnumByCode(String code) {
        for(TradeOrderTypeEnum e: TradeOrderTypeEnum.values()){
            if(StringUtils.equals(e.getCode(),code)){
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}