package com.sdu.fund.core.repository;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.mapper.TradeOrderMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.TradeOrderConverter;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


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


    @Override
    public TradeOrder get(String tradeOrderId) {
        Validator.notNull(tradeOrderId);
        return TradeOrderConverter.TradeOrderDoconvert2TradeOrder(tradeOrderMapper.selectByPrimaryKey(tradeOrderId));
    }

    @Override
    public Result add(TradeOrder tradeOrder) {
        // 预校验
        boolean check = preCheck(tradeOrder);
        if (!check) {
            LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id = tradeOrderMapper.insert(TradeOrderConverter.TradeOrderconvert2TradeOrderDo(tradeOrder));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单插入失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(TradeOrder tradeOrder) {
        // 预校验
        boolean check = preCheck(tradeOrder);
        if (!check) {
            LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                tradeOrderMapper.updateByPrimaryKeySelective(TradeOrderConverter.TradeOrderconvert2TradeOrderDo(tradeOrder));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单更新失败，tradeOrderId={},errCode={}", tradeOrder.getTradeOrderId(),
                ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(String tradeOrderId) {
        try {
            int count = tradeOrderMapper.deleteByPrimaryKey(tradeOrderId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("订单删除失败，tradeOrderId={},errCode={}", tradeOrderId,
                    ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单删除失败，tradeOrderId={},errCode={}", tradeOrderId,
                ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单删除失败，tradeOrderId={},errCode={}", tradeOrderId,
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
    private boolean preCheck(TradeOrder tradeOrder) {
        return Validator.notNull(tradeOrder) && Validator.notNull(tradeOrder.getTradeOrderId());
    }
    
}
