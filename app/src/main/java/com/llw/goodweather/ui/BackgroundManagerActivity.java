package com.llw.goodweather.ui;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
import com.llw.mvplibrary.utils.AnimationUtil;
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
 *
 * @author llw
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
    RxPermissions rxPermissions;//权限请求

    private AnimationUtil animationUtil;//动画工具类
    private float bgAlpha = 1f;//背景透明度
    private boolean bright = false;//判断标识
    private static final long DURATION = 500;//0.5s
    private static final float START_ALPHA = 0.7f;//开始透明度
    private static final float END_ALPHA = 1f;//结束透明度

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.StatusBarLightMode(this);//高亮状态栏
        Back(toolbar);//左上角的返回
        animationUtil = new AnimationUtil();//实例化
        initSwitchButton();
    }

    //初始化三个开关按钮  三个只能开一个
    private void initSwitchButton() {

        boolean isEverydayImg = SPUtils.getBoolean(Constant.EVERYDAY_IMG, false, context);//每日图片
        boolean isImgList = SPUtils.getBoolean(Constant.IMG_LIST, false, context);//图片列表
        boolean isCustomImg = SPUtils.getBoolean(Constant.CUSTOM_IMG, false, context);//手动定义
        if (isEverydayImg == true) {
            wbEveryday.setChecked(true);
        } else if (isImgList == true) {
            wbImgList.setChecked(true);
        } else if (isCustomImg == true) {
            wbCustom.setChecked(true);
        }

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
                    toggleBright();
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
                    SPUtils.putBoolean(Constant.CUSTOM_IMG, true, context);
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
            if (Build.VERSION.SDK_INT < 19) {
                intent.setAction(Intent.ACTION_GET_CONTENT);
            } else {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            }
            startActivityForResult(intent, SELECT_PHOTO);
        }

    }

    //动态权限申请
    private void permissionsRequest() {//使用这个框架需要制定JDK版本，建议用1.8
        rxPermissions = new RxPermissions(context);
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
            SPUtils.putString(Constant.CUSTOM_IMG_PATH, imagePath, context);
            ToastUtils.showShortToast(context, "已更换为你选择的壁纸");
        } else {
            wbCustom.setChecked(true);//关闭手动定义开关
            ToastUtils.showShortToast(context, "图片获取失败");
        }
    }


    /**
     * 显示图片弹窗
     */
    private void showImgWindow() {
        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_img_list, null);
        XRadioGroup xRadioGroup = (XRadioGroup) view.findViewById(R.id.xrg_img);
        //显示弹窗的时候，取缓存，判断里面有没有选中过图片
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
        //xRadioGroup的选中监听
        xRadioGroup.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup xRadioGroup, int i) {
                //得出选中id对应的RadioButton,从而知道选中的是哪一个，并放入缓存，0~5
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
                ToastUtils.showShortToast(context, "已更换壁纸");

            }
        });
        //弹窗关闭监听  弹窗关闭时，如果什么都没有选中，则自然不会有缓存中的取会0~5,所以当为-1时，关闭图片列表的开关
        PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
                int position = SPUtils.getInt(Constant.IMG_POSITION, -1, context);
                if (position != -1) {
                    wbImgList.setChecked(true);
                } else {
                    wbImgList.setChecked(false);
                }
            }
        };
        liWindow.showBottomPopupWindow(view, onDismissListener);//显示弹窗 ，传入关闭弹窗监听

    }

    /**
     * 计算动画时间
     */
    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animationUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animationUtil.addUpdateListener(new AnimationUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animationUtil.addEndListner(new AnimationUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animationUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


}
