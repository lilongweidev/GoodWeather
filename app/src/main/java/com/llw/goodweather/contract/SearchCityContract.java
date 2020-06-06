package com.llw.goodweather.contract;

import android.content.Context;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.SearchCityResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

public class SearchCityContract {

    public static class SearchCityPresenter extends BasePresenter<ISearchCityView> {

        /**
         * 搜索城市
         * @param context
         * @param location
         */
        public void searchCity(final Context context, String location) {
            ApiService service = ServiceGenerator.createService(ApiService.class, 2);//指明访问的地址
            service.searchCity(location).enqueue(new NetCallBack<SearchCityResponse>() {
                @Override
                public void onSuccess(Call<SearchCityResponse> call, Response<SearchCityResponse> response) {
                    if(getView() != null){
                        getView().getSearchCityResult(response);
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

    public interface ISearchCityView extends BaseView {
        //查询城市返回数据
        void getSearchCityResult(Response<SearchCityResponse> response);
        //错误返回
        void getDataFailed();
    }
}
