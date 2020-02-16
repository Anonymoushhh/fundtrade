package com.sdu.fund.core.model.account.enums;

import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:21
 **/
public enum AuthorityEnum {

    ADMINISTRATORS("ADMINISTRATORS", "管理员"),
    INTEST("INTEST", "内测用户"),
    CONSUMER("CONSUMER", "普通用户"),
    TOURIST("TOURIST", "游客"),;
    private final String code;
    private final String msg;
    AuthorityEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static AuthorityEnum getEnumByCode(String code) {
        for(AuthorityEnum e: AuthorityEnum.values()){
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
