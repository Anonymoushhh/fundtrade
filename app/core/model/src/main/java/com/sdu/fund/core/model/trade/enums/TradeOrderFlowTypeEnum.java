package com.sdu.fund.core.model.trade.enums;

import org.apache.commons.lang3.StringUtils;

public enum TradeOrderFlowTypeEnum {

    /**
     * 订单状态机  WAIT_CONFIRM_ACTION暂未用到（无认购业务）
     * INIT（用户下单）---用户支付---> PAIED -----------------系统接受订单------------> WAIT_CONFIRM_SHARE
     *   |                             ^                                                      |
     *   |                             | （用户延时支付）                                       |
     *   |                             |                                                      |
     *    --------用户未支付-----> WAIT_PAY ---用户取消或超时--> CANCEL <--用户取消（T日15点前）--  --系统确认-->  CONFIRM
     *                                                                                                （系统完单）/
     *                                                                                                       FINISHED
     */
    USER_ORDER("USER_ORDER", "用户下单"),
    USER_PAY("USER_PAY", "用户支付"),
    USER_NO_PAY("USER_NO_PAY", "用户未支付"),
    USER_CANCEL_OR_TIMEOUT("USER_CANCEL_OR_TIMEOUT", "用户取消或超时"),
    USER_CACEL("USER_CACEL", "用户取消"),
    USER_DELAY_PAY("USER_DELAY_PAY", "用户延时支付"),
    SYSTEM_RECEIVE_ORDER("SYSTEM_RECEIVE_ORDER", "系统接受订单"),
    SYSTEM_CONFIRM("SYSTEM_CONFIRM", "系统确认"),
    SYSTEM_FINFISH("SYSTEM_FINFISH", "系统完单"),;

    private final String code;
    private final String msg;
    TradeOrderFlowTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static TradeOrderFlowTypeEnum getEnumByCode(String code) {
        for(TradeOrderFlowTypeEnum e: TradeOrderFlowTypeEnum.values()){
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