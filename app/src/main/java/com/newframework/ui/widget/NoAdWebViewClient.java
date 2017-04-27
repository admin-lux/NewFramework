package com.newframework.ui.widget;


import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.newframework.utils.ADFilterTool;


/**
 * 屏蔽广告，通过重载WebViewClient
 * <p>
 * Created by MyPC on 2017/4/18.
 */

public class NoAdWebViewClient extends WebViewClient {

//    private String homeurl;
//    private Context context;
//
//    public NoAdWebViewClient(Context context, String homeurl) {
//        this.context = context;
//        this.homeurl = homeurl;
//    }
//
//    @Override
//    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//        if (!url.contains(homeurl)) {
//            if (ADFilterTool.hasNotAd(url)) {
//                return super.shouldInterceptRequest(view, url);
//            } else {
//                return new WebResourceResponse(null, null, null);
//            }
//        } else {
//            return super.shouldInterceptRequest(view, url);
//        }
//    }


    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (ADFilterTool.hasNotAd(url)) {
                return super.shouldInterceptRequest(view, url);
            } else {
                return new WebResourceResponse(null, null, null);
            }
    }
}
