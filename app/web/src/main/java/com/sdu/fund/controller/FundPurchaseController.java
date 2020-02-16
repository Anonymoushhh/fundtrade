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
import com.sdu.fund.biz.shared.holder.UserContext;
import com.sdu.fund.biz.shared.service.UserService;
import com.sdu.fund.biz.shared.vo.UserLoginVO;
import com.sdu.fund.biz.shared.request.WeChatPurchaseApplyRequest;
import com.sdu.fund.vo.Response;
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
@RequestMapping("/fundTrade/purchase")
public class FundPurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundPurchaseController.class);

    @SofaReference
    private UserService userService;

    @SofaReference
    private UserContext userContext;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Response<UserLoginVO> apply(@RequestBody WeChatPurchaseApplyRequest weChatPurchaseApplyRequest) {
        try {

            return Response.buildSuccessResponse();
        } catch (Exception e) {
            LOGGER.error("基金购买申请失败，fundCode={},userId={},msg={}", weChatPurchaseApplyRequest.getFundCode(),
                    userContext.getCurrentUser().getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }
}