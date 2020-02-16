package com.sdu.fund.biz.shared.service;

import com.sdu.fund.core.model.account.bo.WeChatSessionInfo;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 17:46
 **/
public interface WeChatApiService {

    public WeChatSessionInfo getSessionInfo(String code);
}
