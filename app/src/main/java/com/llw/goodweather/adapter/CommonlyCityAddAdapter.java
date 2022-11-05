package com.llw.goodweather.adapter;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.SearchCityResponse;

import java.util.List;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * 添加城市时搜索返回结果列表适配器
 *
 * @author llw
 */
public class CommonlyCityAddAdapter extends BaseQuickAdapter<NewSearchCityResponse.LocationBean, BaseViewHolder> {

    //关键字
    private String edStr;

    public CommonlyCityAddAdapter(int layoutResId, @Nullable List<NewSearchCityResponse.LocationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewSearchCityResponse.LocationBean item) {
        TextView textView = helper.getView(R.id.tv_location);

        String result = item.getName() + " , " + item.getAdm2() + " , " + item.getAdm1() + " , " + item.getCountry();
        if (edStr != null && edStr.length() > 0) {
            textView.setText(matcherSearchText(getContext().getResources().getColor(R.color.shallow_yellow), result, edStr));

        } else {
            textView.setText(item.getName() + " , " +
                    item.getAdm2() + " , " +
                    item.getAdm1() + " , " +
                    item.getCountry());
        }
    }

    /**
     * 改变颜色
     *
     * @param str 输入的文本
     */
    public void changTxColor(String str) {
        edStr = str;
        notifyDataSetChanged();
    }


    /**
     * 改变一段文本中第一个关键字的文字颜色
     *
     * @param color   要改变文字的颜色
     * @param string  文本字符串
     * @param keyWord 关键字
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
