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
public class TradeOrderIdUtil {


    public static String genTradeOrderId(Long userId, long tradeOrderTime, String tradeOrderChannel) {
        StringBuilder tradeOrderId = new StringBuilder();
        Date tradeOrderDate = new Date(tradeOrderTime);
        tradeOrderId.append(DateUtil.dateToStr(tradeOrderDate, DateUtil.FMT_YMDHMS2));
        tradeOrderId.append(tradeOrderChannel);
        // 1000-9999随机数
        tradeOrderId.append(new Random().nextInt(9000)+1000);
        tradeOrderId.append(StringUtils.substring(Long.toString(userId), 14));
        return tradeOrderId.toString();
    }

    public static void main(String[] args) {
        System.out.println(genTradeOrderId(678279205099663360L, System.currentTimeMillis(), "00"));
    }
}
