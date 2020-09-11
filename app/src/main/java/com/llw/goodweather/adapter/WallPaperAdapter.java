package com.llw.goodweather.adapter;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.baidu.panosdk.plugin.indoor.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.imageview.ShapeableImageView;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WallPaperResponse;


import java.util.List;
import java.util.Random;

public class WallPaperAdapter extends BaseQuickAdapter<WallPaperResponse.ResBean.VerticalBean, BaseViewHolder> {


    public WallPaperAdapter(int layoutResId, @Nullable List<WallPaperResponse.ResBean.VerticalBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WallPaperResponse.ResBean.VerticalBean item) {
        ShapeableImageView imageView = helper.getView(R.id.iv_wallpaper);

        Glide.with(mContext).load(item.getImg()).into(imageView);
        helper.addOnClickListener(R.id.item_wallpaper);
    }

}
