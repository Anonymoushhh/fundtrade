package com.sdu.fund.common.exception;

/**
 * @program: fundtrade
 * @description: 通用异常类
 * @author: anonymous
 * @create: 2019-12-15 16:32
 **/
public class CommonException extends RuntimeException{

    private static final long serialVersionUID = 6683876954452198242L;

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
    }
}
