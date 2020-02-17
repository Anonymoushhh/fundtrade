package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserAccountChangeDirectionEnum {


    IN("IN", "入账"),
    OUT("OUT", "出账"),;

    private final String code;
    private final String msg;

    UserAccountChangeDirectionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static UserAccountChangeDirectionEnum getEnumByCode(String code) {
        for(UserAccountChangeDirectionEnum e: UserAccountChangeDirectionEnum.values()){
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