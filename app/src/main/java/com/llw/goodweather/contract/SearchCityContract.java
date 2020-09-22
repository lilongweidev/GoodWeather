package com.llw.goodweather.contract;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 搜索城市订阅器  V7
 *
 * @author llw
 */
public class SearchCityContract {

    public static class SearchCityPresenter extends BasePresenter<ISearchCityView> {
        /**
         * 搜索城市  V7版本中  模糊搜索  返回10条相关数据
         *
         * @param location 城市名
         */
        public void newSearchCity(String location) {//注意这里的4表示新的搜索城市地址接口
            ApiService service = ServiceGenerator.createService(ApiService.class, 4);//指明访问的地址
            service.newSearchCity(location, "fuzzy").enqueue(new NetCallBack<NewSearchCityResponse>() {
                @Override
                public void onSuccess(Call<NewSearchCityResponse> call, Response<NewSearchCityResponse> response) {
                    if (getView() != null) {
                        getView().getNewSearchCityResult(response);
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

    public interface ISearchCityView extends BaseView {
        //搜索城市返回数据  V7
        void getNewSearchCityResult(Response<NewSearchCityResponse> response);

        //错误返回
        void getDataFailed();
    }
}
