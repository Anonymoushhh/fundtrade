package com.sdu.fund.core.model.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/6 17:46
 **/
public enum RedeemStatusEnum {

    //
    OPEN_REDEEM(0,"开放赎回"),
    //
    ONLY_LARGE(1,"限大额"),
    //
    STOP_REDEEM(2,"暂停赎回"),;

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    RedeemStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RedeemStatusEnum getEnumByCode(Integer code) {
        for(RedeemStatusEnum e: RedeemStatusEnum.values()){
            if(Integer.valueOf(e.getCode()).equals(code)){
                return e;
            }
        }
        return null;
    }

    public static RedeemStatusEnum getEnumByMsg(String msg) {
        for(RedeemStatusEnum e: RedeemStatusEnum.values()){
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
