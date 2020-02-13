package com.sdu.fund.common.exception;

/**
 * @program: fundtrade
 * @description: http异常类
 * @author: anonymous
 * @create: 2019-12-15 16:32
 **/
public class HttpException extends RuntimeException{

    private static final long serialVersionUID = 6683876954452198242L;

    public HttpException() {
        super();
    }

    public HttpException(String message) {
        super(message);
    }
}
