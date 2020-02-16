package com.sdu.fund.vo;


import com.sdu.fund.enums.CodeEnum;

/**
 * @program: fundproduct
 * @description: 统一返回结果抽象类
 * @author: anonymous
 * @create: 2020/2/5 17:49
 **/
public class Response<T> {

    // 状态码
    private Integer code;

    // 消息
    private String msg;

    // 要返回的数据
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Response(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    private Response(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    /**
     * 状态码 + 成功提示信息
     */
    public static <T> Response<T> buildSuccessResponse() {
        return new Response<>(CodeEnum.SUCCESS);
    }

    /**
     * 状态码 + 成功提示信息 + 数据
     */
    public static <T> Response<T> buildSuccessResponse(T data) {
        return new Response<>(CodeEnum.SUCCESS,data);
    }

    /**
     * 状态码 + 错误信息
     */
    public static <T> Response<T> buildErrorResponse() {
        return new Response<>(CodeEnum.FAIL);
    }

    /**
     * 状态码 + 错误信息
     */
    public static <T> Response<T> buildErrorResponse(Integer code) {
        return new Response<>(CodeEnum.getEnumByCode(code));
    }
}