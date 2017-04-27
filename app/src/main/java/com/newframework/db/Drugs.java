package com.newframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by GaoQian on 2017/1/20.
 */
@Entity
public class Drugs {

    @Id
    @Unique
    private String id;
    @Property
    private String commonName;
    @Property
    private String drugName;
    @Property
    private String amount;
    @Property
    private String pinyingCode;
    @Generated(hash = 1546821719)
    public Drugs(String id, String commonName, String drugName, String amount,
                 String pinyingCode) {
        this.id = id;
        this.commonName = commonName;
        this.drugName = drugName;
        this.amount = amount;
        this.pinyingCode = pinyingCode;
    }

    @Generated(hash = 1295728688)
    public Drugs() {
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getPinyingCode() {
        return this.pinyingCode;
    }

    public void setPinyingCode(String pinyingCode) {
        this.pinyingCode = pinyingCode;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}
