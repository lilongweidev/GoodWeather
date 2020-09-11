package com.llw.goodweather.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.utils.RecyclerViewScrollHelper;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.bean.WallPaper;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 查看图片
 */
public class ImageActivity extends BaseActivity {


    @BindView(R.id.rv)
    RecyclerView rv;
    private int position;
    private int currentPosition;

    List<WallPaper> mList = new ArrayList<>();
    WallPaperAdapter mAdapter;
    @Override
    public void initData(Bundle savedInstanceState) {

        StatusBarUtil.transparencyBar(context);

        position = getIntent().getIntExtra("position", 0);
        Log.d("position", "" + position);

        mList = LitePal.findAll(WallPaper.class);

        mAdapter = new WallPaperAdapter(R.layout.item_image_list,mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);//滚动对齐，使RecyclerView像ViewPage一样，一次滑动一项,居中
        rv.setAdapter(mAdapter);
        RecyclerViewScrollHelper.scrollToPosition(rv,position);
        mAdapter.notifyDataSetChanged();

        Log.d("wallPaper",new Gson().toJson(mList));


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image;
    }


    public class WallPaperAdapter extends BaseQuickAdapter<WallPaper, BaseViewHolder> {

        public WallPaperAdapter(int layoutResId, @Nullable List<WallPaper> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WallPaper item) {
            ImageView imageView = helper.getView(R.id.wallpaper);
            Glide.with(mContext).load(item.getImgUrl()).into(imageView);
        }
    }

}
