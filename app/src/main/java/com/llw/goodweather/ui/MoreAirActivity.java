package com.llw.goodweather.ui;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.MoreAirFiveAdapter;
import com.llw.goodweather.adapter.MoreAirStationAdapter;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.MoreAirFiveResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.contract.MoreAirContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.DateUtils;
import com.llw.mvplibrary.view.LineProgressbar;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.goodweather.utils.WeatherUtil;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.llw.mvplibrary.view.RoundProgressBar;
import java.util.List;
import butterknife.BindView;
import retrofit2.Response;

/**
 * 更多空气质量信息
 */
public class MoreAirActivity extends MvpActivity<MoreAirContract.MoreAirPresenter> implements MoreAirContract.IMoreAirView {

    @BindView(R.id.tv_title)
    TextView tvTitle;//标题
    @BindView(R.id.toolbar)
    Toolbar toolbar;//toolbar
    @BindView(R.id.rpb_aqi)
    RoundProgressBar rpbAqi;//圆环进度条
    @BindView(R.id.tv_pm10)
    TextView tvPm10;//pm10
    @BindView(R.id.tv_pm25)
    TextView tvPm25;//pm2.5
    @BindView(R.id.tv_no2)
    TextView tvNo2;//二氧化氮
    @BindView(R.id.tv_so2)
    TextView tvSo2;//二氧化硫
    @BindView(R.id.tv_o3)
    TextView tvO3;//臭氧
    @BindView(R.id.tv_co)
    TextView tvCo;//一氧化碳
    @BindView(R.id.tv_old_time)
    TextView tvOldTime;//最近更新时间
    @BindView(R.id.rv_station)
    RecyclerView rvStation;//检测站数据列表
    @BindView(R.id.progress_pm10)
    LineProgressbar progressPm10;//pm10含量进度条展示
    @BindView(R.id.progress_pm25)
    LineProgressbar progressPm25;//pm2.5含量进度条展示
    @BindView(R.id.progress_no2)
    LineProgressbar progressNo2;//二氧化氮含量进度条展示
    @BindView(R.id.progress_so2)
    LineProgressbar progressSo2;//二氧化硫含量进度条展示
    @BindView(R.id.progress_o3)
    LineProgressbar progressO3;//臭氧含量进度条展示
    @BindView(R.id.progress_co)
    LineProgressbar progressCo;//一氧化碳含量进度条展示
    @BindView(R.id.rv_five_air)
    RecyclerView rvFiveAir;//最近5天空气质量列表

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.transparencyBar(context);//透明状态栏
        Back(toolbar);
        showLoadingDialog();
        String stationName = getIntent().getStringExtra("stationName");
        tvTitle.setText(stationName + " - " + getIntent().getStringExtra("cityName"));
        mPresent.searchCityId(stationName);//搜索城市返回Id
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_more_air;
    }

    @Override
    protected MoreAirContract.MoreAirPresenter createPresent() {
        return new MoreAirContract.MoreAirPresenter();
    }

    /**
     * 搜索城市返回Id  通过id查询城市的空气质量和站点空气质量
     *
     * @param response
     */
    @Override
    public void getSearchCityIdResult(Response<NewSearchCityResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            showLoadingDialog();
            List<NewSearchCityResponse.LocationBean> data = response.body().getLocation();
            if (data != null && data.size() > 0) {
                mPresent.air(data.get(0).getId());//查询该站点的空气质量数据
                mPresent.airFive(data.get(0).getId());//查询该站点的空气质量数据
            } else {
                ToastUtils.showShortToast(context, "未查询到相关数据");
            }

        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 更多空气质量的数据展示
     *
     * @param response
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getMoreAirResult(Response<AirNowResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            AirNowResponse.NowBean data = response.body().getNow();
            List<AirNowResponse.StationBean> station = response.body().getStation();
            if (response.body().getNow() != null) {
                String time = DateUtils.updateTime(response.body().getUpdateTime());//截去前面的字符，保留后面所有的字符，就剩下 22:00
                tvOldTime.setText("最近更新时间：" + WeatherUtil.showTimeInfo(time) + time);
                showAirBasicData(data);//展示基础数据

                //展示检测站列表数据
                MoreAirStationAdapter mAdapter = new MoreAirStationAdapter(R.layout.item_more_air_station_list, station);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvStation.setLayoutManager(linearLayoutManager);
                PagerSnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(rvStation);//滚动对齐，使RecyclerView像ViewPage一样，一次滑动一项,居中
                rvStation.setAdapter(mAdapter);

            } else {
                ToastUtils.showShortToast(context, "空气质量数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 未来5天空气质量返回
     *
     * @param response
     */
    @Override
    public void getMoreAirFiveResult(Response<MoreAirFiveResponse> response) {
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            List<MoreAirFiveResponse.DailyBean> data = response.body().getDaily();
            if (data != null && data.size() > 0) {
                MoreAirFiveAdapter adapter = new MoreAirFiveAdapter(R.layout.item_more_air_five_list, data);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(RecyclerView.HORIZONTAL);
                rvFiveAir.setLayoutManager(manager);
                rvFiveAir.setAdapter(adapter);
            } else {
                ToastUtils.showShortToast(context, "未来5天空气质量数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 展示基础数据
     *
     * @param data 数据源
     */
    private void showAirBasicData(AirNowResponse.NowBean data) {
        rpbAqi.setMaxProgress(300);//最大进度，用于计算
        rpbAqi.setMinText("0");//设置显示最小值
        rpbAqi.setMinTextSize(32f);
        rpbAqi.setMaxText("300");//设置显示最大值
        rpbAqi.setMaxTextSize(32f);
        rpbAqi.setProgress(Float.valueOf(data.getAqi()));//当前进度
        rpbAqi.setArcBgColor(getResources().getColor(R.color.arc_bg_color));//圆弧的颜色
        rpbAqi.setProgressColor(getResources().getColor(R.color.arc_progress_color));//进度圆弧的颜色
        rpbAqi.setFirstText(data.getCategory());//空气质量描述 取值范围：优，良，轻度污染，中度污染，重度污染，严重污染
        rpbAqi.setFirstTextSize(44f);//第一行文本的字体大小
        rpbAqi.setSecondText(data.getAqi());//空气质量值
        rpbAqi.setSecondTextSize(64f);//第二行文本的字体大小
        rpbAqi.setMinText("0");
        rpbAqi.setMinTextColor(getResources().getColor(R.color.arc_progress_color));

        tvPm10.setText(data.getPm10());//PM10  + " μg/m3"
        progressPm10.setProgress(data.getPm10(), 100);
        tvPm25.setText(data.getPm2p5());//PM2.5
        progressPm25.setProgress(data.getPm2p5(), 100);
        tvNo2.setText(data.getNo2());//二氧化氮
        progressNo2.setProgress(data.getNo2(), 100);
        tvSo2.setText(data.getSo2());//二氧化硫
        progressSo2.setProgress(data.getSo2(), 100);
        tvO3.setText(data.getO3());//臭氧
        progressO3.setProgress(data.getO3(), 100);
        tvCo.setText(data.getCo());//一氧化碳
        progressCo.setProgress(data.getCo(), 100);
    }

    /**
     * 其他异常返回
     */
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "更多空气质量数据获取异常");
    }

}
