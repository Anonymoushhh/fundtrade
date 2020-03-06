package com.sdu.fund.biz.shared.enums;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/5 17:56
 **/

import org.apache.commons.lang3.StringUtils;

/**
 * 错误信息和说明
 */
public enum ErrorMsgEnum {

    PURCHASE_AMOUNT_IS_BELOW_THE_LIMIT("PURCHASE_AMOUNT_IS_BELOW_THE_LIMIT", "购买金额低于限额"),
    PURCHASE_AMOUNT_IS_HIGHER_THAN_THE_LIMIT("PURCHASE_AMOUNT_IS_HIGHER_THAN_THE_LIMIT", "购买金额高于单日限额"),
    INSUFFICIENT_AVAILABLE_BALANCE("INSUFFICIENT_AVAILABLE_BALANCE", "可用余额不足"),;

    private final String code;
    private final String msg;
    ErrorMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static ErrorMsgEnum getEnumByCode(String code) {
        for(ErrorMsgEnum e: ErrorMsgEnum.values()){
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
