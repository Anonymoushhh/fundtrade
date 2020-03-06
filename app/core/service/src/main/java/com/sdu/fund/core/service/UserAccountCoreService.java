package com.sdu.fund.core.service;

import com.sdu.fund.common.result.Result;
import com.sdu.fund.core.request.AccountFlowRequest;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/19 20:20
 **/
public interface UserAccountCoreService {

    /**
     * 出账
     */
    public Result accountPay(AccountFlowRequest accountFlowRequest);

    /**
     * 入账
     */
    public Result accountReceive();
}
