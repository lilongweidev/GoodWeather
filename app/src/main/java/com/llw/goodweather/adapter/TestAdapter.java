package com.llw.goodweather.adapter;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.ImgResponse;
import com.llw.goodweather.bean.TestResponse;
import com.llw.mvplibrary.view.CollapsibleTextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TestAdapter extends BaseQuickAdapter<TestResponse.DataBean, BaseViewHolder> {
    public TestAdapter(int layoutResId, @Nullable List<TestResponse.DataBean> data) {
        super(layoutResId, data);
    }

    private List<String> mList;

    private ImgAdapter mAdapter;
    CircleImageView headImg;
    @Override
    protected void convert(BaseViewHolder helper, TestResponse.DataBean item) {
        headImg = helper.getView(R.id.head_image);
        CollapsibleTextView tvContent = helper.getView(R.id.tv_content);
        //头像显示
        Glide.with(mContext)
                .load(item.getUserInfo().getHeadImage())
                .placeholder(R.drawable.ic_launcher_background)
                .addListener(mRequestListener)
                .into(headImg);
        helper.setText(R.id.tv_nickname, item.getUserInfo().getUsername())//用户昵称
                .setText(R.id.tv_title, item.getTitle());//标题
        //因为内容是HtML的，所以用富文本加载
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页
        tvContent.setText(Html.fromHtml(item.getContent(), imgGetter, null));

//        RichText.from(item.getContent()).bind(mContext)
//                .showBorder(false)//无边框
//                .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)//图片尺寸
//                .into(tvContent);

        //图片列表
        RecyclerView rvImg = helper.getView(R.id.rv_img);

        mList = new ArrayList<>();
        mList = item.getImages();
        mAdapter = new ImgAdapter(R.layout.item_img_list,mList);
        GridLayoutManager manager;
        if(item.getImages().size() >= 3){
            manager = new GridLayoutManager(mContext,3);

        }else {
            manager = new GridLayoutManager(mContext,2);
        }
        rvImg.setLayoutManager(manager);
        rvImg.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            Log.d(TAG, "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
            headImg.setImageResource(R.mipmap.ic_launcher);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            Log.e(TAG,  "model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }
    };

    //这里面的resource就是fromhtml函数的第一个参数里面的含有的url
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Log.i("RG", "source---?>>>" + source);
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                Log.i("RG", "url---?>>>" + url);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            Log.i("RG", "url---?>>>" + url);
            return drawable;
        }
    };




}
