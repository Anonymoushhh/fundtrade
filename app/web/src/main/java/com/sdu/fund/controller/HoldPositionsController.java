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
import com.sdu.fund.biz.shared.query.service.HoldPositionQueryService;
import com.sdu.fund.biz.shared.request.WeChatLoginRequest;
import com.sdu.fund.biz.shared.service.UserService;
import com.sdu.fund.biz.shared.vo.HoldPositionsVO;
import com.sdu.fund.biz.shared.vo.Response;
import com.sdu.fund.biz.shared.vo.UserLoginVO;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.account.enums.GenderEnum;
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
@RequestMapping("/fundTrade")
public class HoldPositionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HoldPositionsController.class);

    @SofaReference
    private HoldPositionQueryService holdPositionQueryService;

    @SofaReference
    private UserContext userContext;

    @RequestMapping(value = "/holdPositions", method = RequestMethod.GET)
    public Response<HoldPositionsVO> queryHoldPositions() {
        User user = userContext.getCurrentUser();
        try {
            Validator.notNull(user);
            HoldPositionsVO holdPositionsVO =
                    holdPositionQueryService.queryHoldPosition(user.getUserId());
            return Response.buildSuccessResponse(holdPositionsVO);
        } catch (Exception e) {
            LOGGER.error("查询持仓失败，userId={},msg={}", user.getUserId(), e.getMessage());
            return Response.buildErrorResponse();
        }
    }
}