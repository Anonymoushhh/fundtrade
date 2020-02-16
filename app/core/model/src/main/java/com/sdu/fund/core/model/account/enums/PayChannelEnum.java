package com.sdu.fund.core.model.account.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:20
 **/
public enum PayChannelEnum {

    WALLET("01", "钱包余额"),
    TEST("00", "影子流量"),;

    private final String code;
    private final String msg;
    PayChannelEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static PayChannelEnum getEnumByCode(String code) {
        for(PayChannelEnum e: PayChannelEnum.values()){
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
