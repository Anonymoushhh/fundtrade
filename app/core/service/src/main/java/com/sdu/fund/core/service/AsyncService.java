package com.sdu.fund.core.service;

import com.sdu.fund.core.request.AsyncUpdateHoldPositonRequest;

/**
 * @program: fundtrade
 * @description: 异步方法
 * @author: anonymous
 * @create: 2020/3/3 18:00
 **/
public interface AsyncService {

    public void holdPositonUpdate(AsyncUpdateHoldPositonRequest asyncUpdateHoldPositonRequest);

}
