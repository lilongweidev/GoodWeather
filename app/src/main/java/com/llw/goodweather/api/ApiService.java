package com.llw.goodweather.api;

import static com.llw.goodweather.Constant.API_KEY;

import com.llw.goodweather.db.bean.BingResponse;
import com.llw.goodweather.db.bean.DailyResponse;
import com.llw.goodweather.db.bean.HourlyResponse;
import com.llw.goodweather.db.bean.LifestyleResponse;
import com.llw.goodweather.db.bean.NowResponse;
import com.llw.goodweather.db.bean.SearchCityResponse;

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
    Observable<SearchCityResponse> searchCity(@Query("location") String location);

    /**
     * 实况天气
     *
     * @param location 城市ID
     * @return 返回实况天气数据 NowResponse
     */
    @GET("/v7/weather/now?key=" + API_KEY)
    Observable<NowResponse> nowWeather(@Query("location") String location);

    /**
     * 天气预报  (免费订阅)最多可以获得7天的数据
     *
     * @param location 城市id
     * @return 返回天气预报数据 DailyResponse
     */
    @GET("/v7/weather/7d?key=" + API_KEY)
    Observable<DailyResponse> dailyWeather(@Query("location") String location);

    /**
     * 生活指数
     *
     * @param type     可以控制定向获取那几项数据 全部数据 0, 运动指数	1 ，洗车指数	2 ，穿衣指数	3 ，
     *                 钓鱼指数	4 ，紫外线指数  5 ，旅游指数  6，花粉过敏指数	7，舒适度指数	8，
     *                 感冒指数	9 ，空气污染扩散条件指数	10 ，空调开启指数	 11 ，太阳镜指数	12 ，
     *                 化妆指数  13 ，晾晒指数  14 ，交通指数  15 ，防晒指数	16
     * @param location 城市id
     * @return LifestyleResponse 生活指数数据返回
     */
    @GET("/v7/indices/1d?key=" + API_KEY)
    Observable<LifestyleResponse> lifestyle(@Query("type") String type, @Query("location") String location);

    /**
     * 必应每日一图
     *
     * @return BiYingImgResponse 必应壁纸返回
     */
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<BingResponse> bing();

    /**
     * 逐小时预报（未来24小时）之前是逐三小时预报
     *
     * @param location 城市id
     * @return 返回逐小时数据 HourlyResponse
     */
    @GET("/v7/weather/24h?key=" + API_KEY)
    Observable<HourlyResponse> hourlyWeather(@Query("location") String location);

}
