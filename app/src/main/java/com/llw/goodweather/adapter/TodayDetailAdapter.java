package com.llw.goodweather.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.TodayDetailBean;

import java.util.List;

/**
 * 地图天气中 今日天气详情数据
 *
 * @author llw
 */
public class TodayDetailAdapter extends BaseQuickAdapter<TodayDetailBean, BaseViewHolder> {
    public TodayDetailAdapter(int layoutResId, @Nullable List<TodayDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodayDetailBean item) {
        ImageView imageView = helper.getView(R.id.iv_icon);
        //图标
        imageView.setImageResource(item.getIcon());
        //值
        helper.setText(R.id.tv_value, item.getValue())
                //名称
                .setText(R.id.tv_name, item.getName());
    }
}
