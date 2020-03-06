package com.sdu.fund.biz.shared.service;

import com.sdu.fund.biz.shared.request.*;
import com.sdu.fund.biz.shared.vo.*;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public interface PurchaseService {

    public PurchaseApplyCheckVO applyCheck(WeChatPurchaseApplyCheckRequest weChatPurchaseApplyCheckRequest) throws Exception;

    public PurchasePreApplyVO preApply(WeChatPurchasePreApplyOrderRequest weChatPurchasePreApplyRequest) throws Exception;

    public PurchaseApplyVO apply(WeChatPurchaseApplyOrderRequest weChatPurchaseApplyRequest) throws Exception;

    public PayVO pay(WeChatPayOrderRequest weChatPayRequest) throws Exception;

    public PayCannelVO payCannel(WeChatPayCannelRequest weChatPayCannelRequest) throws Exception;
}
