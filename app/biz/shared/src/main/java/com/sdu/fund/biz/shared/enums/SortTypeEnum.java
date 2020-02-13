package com.sdu.fund.biz.shared.enums;

/**
 * @program: fundproduct
 * @description: 查询用到的枚举
 * @author: anonymous
 * @create: 2020/2/6 12:20
 **/
public enum SortTypeEnum {

    NETVALUE(0,"净值排行"),
    INTELLIGENCE(1,"智能排行");

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    SortTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SortTypeEnum getEnumByCode(Integer code) {
        for(SortTypeEnum e:SortTypeEnum.values()){
            if(Integer.valueOf(e.getCode()).equals(code)){
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
