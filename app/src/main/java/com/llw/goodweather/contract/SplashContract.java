package com.llw.goodweather.contract;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 欢迎页订阅器
 */
public class SplashContract {

    public static class SplashPresenter extends BasePresenter<ISplashView> {

        /**
         * 获取最新的APP版本信息
         */
        public void getAppInfo() {//注意这里的4表示新的搜索城市地址接口
            ApiService service = ServiceGenerator.createService(ApiService.class, 5);
            service.getAppInfo().enqueue(new NetCallBack<AppVersion>() {
                @Override
                public void onSuccess(Call<AppVersion> call, Response<AppVersion> response) {
                    if(getView() != null){
                        getView().getAppInfoResult(response);
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

    public interface ISplashView extends BaseView {
        //APP信息返回
        void getAppInfoResult(Response<AppVersion> response);

        //错误返回
        void getDataFailed();


    }
}
