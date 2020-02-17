package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserAccountFlowTypeEnum {


    CHARGE("CHARGE", "充值"),
    PURCHASE("PURCHASE", "基金申购"),
    FIXED("PURCHASE", "基金定投"),
    REDEEM("REDEEM", "基金赎回"),;

    private final String code;
    private final String msg;

    UserAccountFlowTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static UserAccountFlowTypeEnum getEnumByCode(String code) {
        for(UserAccountFlowTypeEnum e: UserAccountFlowTypeEnum.values()){
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