package com.llw.mvplibrary.newnet;

import com.llw.mvplibrary.newnet.environment.NetworkEnvironmentActivity;
import com.llw.mvplibrary.newnet.errorhandler.ExceptionHandle;
import com.llw.mvplibrary.newnet.errorhandler.HttpErrorHandler;
import com.llw.mvplibrary.newnet.interceptor.RequestInterceptor;
import com.llw.mvplibrary.newnet.interceptor.ResponseInterceptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络API
 *
 * @author llw
 */
public class NetworkApi {

    //获取APP运行状态及版本信息，用于日志打印
    private static INetworkRequiredInfo iNetworkRequiredInfo;
    //OkHttp客户端
    private static OkHttpClient okHttpClient;
    //retrofitHashMap
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();
    //API访问地址
    private static String mBaseUrl;
    //是否为正式环境
    private static boolean isFormal = true;

    /**
     * 初始化
     */
    public static void init(INetworkRequiredInfo networkRequiredInfo) {
        iNetworkRequiredInfo = networkRequiredInfo;
        //当初始化这个NetworkApi时，会判断当前App的网络环境
        isFormal = NetworkEnvironmentActivity.isFormalEnvironment(networkRequiredInfo.getApplicationContext());
        /*if (isFormal) {
            //正式环境
            mBaseUrl = "http://api.tianapi.com";
        } else {
            //测试环境
            mBaseUrl = "https://cn.bing.com";
        }*/
    }

    /**
     * 创建serviceClass的实例
     */
    public static <T> T createService(Class<T> serviceClass,int type) {
        getBaseUrl(type);
        return getRetrofit(serviceClass).create(serviceClass);
    }

    /**
     * 修改访问地址
     * @param type
     */
    private static void getBaseUrl(int type) {
        switch (type) {
            //和风天气
            case 0:
                //S6版本接口地址
                mBaseUrl = "https://free-api.heweather.net";
                break;
            //必应每日一图
            case 1:
                mBaseUrl = "https://cn.bing.com";
                break;
            //搜索城市
            case 2:
                mBaseUrl = "https://search.heweather.net";
                break;
            //和风天气  新增
            case 3:
                //V7版本接口地址
                mBaseUrl = "https://devapi.qweather.net";
                break;
            //搜索城市  新增
            case 4:
                //V7版本下的搜索城市地址
                mBaseUrl = "https://geoapi.qweather.net";
                break;
            //APP更新  分发平台更新接口
            case 5:
                //Fr.im更新
                mBaseUrl = "http://api.bq04.com";
                break;
            //随机手机壁纸
            case 6:
                //网络手机壁纸返回地址
                mBaseUrl = "http://service.picasso.adesk.com";
                break;
            default:
                break;
        }
    }

    /**
     * 配置OkHttp
     *
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient() {
        //不为空则说明已经配置过了，直接返回即可。
        if (okHttpClient == null) {
            //OkHttp构建器
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //设置缓存大小
            int cacheSize = 100 * 1024 * 1024;
            //设置OkHttp网络缓存
            builder.cache(new Cache(iNetworkRequiredInfo.getApplicationContext().getCacheDir(), cacheSize));
            //设置网络请求超时时长，这里设置为10s
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS).build();
            //添加请求拦截器，如果接口有请求头的话，可以放在这个拦截器里面
            builder.addInterceptor(new RequestInterceptor(iNetworkRequiredInfo));
            //添加返回拦截器，可用于查看接口的请求耗时，对于网络优化有帮助
            builder.addInterceptor(new ResponseInterceptor());
            //当程序在debug过程中则打印数据日志，方便调试用。
            if (iNetworkRequiredInfo != null && iNetworkRequiredInfo.isDebug()) {
                //iNetworkRequiredInfo不为空且处于debug状态下则初始化日志拦截器
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                //设置要打印日志的内容等级，BODY为主要内容，还有BASIC、HEADERS、NONE。
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //将拦截器添加到OkHttp构建器中
                builder.addInterceptor(httpLoggingInterceptor);
            }
            //OkHttp配置完成
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    /**
     * 配置Retrofit
     *
     * @param serviceClass 服务类
     * @return Retrofit
     */
    private static Retrofit getRetrofit(Class serviceClass) {
        if (retrofitHashMap.get(mBaseUrl + serviceClass.getName()) != null) {
            //刚才上面定义的Map中键是String，值是Retrofit，当键不为空时，必然有值，有值则直接返回。
            return retrofitHashMap.get(mBaseUrl + serviceClass.getName());
        }
        //初始化Retrofit  Retrofit是对OKHttp的封装，通常是对网络请求做处理，也可以处理返回数据。
        //Retrofit构建器
        Retrofit.Builder builder = new Retrofit.Builder();
        //设置访问地址
        builder.baseUrl(mBaseUrl);
        //设置OkHttp客户端，传入上面写好的方法即可获得配置后的OkHttp客户端。
        builder.client(getOkHttpClient());
        //设置数据解析器 会自动把请求返回的结果（json字符串）通过Gson转化工厂自动转化成与其结构相符的实体Bean
        builder.addConverterFactory(GsonConverterFactory.create());
        //设置请求回调，使用RxJava 对网络返回进行处理
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        //retrofit配置完成
        Retrofit retrofit = builder.build();
        //放入Map中
        retrofitHashMap.put(mBaseUrl + serviceClass.getName(), retrofit);
        //最后返回即可
        return retrofit;
    }

    /**
     * 配置RxJava 完成线程的切换，如果是Kotlin中完全可以直接使用协程
     *
     * @param observer 这个observer要注意不要使用lifecycle中的Observer
     * @param <T>      泛型
     * @return Observable
     */
    public static <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream
                        .subscribeOn(Schedulers.io())//线程订阅
                        .observeOn(AndroidSchedulers.mainThread())//观察Android主线程
                        .map(NetworkApi.<T>getAppErrorHandler())//判断有没有500的错误，有则进入getAppErrorHandler
                        .onErrorResumeNext(new HttpErrorHandler<T>());//判断有没有400的错误
                //这里还少了对异常
                //订阅观察者
                observable.subscribe(observer);
                return observable;
            }
        };
    }

    /**
     * 错误码处理
     */
    protected static <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //当response返回出现500之类的错误时
                if (response instanceof BaseResponse && ((BaseResponse) response).responseCode >= 500) {
                    //通过这个异常处理，得到用户可以知道的原因
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((BaseResponse) response).responseCode;
                    exception.message = ((BaseResponse) response).responseError != null ? ((BaseResponse) response).responseError : "";
                    throw exception;
                }
                return response;
            }
        };
    }


}
