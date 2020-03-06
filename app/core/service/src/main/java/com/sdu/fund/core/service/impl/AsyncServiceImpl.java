package com.sdu.fund.core.service.impl;

import com.sdu.fund.common.utils.SnowflakeIdUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.trade.bo.FundData;
import com.sdu.fund.core.model.trade.bo.HoldPosition;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.repository.FundDataRepository;
import com.sdu.fund.core.repository.HoldPositionRepository;
import com.sdu.fund.core.repository.TradeOrderRepository;
import com.sdu.fund.core.request.AsyncUpdateHoldPositonRequest;
import com.sdu.fund.core.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

/**
 * @program: fundtrade
 * @description: 异步微服务
 * @author: anonymous
 * @create: 2020/3/3 18:01
 **/
public class AsyncServiceImpl implements AsyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Autowired
    private HoldPositionRepository holdPositionRepository;

    @Autowired
    private FundDataRepository fundDataRepository;

    @Autowired
    private TradeOrderRepository tradeOrderRepository;

    @Override
    @Async("holdPositonUpdateTaskExecutor")
    public void holdPositonUpdate(AsyncUpdateHoldPositonRequest asyncUpdateHoldPositonRequest) {
        try {
            Long startTime = System.currentTimeMillis();
            LOGGER.info("holdPositonUpdateTask thread start,threadName={},startTime={}",
                    Thread.currentThread().getName(), startTime);
            TradeOrder tradeOrder = tradeOrderRepository.get(asyncUpdateHoldPositonRequest.getTradeOrderId());
            if (tradeOrder != null) {
                HoldPosition holdPosition = new HoldPosition();
                // 并发插入唯一索引冲突就忽略
                FundData fundData = fundDataRepository.get(tradeOrder.getFundCode());
                holdPosition.setId(SnowflakeIdUtil.getInstance().nextId());
                holdPosition.setUserId(tradeOrder.getUserId());
                holdPosition.setFundCode(tradeOrder.getFundCode());
                holdPosition.setFundName(tradeOrder.getFundName());
                holdPosition.setFundType(fundData.getFundType());
                holdPosition.setHoldAmount(tradeOrder.getOrderAmount());
                holdPosition.setWaitConfirmAmount(tradeOrder.getOrderAmount());
                holdPosition.setValid(true);
                holdPositionRepository.addIgnore(holdPosition);

            }

            Long endTime = System.currentTimeMillis();
            LOGGER.info("holdPositonUpdateTask thread end,threadName={},endTime={},costTime={}",
                    Thread.currentThread().getName(), endTime, endTime - startTime);
        } catch (Exception e) {
            LOGGER.error("异步更新持仓执行失败,tradeOrderId={},msg={}", asyncUpdateHoldPositonRequest.getTradeOrderId(),
                    e.getMessage());
        }
    }
}
