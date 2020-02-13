package com.sdu.fund.core.request;

import com.google.common.collect.Maps;
import com.sdu.fund.common.enums.RequestMethodEnum;

import java.util.List;
import java.util.Map;

/**
 * @program: fundtrade
 * @description: 数据爬取请求
 * @author: anonymous
 * @create: 2019-11-23 20:36
 **/
public class CrawingRequest {

    /* 爬取地址*/
    private String url;

    /* 请求方法枚举*/
    private RequestMethodEnum requestMethodEnum;

    /* 是否分页*/
    private boolean paging;

    /* 组合请求*/
    private List<CrawingRequest> crawingRequests;

    /* 扩展字段*/
    private Map<String,Object> extInfo = Maps.newHashMap();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethodEnum getRequestMethodEnum() {
        return requestMethodEnum;
    }

    public void setRequestMethodEnum(RequestMethodEnum requestMethodEnum) {
        this.requestMethodEnum = requestMethodEnum;
    }

    public boolean getPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public int getCurPage() {
        return (Integer)extInfo.get("curPage");
    }

    public void setCurPage(int curPage) {
        extInfo.put("curPage",curPage);
    }

    public List<CrawingRequest> getCrawingRequests() {
        return crawingRequests;
    }

    public void setCrawingRequests(List<CrawingRequest> crawingRequests) {
        this.crawingRequests = crawingRequests;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }
}
