package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HotCityResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.bean.WeatherResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 热门城市订阅器
 */
public class HotCityWeatherContract {

    public static class HotCityPresenter extends BasePresenter<IHotCityView> {

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
                        getView().getDataFailed();
                    }
                }
            });
        }

        /**
         * 实况天气  V7版本
         * @param location  城市名
         */
        public void nowWeather(String location){//这个3 表示使用新的V7API访问地址
            ApiService service = ServiceGenerator.createService(ApiService.class,3);
            service.nowWeather(location).equals(new NetCallBack<NowResponse>() {
                @Override
                public void onSuccess(Call<NowResponse> call, Response<NowResponse> response) {
                    if(getView() != null){
                        getView().getNowResult(response);
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
         * 天气预报  V7版本   7d 表示天气的数据 为了和之前看上去差别小一些，这里先用七天的
         * @param location   城市名
         */
        public void dailyWeather(String location){//这个3 表示使用新的V7API访问地址
            ApiService service = ServiceGenerator.createService(ApiService.class,3);
            service.dailyWeather("7d",location).equals(new NetCallBack<DailyResponse>() {
                @Override
                public void onSuccess(Call<DailyResponse> call, Response<DailyResponse> response) {
                    if(getView() != null){
                        getView().getDailyResult(response);
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
         * 逐小时预报（未来24小时）
         * @param location   城市名
         */
        public void hourlyWeather(String location){
            ApiService service = ServiceGenerator.createService(ApiService.class,3);
            service.hourlyWeather(location).equals(new NetCallBack<HourlyResponse>() {
                @Override
                public void onSuccess(Call<HourlyResponse> call, Response<HourlyResponse> response) {
                    if(getView() != null){
                        getView().getHourlyResult(response);
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

    public interface IHotCityView extends BaseView {
        //查询天气所有数据
        void getWeatherDataResult(Response<WeatherResponse> response);

        /*               以下为V7版本新增               */
        //实况天气
        void getNowResult(Response<NowResponse> response);
        //天气预报  7天
        void getDailyResult(Response<DailyResponse> response);
        //逐小时天气预报
        void getHourlyResult(Response<HourlyResponse> response);

        //错误返回
        void getDataFailed();
    }
}
