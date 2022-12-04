package com.llw.goodweather.viewmodel;

import androidx.lifecycle.MutableLiveData;

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

    /**
     * 搜索城市
     *
     * @param cityName 城市名称
     */
    public void searchCity(String cityName) {
        new SearchCityRepository().searchCity(searchCityResponseMutableLiveData, failed, cityName);
    }

    /**
     * 实况天气
     *
     * @param cityId 城市ID
     */
    public void nowWeather(String cityId) {
        new WeatherRepository().nowWeather(nowResponseMutableLiveData, failed, cityId);
    }
}
