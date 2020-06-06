package com.llw.goodweather.api;


import com.llw.goodweather.bean.AirNowCityResponse;
import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.bean.SearchCityResponse;
import com.llw.goodweather.bean.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API服务接口
 */
public interface ApiService {
    /**
     * https://free-api.heweather.net/s6/weather/now?key=3086e91d66c04ce588a7f538f917c7f4&location=深圳
     *   将地址进一步拆分，将可变的一部分放在注解@GET的地址里面，其中
     *   /s6/weather/now?key=3086e91d66c04ce588a7f538f917c7f4 这一部分在这个接口中又是不变的，变的是location的值
     *   所以将location的参数放入@Query里面，因为是使用的GET请求，所以里面的内容会拼接到地址后面，并且自动会加上 & 符号
     *   Call是retrofit2框架里面的，这个框架是对OKHttp的进一步封装，会让你的使用更加简洁明了，里面放入之前通过接口返回
     *   的JSON字符串生成返回数据实体Bean,Retrofit支持Gson解析实体类,所以，后面的返回值就不用做解析了。
     *   getTodayWeather是这个接口的方法名。这样说应该很清楚了吧
     */

    /**
     * 必应每日一图
     */
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Call<BiYingImgResponse> biying();

    /**
     * 空气质量数据  这个的location要传入市的参数，不再是区/县，否则会提示 permission denied  无访问权限
     */
    @GET("/s6/air/now?key=3086e91d66c04ce588a7f538f917c7f4")
    Call<AirNowCityResponse> getAirNowCity(@Query("location") String location);

    /**
     * 获取所有天气数据，在返回值中再做处理
     * @param location
     * @return
     */
    @GET("/s6/weather?key=3086e91d66c04ce588a7f538f917c7f4")
    Call<WeatherResponse> weatherData(@Query("location") String location);

    /**
     * 搜索城市
     */
    @GET("/find?key=3086e91d66c04ce588a7f538f917c7f4&group=cn&number=10")
    Call<SearchCityResponse> searchCity(@Query("location") String location);

}
