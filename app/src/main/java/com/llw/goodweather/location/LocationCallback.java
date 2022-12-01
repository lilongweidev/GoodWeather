package com.llw.goodweather.location;

import com.baidu.location.BDLocation;

/**
 * 定位接口
 */
public interface LocationCallback {
    /**
     * 接收定位
     * @param bdLocation 定位数据
     */
    void onReceiveLocation(BDLocation bdLocation);
}
