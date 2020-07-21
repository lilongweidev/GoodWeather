package com.llw.goodweather.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.R;
import com.llw.goodweather.adapter.HourlyWorldCityAdapter;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.contract.HotCityWeatherContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.goodweather.utils.WeatherUtil;
import com.llw.mvplibrary.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * 热门城市天气
 */
public class WorldCityWeatherActivity extends MvpActivity<HotCityWeatherContract.HotCityPresenter>
        implements HotCityWeatherContract.IHotCityView {

    @BindView(R.id.tv_title)
    TextView tvTitle;//城市
    @BindView(R.id.toolbar)
    Toolbar toolbar;//标题bar
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;//温度
    @BindView(R.id.iv_weather_state)
    ImageView ivWeatherState;//天气状况图片
    @BindView(R.id.tv_tem_max)
    TextView tvTemMax;//最高温
    @BindView(R.id.tv_tem_min)
    TextView tvTemMin;//最低温
    @BindView(R.id.rv_hourly)
    RecyclerView rvHourly;

    List<HourlyResponse.HourlyBean> mList = new ArrayList<>();
    HourlyWorldCityAdapter mAdapter;
    @BindView(R.id.tv_weather_state)
    TextView tvWeatherState;
    @BindView(R.id.tv_wind_state)
    TextView tvWindState;

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoadingDialog();
        StatusBarUtil.transparencyBar(context);//设置状态栏背景颜色
        Back(toolbar);

        initList();

        String locationId = getIntent().getStringExtra("locationId");
        tvTitle.setText(getIntent().getStringExtra("name"));
        showLoadingDialog();
        mPresent.nowWeather(locationId);//查询实况天气
        mPresent.dailyWeather(locationId);//查询天气预报
        mPresent.hourlyWeather(locationId);//查询逐小时天气预报
    }

    private void initList() {
        mAdapter = new HourlyWorldCityAdapter(R.layout.item_weather_hourly_hot_list, mList);
        rvHourly.setLayoutManager(new LinearLayoutManager(context));
        rvHourly.setAdapter(mAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_world_city_weather;
    }

    @Override
    protected HotCityWeatherContract.HotCityPresenter createPresent() {
        return new HotCityWeatherContract.HotCityPresenter();
    }


    /**
     * 实况天气返回  V7
     *
     * @param response
     */
    @Override
    public void getNowResult(Response<NowResponse> response) {

        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/GenJyuuGothic-ExtraLight.ttf");
            tvTemperature.setText(response.body().getNow().getTemp() + "°");
            tvTemperature.setTypeface(typeface);//使用字体
            int code = Integer.parseInt(response.body().getNow().getIcon());//获取天气状态码，根据状态码来显示图标
            WeatherUtil.changeIcon(ivWeatherState, code);//调用工具类中写好的方法

            tvWeatherState.setText("当前：" + response.body().getNow().getText());
            tvWindState.setText(response.body().getNow().getWindDir()+"   "+response.body().getNow().getWindScale()+"级");
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 天气预报 V7
     *
     * @param response
     */
    @Override
    public void getDailyResult(Response<DailyResponse> response) {
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            if (response.body().getDaily() != null && response.body().getDaily().size() > 0) {
                tvTemMax.setText(response.body().getDaily().get(0).getTempMax());
                tvTemMin.setText(" / " + response.body().getDaily().get(0).getTempMin());
            } else {
                ToastUtils.showShortToast(context, "暂无天气预报数据");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 逐小时天气预报 V7
     *
     * @param response
     */
    @Override
    public void getHourlyResult(Response<HourlyResponse> response) {

        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            List<HourlyResponse.HourlyBean> data = response.body().getHourly();
            if (data != null && data.size() > 0) {
                mList.clear();
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                dismissLoadingDialog();
            } else {
                ToastUtils.showShortToast(context, "逐小时天气查询不到");
            }

        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    //异常返回
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "请求超时");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
