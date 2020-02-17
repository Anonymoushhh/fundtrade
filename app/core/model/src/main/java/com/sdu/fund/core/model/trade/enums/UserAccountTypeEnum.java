package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserAccountTypeEnum {


    ORDINARY_ACCOUNT("ORDINARY_ACCOUNT", "普通账户"),
    TEST("TEST", "内测账户"),;

    private final String code;
    private final String msg;
    UserAccountTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static UserAccountTypeEnum getEnumByCode(String code) {
        for(UserAccountTypeEnum e: UserAccountTypeEnum.values()){
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