package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.HotCityResponse;
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

    }

    public interface IHotCityView extends BaseView {
        //查询天气所有数据
        void getWeatherDataResult(Response<WeatherResponse> response);
        //错误返回
        void getDataFailed();
    }
}
