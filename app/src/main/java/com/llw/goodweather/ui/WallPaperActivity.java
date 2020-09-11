package com.llw.goodweather.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.WallPaperAdapter;
import com.llw.goodweather.bean.WallPaperResponse;
import com.llw.goodweather.contract.WallPaperContract;
import com.llw.goodweather.utils.CameraUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.bean.WallPaper;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.llw.mvplibrary.utils.SizeUtils;
import com.llw.mvplibrary.view.dialog.AlertDialog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

import static com.llw.goodweather.utils.Constant.SELECT_PHOTO;

/**
 * 壁纸管理
 */
public class WallPaperActivity extends MvpActivity<WallPaperContract.WallPaperPresenter> implements WallPaperContract.IWallPaperView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.fab_setting)
    FloatingActionButton fabSetting;


    List<WallPaperResponse.ResBean.VerticalBean> mList = new ArrayList<>();
    WallPaperAdapter mAdapter;
    AlertDialog bottomSettingDialog = null;

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.StatusBarLightMode(this);//高亮状态栏
        Back(toolbar);//左上角的返回
        initWallPaperList();

    }

    private void initWallPaperList() {
        mAdapter = new WallPaperAdapter(R.layout.item_wallpaper_list, mList);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        rv.setAdapter(mAdapter);
        mPresent.getWallPaper();//请求数据

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        //滑动监听
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) {
                    fabSetting.show();
                } else {//上滑
                    fabSetting.hide();
                }
            }
        });


    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_wall_paper;
    }

    @Override
    protected WallPaperContract.WallPaperPresenter createPresent() {
        return new WallPaperContract.WallPaperPresenter();
    }

    @Override
    public void getWallPaperResult(Response<WallPaperResponse> response) {
        if (response.body().getMsg().equals("success")) {
            List<WallPaperResponse.ResBean.VerticalBean> data = response.body().getRes().getVertical();
            if (data != null && data.size() > 0) {
                mList.clear();
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();

                LitePal.deleteAll(WallPaper.class);
                for (int i = 0;i< mList.size();i++){
                    WallPaper wallPaper = new WallPaper();
                    wallPaper.setImgUrl(mList.get(i).getImg());
                    wallPaper.save();
                }



            } else {
                ToastUtils.showShortToast(context, "壁纸数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, "未获取到壁纸数据");
        }
    }

    @Override
    public void getDataFailed() {

    }


    @OnClick(R.id.fab_setting)
    public void onViewClicked() {
        fabSetting.hide();
        int type = SPUtils.getInt(Constant.WALLPAPER_TYPE, 4, context);
        showSettingDialog(type);
    }


    /**
     * 更新弹窗
     */
    private void showSettingDialog(int type) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromBottom(true)
                .setContentView(R.layout.dialog_bottom_wallpaper_setting)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.lay_wallpaper_list, v -> {//壁纸列表
                    ToastUtils.showShortToast(context, "壁纸列表");
                    bottomSettingDialog.dismiss();
                }).setOnClickListener(R.id.lay_everyday_wallpaper, v -> {//每日一图
                    ToastUtils.showShortToast(context, "每日一图");
                    bottomSettingDialog.dismiss();
                }).setOnClickListener(R.id.lay_upload_wallpaper, v -> {//手动上传
                    ToastUtils.showShortToast(context, "手动上传");

                    startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);

                    bottomSettingDialog.dismiss();
                }).setOnClickListener(R.id.lay_default_wallpaper, v -> {//默认壁纸
                    ToastUtils.showShortToast(context, "默认壁纸");
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
        }

        bottomSettingDialog.show();

        //弹窗关闭监听
        bottomSettingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                fabSetting.show();
            }
        });
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
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImgeOnKitKatPath(data, this);
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, this);
                    }
                    displayImage(imagePath);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 从相册获取完图片(根据图片路径显示图片)
     */
    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            //将本地上传选中的图片地址放入缓存,当手动定义开关打开时，取出缓存中的图片地址，显示为背景
            SPUtils.putInt(Constant.WALLPAPER_TYPE, 4, context);
            ToastUtils.showShortToast(context, "已更换为你上传的壁纸");
        } else {
            SPUtils.putInt(Constant.WALLPAPER_TYPE, 0, context);
            ToastUtils.showShortToast(context, "图片获取失败");
        }
    }


}
