package com.sdu.fund.biz.shared.serviceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.holder.UserContext;
import com.sdu.fund.biz.shared.request.WeChatPurchaseApplyRequest;
import com.sdu.fund.biz.shared.service.PurchaseService;
import com.sdu.fund.biz.shared.vo.PurchaseApplyVO;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.enums.TradeOrderTypeEnum;
import com.sdu.fund.core.service.PurchaseCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 18:11
 **/
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @SofaReference
    private PurchaseCoreService purchaseCoreService;

    @Autowired
    private UserContext userContext;

    @Override
    public PurchaseApplyVO apply(WeChatPurchaseApplyRequest weChatPurchaseApplyRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setUserId(currentUser.getUserId());
        tradeOrder.setFundCode(weChatPurchaseApplyRequest.getFundCode());
        tradeOrder.setFundName(weChatPurchaseApplyRequest.getFundName());
        tradeOrder.setTradeOrderType(TradeOrderTypeEnum.PURCHASE);
        tradeOrder.setTradeOrderAmount(new BigDecimal(weChatPurchaseApplyRequest.getTradeOrderAmount()));
        tradeOrder.setFee(new BigDecimal(weChatPurchaseApplyRequest.getFee()));
        tradeOrder.setFeeAmount(new BigDecimal(weChatPurchaseApplyRequest.getFeeAmount()));
        tradeOrder.setRealPayAmount(new BigDecimal(weChatPurchaseApplyRequest.getRealPayAmount()));
        // 校验订单参数
        Validator.noNullElements(tradeOrder.getUserId(), tradeOrder.getFundCode(), tradeOrder.getFundName(), tradeOrder.getTradeOrderAmount(),
                tradeOrder.getTradeOrderChannel(), tradeOrder.getPayChannel(), tradeOrder.getFee(),
                tradeOrder.getFeeAmount(),tradeOrder.getRealPayAmount());
        // 订单初始化
        tradeOrder.createTradeOrder();
        // 执行申购申请
        purchaseCoreService.apply(tradeOrder);
        return null;
    }
}
