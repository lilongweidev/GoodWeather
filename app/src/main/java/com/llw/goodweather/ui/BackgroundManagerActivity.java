package com.llw.goodweather.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.R;
import com.llw.goodweather.utils.CameraUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.utils.LiWindow;
import com.llw.mvplibrary.view.SwitchButton;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shihao.library.XRadioGroup;

import static com.llw.goodweather.utils.Constant.SELECT_PHOTO;

/**
 * 壁纸管理  三种模式，本地壁纸列表、必应每日一图、自己上传图片
 */
public class BackgroundManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;//标题栏返回
    @BindView(R.id.wb_everyday)
    SwitchButton wbEveryday;//每日一图开关
    @BindView(R.id.wb_img_list)
    SwitchButton wbImgList;//图片列表
    @BindView(R.id.wb_custom_)
    SwitchButton wbCustom;//手动定义

    LiWindow liWindow;//弹窗
    RxPermissions rxPermissions;

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.StatusBarLightMode(this);//高亮状态栏
        Back(toolbar);
        initSwitchButton();
    }

    //初始化三个开关按钮  三个只能开一个
    private void initSwitchButton() {
        //每日一图按钮开关监听
        wbEveryday.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {//开
                    SPUtils.putBoolean(Constant.EVERYDAY_IMG, true, context);
                    wbImgList.setChecked(false);
                    wbCustom.setChecked(false);
                } else {//关
                    SPUtils.putBoolean(Constant.EVERYDAY_IMG, false, context);
                }
            }
        });
        //图片列表按钮开关监听
        wbImgList.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    SPUtils.putBoolean(Constant.IMG_LIST, true, context);
                    wbEveryday.setChecked(false);
                    wbCustom.setChecked(false);
                    //弹窗窗口显示布局
                    showImgWindow();
                } else {
                    SPUtils.putBoolean(Constant.IMG_LIST, false, context);
                }
            }
        });
        //手动定义按钮开关监听
        wbCustom.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    SPUtils.putBoolean(Constant.CUSTOM_IMG, false, context);
                    wbEveryday.setChecked(false);
                    wbImgList.setChecked(false);
                    //Android版本判断 6.0及以上动态权限获取
                    permissionVersion();
                } else {
                    SPUtils.putBoolean(Constant.CUSTOM_IMG, false, context);
                }
            }
        });
    }

    //权限判断
    private void permissionVersion() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 23) {//6.0或6.0以上
            //动态权限申请
            permissionsRequest();
        } else {//6.0以下
            //发现只要权限在AndroidManifest.xml中注册过，均会认为该权限granted  提示一下即可
            if (Build.VERSION.SDK_INT <19) {
                intent.setAction(Intent.ACTION_GET_CONTENT);
            }else {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            }
            startActivityForResult(intent, SELECT_PHOTO);
        }

    }

    //动态权限申请
    private void permissionsRequest() {//使用这个框架需要制定JDK版本，建议用1.8
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {//申请成功
                        //得到权限之后打开本地相册
                        Intent selectPhotoIntent = CameraUtils.getSelectPhotoIntent();
                        startActivityForResult(selectPhotoIntent, SELECT_PHOTO);
                    } else {//申请失败
                        wbCustom.setChecked(false);
                        ToastUtils.showShortToast(this, "权限未开启");
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_background_manager;
    }

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
            SPUtils.putString(Constant.CUSTOM_IMG_PATH, imagePath, context);
        } else {
            wbCustom.setChecked(true);
            ToastUtils.showShortToast(context,"图片获取失败");
        }
    }


    //显示图片弹窗
    private void showImgWindow() {
        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_img_list, null);
        XRadioGroup xRadioGroup = (XRadioGroup) view.findViewById(R.id.xrg_img);
        int position = SPUtils.getInt(Constant.IMG_POSITION, -1, context);
        RadioButton rbImg1 = (RadioButton) view.findViewById(R.id.rb_img_1);
        RadioButton rbImg2 = (RadioButton) view.findViewById(R.id.rb_img_2);
        RadioButton rbImg3 = (RadioButton) view.findViewById(R.id.rb_img_3);
        RadioButton rbImg4 = (RadioButton) view.findViewById(R.id.rb_img_4);
        RadioButton rbImg5 = (RadioButton) view.findViewById(R.id.rb_img_5);
        RadioButton rbImg6 = (RadioButton) view.findViewById(R.id.rb_img_6);
        switch (position) {
            case 0:
                rbImg1.setChecked(true);
                break;
            case 1:
                rbImg2.setChecked(true);
                break;
            case 2:
                rbImg3.setChecked(true);
                break;
            case 3:
                rbImg4.setChecked(true);
                break;
            case 4:
                rbImg5.setChecked(true);
                break;
            case 5:
                rbImg6.setChecked(true);
                break;
        }


        xRadioGroup.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup xRadioGroup, int i) {

                switch (xRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_img_1:
                        SPUtils.putInt(Constant.IMG_POSITION, 0, context);
                        liWindow.closePopupWindow();
                        break;
                    case R.id.rb_img_2:
                        SPUtils.putInt(Constant.IMG_POSITION, 1, context);
                        liWindow.closePopupWindow();
                        break;
                    case R.id.rb_img_3:
                        SPUtils.putInt(Constant.IMG_POSITION, 2, context);
                        liWindow.closePopupWindow();
                        break;
                    case R.id.rb_img_4:
                        SPUtils.putInt(Constant.IMG_POSITION, 3, context);
                        liWindow.closePopupWindow();
                        break;
                    case R.id.rb_img_5:
                        SPUtils.putInt(Constant.IMG_POSITION, 4, context);
                        liWindow.closePopupWindow();
                        break;
                    case R.id.rb_img_6:
                        SPUtils.putInt(Constant.IMG_POSITION, 5, context);
                        liWindow.closePopupWindow();
                        break;
                    default:
                        SPUtils.putInt(Constant.IMG_POSITION, 5, context);
                        break;
                }
                ToastUtils.showShortToast(context,"已更换壁纸");

            }
        });
        PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                int position = SPUtils.getInt(Constant.IMG_POSITION, -1, context);
                if (position != -1) {
                    wbImgList.setChecked(true);
                } else {
                    wbImgList.setChecked(false);
                }
            }
        };
        liWindow.showBottomPopupWindow(view, onDismissListener);//显示弹窗

    }


}
