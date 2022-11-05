package com.llw.goodweather.ui;

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
import com.llw.goodweather.databinding.ActivityMoreAirBinding;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.DateUtils;
import com.llw.mvplibrary.mvp.MvpVBActivity;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.goodweather.utils.WeatherUtil;
import java.util.List;

/**
 * 更多空气质量信息
 *
 * @author llw
 */
public class MoreAirActivity extends MvpVBActivity<ActivityMoreAirBinding, MoreAirContract.MoreAirPresenter> implements MoreAirContract.IMoreAirView {

    @Override
    public void initData() {
        StatusBarUtil.transparencyBar(context);//透明状态栏
        Back(binding.toolbar);
        showLoadingDialog();
        String stationName = getIntent().getStringExtra("stationName");
        binding.tvTitle.setText(stationName + " - " + getIntent().getStringExtra("cityName"));
        mPresent.searchCityId(stationName);//搜索城市返回Id
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
    public void getSearchCityIdResult(NewSearchCityResponse response) {
        dismissLoadingDialog();
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            showLoadingDialog();
            List<NewSearchCityResponse.LocationBean> data = response.getLocation();
            if (data != null && data.size() > 0) {
                mPresent.air(data.get(0).getId());//查询该站点的空气质量数据
                mPresent.airFive(data.get(0).getId());//查询该站点的空气质量数据
            } else {
                ToastUtils.showShortToast(context, "未查询到相关数据");
            }

        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 更多空气质量的数据展示
     *
     * @param response
     */
    @Override
    public void getMoreAirResult(AirNowResponse response) {
        dismissLoadingDialog();
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            AirNowResponse.NowBean data = response.getNow();
            List<AirNowResponse.StationBean> station = response.getStation();
            if (response.getNow() != null) {
                String time = DateUtils.updateTime(response.getUpdateTime());//截去前面的字符，保留后面所有的字符，就剩下 22:00
                binding.tvOldTime.setText("最近更新时间：" + WeatherUtil.showTimeInfo(time) + time);
                showAirBasicData(data);//展示基础数据

                //展示检测站列表数据
                MoreAirStationAdapter mAdapter = new MoreAirStationAdapter(R.layout.item_more_air_station_list, station);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                binding.rvStation.setLayoutManager(linearLayoutManager);
                PagerSnapHelper snapHelper = new PagerSnapHelper();
                binding.rvStation.setOnFlingListener(null);//避免抛异常
                snapHelper.attachToRecyclerView(binding.rvStation);//滚动对齐，使RecyclerView像ViewPage一样，一次滑动一项,居中
                binding.rvStation.setAdapter(mAdapter);
            } else {
                ToastUtils.showShortToast(context, "空气质量数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 未来5天空气质量返回
     *
     * @param response
     */
    @Override
    public void getMoreAirFiveResult(MoreAirFiveResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<MoreAirFiveResponse.DailyBean> data = response.getDaily();
            if (data != null && data.size() > 0) {
                MoreAirFiveAdapter adapter = new MoreAirFiveAdapter(R.layout.item_more_air_five_list, data);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(RecyclerView.HORIZONTAL);
                binding.rvFiveAir.setLayoutManager(manager);
                binding.rvFiveAir.setAdapter(adapter);
            } else {
                ToastUtils.showShortToast(context, "未来5天空气质量数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 展示基础数据
     *
     * @param data 数据源
     */
    private void showAirBasicData(AirNowResponse.NowBean data) {
        binding.rpbAqi.setMaxProgress(300);//最大进度，用于计算
        binding.rpbAqi.setMinText("0");//设置显示最小值
        binding.rpbAqi.setMinTextSize(32f);
        binding.rpbAqi.setMaxText("300");//设置显示最大值
        binding.rpbAqi.setMaxTextSize(32f);
        binding.rpbAqi.setProgress(Float.valueOf(data.getAqi()));//当前进度
        binding.rpbAqi.setArcBgColor(getResources().getColor(R.color.arc_bg_color));//圆弧的颜色
        binding.rpbAqi.setProgressColor(getResources().getColor(R.color.arc_progress_color));//进度圆弧的颜色
        binding.rpbAqi.setFirstText(data.getCategory());//空气质量描述 取值范围：优，良，轻度污染，中度污染，重度污染，严重污染
        binding.rpbAqi.setFirstTextSize(44f);//第一行文本的字体大小
        binding.rpbAqi.setSecondText(data.getAqi());//空气质量值
        binding.rpbAqi.setSecondTextSize(64f);//第二行文本的字体大小
        binding.rpbAqi.setMinText("0");
        binding.rpbAqi.setMinTextColor(getResources().getColor(R.color.arc_progress_color));

        binding.tvPm10.setText(data.getPm10());//PM10  + " μg/m3"
        binding.progressPm10.setProgress(data.getPm10(), 100);
        binding.tvPm25.setText(data.getPm2p5());//PM2.5
        binding.progressPm25.setProgress(data.getPm2p5(), 100);
        binding.tvNo2.setText(data.getNo2());//二氧化氮
        binding.progressNo2.setProgress(data.getNo2(), 100);
        binding.tvSo2.setText(data.getSo2());//二氧化硫
        binding.progressSo2.setProgress(data.getSo2(), 100);
        binding.tvO3.setText(data.getO3());//臭氧
        binding.progressO3.setProgress(data.getO3(), 100);
        binding.tvCo.setText(data.getCo());//一氧化碳
        binding.progressCo.setProgress(data.getCo(), 100);
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
