package com.sdu.fund.core.model.bo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/1/29 10:32
 **/
public class Rate {

    /* 适用金额*/
    private String applicableAmount;

    /* 适用期限*/
    private String applicableTime;

    /* 费率*/
    private String rate;

    public String getApplicableAmount() {
        return applicableAmount;
    }

    public void setApplicableAmount(String applicableAmount) {
        this.applicableAmount = applicableAmount;
    }

    public String getApplicableTime() {
        return applicableTime;
    }

    public void setApplicableTime(String applicableTime) {
        this.applicableTime = applicableTime;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
