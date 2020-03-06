package com.sdu.fund.biz.shared.vo;

import com.google.common.collect.Lists;
import com.sdu.fund.biz.shared.constants.PurchaseStepsVOKey;
import com.sdu.fund.common.model.BaseEntry;
import com.sdu.fund.common.model.StepsEntry;
import com.sdu.fund.common.utils.NumberUtil;
import com.sdu.fund.core.model.trade.bo.FundData;
import com.sdu.fund.core.model.trade.bo.HoldPosition;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: fundproduct
 * @description: 用户维度持仓VO
 * @author: anonymous
 * @create: 2020/2/7 16:32
 **/
public class HoldPositionsVO {

    private String holdAmount;
    private String profitLastDay;
    private String gainLastDay;
    private String positionCostPrice;
    private String holdProfit;
    private String historyProfit;
    private List<HoldPositionVO> listData = Lists.newArrayList();

    public static HoldPositionsVO convert(List<HoldPosition> holdPositions) {
        if (holdPositions == null) {
            return null;
        }
        HoldPositionsVO holdPositionsVO = new HoldPositionsVO();
        BigDecimal holdAmount = new BigDecimal(0);
        BigDecimal profitLastDay = new BigDecimal(0);
        BigDecimal gainLastDay = new BigDecimal(0);
        BigDecimal positionCostPrice = new BigDecimal(0);
        BigDecimal holdProfit = new BigDecimal(0);
        BigDecimal historyProfit = new BigDecimal(0);
        for (HoldPosition holdPosition : holdPositions) {
            HoldPositionVO holdPositionVO = new HoldPositionVO();
            holdPositionVO.setFundCode(holdPosition.getFundCode());
            holdPositionVO.setFundName(holdPosition.getFundName());
            holdPositionVO.setFundType(holdPosition.getFundType().getMsg());
            if (holdPosition.getHoldAmount() != null) {
                // 持仓金额没有负数，不需要格式化
                holdPositionVO.setHoldAmount(NumberUtil.getBigDecimal_to2_no45(holdPosition.getHoldAmount()).toString());
                holdAmount = holdAmount.add(NumberUtil.getBigDecimal_to2_no45(holdPosition.getHoldAmount()));
            }
            if (holdPosition.getProfitLastDay() != null) {
                profitLastDay = profitLastDay.add(NumberUtil.getBigDecimal_to2_no45(holdPosition.getProfitLastDay()));
                holdPositionVO.setProfitLastDay(NumberUtil.getBigDecimal_to2_no45Format(holdPosition.getProfitLastDay()));
            }
            if (holdPosition.getGainLastDay() != null) {
                gainLastDay = gainLastDay.add(NumberUtil.getBigDecimal_to2_no45(holdPosition.getGainLastDay()));
            }
            if (holdPosition.getPositionCostPrice() != null) {
                positionCostPrice =
                        positionCostPrice.add(NumberUtil.getBigDecimal_to2_no45(holdPosition.getPositionCostPrice()));
            }
            if (holdPosition.getHoldProfit() != null) {
                holdProfit = holdProfit.add(NumberUtil.getBigDecimal_to2_no45(holdPosition.getHoldProfit()));
                holdPositionVO.setHoldProfit(NumberUtil.getBigDecimal_to2_no45Format(holdPosition.getHoldProfit()));
            }
            if (holdPosition.getHistoryProfit() != null) {
                historyProfit = historyProfit.add(NumberUtil.getBigDecimal_to2_no45(holdPosition.getHistoryProfit()));
            }
            holdPositionsVO.getListData().add(holdPositionVO);
        }
        holdPositionsVO.setHoldAmount(NumberUtil.getBigDecimal_to2_no45(holdAmount).toString());
        holdPositionsVO.setProfitLastDay(NumberUtil.getBigDecimal_to2_no45Format(profitLastDay));
        holdPositionsVO.setGainLastDay(NumberUtil.getBigDecimal_to2_no45Format(gainLastDay));
        // 不需要格式化
        holdPositionsVO.setPositionCostPrice(NumberUtil.getBigDecimal_to2_no45(positionCostPrice).toString());
        holdPositionsVO.setHoldProfit(NumberUtil.getBigDecimal_to2_no45Format(holdProfit));
        holdPositionsVO.setHistoryProfit(NumberUtil.getBigDecimal_to2_no45Format(historyProfit));

        return holdPositionsVO;
    }

    public List<HoldPositionVO> getListData() {
        return listData;
    }

    public void setListData(List<HoldPositionVO> listData) {
        this.listData = listData;
    }

    public String getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(String holdAmount) {
        this.holdAmount = holdAmount;
    }

    public String getProfitLastDay() {
        return profitLastDay;
    }

    public void setProfitLastDay(String profitLastDay) {
        this.profitLastDay = profitLastDay;
    }

    public String getGainLastDay() {
        return gainLastDay;
    }

    public void setGainLastDay(String gainLastDay) {
        this.gainLastDay = gainLastDay;
    }

    public String getPositionCostPrice() {
        return positionCostPrice;
    }

    public void setPositionCostPrice(String positionCostPrice) {
        this.positionCostPrice = positionCostPrice;
    }

    public String getHoldProfit() {
        return holdProfit;
    }

    public void setHoldProfit(String holdProfit) {
        this.holdProfit = holdProfit;
    }

    public String getHistoryProfit() {
        return historyProfit;
    }

    public void setHistoryProfit(String historyProfit) {
        this.historyProfit = historyProfit;
    }

}

class HoldPositionVO {
    private String fundCode;
    private String fundName;
    private String holdAmount;
    private String holdProfit;
    private String profitLastDay;
    private String fundType;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(String holdAmount) {
        this.holdAmount = holdAmount;
    }

    public String getHoldProfit() {
        return holdProfit;
    }

    public void setHoldProfit(String holdProfit) {
        this.holdProfit = holdProfit;
    }

    public String getProfitLastDay() {
        return profitLastDay;
    }

    public void setProfitLastDay(String profitLastDay) {
        this.profitLastDay = profitLastDay;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }
}