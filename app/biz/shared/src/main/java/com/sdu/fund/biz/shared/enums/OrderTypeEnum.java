package com.sdu.fund.biz.shared.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description: 区分创单类型
 * @author: anonymous
 * @create: 2020/2/13 20:07
 **/
public enum OrderTypeEnum {


    PURCHASE("PURCHASE", "申购单"),
    PAY("PAY", "支付单"),
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