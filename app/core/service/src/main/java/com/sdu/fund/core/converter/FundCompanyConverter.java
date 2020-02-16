package com.sdu.fund.core.converter;

import com.sdu.fund.core.model.bo.FundCompany;
import com.sdu.fund.common.dal.entity.FundCompanyDo;
import org.springframework.beans.BeanUtils;

/**
 * @program: fundproduct
 * @description: 基金公司对象转换类
 * @author: anonymous
 * @create: 2019-11-29 10:51
 **/
public class FundCompanyConverter{

    public static FundCompany FundCompanyDoconvert2FundCompany(FundCompanyDo fundCompanyDo){
        if(fundCompanyDo == null){
            return null;
        }
        FundCompany fundCompany = new FundCompany();
        BeanUtils.copyProperties(fundCompanyDo,fundCompany);

        return fundCompany;
    }

    public static FundCompanyDo FundCompanyconvert2FundCompanyDo(FundCompany fundCompany){
        if(fundCompany == null){
            return null;
        }
        FundCompanyDo fundCompanyDo = new FundCompanyDo();
        BeanUtils.copyProperties(fundCompany,fundCompanyDo);

        return fundCompanyDo;
    }

}
