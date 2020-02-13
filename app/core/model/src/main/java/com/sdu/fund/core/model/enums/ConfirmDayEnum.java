package com.sdu.fund.core.model.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/6 17:46
 **/
public enum ConfirmDayEnum {

    //
    T0(0,"T+0"),
    //
    T1(1,"T+1"),
    //
    T2(2,"T+2"),
    //
    T3(3,"T+3"),;

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    ConfirmDayEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ConfirmDayEnum getEnumByCode(Integer code) {
        for(ConfirmDayEnum e: ConfirmDayEnum.values()){
            if(Integer.valueOf(e.getCode()).equals(code)){
                return e;
            }
        }
        return null;
    }

    public static ConfirmDayEnum getEnumByMsg(String msg) {
        for(ConfirmDayEnum e: ConfirmDayEnum.values()){
            if(StringUtils.equals(msg,e.getMsg())){
                return e;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static void main(String[] args) {
        ConfirmDayEnum confirmDayEnum = getEnumByMsg("T+1");
        int i = 1;
        i++;
    }
}
