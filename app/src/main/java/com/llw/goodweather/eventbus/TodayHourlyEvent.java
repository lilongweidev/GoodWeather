package com.llw.goodweather.eventbus;

import com.llw.goodweather.bean.WeatherResponse;

import java.util.List;
/**
 * 热门城市当天逐三小时天气信息事件
 */
public class TodayHourlyEvent {
    public List<WeatherResponse.HeWeather6Bean.HourlyBean> mHourlyBean;


    public TodayHourlyEvent(List<WeatherResponse.HeWeather6Bean.HourlyBean> hourlyBean) {
        this.mHourlyBean = hourlyBean;
    }
}
