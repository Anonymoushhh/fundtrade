package com.sdu.fund.common.enums;

/**
 * @program: fundtrade
 * @description: 请求方式枚举
 * @author: anonymous
 * @create: 2019-11-26 16:50
 **/
public enum RequestMethodEnum {

    GET(0, "get方法"),
    POST(1, "post方法");

    /* 枚举编码*/
    private Integer code;
    /* 枚举值描述*/
    private String  desc;

    RequestMethodEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static RequestMethodEnum getByCode(Integer code) {
        for (RequestMethodEnum requestMethodEnum : RequestMethodEnum.values()) {
            if (requestMethodEnum.getCode().equals(code)) {
                return requestMethodEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
