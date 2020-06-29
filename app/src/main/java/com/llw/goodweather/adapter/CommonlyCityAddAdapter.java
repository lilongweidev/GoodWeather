package com.llw.goodweather.adapter;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.SearchCityResponse;

import java.util.List;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * 添加城市时搜索返回结果列表适配器
 */
public class CommonlyCityAddAdapter extends BaseQuickAdapter<SearchCityResponse.HeWeather6Bean.BasicBean, BaseViewHolder> {

    private String edStr;//关键字

    public CommonlyCityAddAdapter(int layoutResId, @Nullable List<SearchCityResponse.HeWeather6Bean.BasicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchCityResponse.HeWeather6Bean.BasicBean item) {
        TextView textView = helper.getView(R.id.tv_location);

        String result = item.getLocation() + " , " + item.getParent_city() + " , " + item.getAdmin_area() + " , " + item.getCnty();
        if (edStr != null && edStr.length() > 0) {
            textView.setText(matcherSearchText(mContext.getResources().getColor(R.color.shallow_yellow),result,edStr));

        } else {
            textView.setText(item.getLocation() + " , " +
                            item.getParent_city() + " , " +
                            item.getAdmin_area() + " , " +
                            item.getCnty());
        }

        helper.addOnClickListener(R.id.item_add_city);

    }

    /**
     * 改变颜色
     * @param str  输入的文本
     */
    public void changTxColor(String str) {
        edStr = str;
        notifyDataSetChanged();
    }


    /**
     * 改变一段文本中第一个关键字的文字颜色
     * @param color  要改变文字的颜色
     * @param string  文本字符串
     * @param keyWord  关键字
     * @return
     */
    public static CharSequence matcherSearchText(int color, String string, String keyWord) {
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        int indexOf = string.indexOf(keyWord);
        if (indexOf != -1) {
            builder.setSpan(new ForegroundColorSpan(color), indexOf, indexOf + keyWord.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}
