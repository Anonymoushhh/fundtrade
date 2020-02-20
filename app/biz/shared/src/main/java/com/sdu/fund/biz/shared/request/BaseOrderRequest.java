package com.sdu.fund.biz.shared.request;

/**
 * @program: fundtrade
 * @description: 订单操作请求基类
 * @author: anonymous
 * @create: 2020/2/18 18:29
 **/
public class BaseOrderRequest {

    // 订单类型
    private String orderType;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
