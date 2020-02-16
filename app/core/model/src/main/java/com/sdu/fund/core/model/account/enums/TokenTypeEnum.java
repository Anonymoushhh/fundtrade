package com.sdu.fund.core.model.account.enums;

import com.sdu.fund.core.model.trade.enums.PurchaseStatusEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:20
 **/
public enum TokenTypeEnum {

    LOGIN("LOGIN", "登录"),;

    private final String code;
    private final String msg;
    TokenTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static TokenTypeEnum getEnumByCode(String code) {
        for(TokenTypeEnum e: TokenTypeEnum.values()){
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
