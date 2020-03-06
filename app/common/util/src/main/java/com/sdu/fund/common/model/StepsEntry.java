package com.sdu.fund.common.model;

/**
 * @program: fundproduct
 * @description:
 * @author: anonymous
 * @create: 2020/2/12 19:45
 **/
public class StepsEntry {
    private String text;
    private String desc;

    public StepsEntry(String text, String desc) {
        this.text = text;
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
