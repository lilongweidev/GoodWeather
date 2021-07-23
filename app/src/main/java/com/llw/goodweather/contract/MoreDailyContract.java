package com.llw.goodweather.contract;

import android.annotation.SuppressLint;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.newnet.NetworkApi;
import com.llw.mvplibrary.newnet.observer.BaseObserver;

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
        @SuppressLint("CheckResult")
        public void dailyWeather(String location) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.dailyWeather("15d", location).compose(NetworkApi.applySchedulers(new BaseObserver<DailyResponse>() {
                @Override
                public void onSuccess(DailyResponse dailyResponse) {
                    if (getView() != null) {
                        getView().getMoreDailyResult(dailyResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            }));
        }

    }

    public interface IMoreDailyView extends BaseView {

        //更多天气预报返回数据 V7
        void getMoreDailyResult(DailyResponse response);

        //错误返回
        void getDataFailed();
    }
}
