package com.newframework;

import android.support.multidex.MultiDexApplication;

import com.newframework.utils.AuthImageDownloader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


public class App extends MultiDexApplication {

    private static App mInstance;

    private static DisplayImageOptions options;

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;
        init();
    }
    /**
     * 初始化操作
     */
    private void init() {
        initImageLoader();
    }


    /**
     * 图片加载模块初始化
     */
    private void initImageLoader() {
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.default_useravatar)
                .showImageOnFail(R.mipmap.default_useravatar)
                .showImageOnLoading(R.mipmap.default_useravatar)
                .displayer(new FadeInBitmapDisplayer(300))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        //初始化图片下载组件
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(200)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .defaultDisplayImageOptions(options)
                .imageDownloader(new AuthImageDownloader(this))
                .build();
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    public static DisplayImageOptions getOptions() {
        return options;
    }

    public static App getInstance() {
        return mInstance;
    }
}
