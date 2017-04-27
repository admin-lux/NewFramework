package com.newframework.ui.actions;


import android.text.TextUtils;

import com.newframework.App;
import com.newframework.server.network.async.AsyncTaskManager;
import com.newframework.server.network.async.OnDataListener;
import com.newframework.server.network.http.HttpException;
import com.newframework.server.response.LoginResponse;
import com.newframework.ui.dispatcher.Dispatcher;
import com.newframework.ui.model.Login;
import com.newframework.utils.AMUtils;

import java.io.UnsupportedEncodingException;

/**
 * Flux的ActionCreator模块
 * Created by ntop on 18/12/15.
 */
public class ActionsCreator {
    public static final String LOGIN_INIT = "login_init";
    public static final String LOGIN_PHONE_EMPTY = "login_phone_empty";
    public static final String LOGIN_PHONE_ERROR = "login_phone_error";
    public static final String LOGIN_PWD_EMPTY = "login_pwd_empty";
    public static final String LOGIN_PWD_SPACES = "login_pwd_spaces";
    public static final String LOGIN_START = "login_start";
    public static final String LOGIN_SUCCESS = "login_success";
    public static final String LOGIN_ERROR = "login_error";
    public final int LOGIN_PROCESS = 0x999;

    private static ActionsCreator instance;
    final Dispatcher dispatcher;

    ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }

    // 登录界面

    public void loginInit() {
        dispatcher.dispatch(new LoginAction(LOGIN_INIT, null));
    }

    /**
     * 登录
     */
    public void login(final String phone, final String pwd) {
        if (TextUtils.isEmpty(phone)) {
            dispatcher.dispatch(new LoginAction(LOGIN_PHONE_EMPTY, null));
            return;
        }

        if (!AMUtils.isMobile(phone)) {
            dispatcher.dispatch(new LoginAction(LOGIN_PHONE_ERROR, null));
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            dispatcher.dispatch(new LoginAction(LOGIN_PWD_EMPTY, null));
            return;
        }
        if (pwd.contains(" ")) {
            dispatcher.dispatch(new LoginAction(LOGIN_PWD_SPACES, null));
            return;
        }
        dispatcher.dispatch(new LoginAction(LOGIN_START, new Login(phone, pwd)));
        AsyncTaskManager.getInstance(App.getInstance().getApplicationContext()).request(LOGIN_PROCESS, new OnDataListener() {
            @Override
            public Object doInBackground(int requestCode, String parameter) throws HttpException, UnsupportedEncodingException {
                return new com.newframework.server.action.LoginAction(App.getInstance().getApplicationContext()).login(phone, pwd);
            }

            @Override
            public void onSuccess(int requestCode, Object result) throws HttpException {
                LoginResponse loginResponse = (LoginResponse) result;
                if (loginResponse.getResult().getCode() == 0) {
                    String token = loginResponse.getToken();
                    if (!TextUtils.isEmpty(token)) {
                        dispatcher.dispatch(new StringAction(LOGIN_SUCCESS, token));
                    }
                } else {
                    dispatcher.dispatch(new LoginAction(LOGIN_ERROR, null));
                }
            }

            @Override
            public void onFailure(int requestCode, int state, Object result) {
                dispatcher.dispatch(new LoginAction(LOGIN_ERROR, null));
            }
        });
    }

}
