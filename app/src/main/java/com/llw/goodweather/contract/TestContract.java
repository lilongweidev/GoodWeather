package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.TestResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

public class TestContract {
    public static class WeatherPresenter extends BasePresenter<ITestView> {
        /**
         * 测试数据
         * @param context
         */
        public void testDataList(final Context context){
            ApiService service = ServiceGenerator.createService(ApiService.class,2);
            String Authorization = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MTAzODg5NTI1NzkzNTA1MjgsInVzZXJuYW1lIjoiMTc2MTIzNDEyMzQiLCJ0eXBlIjoiMTAiLCJleHAiOjE1ODg3NzcxODF9.c2YEAeO1opJFactB8P_Tw5dQV93KAHWb9S_dWGHw8OjhNybzeVS093DUKnmwOCI5n86hr6OskpDcV20ERdjbGfbLIzfTHbneEgiKzwt309hI023ZEGUILaz8eAltXliQq-ODO-Jdbyy_TuXai2mpVBLCyuQ6UpR0NbWlJ2sqeEk";
            service.testList(Authorization).enqueue(new NetCallBack<TestResponse>() {
                @Override
                public void onSuccess(Call<TestResponse> call, Response<TestResponse> response) {
                    if(getView() != null){
                        getView().getTestListResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if(getView() != null){
                        getView().getDataFailed();
                    }
                }
            });
        }
    }

    public interface ITestView extends BaseView {
        //测试数据返回
        void getTestListResult(Response<TestResponse> response);
        //错误返回
        void getDataFailed();
    }
}
