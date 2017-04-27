package com.newframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by GaoQian on 2017/3/14.
 */
@Entity
public class Pat {
    @Id
    @Unique
    private String patId;
    @Property
    private String age;
    @Property
    private String district;
    @Property
    private String name;
    @Property
    private String nickName;
    @Property
    private String sex;
    @Property
    private String time;
    @Property
    private String url;
    @Property
    private String tags;
    @Property
    private String userid;
    @Generated(hash = 2007624069)
    public Pat(String patId, String age, String district, String name,
               String nickName, String sex, String time, String url, String tags,
               String userid) {
        this.patId = patId;
        this.age = age;
        this.district = district;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.time = time;
        this.url = url;
        this.tags = tags;
        this.userid = userid;
    }
    @Generated(hash = 609627438)
    public Pat() {
    }
    public String getPatId() {
        return this.patId;
    }
    public void setPatId(String patId) {
        this.patId = patId;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getDistrict() {
        return this.district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTags() {
        return this.tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

}
