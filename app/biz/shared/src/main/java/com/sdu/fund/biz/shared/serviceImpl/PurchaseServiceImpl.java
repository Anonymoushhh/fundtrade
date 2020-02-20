package com.sdu.fund.biz.shared.serviceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.enums.OrderTypeEnum;
import com.sdu.fund.biz.shared.factory.OrderCreateFactory;
import com.sdu.fund.biz.shared.holder.UserContext;
import com.sdu.fund.biz.shared.request.WeChatPayOrderRequest;
import com.sdu.fund.biz.shared.request.WeChatPurchaseApplyOrderRequest;
import com.sdu.fund.biz.shared.service.PurchaseService;
import com.sdu.fund.biz.shared.vo.PayVO;
import com.sdu.fund.biz.shared.vo.PurchaseApplyVO;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.trade.bo.FundData;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.repository.FundDataRepository;
import com.sdu.fund.core.service.PurchaseCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


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

    @SofaReference
    private FundDataRepository fundDataRepository;

    @Autowired
    private UserContext userContext;

    @Autowired
    private OrderCreateFactory orderCreateFactory;

    @Override
    public PurchaseApplyVO apply(WeChatPurchaseApplyOrderRequest weChatPurchaseApplyRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        // 创建订单
        TradeOrder tradeOrder = (TradeOrder) orderCreateFactory.getOrder(weChatPurchaseApplyRequest,
                currentUser.getUserId());
        // 校验订单参数
        Validator.noNullElements(tradeOrder.getUserId(), tradeOrder.getFundCode(), tradeOrder.getFundName(),
                tradeOrder.getTradeOrderAmount(),
                tradeOrder.getTradeOrderChannel(), tradeOrder.getPayChannel(), tradeOrder.getFee(),
                tradeOrder.getFeeAmount(), tradeOrder.getRealPayAmount());
        // TODO 规则校验器

        // 计算预计确认时间和预计查看收益时间
        Result<FundData> fundDataResult = fundDataRepository.get(weChatPurchaseApplyRequest.getFundCode());
        if (fundDataResult != null && fundDataResult.isSuccess() && fundDataResult.getData() != null) {
            FundData fundData = fundDataResult.getData();
            switch (fundData.getBuyConfirmDay()) {
                case T0:
                    tradeOrder.setPurchaseConfirmDay(DateUtil.getNextXDay(0));
                case T1:
                    tradeOrder.setPurchaseConfirmDay(DateUtil.getNextXDay(1));
                case T2:
                    tradeOrder.setPurchaseConfirmDay(DateUtil.getNextXDay(2));
                default:
                    throw new CommonException("没有该确认日类型！");
            }
        }
        // TODO 保存交易快照信息

        // 执行申购申请
        tradeOrder = purchaseCoreService.apply(tradeOrder);
        return PurchaseApplyVO.convert(tradeOrder);
    }

    @Override
    public PayVO pay(WeChatPayOrderRequest weChatPayRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        // 创建订单
        Payment payment = (Payment) orderCreateFactory.getOrder(weChatPayRequest, currentUser.getUserId());
        // 校验订单参数
        Validator.noNullElements(payment.getPayOrderId(), payment.getUserId(), payment.getOrderId(),
                payment.getOrderAmount(), payment.getPayChannel(), payment.getPayStatus());
        // 调用支付微服务
        payment = purchaseCoreService.pay(payment);
        return null;
    }
}
