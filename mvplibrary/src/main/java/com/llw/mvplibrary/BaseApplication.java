package com.llw.mvplibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.llw.mvplibrary.utils.ActivityManager;

/**
 * 工程管理
 */
public class BaseApplication extends Application {
    private static ActivityManager activityManager;
    @SuppressLint("StaticFieldLeak")
    private  static BaseApplication application;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //声明Activity管理
        activityManager=new ActivityManager();
        context = getApplicationContext();
        application=this;

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static ActivityManager getActivityManager() {
        return activityManager;
    }

    //内容提供器
    public static Context getContext(){
        return context;
    }

    public static BaseApplication getApplication() {
        return application;
    }
}

