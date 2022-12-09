package com.llw.goodweather.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.llw.goodweather.Constant;
import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.db.bean.SearchCityResponse;
import com.llw.library.network.ApiType;
import com.llw.library.network.NetworkApi;
import com.llw.library.network.observer.BaseObserver;

/**
 * 搜索城市存储库，数据处理
 */
@SuppressLint("CheckResult")
public class SearchCityRepository {

    private static final String TAG = SearchCityRepository.class.getSimpleName();

    private static final class SearchCityRepositoryHolder {
        private static final SearchCityRepository mInstance = new SearchCityRepository();
    }

    public static SearchCityRepository getInstance() {
        return SearchCityRepository.SearchCityRepositoryHolder.mInstance;
    }

    /**
     * 搜索城市
     *
     * @param responseLiveData 成功数据
     * @param failed           错误信息
     * @param cityName         城市名称
     */
    public void searchCity(MutableLiveData<SearchCityResponse> responseLiveData,
                           MutableLiveData<String> failed, String cityName) {
        String type = "搜索城市-->";
        NetworkApi.createService(ApiService.class, ApiType.SEARCH).searchCity(cityName)
                .compose(NetworkApi.applySchedulers(new BaseObserver<>() {
                    @Override
                    public void onSuccess(SearchCityResponse searchCityResponse) {
                        if (searchCityResponse == null) {
                            failed.postValue("搜索城市数据为null，请检查城市名称是否正确。");
                            return;
                        }
                        //请求接口成功返回数据，失败返回状态码
                        if (Constant.SUCCESS.equals(searchCityResponse.getCode())) {
                            responseLiveData.postValue(searchCityResponse);
                        } else {
                            failed.postValue(type + searchCityResponse.getCode());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.e(TAG, "onFailure: " + e.getMessage());
                        failed.postValue(type + e.getMessage());
                    }
                }));
    }
}
