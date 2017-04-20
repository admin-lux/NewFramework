/*
    ShengDao Android Client, OnDataListener
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.newframework.server.network.async;


import com.newframework.server.network.http.HttpException;

import java.io.UnsupportedEncodingException;

public interface OnDataListener {


    /**
     * 异步耗时方法
     * @String parameter 请求传参,可不填
     * @param requestCode 请求码
     * @return
     * @throws HttpException
     */
    public Object doInBackground(int requestCode, String parameter) throws HttpException, UnsupportedEncodingException;
    /**
     * 成功方法（可直接更新UI）
     * @param requestCode 请求码
     * @param result 返回结果
     */
    public void onSuccess(int requestCode, Object result) throws HttpException;

    /**
     * 失败方法（可直接更新UI）
     * @param requestCode 请求码
     * @param state 返回状态
     * @param result 返回结果
     */
    public void onFailure(int requestCode, int state, Object result);
}
