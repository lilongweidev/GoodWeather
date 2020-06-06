package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.SearchCityResponse;

import java.util.List;

/**
 * 搜索城市结果列表适配器
 */
public class SearchCityAdapter extends BaseQuickAdapter<SearchCityResponse.HeWeather6Bean.BasicBean, BaseViewHolder> {
    public SearchCityAdapter(int layoutResId, @Nullable List<SearchCityResponse.HeWeather6Bean.BasicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchCityResponse.HeWeather6Bean.BasicBean item) {
        helper.setText(R.id.tv_city_name, item.getLocation());
        helper.addOnClickListener(R.id.tv_city_name);//绑定点击事件

    }
}
