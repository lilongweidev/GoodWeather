package com.llw.goodweather.api;

import static com.llw.goodweather.Constant.API_KEY;

import com.llw.goodweather.bean.SearchCityResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API服务接口
 */
public interface ApiService {

    /**
     * 搜索城市  模糊搜索，国内范围 返回10条数据
     *
     * @param location 城市名
     * @return NewSearchCityResponse 搜索城市数据返回
     */
    @GET("/v2/city/lookup?key=" + API_KEY + "&range=cn")
    //https://geoapi.qweather.com/v2/city/lookup?location=beij&key=d4a619bfe3244190bfa84bb468c14316
    Observable<SearchCityResponse> searchCity(@Query("location") String location);
}
