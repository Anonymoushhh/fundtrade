package com.sdu.fund.common.code;

/**
 * @program: fundtrade
 * @description: 请求结果代码
 * @author: anonymous
 * @create: 2019-11-28 23:01
 **/
public class ResultCode {

    /* 全部成功*/
    public static final int  SUCCESS = 0;

    /* 服务端异常*/
    public static final int  SERVER_EXCEPTION = 1;

    /* 参数非法*/
    public static final int  PARAMETER_ILLEGAL = 2;

    /* 用户不存在*/
    public static final int  USER_NOT_EXSIST = 3;

    /* 数据库异常*/
    public static final int  DATABASE_EXCEPTION = 4;

    /* 部分失败*/
    public static final int  PARTIAL_FAILURE = 5;

    /* 数据url失效*/
    public static final int URL_INVAILD = 6;

    /* 全部失败*/
    public static final int  FAILURE = 7;

    /* 未登录*/
    public static final int  NO_LOGIN = 8;
}
