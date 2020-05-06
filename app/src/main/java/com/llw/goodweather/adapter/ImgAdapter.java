package com.llw.goodweather.adapter;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.ImgResponse;
import com.llw.goodweather.bean.TestResponse;

import java.util.List;

public class ImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ImgAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Log.e("list->",item);
        ImageView img = helper.getView(R.id.iv_img);
        Glide.with(mContext).load(item).into(img);
    }
}
