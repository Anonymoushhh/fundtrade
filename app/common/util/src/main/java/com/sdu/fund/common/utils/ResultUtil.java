package com.sdu.fund.common.utils;

import com.sdu.fund.common.code.ResultCode;
import com.sdu.fund.common.result.Result;

/**
 * @program: fundproduct
 * @description: 请求结果构造工具类
 * @author: anonymous
 * @create: 2019-11-28 22:58
 **/
public class ResultUtil {

    /*
     * @description 成功方法-含参 
     * @param [] 
     * @return com.sdu.fund.common.result.Result 
     * @author anonymous 
     * @date 2019/11/28 
     */
    public static Result buildSuccessResult(){
        Result result = new Result<>();
        result.setCode(ResultCode.SUCCESS);
        result.setSuccess(true);
        return result;
    }
    
    /*
     * @description 成功方法-无参 
     * @param [object] 
     * @return com.sdu.fund.common.result.Result 
     * @author anonymous 
     * @date 2019/11/28 
     */
    public static Result buildSuccessResult(Object object){
        Result result = new Result<>();
        result.setCode(ResultCode.SUCCESS);
        result.setSuccess(true);
        result.setData(object);
        return result;
    }

    /*
     * @description 异常方法 
     * @param [errCode] 
     * @return com.sdu.fund.common.result.Result 
     * @author anonymous 
     * @date 2019/11/28 
     */
    public static Result buildFailedResult(int errCode){
        Result result = new Result<>();
        result.setCode(errCode);
        result.setSuccess(false);
        result.setData(null);
        return result;
    }
    /*
     * @description 异常方法
     * @param [errCode]
     * @return com.sdu.fund.common.result.Result
     * @author anonymous
     * @date 2019/11/28
     */
    public static Result buildFailedResult(int errCode, Object object){
        Result result = new Result<>();
        result.setCode(errCode);
        result.setSuccess(false);
        result.setData(object);
        return result;
    }
}
