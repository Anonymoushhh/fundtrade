/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sdu.fund.controller;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.context.UserContext;
import com.sdu.fund.biz.shared.request.*;
import com.sdu.fund.biz.shared.service.PurchaseService;
import com.sdu.fund.biz.shared.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qilong.zql
 * @since 2.5.8
 */
@RestController
@RequestMapping("/fundTrade/trade/purchase")
public class FundPurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundPurchaseController.class);

    @SofaReference
    private PurchaseService purchaseService;

    @SofaReference
    private UserContext userContext;

    @RequestMapping(value = "/preApply", method = RequestMethod.POST)
    public Response<PurchasePreApplyVO> preApply(@RequestBody WeChatPurchasePreApplyOrderRequest weChatPurchasePreApplyOrderRequest) {
        try {
            PurchasePreApplyVO purchasePreApplyVO = purchaseService.preApply(weChatPurchasePreApplyOrderRequest);
            return Response.buildSuccessResponse(purchasePreApplyVO);
        } catch (Exception e) {
            LOGGER.error("基金购买预申请失败，fundCode={},userId={},msg={}", weChatPurchasePreApplyOrderRequest.getFundCode(),
                    userContext.getCurrentUser().getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }

    @RequestMapping(value = "/applyCheck", method = RequestMethod.POST)
    public Response<PurchaseApplyCheckVO> applyCheck(@RequestBody WeChatPurchaseApplyCheckRequest weChatPurchaseApplyCheckRequest) {
        try {
            PurchaseApplyCheckVO purchaseApplyCheckVO = purchaseService.applyCheck(weChatPurchaseApplyCheckRequest);
            return Response.buildSuccessResponse(purchaseApplyCheckVO);
        } catch (Exception e) {
            LOGGER.error("用户申购申请金额校验失败，fundCode={},userId={},msg={}", weChatPurchaseApplyCheckRequest.getFundCode(),
                    userContext.getCurrentUser().getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Response<PurchaseApplyVO> apply(@RequestBody WeChatPurchaseApplyOrderRequest weChatPurchaseApplyRequest) {
        try {
            PurchaseApplyVO purchaseApplyVO = purchaseService.apply(weChatPurchaseApplyRequest);
            return Response.buildSuccessResponse(purchaseApplyVO);
        } catch (Exception e) {
            LOGGER.error("基金购买申请失败，fundCode={},userId={},msg={}", weChatPurchaseApplyRequest.getFundCode(),
                    userContext.getCurrentUser().getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public Response<PayVO> pay(@RequestBody WeChatPayOrderRequest weChatPayRequest) {
        try {
            PayVO payVO = purchaseService.pay(weChatPayRequest);
            return Response.buildSuccessResponse(payVO);
        } catch (Exception e) {
            LOGGER.error("基金购买支付失败，tradeOrderId={},userId={},msg={}", weChatPayRequest.getTradeOrderId(),
                    userContext.getCurrentUser().getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }
    @RequestMapping(value = "/payCannel", method = RequestMethod.POST)
    public Response<PayCannelVO> payCannel(@RequestBody WeChatPayCannelRequest weChatPayRequest) {
        try {
            PayCannelVO payCannelVO = purchaseService.payCannel(weChatPayRequest);
            return Response.buildSuccessResponse(payCannelVO);
        } catch (Exception e) {
            LOGGER.error("基金购买支付取消失败，tradeOrderId={},userId={},msg={}", weChatPayRequest.getTradeOrderId(),
                    userContext.getCurrentUser().getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }
}