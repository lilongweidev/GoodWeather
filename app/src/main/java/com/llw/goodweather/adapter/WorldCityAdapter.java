package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WorldCityResponse;

import java.util.List;

/**
 * 国家/地区中的城市适配器
 *
 * @author llw
 */
public class WorldCityAdapter extends BaseQuickAdapter<WorldCityResponse.TopCityListBean, BaseViewHolder> {
    public WorldCityAdapter(int layoutResId, @Nullable List<WorldCityResponse.TopCityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorldCityResponse.TopCityListBean item) {
        //名称
        helper.setText(R.id.tv_city, item.getName());
    }
}
