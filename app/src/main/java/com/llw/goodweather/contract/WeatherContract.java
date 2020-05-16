package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.AirNowCityResponse;
import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.bean.WeatherResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 天气订阅器
 */
public class WeatherContract {

    public static class WeatherPresenter extends BasePresenter<IWeatherView> {

        /**
         * 必应  每日一图
         * @param context
         */
        public void biying(final Context context){
            ApiService service = ServiceGenerator.createService(ApiService.class,1);
            service.biying().enqueue(new NetCallBack<BiYingImgResponse>() {
                @Override
                public void onSuccess(Call<BiYingImgResponse> call, Response<BiYingImgResponse> response) {
                    if(getView() != null){
                        getView().getBiYingResult(response);
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

        /**
         * 空气质量数据
         * @param context
         * @param location
         */
        public void airNowCity(final Context context,String location){
            ApiService service = ServiceGenerator.createService(ApiService.class,0);
            service.getAirNowCity(location).enqueue(new NetCallBack<AirNowCityResponse>() {
                @Override
                public void onSuccess(Call<AirNowCityResponse> call, Response<AirNowCityResponse> response) {
                    if(getView() != null){
                        getView().getAirNowCityResult(response);
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

        /**
         * 天气所有数据
         * @param context
         * @param location
         */
        public void weatherData(final Context context,String location){
            ApiService service = ServiceGenerator.createService(ApiService.class,0);
            service.weatherData(location).enqueue(new NetCallBack<WeatherResponse>() {
                @Override
                public void onSuccess(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    if(getView() != null){
                        getView().getWeatherDataResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if(getView() != null){
                        getView().getWeatherDataFailed();
                    }
                }
            });
        }



    }

    public interface IWeatherView extends BaseView {
        //获取必应每日一图返回
        void getBiYingResult(Response<BiYingImgResponse> response);
        //查询空气质量的数据返回
        void getAirNowCityResult(Response<AirNowCityResponse> response);
        //查询天气所有数据
        void getWeatherDataResult(Response<WeatherResponse> response);
        //天气数据获取错误返回
        void getWeatherDataFailed();
        //错误返回
        void getDataFailed();
    }
}
