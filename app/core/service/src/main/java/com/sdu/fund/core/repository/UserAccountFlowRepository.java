package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.account.bo.UserAccountFlow;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface UserAccountFlowRepository extends Repository<UserAccountFlow,Long> {

    public UserAccountFlow lock(Long flowId);

}
