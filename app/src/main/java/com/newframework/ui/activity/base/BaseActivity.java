package com.newframework.ui.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.newframework.R;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends FragmentActivity {
    private InnerReceiver mReceiver;// 接收者

    private IntentFilter mFilter;// 过滤器
    protected Context mContext;
    @BindView(R.id.layout_container)
     ViewFlipper mContentView;
    @BindView(R.id.layout_head)
     RelativeLayout layout_head;
    @BindView(R.id.btn_left)
     Button btn_left;
    @BindView(R.id.btn_right)
     TextView btn_right;
    @BindView(R.id.tv_title)
     TextView tv_title;
    @BindDrawable(R.mipmap.actionbar_back)
     Drawable btn_back;
    @BindView(R.id.btn_rightt)
     Button btn_rightt;
    @BindView(R.id.base_layout)
     LinearLayout base_layout;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.layout_base);
        unbinder = ButterKnife.bind(this);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        mContext = this;

        // 初始化公共头部
        btn_back.setBounds(0, 0, btn_back.getMinimumWidth(),
                btn_back.getMinimumHeight());
        setImmersionStatus();
    }

    public void setImmersionStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mContentView.addView(view, lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }


    /**
     * 设置头部是否可见
     *
     * @param visibility
     */
    public void setHeadVisibility(int visibility) {
        layout_head.setVisibility(visibility);
    }

    /**
     * 设置左边是否可见
     *
     * @param visibility
     */
    public void setLeftVisibility(int visibility) {
        btn_left.setVisibility(visibility);
    }

    /**
     * 设置右边是否可见
     *
     * @param visibility
     */
    public void setRightVisibility(int visibility) {
        btn_right.setVisibility(visibility);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        setTitle(getString(titleId), false);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId, boolean flag) {
        setTitle(getString(titleId), flag);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        setTitle(title, false);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, boolean flag) {
        btn_left.setText(title);
        if (flag) {
            btn_left.setCompoundDrawables(null, null, null, null);
        } else {
            btn_left.setCompoundDrawables(btn_back, null, null, null);
        }
    }

    /**
     * 点击左按钮
     */
    public void onLeftClick(View v) {
        hintKbTwo();
        finish();
    }

    /**
     * 点击右文字按钮
     */
    public void onRightClick(View v) {

    }

    /**
     * 点击右图片按钮
     */
    public void onRighttClick(View v) {
    }
    public Button getBtn_left() {
        return btn_left;
    }

    public InnerReceiver getmReceiver() {
        return mReceiver;
    }

    public void setmReceiver(InnerReceiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    public void setBtn_left(Button btn_left) {
        this.btn_left = btn_left;
    }

    public TextView getBtn_right() {
        return btn_right;
    }

    public void setBtn_right(Button btn_right) {
        this.btn_right = btn_right;
    }

    public Button getBtn_rightt() {
        return btn_rightt;
    }

    public void setBtn_rightt(Button btn_rightt) {
        this.btn_rightt = btn_rightt;
    }

    public Drawable getBtn_back() {
        return btn_back;
    }

    public void setBtn_back(Drawable btn_back) {
        this.btn_back = btn_back;
    }

    @Override
    protected void onDestroy() {
        unRegister();
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hintKbTwo();
    }


    /**
     * 注册监听广播
     *
     * @param actions
     */
    protected void register(String... actions) {

        if (mReceiver == null) {
            mReceiver = new InnerReceiver();
        }

        if (mFilter == null) {
            mFilter = new IntentFilter();
        }

        for (String action : actions) {
            if (!TextUtils.isEmpty(action)) {
                mFilter.addAction(action);
            }
        }
        registerReceiver(mReceiver, mFilter);
    }

    /**
     * 注销广播监听服务
     */
    protected void unRegister() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    // 广播接收
    final class InnerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            handleReceive(context, intent);
        }
    }

    /**
     * 处理广播的操做
     */
    protected void handleReceive(Context context, Intent intent) {
    }


    protected void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
