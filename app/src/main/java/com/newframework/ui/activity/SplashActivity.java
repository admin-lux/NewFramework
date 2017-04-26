package com.newframework.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.newframework.R;
import com.newframework.ui.activity.base.BaseActivity;
import com.newframework.utils.NToast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


/**
 * Created by AMing on 16/8/5.
 * Company RongCloud
 */

@RuntimePermissions
public class SplashActivity extends BaseActivity {
    private static final int SYNC_USER_INFO = 9;
    private static final int GIVE_SCORES = 25;

    private Context context;
    private android.os.Handler handler = new android.os.Handler();
    private SharedPreferences sp;
    private String loginid;
    private SharedPreferences.Editor editor;
    private String cacheToken;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        setHeadVisibility(View.GONE);
        context = this;
        sp = getSharedPreferences("config", MODE_PRIVATE);
        cacheToken = sp.getString("loginToken", "");
        loginid = sp.getString("loginid", "");
        editor = sp.edit();
        requestPermission();//申请权限
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermission() {
        SplashActivityPermissionsDispatcher.NeedsPermissionWithCheck(SplashActivity.this);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void NeedsPermission() {
        getPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void OnShowRationale(final PermissionRequest request) {
        //再次执行请求
        request.proceed();

    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void OnPermissionDenied() {
        getPermission();
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void OnNeverAskAgain() {
        NToast.shortToast(this, "不再询问");
    }


//--------------------业务逻辑 start-----------------------------------------------------------------

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    private void getPermission() {
//        if (!isNetworkConnected(context)) {
//            NToast.shortToast(context, getString(R.string.network_not_available));
//            goToLogin();
//            return;
//        }
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                goToLogin();
//            }
//        }, 800);
    }

    private void goToLogin() {
//        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }

}
