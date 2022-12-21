package com.llw.goodweather.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.llw.goodweather.db.bean.AirResponse;
import com.llw.goodweather.db.bean.DailyResponse;
import com.llw.goodweather.db.bean.HourlyResponse;
import com.llw.goodweather.db.bean.LifestyleResponse;
import com.llw.goodweather.db.bean.MyCity;
import com.llw.goodweather.db.bean.NowResponse;
import com.llw.goodweather.db.bean.Province;
import com.llw.goodweather.db.bean.SearchCityResponse;
import com.llw.goodweather.repository.CityRepository;
import com.llw.goodweather.repository.SearchCityRepository;
import com.llw.goodweather.repository.WeatherRepository;
import com.llw.library.base.BaseViewModel;

import java.util.List;

/**
 * 主页面ViewModel
 * {@link com.llw.goodweather.ui.MainActivity}
 */
public class MainViewModel extends BaseViewModel {

    public MutableLiveData<SearchCityResponse> searchCityResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NowResponse> nowResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<DailyResponse> dailyResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<LifestyleResponse> lifestyleResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Province>> cityMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<HourlyResponse> hourlyResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<AirResponse> airResponseMutableLiveData = new MutableLiveData<>();

    /**
     * 搜索城市
     *
     * @param cityName 城市名称
     */
    public void searchCity(String cityName) {
        SearchCityRepository.getInstance().searchCity(searchCityResponseMutableLiveData, failed, cityName);
    }

    /**
     * 实况天气
     *
     * @param cityId 城市ID
     */
    public void nowWeather(String cityId) {
        WeatherRepository.getInstance().nowWeather(nowResponseMutableLiveData, failed, cityId);
    }

    /**
     * 天气预报
     *
     * @param cityId 城市ID
     */
    public void dailyWeather(String cityId) {
        WeatherRepository.getInstance().dailyWeather(dailyResponseMutableLiveData, failed, cityId);
    }

    /**
     * 生活指数
     *
     * @param cityId 城市ID
     */
    public void lifestyle(String cityId) {
        WeatherRepository.getInstance().lifestyle(lifestyleResponseMutableLiveData, failed, cityId);
    }

    /**
     * 获取行政区数据
     */
    public void getAllCity() {
        CityRepository.getInstance().getCityData(cityMutableLiveData);
    }

    /**
     * 逐小时天气预报
     *
     * @param cityId 城市ID
     */
    public void hourlyWeather(String cityId) {
        WeatherRepository.getInstance().hourlyWeather(hourlyResponseMutableLiveData, failed, cityId);
    }

    /**
     * 逐小时天气预报
     *
     * @param cityId 城市ID
     */
    public void airWeather(String cityId) {
        WeatherRepository.getInstance().airWeather(airResponseMutableLiveData, failed, cityId);
    }

    /**
     * 添加我的城市数据，在定位之后添加数据
     */
    public void addMyCityData(String cityName) {
        MyCity myCity = new MyCity(cityName);
        CityRepository.getInstance().addMyCityData(myCity);
    }
}
