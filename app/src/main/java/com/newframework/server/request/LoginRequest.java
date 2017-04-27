package com.newframework.server.request;

/**
 * Created by MyPC on 2017/4/26.
 */

public class LoginRequest {

    private String account;
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LoginRequest(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }
}
