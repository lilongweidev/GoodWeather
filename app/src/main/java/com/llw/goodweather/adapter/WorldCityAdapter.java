package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.NewHotCityResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;

import java.util.List;

public class WorldCityAdapter extends BaseQuickAdapter<NewHotCityResponse.TopCityListBean, BaseViewHolder> {
    public WorldCityAdapter(int layoutResId, @Nullable List<NewHotCityResponse.TopCityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewHotCityResponse.TopCityListBean item) {
        helper.setText(R.id.tv_city,item.getName());
        helper.addOnClickListener(R.id.tv_city);
    }
}
