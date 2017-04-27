package com.newframework.ui.stores;

import android.content.Context;
import android.content.SharedPreferences;

import com.newframework.App;
import com.newframework.ui.actions.Action;
import com.newframework.ui.actions.ActionsCreator;
import com.newframework.ui.actions.LoginAction;
import com.newframework.ui.actions.StringAction;

/**
 * Created by MyPC on 2017/4/26.
 */

public class LoginStore extends Store {
    private Context context;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String oldPhone;
    private String oldPassword;
    private String phoneString;
    private String passwordString;
    private String type;
    private String token;

    public LoginStore(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("config", App.MODE_PRIVATE);
        editor = sp.edit();
    }

    public class LoginStoreChangeEvent implements StoreChangeEvent {
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new LoginStoreChangeEvent();
    }

    @Override
    public void onAction(Action action) {
        type = action.getType();
        switch (action.getType()) {
            case ActionsCreator.LOGIN_INIT:
                initData();
                emitStoreChange();
                break;
            case ActionsCreator.LOGIN_START:
                LoginAction loginAction = (LoginAction) action;
                phoneString = loginAction.getData().getPhone();
                passwordString = loginAction.getData().getPwd();
                emitStoreChange();
                break;
            case ActionsCreator.LOGIN_SUCCESS:
                StringAction stringAction = (StringAction) action;
                token = stringAction.getData();

                editor.putBoolean("exit", false);
                editor.putString("verify_token", token);
                editor.putString("loginphone", phoneString);
                editor.putString("loginpassword", passwordString);
                editor.apply();
                emitStoreChange();
                break;
            case ActionsCreator.LOGIN_ERROR:
                emitStoreChange();
                break;
        }
    }

    private void initData() {
        oldPhone = sp.getString("loginphone", "");
        oldPassword = sp.getString("loginpassword", "");
    }

    public boolean isExit() {
        return sp.getBoolean("exit", false);
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getType() {
        return type;
    }
}
