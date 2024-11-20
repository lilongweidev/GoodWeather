package com.llw.goodweather.api;


import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.LifestyleResponse;
import com.llw.goodweather.bean.MinutePrecResponse;
import com.llw.goodweather.bean.MoreAirFiveResponse;
import com.llw.goodweather.bean.SunMoonResponse;
import com.llw.goodweather.bean.WallPaperResponse;
import com.llw.goodweather.bean.WarningResponse;
import com.llw.goodweather.bean.WorldCityResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.bean.WallPaper;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.llw.goodweather.utils.Constant.API_KEY;
import static com.llw.goodweather.utils.Constant.UPDATE_API_TOKEN;
import static com.llw.goodweather.utils.Constant.UPDATE_USER_ID;

/**
 * API服务接口
 *
 * @author llw
 */
public interface ApiService {
    /**
     * https://free-api.heweather.net/s6/weather/now?key="+API_KEY+"&location=深圳
     *   将地址进一步拆分，将可变的一部分放在注解@GET的地址里面，其中
     *   /s6/weather/now?key="+API_KEY+" 这一部分在这个接口中又是不变的，变的是location的值
     *   所以将location的参数放入@Query里面，因为是使用的GET请求，所以里面的内容会拼接到地址后面，并且自动会加上 & 符号
     *   Call是retrofit2框架里面的，这个框架是对OKHttp的进一步封装，会让你的使用更加简洁明了，里面放入之前通过接口返回
     *   的JSON字符串生成返回数据实体Bean,Retrofit支持Gson解析实体类,所以，后面的返回值就不用做解析了。
     *   getTodayWeather是这个接口的方法名。这样说应该很清楚了吧
     */

    /**
     * 必应每日一图
     *
     * @return BiYingImgResponse 必应壁纸返回
     */
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<BiYingImgResponse> biying();

    /**********       以下为 V7版本API使用     **************/

    /**
     * 实况天气
     *
     * @param location 城市名
     * @return 返回实况天气数据 NowResponse
     */
    @GET("/v7/weather/now?key=" + API_KEY + "&gzip=n")
    Observable<NowResponse> nowWeather(@Query("location") String location);

    /**
     * 天气预报  因为是开发者所以最多可以获得15天的数据，但是如果你是普通用户，那么最多只能获得三天的数据
     * 分为 3天、7天、10天、15天 四种情况，这是时候就需要动态的改变请求的url
     *
     * @param type     天数类型  传入3d / 7d / 10d / 15d  通过Path拼接到请求的url里面
     * @param location 城市id
     * @return 返回天气预报数据 DailyResponse
     */
    @GET("/v7/weather/{type}?key=" + API_KEY)
    Observable<DailyResponse> dailyWeather(@Path("type") String type, @Query("location") String location);

    /**
     * 逐小时预报（未来24小时）之前是逐三小时预报
     *
     * @param location 城市id
     * @return 返回逐小时数据 MoreAirFiveResponse
     */
    @GET("/v7/weather/24h?key=" + API_KEY)
    Observable<HourlyResponse> hourlyWeather(@Query("location") String location);

    /**
     * 当天空气质量
     *
     * @param location 城市id
     * @return 返回当天空气质量数据 MoreAirFiveResponse
     */
    @GET("/v7/air/now?key=" + API_KEY)
    Observable<AirNowResponse> airNowWeather(@Query("location") String location);

    /**
     * 空气质量5天预报
     *
     * @param location 城市id
     * @return 返回空气质量5天预报数据 MoreAirFiveResponse
     */
    @GET("/v7/air/5d?key=" + API_KEY)
    Observable<MoreAirFiveResponse> airFiveWeather(@Query("location") String location);

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
    Observable<LifestyleResponse> lifestyle(@Query("type") String type,
                                      @Query("location") String location);

    /**
     * 搜索城市  V7版本  模糊搜索，国内范围 返回10条数据
     *
     * @param location 城市名
     * @param mode     exact 精准搜索  fuzzy 模糊搜索
     * @return NewSearchCityResponse 搜索城市数据返回
     */
    @GET("/v2/city/lookup?key=" + API_KEY + "&range=cn")
    Observable<NewSearchCityResponse> newSearchCity(@Query("location") String location,
                                              @Query("mode") String mode);

    /**
     * 世界城市
     *
     * @param range cn表示国内  world 表示全世界
     * @return WorldCityResponse 世界城市数据返回
     */
    @GET("/v2/city/top?key=" + API_KEY + "&number=20")
    Observable<WorldCityResponse> worldCity(@Query("range") String range);

    /**
     * 当前城市灾害预警
     *
     * @param location 城市id ，通过搜索城市获得
     * @return WarningResponse 灾害预警返回
     */
    @GET("/v7/warning/now?key=" + API_KEY)
    Observable<WarningResponse> nowWarn(@Query("location") String location);

    /**
     * APP版本更新
     *
     * @return AppVersion 版本信息返回
     */
    @GET("/apps/latest/" + UPDATE_USER_ID + "?api_token=" + UPDATE_API_TOKEN)
    Observable<AppVersion> getAppInfo();

    /**
     * 太阳和月亮  日出日落、月升月落
     *
     * @param location 位置
     * @param date     日期
     * @return SunMoonResponse 太阳和月亮数据返回
     */
    @GET("/v7/astronomy/sunmoon?key=" + API_KEY)
    Observable<SunMoonResponse> getSunMoon(@Query("location") String location, @Query("date") String date);

    /**
     * 手机壁纸API
     *
     * @return WallPaperResponse 网络壁纸数据返回
     */
    @GET("/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    Observable<WallPaperResponse> getWallPaper();

    /**
     * 分钟级降水 最近两小时内
     *
     * @param location 经纬度拼接字符串，使用英文逗号分隔,经度在前纬度在后
     * @return
     */
    @GET("/v7/minutely/5m?key=" + API_KEY)
    Observable<MinutePrecResponse> getMinutePrec(@Query("location") String location);

}
