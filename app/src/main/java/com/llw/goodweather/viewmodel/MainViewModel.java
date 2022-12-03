package com.llw.goodweather.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.llw.goodweather.bean.SearchCityResponse;
import com.llw.goodweather.repository.SearchCityRepository;
import com.llw.library.base.BaseViewModel;

/**
 * 主页面ViewModel
 * {@link com.llw.goodweather.MainActivity}
 */
public class MainViewModel extends BaseViewModel {

    public MutableLiveData<SearchCityResponse> searchCityResponseMutableLiveData = new MutableLiveData<>();

    /**
     * 搜索成功
     * @param cityName 城市名称
     */
    public void searchCity(String cityName) {
        new SearchCityRepository().searchCity(searchCityResponseMutableLiveData, failed, cityName);
    }
}
