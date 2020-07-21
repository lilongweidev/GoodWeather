package com.llw.goodweather.adapter;

import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

/**
 * V7 API 热门城市  逐小时预报数据列表适配器
 */
public class HourlyWorldCityAdapter extends BaseQuickAdapter<HourlyResponse.HourlyBean, BaseViewHolder> {
    public HourlyWorldCityAdapter(int layoutResId, @Nullable List<HourlyResponse.HourlyBean> data) {
        super(layoutResId, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void convert(BaseViewHolder helper, HourlyResponse.HourlyBean item) {
        //首先是对时间格式的处理,因为拿到的时间是  2020-04-28 22:00  要改成   晚上22:00
        //分两步，第一个是字符串的截取，第二个是时间段的判断返回文字描述
        /**
         * V7 API 涉及到时间的，都会返回 2020-07-16T09:39+08:00  这种格式
         * 所以最好写一个通用的返回进行处理 方法已经写好了使用可以了
         */


        String time = DateUtils.updateTime(item.getFxTime());
        helper.setText(R.id.tv_time, WeatherUtil.showTimeInfo(time) + time)//时间
                .setText(R.id.tv_temperature, item.getTemp() + "℃")
                .setText(R.id.tv_weather_state, item.getText())
                .setText(R.id.tv_wind_info, item.getWindDir() + "，" + item.getWindScale() + "级");//温度

        //天气状态图片
        ImageView weatherStateIcon = helper.getView(R.id.iv_weather_state);
        int code = Integer.parseInt(item.getIcon());//获取天气状态码，根据状态码来显示图标
        WeatherUtil.changeIcon(weatherStateIcon, code);
    }
}
