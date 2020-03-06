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

    private static final int retryCount = 3;

    @Override
    public WeChatSessionInfo getSessionInfo(String code) {
        try {
            // 这个辣鸡接口时不时就超时，默认超时重试三次
            String response = null;
            for (int i = 0; i < retryCount; i++) {
                try {
                    response =
                            HttpUtil.sendHTTPs("https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatAppInfo.APP_ID +
                                    "&secret=" + WeChatAppInfo.APP_SECRET + "&js_code=" + code + "&grant_type" +
                                    "=authorization_code");
                    break;
                } catch (Exception e) {
                    LOG.info("微信登陆session辣鸡接口超时了,code={},重试次数={}", code, i + 1);
                }
            }
            if(response == null){
                throw new CommonException("登录超时");
            }
            JSONObject sessionJsonObj = JSONObject.parseObject(response);
            if(sessionJsonObj.getString("errcode")!=null){
                // code错误
                throw new CommonException("登录失败");
            }

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
