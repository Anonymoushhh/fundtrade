package com.sdu.fund.core.model.trade.bo;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/19 11:47
 **/
public class BaseOrder {

    /**
     * 扩展字段
     */
    public Map<String, Object> extInfo = Maps.newHashMap();

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }
}
