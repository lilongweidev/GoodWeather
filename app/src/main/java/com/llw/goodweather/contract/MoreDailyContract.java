package com.llw.goodweather.contract;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.WorldCityResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 更多天气预报订阅器
 *
 * @author llw
 */
public class MoreDailyContract {

    public static class MoreDailyPresenter extends BasePresenter<IMoreDailyView> {

        /**
         * 更多天气预报  V7
         *
         * @param location 城市id
         */
        public void worldCity(String location) {
            ApiService service = ServiceGenerator.createService(ApiService.class, 3);
            service.dailyWeather("15d", location).enqueue(new NetCallBack<DailyResponse>() {
                @Override
                public void onSuccess(Call<DailyResponse> call, Response<DailyResponse> response) {
                    if (getView() != null) {
                        getView().getMoreDailyResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            });
        }

    }

    public interface IMoreDailyView extends BaseView {

        //更多天气预报返回数据 V7
        void getMoreDailyResult(Response<DailyResponse> response);

        //错误返回
        void getDataFailed();
    }
}
