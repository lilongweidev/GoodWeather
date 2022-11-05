package com.llw.goodweather.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

/**
 * 地图天气中 未来七天的天气列表适配器
 *
 * @author llw
 */
public class SevenDailyAdapter extends BaseQuickAdapter<DailyResponse.DailyBean, BaseViewHolder> {
    public SevenDailyAdapter(int layoutResId, @Nullable List<DailyResponse.DailyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyResponse.DailyBean item) {
        //日期
        helper.setText(R.id.tv_date, DateUtils.dateSplitPlus(item.getFxDate()) +
                DateUtils.Week(item.getFxDate()))
                //最高温
                .setText(R.id.tv_temp_height, item.getTempMax() + "℃")
                //最低温
                .setText(R.id.tv_temp_low, " / " + item.getTempMin() + "℃");

        //天气状态图片
        ImageView weatherStateIcon = helper.getView(R.id.iv_weather_state);
        //获取天气状态码，根据状态码来显示图标
        int code = Integer.parseInt(item.getIconDay());
        //调用工具类中写好的方法
        WeatherUtil.changeIcon(weatherStateIcon, code);

    }
}
