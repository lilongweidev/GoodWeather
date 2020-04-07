package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WeatherForecastResponse;

import java.util.List;

/**
 * 天气预报列表展示适配器
 */
public class WeatherForecastAdapter extends BaseQuickAdapter<WeatherForecastResponse.HeWeather6Bean.DailyForecastBean, BaseViewHolder> {

    public WeatherForecastAdapter(int layoutResId, @Nullable List<WeatherForecastResponse.HeWeather6Bean.DailyForecastBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherForecastResponse.HeWeather6Bean.DailyForecastBean item) {
        helper.setText(R.id.tv_date, item.getDate())//日期
                .setText(R.id.tv_info, item.getCond_txt_d())//天气
                .setText(R.id.tv_low_and_height, item.getTmp_min() + "/" + item.getTmp_max() + "℃");//最低温和最高温
    }
}
