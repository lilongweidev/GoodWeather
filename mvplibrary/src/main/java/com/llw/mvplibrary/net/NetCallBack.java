package com.llw.mvplibrary.net;

import android.util.Log;

import com.google.gson.Gson;
import com.llw.mvplibrary.base.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求回调
 *
 * @param <T>
 */
public abstract class NetCallBack<T> implements Callback<T> {//这里实现了retrofit2.Callback

    //访问成功回调
    @Override
    public void onResponse(Call<T> call, Response<T> response) {//数据返回
        if (response.body() != null && response.isSuccessful()) {

            onSuccess(call, response);

            BaseResponse baseResponse = new Gson().fromJson(new Gson().toJson(response.body()), BaseResponse.class);
            if (baseResponse.getCode() == 400) {
                Log.e("Error", 400 + "");
                onSuccess(call, response);
            } else if (baseResponse.getCode() == 404) {//404
                Log.e("Error", 404 + "");
                onSuccess(call, response);
            } else if (baseResponse.getCode() == 500) {//500
                Log.e("Error", 500 + "");
                onSuccess(call, response);
            } else {//无异常则返回数据
                onSuccess(call, response);
            }
        } else {
            onFailed();
        }
    }

    //访问失败回调
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("data str", t.toString());
        onFailed();
    }

    //数据返回
    public abstract void onSuccess(Call<T> call, Response<T> response);

    //失败异常
    public abstract void onFailed();


}
