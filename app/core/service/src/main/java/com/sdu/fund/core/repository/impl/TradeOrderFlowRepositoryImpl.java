package com.sdu.fund.core.repository.impl;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.dal.extMapper.ExtTradeOrderFlowMapper;
import com.sdu.fund.common.dal.mapper.TradeOrderFlowMapper;
import com.sdu.fund.common.exception.CommonException;
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
    public TradeOrderFlow get(Long flowId) {
        Validator.notNull(flowId);
        try {
            TradeOrderFlow tradeOrderFlow =
                    TradeOrderFlowConverter.TradeOrderFlowDoconvert2TradeOrderFlow(tradeOrderFlowMapper.selectByPrimaryKey(flowId));
            return tradeOrderFlow;
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水查询失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单流水查询失败");
        } catch (Exception e2) {
            LOGGER.error("订单流水查询失败，flowId={},errCode={}", flowId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单流水查询失败");
        }
    }

    @Override
    public void add(TradeOrderFlow tradeOrderFlow) {
        try {
            // 预校验
            preCheck(tradeOrderFlow);
            int id =
                    tradeOrderFlowMapper.insertSelective(TradeOrderFlowConverter.TradeOrderFlowconvert2TradeOrderFlowDo(tradeOrderFlow));
            if (id <= 0) {
                LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("订单流水插入失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单流水插入失败");
        } catch (Exception e2) {
            LOGGER.error("订单流水插入失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单流水插入失败");
        }
    }

    @Override
    public void update(TradeOrderFlow tradeOrderFlow) {
        try {
            // 预校验
            preCheck(tradeOrderFlow);
            int count =
                    tradeOrderFlowMapper.updateByPrimaryKeySelective(TradeOrderFlowConverter.TradeOrderFlowconvert2TradeOrderFlowDo(tradeOrderFlow));
            if (count <= 0) {
                LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("订单流水更新失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单流水更新失败");
        } catch (Exception e2) {
            LOGGER.error("订单流水更新失败，flowId={},errCode={}", tradeOrderFlow.getFlowId(),
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单流水更新失败");
        }
    }

    @Override
    public void delete(Long flowId) {
        try {
            int count = tradeOrderFlowMapper.deleteByPrimaryKey(flowId);
            if (count <= 0) {
                LOGGER.error("订单流水删除失败，flowId={},errCode={}", flowId,
                        ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("订单流水删除失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水删除失败，flowId={},errCode={}", flowId,
                    ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单流水删除失败");
        } catch (Exception e2) {
            LOGGER.error("订单流水删除失败，flowId={},errCode={}", flowId,
                    ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单流水删除失败");
        }
    }

    /*
     * @description 预校验
     * @param [fundCompany]
     * @return boolean
     * @author anonymous
     * @date 2019/11/29
     */
    private void preCheck(TradeOrderFlow tradeOrderFlow) {
        Validator.notNull(tradeOrderFlow);
        Validator.notNull(tradeOrderFlow.getFlowId());
    }

    @Override
    public TradeOrderFlow lock(Long flowId) {
        Validator.notNull(flowId);
        try {
            return TradeOrderFlowConverter.TradeOrderFlowDoconvert2TradeOrderFlow(extTradeOrderFlowMapper.lockByPrimaryKey(flowId));
        } catch (DataAccessException e1) {
            LOGGER.error("订单流水锁定失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
            throw new CommonException("订单流水锁定失败");
        } catch (Exception e2) {
            LOGGER.error("订单流水锁定失败，flowId={},errCode={}", flowId, ResultCode.SERVER_EXCEPTION);
            throw new CommonException("订单流水锁定失败");
        }
    }

    @Override
    public TradeOrderFlow getByIdempotentId(String idempotentId) {
        Validator.notNull(idempotentId);
        try {
            return TradeOrderFlowConverter.TradeOrderFlowDoconvert2TradeOrderFlow(extTradeOrderFlowMapper.selectByIdempotentId(idempotentId));
        } catch (DataAccessException e1) {
            LOGGER.error("幂等号查询订单流水失败，idempotentId={},errCode={},msg={}", idempotentId, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("幂等号查询订单流水失败");
        } catch (Exception e2) {
            LOGGER.error("幂等号查询订单流水失败，idempotentId={},errCode={},msg={}", idempotentId, ResultCode.SERVER_EXCEPTION,
                    e2.getMessage());
            throw new CommonException("幂等号查询订单流水失败");
        }
    }

    @Override
    public void makeInValid(Long flowId) {
        Validator.notNull(flowId);
        try {
            int count =
                    extTradeOrderFlowMapper.makeInValid(flowId);
            if (count <= 0) {
                LOGGER.error("使订单流水非法失败，flowId={},errCode={}", flowId, ResultCode.DATABASE_EXCEPTION);
                throw new CommonException("使订单流水非法失败");
            }
        } catch (DataAccessException e1) {
            LOGGER.error("使订单流水非法失败，flowId={},errCode={},msg={}", flowId, ResultCode.DATABASE_EXCEPTION,
                    e1.getMessage());
            throw new CommonException("使订单流水非法失败");
        } catch (Exception e2) {
            LOGGER.error("使订单流水非法失败，flowId={},errCode={},msg={}", flowId, ResultCode.SERVER_EXCEPTION, e2.getMessage());
            throw new CommonException("使订单流水非法失败");
        }
    }
}
