package com.sdu.fund.biz.shared.vo;

import com.google.common.collect.Lists;
import com.sdu.fund.biz.shared.constants.PurchaseStepsVOKey;
import com.sdu.fund.common.model.StepsEntry;
import com.sdu.fund.core.model.trade.bo.FundData;

import java.util.List;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/7 16:32
 **/
public class RedeemStepsVO {

    private List<StepsEntry> listData;

    public RedeemStepsVO convert(FundData fundData) {
        if(fundData==null){
            return this;
        }
        List<StepsEntry> listData = Lists.newArrayList();
        listData.add(new StepsEntry(PurchaseStepsVOKey.BUY, "T日"));
        listData.add(new StepsEntry(PurchaseStepsVOKey.SHARE_CONFIRM, fundData.getRedeemConfirmDay().getMsg()));
        listData.add(new StepsEntry(PurchaseStepsVOKey.SEE_PROFIT, fundData.getRedeemConfirmDay().getMsg()+ "清算结束后"));
        this.listData = listData;
        return this;
    }

    public List<StepsEntry> getListData() {
        return listData;
    }

    public void setListData(List<StepsEntry> listData) {
        this.listData = listData;
    }
}
