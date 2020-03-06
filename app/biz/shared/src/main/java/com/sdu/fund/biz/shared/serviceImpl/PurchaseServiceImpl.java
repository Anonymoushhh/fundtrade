package com.sdu.fund.biz.shared.serviceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.sdu.fund.biz.shared.enums.ErrorMsgEnum;
import com.sdu.fund.biz.shared.factory.OrderCreateFactory;
import com.sdu.fund.biz.shared.context.UserContext;
import com.sdu.fund.biz.shared.request.*;
import com.sdu.fund.biz.shared.service.PurchaseService;
import com.sdu.fund.biz.shared.vo.*;
import com.sdu.fund.common.exception.CommonException;
import com.sdu.fund.common.result.Result;
import com.sdu.fund.common.utils.DateUtil;
import com.sdu.fund.common.utils.IdempotentIdUtil;
import com.sdu.fund.common.utils.NumberUtil;
import com.sdu.fund.common.validator.Validator;
import com.sdu.fund.core.model.account.bo.User;
import com.sdu.fund.core.model.account.bo.UserAccount;
import com.sdu.fund.core.model.trade.bo.FundData;
import com.sdu.fund.core.model.trade.bo.Payment;
import com.sdu.fund.core.model.trade.bo.Rate;
import com.sdu.fund.core.model.trade.bo.TradeOrder;
import com.sdu.fund.core.model.trade.enums.PayChannelEnum;
import com.sdu.fund.core.repository.FundDataRepository;
import com.sdu.fund.core.repository.UserAccountRepository;
import com.sdu.fund.core.request.AsyncUpdateHoldPositonRequest;
import com.sdu.fund.core.service.AsyncService;
import com.sdu.fund.core.service.PurchaseCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;


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

    @SofaReference
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserContext userContext;

    @Autowired
    private OrderCreateFactory orderCreateFactory;

    @SofaReference
    private AsyncService asyncService;

    @Override
    public PurchaseApplyVO apply(WeChatPurchaseApplyOrderRequest weChatPurchaseApplyRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        // 创建订单
        TradeOrder tradeOrder = (TradeOrder) orderCreateFactory.getOrder(weChatPurchaseApplyRequest,
                currentUser.getUserId());
        // 校验订单参数
        Validator.noNullElements(tradeOrder.getIdempotentId(), tradeOrder.getUserId(), tradeOrder.getFundCode(),
                tradeOrder.getFundName(), tradeOrder.getOrderAmount(), tradeOrder.getOrderChannel(),
                tradeOrder.getFee(), tradeOrder.getFeeAmount(), tradeOrder.getRealPayAmount());
        // TODO 规则校验器

        // 计算预计确认时间和预计查看收益时间
        FundData fundData = fundDataRepository.get(weChatPurchaseApplyRequest.getFundCode());
        if (fundData != null) {
            switch (fundData.getBuyConfirmDay()) {
                case T0:
                    tradeOrder.setPurchaseConfirmDay(DateUtil.getNextXDay(0));
                    break;
                case T1:
                    tradeOrder.setPurchaseConfirmDay(DateUtil.getNextXDay(1));
                    break;
                case T2:
                    tradeOrder.setPurchaseConfirmDay(DateUtil.getNextXDay(2));
                    break;
                default:
                    throw new CommonException("没有该确认日类型！");
            }
        } else {
            throw new CommonException("用户申购申请失败,未查询到基金数据！");
        }
        // TODO 保存交易快照信息

        // 执行申购申请
        Result<TradeOrder> result = purchaseCoreService.apply(tradeOrder);
        if (result != null && result.isSuccess() && result.getData() != null) {
            return PurchaseApplyVO.convert(result.getData());
        } else {
            throw new CommonException("用户申购申请失败！");
        }
    }

    @Override
    public PurchaseApplyCheckVO applyCheck(WeChatPurchaseApplyCheckRequest weChatPurchaseApplyCheckRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        // 校验参数
        Validator.noNullElements(weChatPurchaseApplyCheckRequest.getFundCode(),
                weChatPurchaseApplyCheckRequest.getOrderAmount());
        PurchaseApplyCheckVO purchaseApplyCheckVO = new PurchaseApplyCheckVO();
        purchaseApplyCheckVO.setFundCode(weChatPurchaseApplyCheckRequest.getFundCode());
        purchaseApplyCheckVO.setOrderAmount(weChatPurchaseApplyCheckRequest.getOrderAmount());
        // 计算相关金额
        FundData fundData = fundDataRepository.get(weChatPurchaseApplyCheckRequest.getFundCode());
        UserAccount userAccount = userAccountRepository.get(currentUser.getUserId());
        if (fundData != null) {
            Double orderAmount = Double.valueOf(weChatPurchaseApplyCheckRequest.getOrderAmount());
            Double purchaseLimitOneDay = fundData.getPurchaseLimitOneDay();
            if (orderAmount.compareTo(fundData.getMinPurchaseAmount()) < 0) {
                purchaseApplyCheckVO.setErrorMsg(ErrorMsgEnum.PURCHASE_AMOUNT_IS_BELOW_THE_LIMIT.getMsg());
                return purchaseApplyCheckVO;
            }
            if (purchaseLimitOneDay != null && orderAmount.compareTo(purchaseLimitOneDay) > 0) {
                purchaseApplyCheckVO.setErrorMsg(ErrorMsgEnum.PURCHASE_AMOUNT_IS_HIGHER_THAN_THE_LIMIT.getMsg());
                return purchaseApplyCheckVO;
            }
            if (new BigDecimal(orderAmount).compareTo(userAccount.getAvailAmount()) > 0) {
                purchaseApplyCheckVO.setErrorMsg(ErrorMsgEnum.INSUFFICIENT_AVAILABLE_BALANCE.getMsg());
                return purchaseApplyCheckVO;
            }
            // TODO 追加限额，首次购买限额，持仓限额
            List<Rate> rates = fundData.getPurchaseRate();
            for (Rate rate : rates) {
                if (rate.inApplicableAmountScope(weChatPurchaseApplyCheckRequest.getOrderAmount())) {
                    purchaseApplyCheckVO.setFee(rate.getRate());
                    BigDecimal amount = new BigDecimal(weChatPurchaseApplyCheckRequest.getOrderAmount());
                    BigDecimal r = new BigDecimal(rate.getRate());
                    BigDecimal feeAmount = null;
                    // 开放式基金的申购费用不超过申购金额的5%
                    if (r.compareTo(new BigDecimal(5)) == -1) {
                        // 此时应该是百分数费率
                        r = r.divide(new BigDecimal(100));
                        feeAmount = amount.multiply(r);
                    } else {
                        // 固定金额费率
                        feeAmount = r;
                    }

                    purchaseApplyCheckVO.setFeeAmount(NumberUtil.getBigDecimal_to2_no45(feeAmount).toString());
                    BigDecimal realPayAmount = new BigDecimal(weChatPurchaseApplyCheckRequest.getOrderAmount());
                    purchaseApplyCheckVO.setRealPayAmount(NumberUtil.getBigDecimal_to2_no45(realPayAmount).toString());
                }
            }
        } else {
            throw new CommonException("用户申购申请金额校验失败,未查询到基金数据！");
        }
        return purchaseApplyCheckVO;
    }

    @Override
    public PurchasePreApplyVO preApply(WeChatPurchasePreApplyOrderRequest weChatPurchasePreApplyRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        // 校验参数
        Validator.noNullElements(weChatPurchasePreApplyRequest.getFundCode(),
                weChatPurchasePreApplyRequest.getFundName(), weChatPurchasePreApplyRequest.getOrderChannel());
        PurchasePreApplyVO purchasePreApplyVO = new PurchasePreApplyVO();
        purchasePreApplyVO.setIdempotentId(IdempotentIdUtil.genIdempotentId(currentUser.getUserId(),
                System.currentTimeMillis(), weChatPurchasePreApplyRequest.getOrderChannel()));
        purchasePreApplyVO.setFundCode(weChatPurchasePreApplyRequest.getFundCode());
        purchasePreApplyVO.setFundName(weChatPurchasePreApplyRequest.getFundName());
        FundData fundData = fundDataRepository.get(weChatPurchasePreApplyRequest.getFundCode());
        if (fundData != null) {
            purchasePreApplyVO.setMinPurchaseAmount(String.valueOf(NumberUtil.getDouble_to2_no4no5(fundData.getMinPurchaseAmount())));
            purchasePreApplyVO.setPurchaseStepsVO(PurchaseStepsVO.convert(fundData));
        } else {
            throw new CommonException("用户申购预申请失败,未查询到基金数据！");
        }
        return purchasePreApplyVO;
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
        Result<Payment> result = purchaseCoreService.pay(payment);
        if (result != null && result.isSuccess()) {
            // 异步更新持仓
            AsyncUpdateHoldPositonRequest asyncUpdateHoldPositonRequest = new AsyncUpdateHoldPositonRequest();
            asyncUpdateHoldPositonRequest.setTradeOrderId(weChatPayRequest.getTradeOrderId());
            asyncService.holdPositonUpdate(asyncUpdateHoldPositonRequest);
            return PayVO.buildSuccessPayVO(payment.getPayOrderId());
        } else {
            throw new CommonException("用户支付失败！");
        }
    }

    @Override
    public PayCannelVO payCannel(WeChatPayCannelRequest weChatPayCannelRequest) throws Exception {
        // 获取当前用户上下文
        User currentUser = userContext.getCurrentUser();
        // 校验订单参数
        Validator.noNullElements(weChatPayCannelRequest.getTradeOrderId());
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setOrderId(weChatPayCannelRequest.getTradeOrderId());
        tradeOrder.setUserId(currentUser.getUserId());
        Result<TradeOrder> result = purchaseCoreService.payCannel(tradeOrder);
        if (result != null && result.isSuccess() && result.getData() != null) {
            return PayCannelVO.buildSuccessPayCannelVO(result.getData());
        } else {
            throw new CommonException("用户支付取消失败！");
        }
    }
}
