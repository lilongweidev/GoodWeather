package com.llw.goodweather.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.contract.SplashContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class SplashActivity extends MvpActivity<SplashContract.SplashPresenter> implements SplashContract.ISplashView {

    @BindView(R.id.btn_test)
    Button btnTest;

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresent.nowWeather("101280603");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashContract.SplashPresenter createPresent() {
        return new SplashContract.SplashPresenter();
    }

    @Override
    public void getNowResult(Response<NowResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (response.body().getNow() != null) {
                String result = new Gson().toJson(response.body().getNow());
                Log.d("Splash", "result: " + result);
            } else {
                ToastUtils.showShortToast(context, "数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    @Override
    public void getNewSearchCityResult(Response<NewSearchCityResponse> response) {
        if (response.body().getStatus() == Constant.SUCCESS_CODE) {
            if (response.body().getLocation() != null && response.body().getLocation().size() > 0) {
                String locationId = response.body().getLocation().get(0).getId();
                mPresent.nowWeather("101280603");
            } else {
                ToastUtils.showShortToast(context, "数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getStatus()));
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, "异常信息");
    }


    @OnClick(R.id.btn_test)
    public void onViewClicked() {
        mPresent.nowWeather("101280603");
    }
}
