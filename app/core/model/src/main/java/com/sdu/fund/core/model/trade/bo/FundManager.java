package com.sdu.fund.core.model.bo;

import java.util.Date;
import java.util.List;

/**
 * @program: fundproduct
 * @description: 基金经理业务对象
 * @author: anonymous
 * @create: 2019-12-16 22:34
 **/
public class FundManager {

    /* 基金经理id*/
    private String managerId;

    /* 基金经理姓名*/
    private String managerName;

    /* 基金公司代码*/
    private String companyCode;

    /* 基金公司名称*/
    private String companyName;

    /* 管理的基金代码*/
    private List<String> fundCodes;

    /* 管理的基金代码*/
    private List<String> fundNames;

    /* 任职天数*/
    private Integer serviceDate;

    /* 现任基金资产总规模*/
    private String assetsScale;

    /* 现任基金最佳回报*/
    private String bestReturnNow;

    /* 现任基金最佳回报基金代码*/
    private String bestReturnCodeNow;

    /* 现任基金最佳回报基金名称*/
    private String bestReturnNameNow;

    /* 现任基金最佳回报*/
    private String bestReturnHistory;

    /* 开始任职时间*/
    private Date startWorkingTime;

    /* 简介*/
    private String managerIntroduction;

    /* 扩展字段*/
    private String extInfo;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getFundCodes() {
        return fundCodes;
    }

    public void setFundCodes(List<String> fundCodes) {
        this.fundCodes = fundCodes;
    }

    public List<String> getFundNames() {
        return fundNames;
    }

    public void setFundNames(List<String> fundNames) {
        this.fundNames = fundNames;
    }

    public Integer getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Integer serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getAssetsScale() {
        return assetsScale;
    }

    public void setAssetsScale(String assetsScale) {
        this.assetsScale = assetsScale;
    }

    public String getBestReturnNow() {
        return bestReturnNow;
    }

    public void setBestReturnNow(String bestReturnNow) {
        this.bestReturnNow = bestReturnNow;
    }

    public String getBestReturnCodeNow() {
        return bestReturnCodeNow;
    }

    public void setBestReturnCodeNow(String bestReturnCodeNow) {
        this.bestReturnCodeNow = bestReturnCodeNow;
    }

    public String getBestReturnNameNow() {
        return bestReturnNameNow;
    }

    public void setBestReturnNameNow(String bestReturnNameNow) {
        this.bestReturnNameNow = bestReturnNameNow;
    }

    public String getBestReturnHistory() {
        return bestReturnHistory;
    }

    public void setBestReturnHistory(String bestReturnHistory) {
        this.bestReturnHistory = bestReturnHistory;
    }

    public Date getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(Date startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public String getManagerIntroduction() {
        return managerIntroduction;
    }

    public void setManagerIntroduction(String managerIntroduction) {
        this.managerIntroduction = managerIntroduction;
    }
}
