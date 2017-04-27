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
public class PatTags {
    @Id
    @Unique
    private String id;
    @Property
    private String tagName;
    @Property
    private String userid;
    @Generated(hash = 1213180020)
    public PatTags(String id, String tagName, String userid) {
        this.id = id;
        this.tagName = tagName;
        this.userid = userid;
    }
    @Generated(hash = 1679006787)
    public PatTags() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
