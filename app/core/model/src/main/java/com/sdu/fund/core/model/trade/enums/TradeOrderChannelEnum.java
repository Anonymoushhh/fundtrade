package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:20
 **/
public enum TradeOrderChannelEnum {

    WECHAT("01", "微信小程序"),
    TEST("00", "影子流量"),;

    private final String code;
    private final String msg;
    TradeOrderChannelEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static TradeOrderChannelEnum getEnumByCode(String code) {
        for(TradeOrderChannelEnum e: TradeOrderChannelEnum.values()){
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
