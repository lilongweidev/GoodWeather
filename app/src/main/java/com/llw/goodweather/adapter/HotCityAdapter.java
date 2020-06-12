package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.HotCityResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 热门城市列表适配器
 */
public class HotCityAdapter extends BaseQuickAdapter<HotCityResponse.HeWeather6Bean.BasicBean, BaseViewHolder> {
    public HotCityAdapter(int layoutResId, @Nullable List<HotCityResponse.HeWeather6Bean.BasicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotCityResponse.HeWeather6Bean.BasicBean item) {

        helper.setText(R.id.tv_hot_city_name,item.getLocation())
                .setText(R.id.tv_cnty_and_area,item.getCnty()+" —— "+item.getAdmin_area());
        helper.addOnClickListener(R.id.item_hot_city);
    }
}
