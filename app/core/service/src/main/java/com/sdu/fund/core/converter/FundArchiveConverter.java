package com.sdu.fund.core.converter;

import com.alibaba.fastjson.JSON;
import com.sdu.fund.common.dal.entity.FundArchiveDo;
import com.sdu.fund.core.model.bo.FundArchive;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundproduct
 * @description: 基金档案对象转换类
 * @author: anonymous
 * @create: 2019-12-09 22:22
 **/
public class FundArchiveConverter {

    public static FundArchive FundArchiveDoconvert2FundArchive(FundArchiveDo fundArchiveDo){
        if(fundArchiveDo == null){
            return null;
        }
        FundArchive fundArchive = new FundArchive();
        BeanUtils.copyProperties(fundArchiveDo,fundArchive,"stockCodes","zqCodes","managerIds");
        fundArchive.setStockCodes(JSON.parseArray(fundArchiveDo.getStockCodes(), String.class));
        fundArchive.setZqCodes(JSON.parseArray(fundArchiveDo.getZqCodes(), String.class));
        fundArchive.setManagerIds(JSON.parseArray(fundArchiveDo.getManagerIds(), String.class));

        return fundArchive;
    }

    public static FundArchiveDo FundArchiveconvert2FundArchiveDo(FundArchive fundArchive){
        if(fundArchive == null){
            return null;
        }
        FundArchiveDo fundArchiveDo = new FundArchiveDo();
        BeanUtils.copyProperties(fundArchive,fundArchiveDo,"stockCodes","zqCodes","managerIds");
        fundArchiveDo.setStockCodes(JSON.toJSONString(fundArchive.getStockCodes()));
        fundArchiveDo.setZqCodes(JSON.toJSONString(fundArchive.getZqCodes()));
        fundArchiveDo.setManagerIds(JSON.toJSONString(fundArchive.getManagerIds()));

        return fundArchiveDo;
    }
}
