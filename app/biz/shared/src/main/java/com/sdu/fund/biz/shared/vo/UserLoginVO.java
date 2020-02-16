package com.sdu.fund.biz.shared.vo;

public class UserLoginVO {

    private long userId;
    private String token;
    private String authority;

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserLoginVO(long userId, String token, String authority) {
        this.userId = userId;
        this.token = token;
        this.authority = authority;
    }
}
