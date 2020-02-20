package com.sdu.fund.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Random;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/15 17:53
 **/
public class PayOrderIdUtil {


    public static String genPayOrderId(String orderId, long payOrderTime, String payChannel) {
        StringBuilder payOrderId = new StringBuilder();
        Date payOrderDate = new Date(payOrderTime);
        payOrderId.append(DateUtil.dateToStr(payOrderDate, DateUtil.FMT_YMDHMS2));
        payOrderId.append(payChannel);
        // 1000-9999随机数
        payOrderId.append(new Random().nextInt(9000) + 1000);
        payOrderId.append(StringUtils.substring(orderId,16,20));
        return payOrderId.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(genPayOrderId("202002182058150025873360", System.currentTimeMillis(), "00"));
        }

    }
}
