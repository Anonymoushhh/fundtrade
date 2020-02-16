package com.sdu.fund.biz.shared.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.sdu.fund.core.service.WeChatApiService;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.core.model.account.bo.WeChatSessionInfo;
import com.sdu.fund.core.model.account.constant.WeChatAppInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatApiServiceImpl implements WeChatApiService {

    private static final Logger LOG = LoggerFactory.getLogger(WeChatApiServiceImpl.class);

    @Override
    public WeChatSessionInfo getSessionInfo(String code){
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet =
                    new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + WeChatAppInfo.APP_ID +
                            "&secret=" + WeChatAppInfo.APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000)
                    .setSocketTimeout(3000)
                    .setConnectionRequestTimeout(3000).build();// 连接主机服务超时时间
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            JSONObject sessionJsonObj = JSONObject.parseObject(EntityUtils.toString(httpEntity));
            LOG.info("#WeChatApiService.getSessionInfo code:{} rlt:{}",code, sessionJsonObj.toString());
            WeChatSessionInfo weChatSessionInfo = new WeChatSessionInfo();
            weChatSessionInfo.setOpenId(sessionJsonObj.getString("openid"));
            weChatSessionInfo.setSessionKey(sessionJsonObj.getString("session_key"));

            return weChatSessionInfo;
        }catch (Exception e){
            LOG.error("#WeChatApiService.getSessionInfo ERROR",e);
            throw new CommonException(e.getMessage());
        }

    }

}
