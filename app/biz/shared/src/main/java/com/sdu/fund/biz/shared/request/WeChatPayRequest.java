package com.sdu.fund.biz.shared.request;

import com.sdu.fund.core.model.account.enums.PayChannelEnum;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:14
 **/
public class WeChatPayRequest {

    private String payTradeOrderId;

    private String tradeOrderId;

    private long userId;

    // 付款金额
    private String tradeOrderAmount;

    // 支付渠道
    private PayChannelEnum payChannel;

    // 支付状态
    private PayChannelEnum payStatus;

}
