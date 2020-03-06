package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtPaymentMapper;
import com.sdu.fund.common.dal.mapper.PaymentMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.PaymentConverter;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;


/**
 * @program: fundproduct
 * @description: 支付单仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class PaymentRepositoryImpl implements PaymentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRepositoryImpl.class);

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private ExtPaymentMapper extPaymentMapper;

    @Override
    public Payment get(String payOrderId) {
        Validator.notNull(payOrderId);
        try {
            Payment payment =
                    PaymentConverter.PaymentDoconvert2Payment(paymentMapper.selectByPrimaryKey(payOrderId));
            return payment;
        } catch (DataAccessException e1) {
            LOGGER.error("支付单查询失败，payOrderId={},errCode={}", payOrderId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("支付单查询失败");
        } catch (Exception e2) {
            LOGGER.error("支付单查询失败，payOrderId={},errCode={}", payOrderId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("支付单查询失败");
        }
    }

    @Override
    public void add(Payment payment) {
        try {
            preCheck(payment);
            int id = paymentMapper.insertSelective(PaymentConverter.Paymentconvert2PaymentDo(payment));
            if (id <= 0) {
                LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("支付单插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
            if (e1 instanceof DuplicateKeyException) {
                // 重复插入
                throw new DuplicateKeyException("支付单重复插入");
            }
            throw new CommonException("支付单插入失败");
        } catch (Exception e2) {
            LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.SERVER_EXCEPTION);
            throw new CommonException("支付单插入失败");
        }
    }

    @Override
    public void update(Payment payment) {
        try {
            preCheck(payment);
            int count =
                paymentMapper.updateByPrimaryKeySelective(PaymentConverter.Paymentconvert2PaymentDo(payment));
            if (count <= 0) {
                LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("支付单更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("支付单更新失败");
        } catch (Exception e2) {
            LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.SERVER_EXCEPTION);
            throw new CommonException("支付单更新失败");
        }
    }

    @Override
    public void delete(String payOrderId) {
        try {
            int count = paymentMapper.deleteByPrimaryKey(payOrderId);
            if (count <= 0) {
                LOGGER.error("支付单删除失败，payOrderId={},errCode={}", payOrderId,
                    ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("支付单删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("支付单删除失败，payOrderId={},errCode={}", payOrderId,
                ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("支付单删除失败");
        } catch (Exception e2) {
            LOGGER.error("支付单删除失败，payOrderId={},errCode={}", payOrderId,
                ResultCode.SERVER_EXCEPTION);
            throw new CommonException("支付单删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(Payment payment) {
        Validator.notNull(payment);
        Validator.notNull(payment.getPayOrderId());
    }

    @Override
    public Payment lock(String payOrderId) {
        Validator.notNull(payOrderId);
        try {
            return PaymentConverter.PaymentDoconvert2Payment(extPaymentMapper.lockByPrimaryKey(payOrderId));
        } catch (DataAccessException e1) {
            LOGGER.error("支付单锁定失败，payOrderId={},errCode={}", payOrderId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("支付单锁定失败");
        } catch (Exception e2) {
            LOGGER.error("支付单锁定失败，payOrderId={},errCode={}", payOrderId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("支付单锁定失败");
        }
    }

    @Override
    public Payment getByOrderId(String orderId) {
        Validator.notNull(orderId);
        try {
            return PaymentConverter.PaymentDoconvert2Payment(extPaymentMapper.selectByOrderId(orderId));
        } catch (DataAccessException e1) {
            LOGGER.error("根据订单号查支付单失败，orderId={},errCode={}", orderId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("支付单锁定失败");
        } catch (Exception e2) {
            LOGGER.error("根据订单号查支付单失败，orderId={},errCode={}", orderId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("支付单锁定失败");
        }
    }
}
