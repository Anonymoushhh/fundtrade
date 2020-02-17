package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserTypeEnum {


    ORDINARY_MEMBER("ORDINARY_MEMBER", "普通会员"),
    BRONZE_MEMBER("BRONZE_MEMBER", "青铜会员"),
    SILVER_MEMBER("SILVER_MEMBER", "白银会员"),
    GOLD_MEMBER("GOLD_MEMBER", "黄金会员"),
    DIAMONDS_MEMBER("DIAMONDS_MEMBER", "钻石会员"),
    BLACK_DURIAN_MEMBER("BLACK_DURIAN_MEMBER", "黑榴莲会员"),

    TEST("TEST", "内测账户"),;

    private final String code;
    private final String msg;
    UserTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static UserTypeEnum getEnumByCode(String code) {
        for(UserTypeEnum e: UserTypeEnum.values()){
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