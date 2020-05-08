package com.llw.goodweather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.R;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.utils.LiWindow;
import com.llw.mvplibrary.view.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shihao.library.XRadioGroup;

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
                } else {
                    SPUtils.putBoolean(Constant.CUSTOM_IMG, false, context);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_background_manager;
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
