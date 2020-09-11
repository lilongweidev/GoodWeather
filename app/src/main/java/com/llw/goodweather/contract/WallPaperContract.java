package com.llw.goodweather.contract;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.WallPaperResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.bean.WallPaper;
import com.llw.mvplibrary.net.NetCallBack;
import com.llw.mvplibrary.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 壁纸订阅器
 */
public class WallPaperContract {

    public static class WallPaperPresenter extends BasePresenter<IWallPaperView> {

        /**
         * 获取壁纸数据
         */
        public void getWallPaper() {//注意这里的4表示新的搜索城市地址接口
            ApiService service = ServiceGenerator.createService(ApiService.class, 6);
            service.getWallPaper().enqueue(new NetCallBack<WallPaperResponse>() {
                @Override
                public void onSuccess(Call<WallPaperResponse> call, Response<WallPaperResponse> response) {
                    if(getView() != null){
                        getView().getWallPaperResult(response);
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

    public interface IWallPaperView extends BaseView {
        //壁纸数据返回
        void getWallPaperResult(Response<WallPaperResponse> response);

        //错误返回
        void getDataFailed();


    }
}
