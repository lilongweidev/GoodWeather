package com.llw.goodweather.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.LifestyleResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.bean.SearchCityResponse;
import com.llw.goodweather.repository.SearchCityRepository;
import com.llw.goodweather.repository.WeatherRepository;
import com.llw.library.base.BaseViewModel;

/**
 * 主页面ViewModel
 * {@link com.llw.goodweather.MainActivity}
 */
public class MainViewModel extends BaseViewModel {

    public MutableLiveData<SearchCityResponse> searchCityResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NowResponse> nowResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<DailyResponse> dailyResponseMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<LifestyleResponse> lifestyleResponseMutableLiveData = new MutableLiveData<>();

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
}
