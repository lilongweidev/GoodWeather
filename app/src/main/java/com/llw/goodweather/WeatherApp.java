package com.llw.goodweather;

import android.app.Application;

import com.baidu.location.LocationClient;

public class WeatherApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //使用定位需要同意隐私合规政策
        LocationClient.setAgreePrivacy(true);
    }
}
