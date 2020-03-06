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
public class PurchaseStepsVO {

    private List<StepsEntry> listData;

    public static PurchaseStepsVO convert(FundData fundData) {
        if(fundData==null){
            return null;
        }
        PurchaseStepsVO purchaseStepsVO = new PurchaseStepsVO();
        List<StepsEntry> listData = Lists.newArrayList();
        listData.add(new StepsEntry(PurchaseStepsVOKey.BUY, "T日"));
        listData.add(new StepsEntry(PurchaseStepsVOKey.SHARE_CONFIRM, fundData.getBuyConfirmDay().getMsg()));
        listData.add(new StepsEntry(PurchaseStepsVOKey.SEE_PROFIT, fundData.getBuyConfirmDay().getMsg() + "收益更新后"));
        purchaseStepsVO.listData = listData;
        return purchaseStepsVO;
    }

    public List<StepsEntry> getListData() {
        return listData;
    }

    public void setListData(List<StepsEntry> listData) {
        this.listData = listData;
    }
}