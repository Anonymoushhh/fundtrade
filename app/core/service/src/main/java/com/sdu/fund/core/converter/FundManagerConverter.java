package com.sdu.fund.core.converter;

import com.sdu.fund.common.dal.entity.FundManagerDo;
import com.sdu.fund.core.model.bo.FundManager;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2019-12-20 21:27
 **/
public class FundManagerConverter {

    public static FundManager FundManagerDoconvert2FundManager(FundManagerDo fundManagerDo){
        if(fundManagerDo == null){
            return null;
        }
        FundManager fundManager = new FundManager();
        BeanUtils.copyProperties(fundManagerDo,fundManager);

        return fundManager;
    }

    public static FundManagerDo FundManagerconvert2FundManagerDo(FundManager fundManager){
        if(fundManager == null){
            return null;
        }
        FundManagerDo fundManagerDo = new FundManagerDo();
        BeanUtils.copyProperties(fundManager,fundManagerDo);

        return fundManagerDo;
    }
}
