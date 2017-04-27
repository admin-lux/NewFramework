package com.newframework.ui.model;

/**
 * Created by MyPC on 2017/4/26.
 */

public class Login {
    private String phone, pwd;

    public Login(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
