package com.llw.goodweather.api;


import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.bean.LifeStyleResponse;
import com.llw.goodweather.bean.TodayResponse;
import com.llw.goodweather.bean.WeatherForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API服务接口
 */
public interface ApiService {

    /**
     * 当天天气查询
     * https://free-api.heweather.net/s6/weather/now?key=3086e91d66c04ce588a7f538f917c7f4&location=深圳
     *   将地址进一步拆分，将可变的一部分放在注解@GET的地址里面，其中
     *   /s6/weather/now?key=3086e91d66c04ce588a7f538f917c7f4 这一部分在这个接口中又是不变的，变的是location的值
     *   所以将location的参数放入@Query里面，因为是使用的GET请求，所以里面的内容会拼接到地址后面，并且自动会加上 & 符号
     *   Call是retrofit2框架里面的，这个框架是对OKHttp的进一步封装，会让你的使用更加简洁明了，里面放入之前通过接口返回
     *   的JSON字符串生成返回数据实体Bean,Retrofit支持Gson解析实体类,所以，后面的返回值就不用做解析了。
     *   getTodayWeather是这个接口的方法名。这样说应该很清楚了吧
     * @param location  区/县
     * @return
     */
    @GET("/s6/weather/now?key=3086e91d66c04ce588a7f538f917c7f4")
    Call<TodayResponse> getTodayWeather(@Query("location") String location);


    /**
     * 未来3 - 7天天气预报
     */
    @GET("/s6/weather/forecast?key=3086e91d66c04ce588a7f538f917c7f4")
    Call<WeatherForecastResponse> getWeatherForecast(@Query("location") String location);

    /**
     * 生活指数
     */
    @GET("/s6/weather/lifestyle?key=3086e91d66c04ce588a7f538f917c7f4")
    Call<LifeStyleResponse> getLifestyle(@Query("location") String location);

    /**
     * 必应每日一图
     */
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Call<BiYingImgResponse> biying();

}
