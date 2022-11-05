package com.llw.goodweather.ui;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.WallPaperAdapter;
import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.bean.WallPaperResponse;
import com.llw.goodweather.contract.WallPaperContract;
import com.llw.goodweather.databinding.ActivityWallPaperBinding;
import com.llw.goodweather.utils.CameraUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.bean.WallPaper;
import com.llw.mvplibrary.mvp.MvpVBActivity;
import com.llw.mvplibrary.view.dialog.AlertDialog;
import org.litepal.LitePal;
import java.util.ArrayList;
import java.util.List;

import static com.llw.goodweather.utils.Constant.SELECT_PHOTO;

/**
 * 壁纸管理
 *
 * @author llw
 */
public class WallPaperActivity extends MvpVBActivity<ActivityWallPaperBinding, WallPaperContract.WallPaperPresenter>
        implements WallPaperContract.IWallPaperView {

    /**
     * 壁纸数据列表
     */
    private List<WallPaperResponse.ResBean.VerticalBean> mList = new ArrayList<>();
    /**
     * 壁纸数据适配器
     */
    private WallPaperAdapter mAdapter;
    /**
     * item高度列表
     */
    private List<Integer> heightList = new ArrayList<>();

    /**
     * 壁纸数量
     */
    private static final int WALLPAPER_NUM = 30;

    /**
     * 头部和底部的item数据
     */
    private WallPaperResponse.ResBean.VerticalBean topBean, bottomBean;

    /**
     * 必应的每日壁纸
     */
    private String biyingUrl = null;

    /**
     * 底部弹窗
     */
    AlertDialog bottomSettingDialog = null;


    @Override
    public void initData() {
        //加载弹窗
        showLoadingDialog();
        //高亮状态栏
        StatusBarUtil.StatusBarLightMode(this);
        //左上角的返回
        Back(binding.toolbar);
        initWallPaperList();

        binding.fabSetting.setOnClickListener(v -> {
            binding.fabSetting.hide();
            int type = SPUtils.getInt(Constant.WALLPAPER_TYPE, 4, context);
            showSettingDialog(type);
        });
    }

    /**
     * 初始化列表数据
     */
    private void initWallPaperList() {

        heightList.add(100);
        for (int i = 0; i < WALLPAPER_NUM; i++) {
            heightList.add(300);
        }
        heightList.add(100);

        mAdapter = new WallPaperAdapter(R.layout.item_wallpaper_list, mList, heightList);
        //瀑布流
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //设置布局管理
        binding.rv.setLayoutManager(manager);
        //设置数据适配器
        binding.rv.setAdapter(mAdapter);
        //请求数据
        mPresent.getWallPaper();
        //获取必应壁纸
        mPresent.biying();
        mAdapter.addChildClickViewIds(R.id.item_wallpaper);//添加点击事件
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //这里的列表数据实际上有32条，有两条假数据，就是首尾这两条，所以点击的时候要做判断处理
            if (position == 0 || position == mList.size() - 1) {//是否为第一条或者最后一条
                startActivity(new Intent(context,AboutUsActivity.class));
            } else {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("position", position - 1);
                startActivity(intent);
            }

        });

        //滑动监听
        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) {
                    binding.fabSetting.show();
                } else {//上滑
                    binding.fabSetting.hide();
                }
            }
        });

    }

    @Override
    protected WallPaperContract.WallPaperPresenter createPresent() {
        return new WallPaperContract.WallPaperPresenter();
    }

    /**
     * 必应壁纸数据返回
     *
     * @param response BiYingImgResponse
     */
    @Override
    public void getBiYingResult(BiYingImgResponse response) {
        if (response.getImages() != null) {
            //得到的图片地址是没有前缀的，所以加上前缀否则显示不出来
            biyingUrl = "http://cn.bing.com" + response.getImages().get(0).getUrl();
            Log.d("type-->", biyingUrl);
        } else {
            ToastUtils.showShortToast(context, "未获取到必应的图片");
        }
    }


    /**
     * 网络壁纸数据返回
     *
     * @param response WallPaperResponse
     */
    @Override
    public void getWallPaperResult(WallPaperResponse response) {
        if (response.getMsg().equals(Constant.SUCCESS)) {

            List<WallPaperResponse.ResBean.VerticalBean> data = response.getRes().getVertical();

            //创建头部和底部的两个广告item的假数据
            topBean = new WallPaperResponse.ResBean.VerticalBean();
            topBean.setDesc("top");
            topBean.setImg("");
            bottomBean = new WallPaperResponse.ResBean.VerticalBean();
            bottomBean.setDesc("bottom");
            bottomBean.setImg("");

            //数据填充
            if (data != null && data.size() > 0) {
                mList.clear();
                //添加头部
                mList.add(topBean);
                //添加主要数据
                for (int i = 0; i < data.size(); i++) {
                    mList.add(data.get(i));
                }
                //添加尾部
                mList.add(bottomBean);
                Log.d("list-->", new Gson().toJson(mList));

                //根据数据数量来刷新列表
                mAdapter.notifyItemInserted(mList.size());
                //删除数据库中的数据
                LitePal.deleteAll(WallPaper.class);
                for (int i = 0; i < mList.size(); i++) {
                    WallPaper wallPaper = new WallPaper();
                    wallPaper.setImgUrl(mList.get(i).getImg());
                    wallPaper.save();
                }
            } else {
                ToastUtils.showShortToast(context, "壁纸数据为空");
            }
            dismissLoadingDialog();
        } else {
            dismissLoadingDialog();
            ToastUtils.showShortToast(context, "未获取到壁纸数据");
        }
    }

    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "请求超时");
    }

    /**
     * 壁纸底部弹窗弹窗
     */
    private void showSettingDialog(int type) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromBottom(true)
                //载入布局文件
                .setContentView(R.layout.dialog_bottom_wallpaper_setting)
                //设置弹窗宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //壁纸列表
                .setOnClickListener(R.id.lay_wallpaper_list, v -> {
                    Intent intent = new Intent(context, ImageActivity.class);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                    bottomSettingDialog.dismiss();
                    //每日一图
                }).setOnClickListener(R.id.lay_everyday_wallpaper, v -> {
                    ToastUtils.showShortToast(context, "使用每日一图");
                    SPUtils.putString(Constant.WALLPAPER_URL, biyingUrl, context);
                    //壁纸列表
                    SPUtils.putInt(Constant.WALLPAPER_TYPE, 2, context);
                    bottomSettingDialog.dismiss();
                    //手动上传
                }).setOnClickListener(R.id.lay_upload_wallpaper, v -> {
                    startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
                    ToastUtils.showShortToast(context, "请选择图片");
                    bottomSettingDialog.dismiss();
                    //默认壁纸
                }).setOnClickListener(R.id.lay_default_wallpaper, v -> {
                    ToastUtils.showShortToast(context, "使用默认壁纸");
                    SPUtils.putInt(Constant.WALLPAPER_TYPE, 4, context);//使用默认壁纸
                    SPUtils.putString(Constant.WALLPAPER_URL, null, context);
                    bottomSettingDialog.dismiss();
                });

        bottomSettingDialog = builder.create();
        ImageView iv_wallpaper_list = (ImageView) bottomSettingDialog.getView(R.id.iv_wallpaper_list);
        ImageView iv_everyday_wallpaper = (ImageView) bottomSettingDialog.getView(R.id.iv_everyday_wallpaper);
        ImageView iv_upload_wallpaper = (ImageView) bottomSettingDialog.getView(R.id.iv_upload_wallpaper);
        ImageView iv_default_wallpaper = (ImageView) bottomSettingDialog.getView(R.id.iv_default_wallpaper);
        switch (type) {
            case 1://壁纸列表
                iv_wallpaper_list.setVisibility(View.VISIBLE);
                break;
            case 2://每日一图
                iv_everyday_wallpaper.setVisibility(View.VISIBLE);
                break;
            case 3://手动上传
                iv_upload_wallpaper.setVisibility(View.VISIBLE);
                break;
            case 4://默认壁纸
                iv_default_wallpaper.setVisibility(View.VISIBLE);
                break;
            default:
                iv_default_wallpaper.setVisibility(View.GONE);
                break;
        }
        bottomSettingDialog.show();
        //弹窗关闭监听
        bottomSettingDialog.setOnDismissListener(dialog -> binding.fabSetting.show());
    }


    /**
     * Activity返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImgeOnKitKatPath(data, this);
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, this);
                    }
                    displayImage(imagePath);
                }
                Log.d("result-->", requestCode + "   " + resultCode + "   " + data.getData().toString());

                break;
            default:
                Log.d("result-->", requestCode + "   " + resultCode + "   " + data.getData().toString());
                break;
        }
    }

    /**
     * 从相册获取完图片(根据图片路径显示图片)
     */
    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            //将本地上传选中的图片地址放入缓存,当手动定义开关打开时，取出缓存中的图片地址，显示为背景
            SPUtils.putInt(Constant.WALLPAPER_TYPE, 3, context);
            SPUtils.putString(Constant.WALLPAPER_URL, imagePath, context);
            ToastUtils.showShortToast(context, "已更换为你选择的图片");
        } else {
            SPUtils.putInt(Constant.WALLPAPER_TYPE, 0, context);
            ToastUtils.showShortToast(context, "图片获取失败");
        }
    }

}
