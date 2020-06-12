package com.llw.goodweather.adapter;

import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WeatherResponse;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

/**
 * 热门城市天气预报列表展示适配器
 */
public class WeatherForecastHotAdapter extends BaseQuickAdapter<WeatherResponse.HeWeather6Bean.DailyForecastBean, BaseViewHolder> {

    public WeatherForecastHotAdapter(int layoutResId, @Nullable List<WeatherResponse.HeWeather6Bean.DailyForecastBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherResponse.HeWeather6Bean.DailyForecastBean item) {
        helper.setText(R.id.tv_date, item.getDate())//日期
                .setText(R.id.tv_day, DateUtils.Week(item.getDate()))
                .setText(R.id.tv_wind_dir_and_sc, item.getWind_dir() + item.getWind_sc() + "级")
                .setText(R.id.tv_uv_index, uvIndex(Integer.parseInt(item.getUv_index())))//紫外线强度
                .setText(R.id.tv_hum, item.getHum() + "%")//相对湿度
                .setText(R.id.tv_pres, item.getPres() + "hPa")//大气压强
        ;
        //最低温和最高温
        TextView minAndMax = helper.getView(R.id.tv_tem_max_min);
        minAndMax.setText(item.getTmp_min() + "/" + item.getTmp_max());
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf");
        minAndMax.setTypeface(typeface);

        helper.addOnClickListener(R.id.item_forecast);//绑定点击事件的id


    }

    //紫外线等级
    private String uvIndex(int level) {//最弱、弱、中等、强、很强
        String result = null;
        if (level <= 2) {
            result = "最弱";
        } else if (level > 2 && level < 5) {
            result = "弱";
        } else if (level > 4 && level < 7) {
            result = "中等";
        } else if (level > 6 && level < 10) {
            result = "强";
        } else {
            result = "很强";
        }
        return result;
    }
}
