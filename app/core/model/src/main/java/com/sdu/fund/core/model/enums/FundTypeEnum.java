package com.sdu.fund.core.model.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/6 17:46
 **/
public enum FundTypeEnum {

    // 股票型
    SHARES(0,"股票型"),
    // 债券型
    BOND(1,"债券型"),
    // 货币型
    CURRENCY(2,"货币型"),
    // 混合型
    MIX(3,"混合型"),
    // 保本型
    CAPITAL(4,"保本型"),
    // 交易型开放式指数基金
    ETF(5,"ETF"),
    //
    QDII(6,"QDII"),
    // 分级基金
    STRUCTURED(7,"分级基金"),
    // fund of fund
    FOF(8,"FOF"),
    // 指数型
    INDEX(9,"指数型"),;
    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    FundTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static FundTypeEnum getEnumByCode(Integer code) {
        for(FundTypeEnum e:FundTypeEnum.values()){
            if(Integer.valueOf(e.getCode()).equals(code)){
                return e;
            }
        }
        return null;
    }

    public static FundTypeEnum getEnumByMsg(String msg) {
        for(FundTypeEnum e:FundTypeEnum.values()){
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
