package com.sdu.fund.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.utils.HttpUtil;
import com.sdu.fund.core.model.account.bo.WeChatSessionInfo;
import com.sdu.fund.core.model.account.constant.WeChatAppInfo;
import com.sdu.fund.core.service.WeChatApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatApiServiceImpl implements WeChatApiService {

    private static final Logger LOG = LoggerFactory.getLogger(WeChatApiServiceImpl.class);

    @Override
    public WeChatSessionInfo getSessionInfo(String code) {
        try {
            String response =
                    HttpUtil.sendHTTPs("https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatAppInfo.APP_ID +
                            "&secret=" + WeChatAppInfo.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code");


            JSONObject sessionJsonObj = JSONObject.parseObject(response);
            LOG.info("#WeChatApiService.getSessionInfo code:{} rlt:{}", code, sessionJsonObj.toString());
            WeChatSessionInfo weChatSessionInfo = new WeChatSessionInfo();
            weChatSessionInfo.setOpenId(sessionJsonObj.getString("openid"));
            weChatSessionInfo.setSessionKey(sessionJsonObj.getString("session_key"));

            return weChatSessionInfo;
        } catch (Exception e) {
            LOG.error("#WeChatApiService.getSessionInfo ERROR", e);
            throw new CommonException(e.getMessage());
        }

    }

}
