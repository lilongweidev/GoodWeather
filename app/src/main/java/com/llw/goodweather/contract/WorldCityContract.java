package com.llw.goodweather.contract;

import android.annotation.SuppressLint;
import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.WorldCityResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.newnet.NetworkApi;
import com.llw.mvplibrary.newnet.observer.BaseObserver;


/**
 * 世界城市订阅器
 *
 * @author llw
 */
public class WorldCityContract {

    public static class WorldCityPresenter extends BasePresenter<IWorldCityView> {

        /**
         * 世界城市  V7
         *
         * @param range 类型
         */
        @SuppressLint("CheckResult")
        public void worldCity(String range) {
            ApiService service = NetworkApi.createService(ApiService.class, 4);//指明访问的地址
            service.worldCity(range).compose(NetworkApi.applySchedulers(new BaseObserver<WorldCityResponse>() {
                @Override
                public void onSuccess(WorldCityResponse worldCityResponse) {
                    if (getView() != null) {
                        getView().getWorldCityResult(worldCityResponse);
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

    public interface IWorldCityView extends BaseView {

        //热门城市返回数据 V7
        void getWorldCityResult(WorldCityResponse response);

        //错误返回
        void getDataFailed();
    }
}
