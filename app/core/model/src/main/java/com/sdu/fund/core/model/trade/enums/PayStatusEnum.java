package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:20
 **/
public enum PayStatusEnum {

    PAYING("PAYING", "支付中"),
    PAID("PAID", "已支付"),;

    private final String code;
    private final String msg;
    PayStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static PayStatusEnum getEnumByCode(String code) {
        for(PayStatusEnum e: PayStatusEnum.values()){
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
