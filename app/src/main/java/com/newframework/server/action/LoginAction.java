package com.newframework.server.action;

import android.content.Context;
import android.text.TextUtils;

import com.newframework.server.network.http.HttpException;
import com.newframework.server.request.LoginRequest;
import com.newframework.server.response.LoginResponse;
import com.newframework.utils.json.JsonMananger;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by MyPC on 2017/4/26.
 */

public class LoginAction  extends BaseAction{


    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public LoginAction(Context context) {
        super(context);
    }

    public LoginResponse login(String account, String pwd) throws HttpException {
        String url = getURL("hos/session?");
        String json = JsonMananger.beanToJson(new LoginRequest(account, pwd));
        StringEntity entity = null;
        try {
            entity = new StringEntity(json, ENCODING);
            entity.setContentType(CONTENT_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = httpManager.post(mContext, url, entity, CONTENT_TYPE);
        LoginResponse response = null;
        if (!TextUtils.isEmpty(result)) {
            response = jsonToBean(result, LoginResponse.class);
        }
        return response;
    }


}
