package com.sdu.fund.core.model.bo;

import java.util.Date;

/**
 * @program: fundproduct
 * @description: 基金公司业务对象
 * @author: anonymous
 * @create: 2019-11-27 21:54
 **/
public class FundCompany {

    /* 基金公司代码*/
    private String fundCompanyCode;

    /* 基金公司名称*/
    private String fundCompanyName;

    /* 成立日*/
    private Date establishDate;

    /* 基金数量*/
    private Integer fundAmount;

    /* 总经理*/
    private String manager;

    /* 基金公司简称*/
    private String fundCompanyNameAbbr;

    /* 基金公司缩写*/
    private String fundCompanyNameAcronym;

    /* 管理规模*/
    private String managementScale;

    /* 评级*/
    private Integer grade;

    /* 数据更新日期*/
    private Date updateTime;

    /* 扩展字段*/
    private String extInfo;

    public String getFundCompanyCode() {
        return fundCompanyCode;
    }

    public void setFundCompanyCode(String fundCompanyCode) {
        this.fundCompanyCode = fundCompanyCode;
    }

    public String getFundCompanyName() {
        return fundCompanyName;
    }

    public void setFundCompanyName(String fundCompanyName) {
        this.fundCompanyName = fundCompanyName;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Integer getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(Integer fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getFundCompanyNameAbbr() {
        return fundCompanyNameAbbr;
    }

    public void setFundCompanyNameAbbr(String fundCompanyNameAbbr) {
        this.fundCompanyNameAbbr = fundCompanyNameAbbr;
    }

    public String getFundCompanyNameAcronym() {
        return fundCompanyNameAcronym;
    }

    public void setFundCompanyNameAcronym(String fundCompanyNameAcronym) {
        this.fundCompanyNameAcronym = fundCompanyNameAcronym;
    }

    public String getManagementScale() {
        return managementScale;
    }

    public void setManagementScale(String managementScale) {
        this.managementScale = managementScale;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
