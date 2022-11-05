package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.AirNowResponse;

import java.util.List;

/**
 * 更多空气质量之空气站点列表适配器
 *
 * @author llw
 */
public class MoreAirStationAdapter extends BaseQuickAdapter<AirNowResponse.StationBean, BaseViewHolder> {
    public MoreAirStationAdapter(int layoutResId, @Nullable List<AirNowResponse.StationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AirNowResponse.StationBean item) {
        //监测站名称
        helper.setText(R.id.tv_station_name, item.getName())
                //空气质量
                .setText(R.id.tv_air_category, item.getCategory())
                //空气质量指数
                .setText(R.id.tv_aqi, item.getAqi())
                //污染物
                .setText(R.id.tv_primary, item.getPrimary().equals("NA") ? "无污染" : item.getPrimary())
                //pm10
                .setText(R.id.tv_pm10, item.getPm10())
                //pm2.5
                .setText(R.id.tv_pm25, item.getPm2p5())
                //二氧化氮
                .setText(R.id.tv_no2, item.getNo2())
                //二氧化硫
                .setText(R.id.tv_so2, item.getSo2())
                //臭氧
                .setText(R.id.tv_o3, item.getO3())
                //一氧化碳
                .setText(R.id.tv_co, item.getCo());
    }
}
