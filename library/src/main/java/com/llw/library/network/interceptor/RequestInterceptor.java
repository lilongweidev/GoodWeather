package com.llw.library.network.interceptor;

import android.annotation.SuppressLint;

import com.llw.library.network.INetworkRequiredInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器
 *
 * @author llw
 */
public class RequestInterceptor implements Interceptor {
    /**
     * 网络请求信息
     */
    private final INetworkRequiredInfo iNetworkRequiredInfo;

    public RequestInterceptor(INetworkRequiredInfo iNetworkRequiredInfo) {
        this.iNetworkRequiredInfo = iNetworkRequiredInfo;
    }

    /**
     * 拦截
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        //构建器
        Request.Builder builder = chain.request().newBuilder();
        //添加使用环境
        builder.addHeader("os", "android");
        //添加版本号
        builder.addHeader("appVersionCode", this.iNetworkRequiredInfo.getAppVersionCode());
        //添加版本名
        builder.addHeader("appVersionName", this.iNetworkRequiredInfo.getAppVersionName());
        //添加日期时间
        builder.addHeader("datetime", getNowDateTime());
        //返回
        return chain.proceed(builder.build());
    }

    public static String getNowDateTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
