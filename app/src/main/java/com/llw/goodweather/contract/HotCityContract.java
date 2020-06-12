package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.HotCityResponse;
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
         * 热门城市城市 - 海外
         * @param context
         */
        public void hotCity(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class, 2);//指明访问的地址
            service.hotCity().enqueue(new NetCallBack<HotCityResponse>() {
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

    }

    public interface IHotCityView extends BaseView {
        //热门城市返回数据
        void getHotCityResult(Response<HotCityResponse> response);
        //错误返回
        void getDataFailed();
    }
}
