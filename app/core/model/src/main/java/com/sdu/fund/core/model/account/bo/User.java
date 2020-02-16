package com.sdu.fund.core.model.account.bo;

import com.alibaba.fastjson.JSON;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.google.common.collect.Maps;
import com.sdu.fund.core.model.account.enums.AuthorityEnum;
import com.sdu.fund.core.model.account.enums.GenderEnum;
import com.sdu.fund.core.model.account.enums.UserStatusEnum;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @program: fundtrade
 * @description:
 * @author: anonymous
 * @create: 2020/2/14 11:17
 **/
public class User {

    private Long userId;

    /**
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

    private Map<String, Object> extInfo;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        // 此写法只适用于基础字段和枚举类，不适用于List，map字段
        return this.getUserId().equals(other.getUserId()) &&
                this.getOpenId().equals(other.getOpenId()) &&
                this.getNickName().equals(other.getNickName()) &&
                this.getGender().equals(other.getGender()) &&
                this.getCity().equals(other.getCity()) &&
                this.getProvince().equals(other.getProvince()) &&
                this.getCountry().equals(other.getCountry()) &&
                this.getAuthority().equals(other.getAuthority()) &&
                this.getStatus().equals(other.getStatus());
    }

    /**
     * 比较对象特定字段,this对应属性若为空则返回true，默认不更新
     */
    public boolean equals(Object obj, String... fields) throws Exception {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        for (String field : fields) {
            if (getGetMethod(this, field) != null && !getGetMethod(this, field).equals(getGetMethod(other, field))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据属性，获取get方法
     *
     * @param ob   对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getGetMethod(Object ob, String name) throws Exception {
        Method[] m = ob.getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            if (("get" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                return m[i].invoke(ob);
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setUserId(1l);
        user.setNickName("1");
        user.setOpenId("1");
        user.setGender(GenderEnum.FEMALE);
        user.setCity("1");
        user.setProvince(null);
        user.setCountry("1");
        user.setAuthority(AuthorityEnum.CONSUMER);
        user.setStatus(UserStatusEnum.VALID);
        Map map1 = Maps.newHashMap();
        map1.put("2", "1");
        map1.put("1", "1");
        user.setExtInfo(map1);

        User other = new User();
        other.setUserId(1l);
        other.setNickName(null);
        other.setOpenId("1");
        other.setGender(GenderEnum.FEMALE);
        other.setCity("1");
        other.setProvince("1");
        other.setCountry("1");
        other.setAuthority(AuthorityEnum.CONSUMER);
        other.setStatus(UserStatusEnum.VALID);
        Map map = Maps.newHashMap();
        map.put("1", "1");
        map1.put("2", "1");
        other.setExtInfo(map);

        System.out.println(user.equals(other, "userId", "gender", "nickName"));

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
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
