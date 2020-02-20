package com.sdu.fund.common.dal.extMapper;

import com.sdu.fund.common.dal.entity.PaymentDo;

public interface ExtPaymentMapper {

    /**
        行级锁
     */
    PaymentDo lockByPrimaryKey(String payOrderId);


}