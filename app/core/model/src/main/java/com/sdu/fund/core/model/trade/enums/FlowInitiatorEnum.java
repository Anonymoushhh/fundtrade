package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum FlowInitiatorEnum {


    USER("USER", "用户"),
    SYSTEM("SYSTEM", "系统"),;

    private final String code;
    private final String msg;
    FlowInitiatorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static FlowInitiatorEnum getEnumByCode(String code) {
        for(FlowInitiatorEnum e: FlowInitiatorEnum.values()){
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