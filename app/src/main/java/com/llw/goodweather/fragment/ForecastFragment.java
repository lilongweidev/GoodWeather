package com.llw.goodweather.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.R;
import com.llw.goodweather.adapter.WeatherForecastHotAdapter;
import com.llw.goodweather.bean.WeatherResponse;
import com.llw.goodweather.eventbus.ForecastEvent;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.BaseFragment;
import com.llw.mvplibrary.view.WeatherChartViewForecast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ForecastFragment extends BaseFragment {

    @BindView(R.id.line_char)
    WeatherChartViewForecast lineChar;//折线图
    @BindView(R.id.rv_forecast)
    RecyclerView rvForecast;//未来七天天气预报

    List<WeatherResponse.HeWeather6Bean.DailyForecastBean> mList = new ArrayList<>();
    WeatherForecastHotAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forecast;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ForecastEvent event) {//接收
        List<WeatherResponse.HeWeather6Bean.DailyForecastBean> data = new ArrayList<>();
        data.addAll(event.mForecastBean);
        int[] maxArray = new int[data.size()];
        int[] minArray = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            maxArray[i] = Integer.parseInt(event.mForecastBean.get(i).getTmp_max());
            minArray[i] = Integer.parseInt(event.mForecastBean.get(i).getTmp_min());
        }

        lineChar.setTempMax(maxArray);
        lineChar.setTempMin(minArray);
        lineChar.invalidate();

        //列表数据
        initList(data);

    }

    private void initList(List<WeatherResponse.HeWeather6Bean.DailyForecastBean> data) {
        mAdapter = new WeatherForecastHotAdapter(R.layout.item_weather_forecast_hot_list,mList);
        rvForecast.setLayoutManager(new LinearLayoutManager(context));
        rvForecast.setAdapter(mAdapter);

        mList.clear();
        mList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
