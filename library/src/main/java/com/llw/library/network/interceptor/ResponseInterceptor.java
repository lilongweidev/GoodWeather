package com.llw.library.network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 返回拦截器(响应拦截器)
 *
 * @author llw
 */
public class ResponseInterceptor implements Interceptor {

    private static final String TAG = ResponseInterceptor.class.getSimpleName();

    /**
     * 拦截
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        Log.i(TAG, "requestSpendTime=" + (System.currentTimeMillis() - requestTime) + "ms");
        return response;
    }
}
