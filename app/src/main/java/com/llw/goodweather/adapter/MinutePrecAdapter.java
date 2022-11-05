package com.llw.goodweather.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.MinutePrecResponse;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

/**
 * 分钟级降水列表适配器
 * @author llw
 */
public class MinutePrecAdapter extends BaseQuickAdapter<MinutePrecResponse.MinutelyBean, BaseViewHolder> {

    public MinutePrecAdapter(int layoutResId, @Nullable List<MinutePrecResponse.MinutelyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MinutePrecResponse.MinutelyBean item) {
        String time = DateUtils.updateTime(item.getFxTime());
        //时间
        helper.setText(R.id.tv_time, WeatherUtil.showTimeInfo(time) + time);
        String type = null;
        if("rain".equals(item.getType())){
            type = "雨";
        }else if("snow".equals(item.getType())){
            type = "雪";
        }
        helper.setText(R.id.tv_precip_info,item.getPrecip()+"   "+type);
    }
}
