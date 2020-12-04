package com.llw.mvplibrary.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 接口地址管理
 *
 * @author llw
 */
public class ServiceGenerator {


    /**
     * https://free-api.heweather.net/s6/weather/now?key=3086e91d66c04ce588a7f538f917c7f4&location=深圳
     * 将上方的API接口地址进行拆分得到不变的一部分,实际开发中可以将这一部分作为服务器的ip访问地址
     */
    //地址
    public static String BASE_URL = null;

    private static String urlType(int type) {
        switch (type) {
            //和风天气
            case 0:
                //S6版本接口地址
                BASE_URL = "https://free-api.heweather.net";
                break;
            //必应每日一图
            case 1:
                BASE_URL = "https://cn.bing.com";
                break;
            //搜索城市
            case 2:
                BASE_URL = "https://search.heweather.net";
                break;
            //和风天气  新增
            case 3:
                //V7版本接口地址
                BASE_URL = "https://devapi.qweather.net";
                break;
            //搜索城市  新增
            case 4:
                //V7版本下的搜索城市地址
                BASE_URL = "https://geoapi.qweather.net";
                break;
            //APP更新  分发平台更新接口
            case 5:
                //Fr.im更新
                BASE_URL = "http://api.bq04.com";
                break;
            //随机手机壁纸
            case 6:
                //网络手机壁纸返回地址
                BASE_URL = "http://service.picasso.adesk.com";
                break;
            default:
                break;
        }
        return BASE_URL;
    }

    /**
     * 创建服务  参数就是API服务
     *
     * @param serviceClass 服务接口
     * @param type         接口类型
     * @param <T>          泛型规范
     * @return api接口服务
     */
    public static <T> T createService(Class<T> serviceClass, int type) {

        //创建OkHttpClient构建器对象
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        //设置请求超时的时间，这里是10秒
        okHttpClientBuilder.connectTimeout(20000, TimeUnit.MILLISECONDS);

        //消息拦截器  因为有时候接口不同在排错的时候 需要先从接口的响应中做分析。利用了消息拦截器可以清楚的看到接口返回的所有内容
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        //setlevel用来设置日志打印的级别，共包括了四个级别：NONE,BASIC,HEADER,BODY
        //BASEIC:请求/响应行
        //HEADER:请求/响应行 + 头
        //BODY:请求/响应航 + 头 + 体
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //为OkHttp添加消息拦截器
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        //在Retrofit中设置httpclient
        //设置地址  就是上面的固定地址,如果你是本地访问的话，可以拼接上端口号  例如 +":8080"
        Retrofit retrofit = new Retrofit.Builder().baseUrl(urlType(type))
                //用Gson把服务端返回的json数据解析成实体
                .addConverterFactory(GsonConverterFactory.create())
                //放入OKHttp，之前说过retrofit是对OkHttp的进一步封装
                .client(okHttpClientBuilder.build())
                .build();
        //返回这个创建好的API服务
        return retrofit.create(serviceClass);
    }

}
