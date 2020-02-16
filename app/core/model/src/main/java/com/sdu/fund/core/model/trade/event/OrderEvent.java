package com.sdu.fund.core.model.trade.event;

public enum TradeOrderEvent {

    PAY_SUCCESS("paySuccess","支付成功"),
    NO_PAY("noPay","未支付"),
    PAY_FAILURE("payFailure","支付失败"),
    CONFIRM_SHARE_SUCCESS("confirmShareSuccess","份额确认成功"),
    CONFIRM_SHARE_FAILURE("confirmShareFailure","份额确认失败"),;
    private String type;
    private String msg;

    TradeOrderEvent(String type, String msg) {
        this.setType(type);
        this.setMsg(msg);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}