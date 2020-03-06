package com.sdu.fund.biz.shared.vo;


/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/18 17:59
 **/
public class PayVO {

    private String payOrderId;
    private boolean success;
    private int code;

    public static PayVO buildSuccessPayVO(String payOrderId) {
        PayVO payVO = new PayVO();
        payVO.setPayOrderId(payOrderId);
        payVO.setSuccess(true);
        return payVO;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
