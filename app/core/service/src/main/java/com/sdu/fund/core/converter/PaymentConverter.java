package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.PaymentDo;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.enums.PayChannelEnum;
import com.sdu.fund.core.model.trade.enums.PayStatusEnum;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 19:50
 **/
public class PaymentConverter {

    public static Payment PaymentDoconvert2Payment(PaymentDo paymentDo){
        if(paymentDo == null){
            return null;
        }
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDo,payment,"payChannel", "payStatus");
        payment.setPayChannel(PayChannelEnum.getEnumByCode(paymentDo.getPayChannel()));
        payment.setPayStatus(PayStatusEnum.getEnumByCode(paymentDo.getPayStatus()));

        return payment;
    }

    public static PaymentDo Paymentconvert2PaymentDo(Payment payment){
        if(payment == null){
            return null;
        }
        PaymentDo paymentDo = new PaymentDo();
        BeanUtils.copyProperties(payment,paymentDo,"payChannel", "payStatus");
        paymentDo.setPayChannel(payment.getPayChannel().getCode());
        paymentDo.setPayStatus(payment.getPayStatus().getCode());

        return paymentDo;
    }
}
