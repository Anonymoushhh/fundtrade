package com.sdu.fund.core.repository;

import com.sdu.fund.core.model.trade.bo.Payment;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 13:26
 **/
public interface PaymentRepository extends Repository<Payment,String> {

    public Payment lock(String payOrderId);

    public Payment getByOrderId(String orderId);
}
