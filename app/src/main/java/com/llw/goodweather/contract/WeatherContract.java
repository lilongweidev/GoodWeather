package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.AirNowCityResponse;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.LifestyleResponse;
import com.llw.goodweather.bean.NowResponse;
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


        /***************** 以下为使用V7版本而新增的接口方法，后期将都会使用这些方法，请注意 ****************/

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
                        getView().getWeatherDataFailed();
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
                        getView().getWeatherDataFailed();
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
                        getView().getWeatherDataFailed();
                    }
                }
            });
        }

        /**
         * 当天空气质量
         * @param location   城市名
         */
        public void airNowWeather(String location){
            ApiService service = ServiceGenerator.createService(ApiService.class,3);
            service.airNowWeather(location).equals(new NetCallBack<AirNowResponse>() {
                @Override
                public void onSuccess(Call<AirNowResponse> call, Response<AirNowResponse> response) {
                    if(getView() != null){
                        getView().getAirNowResult(response);
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

        /**
         * 生活指数
         * @param location   城市名  type中的"1,2,3,5,6,8,9,10"，表示只获取这8种类型的指数信息，同样是为了对应之前的界面UI
         */
        public void Lifestyle(String location){
            ApiService service = ServiceGenerator.createService(ApiService.class,3);
            service.Lifestyle("1,2,3,5,6,8,9,10",location).equals(new NetCallBack<LifestyleResponse>() {
                @Override
                public void onSuccess(Call<LifestyleResponse> call, Response<LifestyleResponse> response) {
                    if(getView() != null){
                        getView().getLifestyleResult(response);
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


        /*                以下为V7版本新增               */

        //实况天气
        void getNowResult(Response<NowResponse> response);
        //天气预报  7天
        void getDailyResult(Response<DailyResponse> response);
        //逐小时天气预报
        void getHourlyResult(Response<HourlyResponse> response);
        //空气质量
        void getAirNowResult(Response<AirNowResponse> response);
        //生活指数
        void getLifestyleResult(Response<LifestyleResponse> response);

        //错误返回
        void getDataFailed();
    }
}
