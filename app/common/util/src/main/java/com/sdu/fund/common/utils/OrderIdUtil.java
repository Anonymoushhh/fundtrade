package com.sdu.fund.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/15 17:53
 **/
public class OrderIdUtil {


    public static String genOrderId(Long userId,long orderTime,String orderChannel,String payChannel){
        StringBuilder orderId = new StringBuilder();
        Date orderDate = new Date(orderTime);
        orderId.append(DateUtil.dateToStr(orderDate,DateUtil.FMT_YMDHMS2));
        orderId.append(orderChannel);
        orderId.append(payChannel);
        orderId.append(StringUtils.substring(Long.toString(userId),14));
        return orderId.toString();
    }

    public static void main(String[] args) {
        System.out.println(genOrderId(678279205099663360L,System.currentTimeMillis(),"00","00"));
    }
}
