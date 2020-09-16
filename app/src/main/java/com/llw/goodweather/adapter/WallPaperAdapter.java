package com.llw.goodweather.adapter;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.baidu.panosdk.plugin.indoor.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.imageview.ShapeableImageView;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WallPaperResponse;
import java.util.List;

public class WallPaperAdapter extends BaseQuickAdapter<WallPaperResponse.ResBean.VerticalBean, BaseViewHolder> {

    List<Integer> mHeightList;
    public WallPaperAdapter(int layoutResId, @Nullable List<WallPaperResponse.ResBean.VerticalBean> data,List<Integer> heightList) {
        super(layoutResId, data);
        this.mHeightList = heightList;

    }

    @Override
    protected void convert(BaseViewHolder helper, WallPaperResponse.ResBean.VerticalBean item) {
        ShapeableImageView imageView = helper.getView(R.id.iv_wallpaper);

        //获取imageView的LayoutParams
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height = dip2px(mHeightList.get(helper.getAdapterPosition()));
        imageView.setLayoutParams(layoutParams);

        if(item.getDesc().equals("top") || item.getDesc().equals("bottom")){
            imageView.setImageResource(R.mipmap.icon_logo);
        }else {
            Glide.with(mContext).load(item.getImg()).into(imageView);
        }

        helper.addOnClickListener(R.id.item_wallpaper);
    }



    // dp 转成 px
    private int dip2px(float dpVale) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }


}
