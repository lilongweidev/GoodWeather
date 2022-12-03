package com.llw.goodweather;

import com.baidu.location.LocationClient;
import com.llw.library.base.BaseApplication;
import com.llw.library.network.NetworkApi;

public class WeatherApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //使用定位需要同意隐私合规政策
        LocationClient.setAgreePrivacy(true);
        //初始化网络框架
        NetworkApi.init(new NetworkRequiredInfo(this));
    }
}
