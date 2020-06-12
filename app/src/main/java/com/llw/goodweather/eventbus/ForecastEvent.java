package com.llw.goodweather.eventbus;

import com.llw.goodweather.bean.WeatherResponse;

import java.util.List;
/**
 * 热门城市天气预报事件
 */
public class ForecastEvent {
    public List<WeatherResponse.HeWeather6Bean.DailyForecastBean> mForecastBean;


    public ForecastEvent(List<WeatherResponse.HeWeather6Bean.DailyForecastBean> forecastBean) {
        this.mForecastBean = forecastBean;
    }
}
