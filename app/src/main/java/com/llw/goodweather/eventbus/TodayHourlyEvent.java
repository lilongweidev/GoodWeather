package com.llw.goodweather.eventbus;

import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.WeatherResponse;

import java.util.List;
/**
 * 热门城市当天逐三小时天气信息事件
 * 修改为另一个实体数据  V7
 */
public class TodayHourlyEvent {
//    public List<WeatherResponse.HeWeather6Bean.HourlyBean> mHourlyBean;
    public List<HourlyResponse.HourlyBean> mHourlyBean;

    public TodayHourlyEvent(List<HourlyResponse.HourlyBean> hourlyBean) {
        this.mHourlyBean = hourlyBean;
    }
}
