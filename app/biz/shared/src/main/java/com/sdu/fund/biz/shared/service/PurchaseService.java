package com.sdu.fund.biz.shared.service;

import com.sdu.fund.biz.shared.request.WeChatPurchaseApplyRequest;
import com.sdu.fund.biz.shared.vo.PurchaseApplyVO;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public interface PurchaseService {

    public PurchaseApplyVO apply(WeChatPurchaseApplyRequest weChatPurchaseApplyRequest) throws Exception;

    public PurchaseApplyVO pay(WeChatPurchaseApplyRequest weChatPurchaseApplyRequest) throws Exception;

}
