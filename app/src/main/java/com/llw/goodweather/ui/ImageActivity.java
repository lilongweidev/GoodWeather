package com.llw.goodweather.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.eventbus.ChangeWallPaperEvent;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.RecyclerViewScrollHelper;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.bean.WallPaper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看图片
 */
public class ImageActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_setting_wallpaper)
    MaterialButton btnSettingWallpaper;
    @BindView(R.id.btn_download)
    MaterialButton btnDownload;
    @BindView(R.id.vp)
    ViewPager2 vp;

    List<WallPaper> mList = new ArrayList<>();
    WallPaperAdapter mAdapter;
    String wallpaperUrl = null;

    private int position;//位置
    private RxPermissions rxPermissions;//权限
    private Bitmap bitmap;


    @Override
    public void initData(Bundle savedInstanceState) {
        showLoadingDialog();
        //透明状态栏
        StatusBarUtil.transparencyBar(context);
        //获取位置
        position = getIntent().getIntExtra("position", 0);
        //获取数据
        mList = LitePal.findAll(WallPaper.class);
        Log.d("list-->", "" + mList.size());
        if (mList != null && mList.size() > 0) {
            for (int i=0;i<mList.size();i++){
                if(mList.get(i).getImgUrl().equals("")){
                    mList.remove(i);
                }
            }
        }
        Log.d("list-->", "" + mList.size());

        //RecyclerView实现方式
        mAdapter = new WallPaperAdapter(R.layout.item_image_list, mList);

        Log.d("wallPaper", new Gson().toJson(mList));

        //ViewPager2实现方式
        vp.setAdapter(mAdapter);

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.d("position-->", "" + position);
                wallpaperUrl = mList.get(position).getImgUrl();
                bitmap = getBitMap(wallpaperUrl);
            }
        });

        mAdapter.notifyDataSetChanged();
        vp.setCurrentItem(position, false);

        dismissLoadingDialog();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image;
    }


    @OnClick({R.id.iv_back, R.id.btn_setting_wallpaper, R.id.btn_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_setting_wallpaper://设置壁纸
                SPUtils.putString(Constant.WALLPAPER_URL, wallpaperUrl, context);//放入缓存
                SPUtils.putInt(Constant.WALLPAPER_TYPE, 1, context);//壁纸列表
                //发送消息
                EventBus.getDefault().post(new ChangeWallPaperEvent(1));
                ToastUtils.showShortToast(context, "已设置");
                break;
            case R.id.btn_download://下载壁纸
                saveImageToGallery(context, bitmap);
                break;
        }
    }

    /**
     * 壁纸适配器
     */
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

    /**
     * 保存图片到本地相册
     *
     * @param context 上下文
     * @param bitmap  bitmap
     * @return
     */
    public boolean saveImageToGallery(Context context, Bitmap bitmap) {
        // 首先保存图片
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "good_weather";
        File appDir = new File(filePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "wallpaper" + 1024 + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            //把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                ToastUtils.showShortToast(context, "图片保存成功");
                return true;
            } else {
                ToastUtils.showShortToast(context, "图片保存失败");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToastUtils.showShortToast(context, "图片保存失败");
        return false;
    }

    /**
     * Url转Bitmap
     *
     * @param url
     * @return
     */
    public Bitmap getBitMap(final String url) {
        //新启一个线程进行转换
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return bitmap;

    }

}
