package com.sdu.fund.core.model.account.enums;

import com.sdu.fund.core.model.trade.enums.TradeOrderStatusEnum;
import com.sdu.fund.core.model.trade.enums.PurchaseStatusEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:20
 **/
public enum GenderEnum {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女"),;
    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    GenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static GenderEnum getEnumByCode(Integer code) {
        for(GenderEnum e: GenderEnum.values()){
            if(Integer.valueOf(e.getCode()).equals(code)){
                return e;
            }
        }
        return null;
    }

    public static PurchaseStatusEnum getEnumByMsg(String msg) {
        for(PurchaseStatusEnum e: PurchaseStatusEnum.values()){
            if(StringUtils.equals(msg,e.getMsg())){
                return e;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
