package com.llw.goodweather.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.R;
import com.llw.goodweather.adapter.WeatherHourlyAdapter;
import com.llw.goodweather.bean.WeatherResponse;
import com.llw.goodweather.eventbus.TodayHourlyEvent;
import com.llw.mvplibrary.base.BaseFragment;
import com.llw.mvplibrary.view.WeatherChartView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TodayFragment extends BaseFragment {


    @BindView(R.id.line_char)
    WeatherChartView lineChar;//折线图
    @BindView(R.id.rv_hourly)
    RecyclerView rvHourly;//逐三小时天气列表

    List<WeatherResponse.HeWeather6Bean.HourlyBean> mList = new ArrayList<>();
    WeatherHourlyAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_today;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TodayHourlyEvent event) {//接收
        List<WeatherResponse.HeWeather6Bean.HourlyBean> data = new ArrayList<>();
        data.addAll(event.mHourlyBean);
        Log.i("dayArray", data.get(0).getCond_txt());
        int[] dayArray = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            dayArray[i] = Integer.parseInt(event.mHourlyBean.get(i).getTmp());
        }
        Log.i("dayArray", dayArray.toString());

        lineChar.setTempDay(dayArray);
        lineChar.invalidate();
        //列表
        initList(data);
    }

    private void initList(List<WeatherResponse.HeWeather6Bean.HourlyBean> data) {
        mAdapter = new WeatherHourlyAdapter(R.layout.item_weather_hourly_hot_list, mList);
        rvHourly.setLayoutManager(new LinearLayoutManager(context));
        rvHourly.setAdapter(mAdapter);

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
