package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserAccountStatusEnum {


    NORMAL("NORMAL", "正常"),
    CANCELLATION("CANCELLATION", "注销"),;

    private final String code;
    private final String msg;
    UserAccountStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static UserAccountStatusEnum getEnumByCode(String code) {
        for(UserAccountStatusEnum e: UserAccountStatusEnum.values()){
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