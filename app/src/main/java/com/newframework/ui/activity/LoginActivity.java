package com.newframework.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newframework.R;
import com.newframework.ui.actions.ActionsCreator;
import com.newframework.ui.activity.base.BaseActivity;
import com.newframework.ui.dispatcher.Dispatcher;
import com.newframework.ui.stores.LoginStore;
import com.newframework.ui.widget.ClearWriteEditText;
import com.newframework.ui.widget.LoadDialog;
import com.newframework.utils.AMUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 * <p>
 * Created by xuelc on 2017/4/26.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.de_login_logo)
    ImageView deLoginLogo;
    @BindView(R.id.de_login_phone)
    ClearWriteEditText deLoginPhone;
    @BindView(R.id.fr_username_delete)
    FrameLayout frUsernameDelete;
    @BindView(R.id.liner1)
    RelativeLayout liner1;
    @BindView(R.id.de_login_password)
    ClearWriteEditText deLoginPassword;
    @BindView(R.id.fr_pass_delete)
    FrameLayout frPassDelete;
    @BindView(R.id.liner2)
    RelativeLayout liner2;
    @BindView(R.id.de_login_forgot)
    TextView deLoginForgot;
    @BindView(R.id.de_login_register)
    TextView deLoginRegister;
    @BindView(R.id.de_login_sign)
    Button deLoginSign;

    private Dispatcher dispatcher;
    private ActionsCreator actionsCreator;
    private LoginStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();
        dispatcher = Dispatcher.get();
        actionsCreator = ActionsCreator.get(dispatcher);
        store = new LoginStore(mContext);
        store.register(this);
        dispatcher.register(store);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        store.unregister(this);
        dispatcher.unregister(store);
    }

    private void initView() {
        deLoginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    AMUtils.onInactive(mContext, deLoginPhone);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actionsCreator.loginInit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.de_login_sign)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.de_login_sign:
                actionsCreator.login(deLoginPhone.getText().toString().trim(), deLoginPassword.getText().toString().trim());
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(LoginStore.LoginStoreChangeEvent event) {
        switch (store.getType()) {
            case ActionsCreator.LOGIN_INIT:
                checkData();
                break;
            case ActionsCreator.LOGIN_START:
                LoadDialog.show(mContext);
                break;
            case ActionsCreator.LOGIN_SUCCESS:
                // 跳转
            case ActionsCreator.LOGIN_ERROR:
                LoadDialog.dismiss(mContext);
                break;
        }
    }

    private void checkData() {
        if (!TextUtils.isEmpty(store.getOldPhone()) && !TextUtils.isEmpty(store.getOldPassword())) {
            deLoginPhone.setText(store.getOldPhone());
            deLoginPassword.setText(store.getOldPassword());
        }
        if (!store.isExit() && !TextUtils.isEmpty(store.getOldPhone()) && !TextUtils.isEmpty(store.getOldPassword())) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    actionsCreator.login(store.getOldPhone(), store.getOldPassword());
                }
            }, 100);
        }
    }


}
