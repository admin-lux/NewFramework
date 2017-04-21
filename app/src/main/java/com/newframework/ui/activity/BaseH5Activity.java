package com.newframework.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.newframework.App;
import com.newframework.R;
import com.newframework.ui.widget.LoadDialog;
import com.newframework.ui.widget.NoAdWebViewClient;

/**
 * h5父类
 *
 * @author Rick
 */
public abstract class BaseH5Activity extends BaseActivity {
    /**
     * 访问的url
     */
    public static final String URL = "";
    /**
     * 界面标题
     */
    public static final String TITLE = "title";

    protected WebView mWebView;
    protected LinearLayout mContainLl;

    public String mWebTitle;//webview的标题

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_h5);

        initWebView();
        initData();
    }

    private void initData() {
        if (getIntent().hasExtra(TITLE)) {
            setTitle(getIntent().getStringExtra(TITLE));
        }
    }

    public void initWebView() {
        mWebView = (WebView) findViewById(R.id.base_h5_webview);
        mContainLl = (LinearLayout) findViewById(R.id.abs_h5_bottom_container);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        if (Build.VERSION.SDK_INT > 19)
            mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        else
            mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        mWebView.getSettings().setDisplayZoomControls(false); //隐藏webview缩放按钮
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSavePassword(false);
        mWebView.getSettings().setSaveFormData(false);
        mWebView.getSettings().setGeolocationEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);// 默认缩放模式

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mWebTitle = title;
            }
        });
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                LoadDialog.show(mContext);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
////                super.onPageFinished(view, url);
//                LoadDialog.dismiss(mContext);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return true;
//            }
//        });
        mWebView.setWebViewClient(new NoAdWebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LoadDialog.show(mContext);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
                LoadDialog.dismiss(mContext);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.addJavascriptInterface(new TokenScripInterface(), "token");
    }

    @SuppressLint("JavascriptInterface")
    public void setJsInterface(Object jsInterface, String name) {
        mWebView.addJavascriptInterface(jsInterface, name);
    }

    /**
     * 打开页面
     *
     * @param url
     */
    protected void openUrl(String url) {
        mWebView.loadUrl(url);
    }

    /**
     * 填充布局
     *
     * @param view
     */
    public void setBottomView(View view) {
        mContainLl.setVisibility(View.VISIBLE);
        mContainLl.removeAllViews();
        mContainLl.addView(view);
    }


    /**
     * 前端H5与终端Native之间的交互函数定义。
     */
    class TokenScripInterface {
        TokenScripInterface() {
        }

        @JavascriptInterface
        public String getToken() {
            return App.getInstance().getSharedPreferences("config", App.MODE_PRIVATE).getString("verify_token", "");
        }
    }
}

