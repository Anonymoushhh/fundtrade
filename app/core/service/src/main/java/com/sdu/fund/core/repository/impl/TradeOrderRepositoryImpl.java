package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtTradeOrderMapper;
import com.sdu.fund.common.dal.mapper.TradeOrderMapper;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.TradeOrderConverter;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.repository.TradeOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;


/**
 * @program: fundproduct
 * @description: 订单仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class TradeOrderRepositoryImpl implements TradeOrderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeOrderRepositoryImpl.class);

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Autowired
    private ExtTradeOrderMapper extTradeOrderMapper;

    @Override
    public TradeOrder get(String tradeOrderId) {
        Validator.notNull(tradeOrderId);
        try {
            TradeOrder tradeOrder =
                    TradeOrderConverter.TradeOrderDoconvert2TradeOrder(tradeOrderMapper.selectByPrimaryKey(tradeOrderId));
            return tradeOrder;
        } catch (DataAccessException e1) {
            LOGGER.error("订单查询失败，tradeOrderId={},errCode={}", tradeOrderId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单查询失败");
        } catch (Exception e2) {
            LOGGER.error("订单查询失败，tradeOrderId={},errCode={}", tradeOrderId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单查询失败");
        }
    }

    @Override
    public void add(TradeOrder tradeOrder) {
        try {
            preCheck(tradeOrder);
            int id = tradeOrderMapper.insertSelective(TradeOrderConverter.TradeOrderconvert2TradeOrderDo(tradeOrder));
            if (id <= 0) {
                LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getOrderId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("订单插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
            if (e1 instanceof DuplicateKeyException) {
                // 重复插入
                throw new DuplicateKeyException("订单重复插入");
            }
            throw new CommonException("订单插入失败");
        } catch (Exception e2) {
            LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getOrderId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单插入失败");
        }
    }

    @Override
    public void update(TradeOrder tradeOrder) {
        try {
            preCheck(tradeOrder);
            int count =
                    tradeOrderMapper.updateByPrimaryKeySelective(TradeOrderConverter.TradeOrderconvert2TradeOrderDo(tradeOrder));
            if (count <= 0) {
                LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getOrderId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("订单更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单更新失败");
        } catch (Exception e2) {
            LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getOrderId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单更新失败");
        }
    }

    @Override
    public void delete(String tradeOrderId) {
        try {
            int count = tradeOrderMapper.deleteByPrimaryKey(tradeOrderId);
            if (count <= 0) {
                LOGGER.error("订单删除失败，tradeOrderId={},errCode={}", tradeOrderId,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("订单删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单删除失败，tradeOrderId={},errCode={}", tradeOrderId,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单删除失败");
        } catch (Exception e2) {
            LOGGER.error("订单删除失败，tradeOrderId={},errCode={}", tradeOrderId,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(TradeOrder tradeOrder) {
        Validator.notNull(tradeOrder);
        Validator.notNull(tradeOrder.getOrderId());
    }

    @Override
    public TradeOrder lock(String orderId) {
        Validator.notNull(orderId);
        try {
            return TradeOrderConverter.TradeOrderDoconvert2TradeOrder(extTradeOrderMapper.lockByPrimaryKey(orderId));
        } catch (DataAccessException e1) {
            LOGGER.error("订单锁定失败，tradeOrderId={},errCode={}", orderId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单锁定失败");
        } catch (Exception e2) {
            LOGGER.error("订单锁定失败，tradeOrderId={},errCode={}", orderId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单锁定失败");
        }
    }

    @Override
    public TradeOrder getByIdempotentId(String idempotentId) {
        Validator.notNull(idempotentId);
        try {
            return TradeOrderConverter.TradeOrderDoconvert2TradeOrder(extTradeOrderMapper.selectByIdempotentId(idempotentId));
        } catch (DataAccessException e1) {
            LOGGER.error("通过幂等号查询订单失败，idempotentId={},errCode={},msg={}", idempotentId, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("通过幂等号查询订单失败");
        } catch (Exception e2) {
            LOGGER.error("通过幂等号查询订单失败，idempotentId={},errCode={},msg={}", idempotentId, ResultCode.SERVER_EXCEPTION,
                    e2.getMessage());
            throw new CommonException("通过幂等号查询订单失败");
        }
    }
}
