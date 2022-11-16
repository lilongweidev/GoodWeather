package com.llw.goodweather.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.goodweather.R;
import com.llw.goodweather.databinding.ActivityImageBinding;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.vb.BaseVBActivity;
import com.llw.mvplibrary.bean.WallPaper;
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

/**
 * 查看图片
 *
 * @author llw
 */
public class ImageActivity extends BaseVBActivity<ActivityImageBinding> implements View.OnClickListener {

    List<WallPaper> mList = new ArrayList<>();
    WallPaperAdapter mAdapter;
    String wallpaperUrl = null;

    private int position;
    private Bitmap bitmap;


    @Override
    public void initData() {
        showLoadingDialog();
        //透明状态栏
        StatusBarUtil.transparencyBar(context);
        //获取位置
        position = getIntent().getIntExtra("position", 0);
        //获取数据
        mList = LitePal.findAll(WallPaper.class);
        Log.d("list-->", "" + mList.size());
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if ("".equals(mList.get(i).getImgUrl())) {
                    mList.remove(i);
                }
            }
        }

        mAdapter = new WallPaperAdapter(R.layout.item_image_list, mList);
        //ViewPager2实现方式
        binding.vp.setAdapter(mAdapter);

        binding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.d("position-->", "" + position);
                wallpaperUrl = mList.get(position).getImgUrl();
                bitmap = getBitMap(wallpaperUrl);
            }
        });

        mAdapter.notifyDataSetChanged();
        binding.vp.setCurrentItem(position, false);

        dismissLoadingDialog();

        //添加点击监听
        binding.ivBack.setOnClickListener(this);
        binding.btnSettingWallpaper.setOnClickListener(this);
        binding.btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            //设置壁纸
            case R.id.btn_setting_wallpaper:
                //放入缓存
                SPUtils.putString(Constant.WALLPAPER_URL, wallpaperUrl, context);
                //壁纸列表
                SPUtils.putInt(Constant.WALLPAPER_TYPE, 1, context);
                ToastUtils.showShortToast(context, "已设置");
                break;
            //下载壁纸
            case R.id.btn_download:
                saveImageToGallery(context, bitmap);
                break;
            default:
                break;
        }
    }

    /**
     * 壁纸适配器
     */
    public static class WallPaperAdapter extends BaseQuickAdapter<WallPaper, BaseViewHolder> {

        public WallPaperAdapter(int layoutResId, @Nullable List<WallPaper> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WallPaper item) {
            ImageView imageView = helper.getView(R.id.wallpaper);
            Glide.with(getContext()).load(item.getImgUrl()).into(imageView);
        }
    }

    /**
     * 保存图片到本地相册
     *
     * @param context 上下文
     * @param bitmap  bitmap
     * @return 结果
     */
    public boolean saveImageToGallery(Context context, Bitmap bitmap) {
        // 图片保存路径要根据Android版本进行修改
        String filePath;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            filePath = context.getExternalFilesDir(null).getAbsolutePath() + File.separator + "good_weather";
        }  else {
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "good_weather";
        }

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
