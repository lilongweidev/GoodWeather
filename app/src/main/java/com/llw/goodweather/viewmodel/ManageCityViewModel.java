package com.llw.goodweather.viewmodel;


import androidx.lifecycle.MutableLiveData;
import com.llw.goodweather.db.bean.MyCity;
import com.llw.goodweather.repository.CityRepository;
import com.llw.library.base.BaseViewModel;

import java.util.List;

/**
 * 管理城市ViewModel
 * {@link com.llw.goodweather.ui.ManageCityActivity}
 */
public class ManageCityViewModel extends BaseViewModel {

    public MutableLiveData<List<MyCity>> listMutableLiveData = new MutableLiveData<>();

    /**
     * 获取所有城市数据
     */
    public void getAllCityData() {
        CityRepository.getInstance().getMyCityData(listMutableLiveData);
    }

}
