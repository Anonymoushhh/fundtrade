package com.sdu.fund.core.model.bo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sdu.fund.core.model.constants.FundArchiveKey;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: fundproduct
 * @description: 基金档案业务对象
 * @author: anonymous
 * @create: 2019-12-05 21:54
 **/
public class FundArchive {

    /* 基金代码*/
    private String fundCode;

    /* 基金名称*/
    private String fundName;

    /* 基金名称简称*/
    private String fundNameAbbr;

    /* 基金名称拼音首字母*/
    private String fundNamePinyin;

    /* 基金类型*/
    private String fundType;

    /* 股票代码*/
    private List<String> stockCodes;

    /* 基金持仓债券代码*/
    private List<String> zqCodes;

    /* 成立日*/
    private Date establishDate;

    /* 发行日*/
    private Date issueDate;

    /* 公司代码*/
    private String companyCode;

    /* 公司名称*/
    private String companyName;

    /* 基金经理ids*/
    private List<String> managerIds;

    /* 基金托管人*/
    private String custodian;

    /* 业绩比较基准*/
    private String performanceBaseline;

    /* 跟踪标的*/
    private String trackingTarget;

    /* 投资目标*/
    private String investmentTarget;

    /* 投资理念*/
    private String investmentConcept;

    /* 投资范围*/
    private String investmentScope;

    /* 投资策略*/
    private String investmentStrategy;

    /* 分红政策*/
    private String dividendPolicy;

    /* 风险收益特征*/
    private String riskReturnCharacteristics;

    /* 扩展字段*/
    private Map<String,Object> extInfo = Maps.newHashMap();

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

    public String getFundNameAbbr() {
        return fundNameAbbr;
    }

    public void setFundNameAbbr(String fundNameAbbr) {
        this.fundNameAbbr = fundNameAbbr;
    }

    public String getFundNamePinyin() {
        return fundNamePinyin;
    }

    public void setFundNamePinyin(String fundNamePinyin) {
        this.fundNamePinyin = fundNamePinyin;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public List<String> getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(List<String> stockCodes) {
        this.stockCodes = stockCodes;
    }

    public List<String> getZqCodes() {
        return zqCodes;
    }

    public void setZqCodes(List<String> zqCodes) {
        this.zqCodes = zqCodes;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
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

    public List<String> getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(List<String> managerIds) {
        this.managerIds = managerIds;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getPerformanceBaseline() {
        return performanceBaseline;
    }

    public void setPerformanceBaseline(String performanceBaseline) {
        this.performanceBaseline = performanceBaseline;
    }

    public String getTrackingTarget() {
        return trackingTarget;
    }

    public void setTrackingTarget(String trackingTarget) {
        this.trackingTarget = trackingTarget;
    }

    public String getInvestmentTarget() {
        return investmentTarget;
    }

    public void setInvestmentTarget(String investmentTarget) {
        this.investmentTarget = investmentTarget;
    }

    public String getInvestmentConcept() {
        return investmentConcept;
    }

    public void setInvestmentConcept(String investmentConcept) {
        this.investmentConcept = investmentConcept;
    }

    public String getInvestmentScope() {
        return investmentScope;
    }

    public void setInvestmentScope(String investmentScope) {
        this.investmentScope = investmentScope;
    }

    public String getInvestmentStrategy() {
        return investmentStrategy;
    }

    public void setInvestmentStrategy(String investmentStrategy) {
        this.investmentStrategy = investmentStrategy;
    }

    public String getDividendPolicy() {
        return dividendPolicy;
    }

    public void setDividendPolicy(String dividendPolicy) {
        this.dividendPolicy = dividendPolicy;
    }

    public String getRiskReturnCharacteristics() {
        return riskReturnCharacteristics;
    }

    public void setRiskReturnCharacteristics(String riskReturnCharacteristics) {
        this.riskReturnCharacteristics = riskReturnCharacteristics;
    }

    public void setManagerNames(List<String> managerNames){
        this.extInfo.put(FundArchiveKey.MANAGERNAMES,managerNames);
    }

    public List<String> getManagerNames(){
        if(this.extInfo.get(FundArchiveKey.MANAGERNAMES)==null){
            return Lists.newArrayList();
        }
        return (List<String>) this.extInfo.get(FundArchiveKey.MANAGERNAMES);
    }
}
