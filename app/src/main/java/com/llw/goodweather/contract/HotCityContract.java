package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.HotCityResponse;
import com.llw.goodweather.bean.NewHotCityResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 热门城市订阅器
 */
public class HotCityContract {

    public static class HotCityPresenter extends BasePresenter<IHotCityView> {

        /**
         * 热门城市 - 海外/国内
         * @param context
         */
        public void hotCity(final Context context,String group) {
            ApiService service = ServiceGenerator.createService(ApiService.class, 2);//指明访问的地址
            service.hotCity(group).enqueue(new NetCallBack<HotCityResponse>() {
                @Override
                public void onSuccess(Call<HotCityResponse> call, Response<HotCityResponse> response) {
                    if(getView() != null){
                        getView().getHotCityResult(response);
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
         * 热门城市 - 海外/国内  V7
         * @param range  类型   world 海外  cn  国内
         */
        public void newHotCity(String range) {
            ApiService service = ServiceGenerator.createService(ApiService.class, 4);//指明访问的地址
            service.newHotCity(range).enqueue(new NetCallBack<NewHotCityResponse>() {
                @Override
                public void onSuccess(Call<NewHotCityResponse> call, Response<NewHotCityResponse> response) {
                    if(getView() != null){
                        getView().getNewHotCityResult(response);
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
        //热门城市返回数据
        void getHotCityResult(Response<HotCityResponse> response);

        //热门城市返回数据 V7
        void getNewHotCityResult(Response<NewHotCityResponse> response);

        //错误返回
        void getDataFailed();
    }
}
