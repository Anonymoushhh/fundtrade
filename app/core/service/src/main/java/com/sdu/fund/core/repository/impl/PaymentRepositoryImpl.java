package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtPaymentMapper;
import com.sdu.fund.common.dal.mapper.PaymentMapper;
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
    public Result<Payment> get(String payOrderId) {
        Validator.notNull(payOrderId);
        try {
            Payment payment =
                    PaymentConverter.PaymentDoconvert2Payment(paymentMapper.selectByPrimaryKey(payOrderId));
            return ResultUtil.buildSuccessResult(payment);
        } catch (DataAccessException e1) {
            LOGGER.error("支付单查询失败，payOrderId={},errCode={}", payOrderId, ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("支付单查询失败，payOrderId={},errCode={}", payOrderId, ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result add(Payment payment) {
        // 预校验
        boolean check = preCheck(payment);
        if (!check) {
            LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = paymentMapper.insertSelective(PaymentConverter.Paymentconvert2PaymentDo(payment));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("支付单插入失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(Payment payment) {
        // 预校验
        boolean check = preCheck(payment);
        if (!check) {
            LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                paymentMapper.updateByPrimaryKeySelective(PaymentConverter.Paymentconvert2PaymentDo(payment));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("支付单更新失败，payOrderId={},errCode={}", payment.getPayOrderId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(String payOrderId) {
        try {
            int count = paymentMapper.deleteByPrimaryKey(payOrderId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("支付单删除失败，payOrderId={},errCode={}", payOrderId,
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("支付单删除失败，payOrderId={},errCode={}", payOrderId,
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("支付单删除失败，payOrderId={},errCode={}", payOrderId,
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private boolean preCheck(Payment payment) {
        return Validator.notNull(payment) && Validator.notNull(payment.getPayOrderId());
    }

    @Override
    public Payment lock(String payOrderId) {
        return PaymentConverter.PaymentDoconvert2Payment(extPaymentMapper.lockByPrimaryKey(payOrderId));
    }
}
