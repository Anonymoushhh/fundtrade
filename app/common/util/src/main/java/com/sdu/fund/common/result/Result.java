package com.sdu.fund.common.result;

import java.io.Serializable;

/**
 * @program: fundtrade
 * @description: 结果封装基类
 * @author: anonymous
 * @create: 2019-11-28 22:42
 **/
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4822188134083220939L;

    /* 结果代码*/
    private int code;
    /* 是否成功*/
    private boolean success;
    /* 返回数据*/
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
