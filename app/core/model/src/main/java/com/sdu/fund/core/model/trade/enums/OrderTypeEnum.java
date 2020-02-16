package com.sdu.fund.core.model.enums;

import org.apache.commons.lang3.StringUtils;

public enum OrderTypeEnum {


    PURCHASE("PURCHASE", "申购单"),
    REDEEM("REDEEM", "赎回单"),
    FIXED("FIXED", "定投单");

    private final String code;
    private final String msg;
    OrderTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static OrderTypeEnum getEnumByCode(String code) {
        for(OrderTypeEnum e: OrderTypeEnum.values()){
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