package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtTradeOrderFlowMapper;
import com.sdu.fund.common.dal.mapper.TradeOrderFlowMapper;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.ResultUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.converter.TradeOrderFlowConverter;
import com.sdu.fund.core.model.trade.bo.TradeOrderFlow;
import com.sdu.fund.core.repository.TradeOrderFlowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


/**
 * @program: fundproduct
 * @description: 订单流水仓储层
 * @author: anonymous
 * @create: 2019-11-28 23:21
 **/
public class TradeOrderFlowRepositoryImpl implements TradeOrderFlowRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeOrderFlowRepositoryImpl.class);

    @Autowired
    private TradeOrderFlowMapper tradeOrderFlowMapper;

    @Autowired
    private ExtTradeOrderFlowMapper extTradeOrderFlowMapper;

    @Override
    public Result<TradeOrderFlow> get(Long flowId) {
        Validator.notNull(flowId);
        try {
            TradeOrderFlow tradeOrderFlow =
                    TradeOrderFlowConverter.TradeOrderFlowDoconvert2TradeOrderFlow(tradeOrderFlowMapper.selectByPrimaryKey(flowId));
            return ResultUtil.buildSuccessResult(tradeOrderFlow);
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水查询失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单流水查询失败，flowId={},errCode={}", flowId, ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result add(TradeOrderFlow tradeOrderFlow) {
        // 预校验
        boolean check = preCheck(tradeOrderFlow);
        if (!check) {
            LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int id =
                    tradeOrderFlowMapper.insertSelective(TradeOrderFlowConverter.TradeOrderFlowconvert2TradeOrderFlowDo(tradeOrderFlow));
            if (id > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result update(TradeOrderFlow tradeOrderFlow) {
        // 预校验
        boolean check = preCheck(tradeOrderFlow);
        if (!check) {
            LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.PARAMETER_ILLEGAL);
            return ResultUtil.buildFailedResult(ResultCode.PARAMETER_ILLEGAL);
        }

        try {
            int count =
                    tradeOrderFlowMapper.updateByPrimaryKeySelective(TradeOrderFlowConverter.TradeOrderFlowconvert2TradeOrderFlowDo(tradeOrderFlow));
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.SERVER_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.SERVER_EXCEPTION);
        }
    }

    @Override
    public Result delete(Long flowId) {
        try {
            int count = tradeOrderFlowMapper.deleteByPrimaryKey(flowId);
            if (count > 0) {
                return ResultUtil.buildSuccessResult();
            } else {
                LOGGER.error("订单流水删除失败，flowId={},errCode={}", flowId,
                        ResultCode.DATABASE_EXCEPTION);
                return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水删除失败，flowId={},errCode={}", flowId,
                    ResultCode.DATABASE_EXCEPTION);
            return ResultUtil.buildFailedResult(ResultCode.DATABASE_EXCEPTION);
        } catch (Exception e2) {
            LOGGER.error("订单流水删除失败，flowId={},errCode={}", flowId,
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
    private boolean preCheck(TradeOrderFlow tradeOrderFlow) {
        return Validator.notNull(tradeOrderFlow) && Validator.notNull(tradeOrderFlow.getFlowId());
    }

    @Override
    public TradeOrderFlow lock(Long flowId) {
        return TradeOrderFlowConverter.TradeOrderFlowDoconvert2TradeOrderFlow(extTradeOrderFlowMapper.lockByPrimaryKey(flowId));
    }
}
