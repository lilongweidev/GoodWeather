package com.llw.goodweather.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.llw.goodweather.db.bean.BingResponse;
import com.llw.goodweather.db.bean.Province;
import com.llw.goodweather.repository.BingRepository;
import com.llw.goodweather.repository.CityRepository;
import com.llw.library.base.BaseViewModel;

import java.util.List;

/**
 * 启动页ViewModel
 * {@link com.llw.goodweather.ui.SplashActivity}
 */
public class SplashViewModel extends BaseViewModel {

    public MutableLiveData<List<Province>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<BingResponse> bingResponseMutableLiveData = new MutableLiveData<>();

    /**
     * 添加城市数据
     */
    public void addCityData(List<Province> provinceList) {
        CityRepository.getInstance().addCityData(provinceList);
    }

    /**
     * 获取所有城市数据
     */
    public void getAllCityData() {
        CityRepository.getInstance().getCityData(listMutableLiveData);
    }

    /**
     * 必应壁纸
     */
    public void bing() {
        BingRepository.getInstance().bing(bingResponseMutableLiveData, failed);
    }
}
