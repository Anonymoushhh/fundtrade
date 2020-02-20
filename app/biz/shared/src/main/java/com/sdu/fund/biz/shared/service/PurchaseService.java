package com.sdu.fund.biz.shared.service;

import com.sdu.fund.biz.shared.request.WeChatPayOrderRequest;
import com.sdu.fund.biz.shared.request.WeChatPurchaseApplyOrderRequest;
import com.sdu.fund.biz.shared.vo.PayVO;
import com.sdu.fund.biz.shared.vo.PurchaseApplyVO;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public interface PurchaseService {

    public PurchaseApplyVO apply(WeChatPurchaseApplyOrderRequest weChatPurchaseApplyRequest) throws Exception;

    public PayVO pay(WeChatPayOrderRequest weChatPayRequest) throws Exception;

}
