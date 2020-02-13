package com.sdu.fund.biz.shared.enums;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/6 18:21
 **/
public enum GainTypeEnum {

    /* 日增长率*/
    GROWTHRATE(0,"growth_rate"),
    /* 近1周收益率*/
    EARNINGRATE1W(1,"earning_rate_1w"),
    /* 近1月收益率*/
    EARNINGRATE1M(2,"earning_rate_1m"),
    /* 近3月收益率*/
    EARNINGRATE3M(3,"earning_rate_3m"),
    /* 近6月收益率*/
    EARNINGRATE6M(4,"earning_rate_6m"),
    /* 近1年收益率*/
    EARNINGRATE1Y(5,"earning_rate_1y"),
    /* 近2年收益率*/
    EARNINGRATE2Y(6,"earning_rate_2y"),
    /* 近3年收益率*/
    EARNINGRATE3Y(7,"earning_rate_3y"),
    /* 今年收益率*/
    EARNINGRATETHISYEAR(8,"earning_rate_this_year"),
    /* 成立以来收益率*/
    EARNINGRATEFROMESTABLISH(9,"earning_rate_from_establish");
    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    GainTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static GainTypeEnum getEnumByCode(Integer code) {
        for(GainTypeEnum e:GainTypeEnum.values()){
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
