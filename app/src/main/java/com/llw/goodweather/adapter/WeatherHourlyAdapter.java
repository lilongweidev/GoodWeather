package com.llw.goodweather.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WeatherResponse;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

public class WeatherHourlyAdapter extends BaseQuickAdapter<WeatherResponse.HeWeather6Bean.HourlyBean, BaseViewHolder> {

    public WeatherHourlyAdapter(int layoutResId, @Nullable List<WeatherResponse.HeWeather6Bean.HourlyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherResponse.HeWeather6Bean.HourlyBean item) {
        //首先是对时间格式的处理,因为拿到的时间是  2020-04-28 22:00  要改成   晚上22:00
        //分两步，第一个是字符串的截取，第二个是时间段的判断返回文字描述
        String datetime = item.getTime();//赋值
        String time = datetime.substring(11);//截去前面的字符，保留后面所有的字符，就剩下 22:00
        helper.setText(R.id.tv_time,WeatherUtil.showTimeInfo(time)+time)//时间
                .setText(R.id.tv_temperature,item.getTmp()+"℃");//温度

        //天气状态图片
        ImageView weatherStateIcon = helper.getView(R.id.iv_weather_state);
        int code = Integer.parseInt(item.getCond_code());//获取天气状态码，根据状态码来显示图标
        WeatherUtil.changeIcon(weatherStateIcon,code);
    }

}
