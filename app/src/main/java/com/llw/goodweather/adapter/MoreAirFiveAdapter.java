package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.MoreAirFiveResponse;
import com.llw.goodweather.utils.DateUtils;

import java.util.List;

/**
 * 5天空气质量预报适配器
 *
 * @author llw
 */
public class MoreAirFiveAdapter extends BaseQuickAdapter<MoreAirFiveResponse.DailyBean, BaseViewHolder> {
    public MoreAirFiveAdapter(int layoutResId, @Nullable List<MoreAirFiveResponse.DailyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreAirFiveResponse.DailyBean item) {
        //日期描述
        helper.setText(R.id.tv_date_info, DateUtils.Week(item.getFxDate()))
                //日期
                .setText(R.id.tv_date, DateUtils.dateSplit(item.getFxDate()))
                //空气质量指数
                .setText(R.id.tv_aqi, item.getAqi())
                //空气质量描述
                .setText(R.id.tv_category, item.getCategory())
                //污染物
                .setText(R.id.tv_primary, item.getPrimary().equals("NA") ? "无污染" : item.getPrimary());
    }
}
