package com.llw.goodweather.eventbus;

import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.WeatherResponse;

import java.util.List;
/**
 * 热门城市天气预报事件
 * 修改为另一个实体数据  V7
 */
public class ForecastEvent {
//    public List<WeatherResponse.HeWeather6Bean.DailyForecastBean> mForecastBean;
    public List<DailyResponse.DailyBean> mDailyBean;


    public ForecastEvent(List<DailyResponse.DailyBean> dailyBean) {
        this.mDailyBean = dailyBean;
    }
}
