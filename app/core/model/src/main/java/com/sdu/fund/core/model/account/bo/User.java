package com.sdu.fund.core.model.account.bo;

import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.GenderEnum;
import com.sdu.fund.core.model.account.enums.UserStatusEnum;

import java.util.Date;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:17
 **/
public class UserAccount {

    private Long userId;

    /**
     *
     * 单个应用下用户唯一id
     */
    private String openId;

    /**
     *
     */
    private String nickName;

    /**
     *
     */
    private GenderEnum gender;

    /**
     *
     */
    private String city;

    /**
     *
     */
    private String province;

    /**
     *
     */
    private String country;

    /**
     *
     */
    private AuthorityEnum authority;

    /**
     *
     */
    private UserStatusEnum status;

    /**
     *
     */
    private Date gmtCreate;

    /**
     *
     */
    private Date gmtModified;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AuthorityEnum getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityEnum authority) {
        this.authority = authority;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
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
