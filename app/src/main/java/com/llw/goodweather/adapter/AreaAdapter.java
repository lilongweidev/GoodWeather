package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.CityResponse;
import java.util.List;

/**
 * 区/县列表适配器
 */
public class AreaAdapter  extends BaseQuickAdapter<CityResponse.CityBean.AreaBean, BaseViewHolder> {
    public AreaAdapter(int layoutResId, @Nullable List<CityResponse.CityBean.AreaBean> data) {
        super(layoutResId, data);
        
    }

    @Override
    protected void convert(BaseViewHolder helper, CityResponse.CityBean.AreaBean item) {
        helper.setText(R.id.tv_city,item.getName());//区/县的名称
        helper.addOnClickListener(R.id.item_city);//点击事件 点击之后得到区/县  然后查询天气数据
    }
}
