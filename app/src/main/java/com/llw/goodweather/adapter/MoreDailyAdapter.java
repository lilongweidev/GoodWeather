package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

/**
 * 更多天气预报信息数据适配器
 *
 * @author llw
 */
public class MoreDailyAdapter extends BaseQuickAdapter<DailyResponse.DailyBean, BaseViewHolder> {
    public MoreDailyAdapter(int layoutResId, @Nullable List<DailyResponse.DailyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyResponse.DailyBean item) {
        //最高温
        helper.setText(R.id.tv_temp_max, item.getTempMax() + "°")
                //最低温
                .setText(R.id.tv_temp_min, item.getTempMin() + "°")
                //日期描述
                .setText(R.id.tv_date_info, DateUtils.Week(item.getFxDate()))
                //日期
                .setText(R.id.tv_date, DateUtils.dateSplit(item.getFxDate()))
                //白天天气状况文字描述
                .setText(R.id.tv_weather_state_d, item.getTextDay())
                //晚间天气状况文字描述
                .setText(R.id.tv_weather_state_n, item.getTextNight())
                //白天风力信息
                .setText(R.id.tv_wind_360_d, item.getWind360Day() + "°")
                .setText(R.id.tv_wind_dir_d, item.getWindDirDay())
                .setText(R.id.tv_wind_scale_d, item.getWindScaleDay() + "级")
                .setText(R.id.tv_wind_speed_d, item.getWindSpeedDay() + "km/h")
                //晚上风力信息
                .setText(R.id.tv_wind_360_n, item.getWind360Night() + "°")
                .setText(R.id.tv_wind_dir_n, item.getWindDirNight())
                .setText(R.id.tv_wind_scale_n, item.getWindScaleNight() + "级")
                .setText(R.id.tv_wind_speed_n, item.getWindSpeedNight() + "km/h")
                //云量
                .setText(R.id.tv_cloud, item.getCloud() + "%")
                //紫外线
                .setText(R.id.tv_uvIndex, uvIndexToString(item.getUvIndex()))
                //能见度
                .setText(R.id.tv_vis, item.getVis() + "km")
                //降水量
                .setText(R.id.tv_precip, item.getPrecip() + "mm")
                //相对湿度
                .setText(R.id.tv_humidity, item.getHumidity() + "%")
                //大气压强
                .setText(R.id.tv_pressure, item.getPressure() + "hPa");


        //白天天气状态图片描述
        WeatherUtil.changeIcon(helper.getView(R.id.iv_weather_state_d), Integer.parseInt(item.getIconDay()));
        //晚上天气状态图片描述
        WeatherUtil.changeIcon(helper.getView(R.id.iv_weather_state_n), Integer.parseInt(item.getIconNight()));
    }

    private String uvIndexToString(String code) {//最弱(1)、弱(2)、中等(3)、强(4)、很强(5)
        String result = null;
        switch (code) {
            case "1":
                result = "最弱";
                break;
            case "2":
                result = "弱";
                break;
            case "3":
                result = "中等";
                break;
            case "4":
                result = "强";
                break;
            case "5":
                result = "很强";
                break;
            default:
                result = "无紫外线";
                break;
        }
        return result;
    }
}
