package com.sdu.fund.core.model.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/6 17:46
 **/
public enum PurchaseStatusEnum {

    //
    OPEN_PURCHASE(0,"开放申购"),
    //
    ONLY_LARGE(1,"限大额"),
    //
    STOP_PURCHASE(2,"暂停申购"),;

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    PurchaseStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static PurchaseStatusEnum getEnumByCode(Integer code) {
        for(PurchaseStatusEnum e: PurchaseStatusEnum.values()){
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
