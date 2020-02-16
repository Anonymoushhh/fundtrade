package com.sdu.fund.enums;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/5 17:56
 **/
/**
 * 响应状态码和说明
 */
public enum CodeEnum {

    SUCCESS(0, "请求成功"),
    FAIL(1, "请求失败");

    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应提示
     */
    private final String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CodeEnum getEnumByCode(Integer code) {
        for(CodeEnum e: CodeEnum.values()){
            if(Integer.valueOf(e.getCode()).equals(code)){
                return e;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static void main(String[] args) {
        getEnumByCode(0);
    }
}
