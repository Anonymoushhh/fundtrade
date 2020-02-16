package com.sdu.fund.core.model.enums;

import org.apache.commons.lang3.StringUtils;

public enum OrderStatusEnum {


    INIT("INIT", "创建订单"),
    WAIT_PAY("WAIT_PAY", "待支付"),
    PAIED("PAIED", "已支付"),
    WAIT_CONFIRM_SHARE("WAIT_CONFIRM_SHARE", "待确认份额"),
    WAIT_CONFIRM_ACTION("WAIT_CONFIRM_ACTION", "待确认行为"),
    WAIT_ARRIVAL("WAIT_ARRIVAL", "待到账"),
    FINISHED("FINISHED", "订单已完成"),
    CANCEL("CANCEL", "订单已取消");

    private final String code;
    private final String msg;
    OrderStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static OrderStatusEnum getEnumByCode(String code) {
        for(OrderStatusEnum e: OrderStatusEnum.values()){
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