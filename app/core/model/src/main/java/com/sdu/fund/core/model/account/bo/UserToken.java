package com.sdu.fund.core.model.account.bo;

import com.sdu.fund.common.utils.SnowflakeIdUtil;
import com.sdu.fund.common.utils.TokenUtil;
import com.sdu.fund.core.model.account.enums.TokenTypeEnum;

import java.util.Date;

public class UserToken {

    /**
     *
     */
    private Long tokenId;
    /**
     *
     */
    private Long userId;

    /**
     * 应用id
     */
    private String appId;

    /**
     *
     */
    private String token;

    private TokenTypeEnum type;

    /**
     * 失效时间
     */
    private Long expireTime;

    /**
     *
     */
    private Boolean valid;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    public UserToken init(String openId,Long userId){
        this.setTokenId(SnowflakeIdUtil.getInstance().nextId());
        this.setToken(TokenUtil.generateToken(openId,userId));
        long now = System.currentTimeMillis();//当前时间
        long expTime = now + TokenUtil.TOKEN_EXPIRED_TIME;//过期时间为5分钟
        this.setExpireTime(expTime);
        this.setType(TokenTypeEnum.LOGIN);
        this.setValid(true);
        return this;
    }

    public TokenTypeEnum getType() {
        return type;
    }

    public void setType(TokenTypeEnum type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}