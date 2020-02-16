package com.sdu.fund.core.model.account.enums;

import com.sdu.fund.core.model.trade.enums.PurchaseStatusEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:20
 **/
public enum OrderChannelEnum {

    WECHAT("01", "微信小程序"),
    TEST("00", "影子流量"),;

    private final String code;
    private final String msg;
    OrderChannelEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static OrderChannelEnum getEnumByCode(String code) {
        for(OrderChannelEnum e: OrderChannelEnum.values()){
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
