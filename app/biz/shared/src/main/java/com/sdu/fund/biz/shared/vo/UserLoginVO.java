package com.wycs.c.server.vo;

public class UserLoginVo {
    private long userId;
    private String token;
    private String mobile;
    private String name;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserLoginVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserLoginVo(long userId, String token, String mobile, String name) {
        this.userId = userId;
        this.token = token;
        this.mobile = mobile;
        this.name = name;
    }
}
