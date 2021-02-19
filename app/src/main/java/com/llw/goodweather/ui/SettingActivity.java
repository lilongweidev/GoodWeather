package com.llw.goodweather.ui;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.slider.Slider;
import com.llw.goodweather.R;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.view.SwitchButton;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 应用设置页面
 *
 * @author llw
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wb_everyday)
    SwitchButton wbEveryday;//每日弹窗开关
    @BindView(R.id.ns_voicer)
    NiceSpinner nsVoicer;//设置播报人
    @BindView(R.id.slider_speed)
    Slider sliderSpeed;//语速
    @BindView(R.id.slider_pitch)
    Slider sliderPitch;//音调
    @BindView(R.id.slider_volume)
    Slider sliderVolume;//音量
    @BindView(R.id.wb_voice_search)
    SwitchButton wbVoiceSearch;//语音搜索开关


    //播报人
    private List<String> nameList = new LinkedList<>(Arrays.asList(
            "小燕", "许久", "小萍", "小婧", "许小宝"
    ));

    //发音人值
    private static final String[] arrayValue = {"xiaoyan", "aisjiuxu", "aisxping", "aisjinger", "aisbabyxu"};

    //语速
    private static String speedValue = "50";
    //音调
    private static String pitchValue = "50";
    //音量
    private static String volumeValue = "50";

    @Override
    public void initData(Bundle savedInstanceState) {
        //白色状态栏
        StatusBarUtil.setStatusBarColor(context, R.color.white);
        //黑色字体
        StatusBarUtil.StatusBarLightMode(context);
        Back(toolbar);
        //设置Switch
        setSwitch(wbEveryday,1);
        setSwitch(wbVoiceSearch,2);

        //初始化Spinner
        initSpinner();

        //设置Slider
        setSlider(sliderSpeed, 1);
        setSlider(sliderPitch, 2);
        setSlider(sliderVolume, 3);
    }

    /**
     * 设置Switch
     */
    private void setSwitch(SwitchButton switchButton, final int type) {

        wbEveryday.setChecked(SPUtils.getBoolean(Constant.EVERYDAY_POP_BOOLEAN, true, context));
        wbVoiceSearch.setChecked(SPUtils.getBoolean(Constant.VOICE_SEARCH_BOOLEAN, true, context));

        switchButton.setOnCheckedChangeListener((view, isChecked) -> {
            switch (type) {
                case 1:
                    if (isChecked) {
                        SPUtils.putBoolean(Constant.EVERYDAY_POP_BOOLEAN, true, context);
                    } else {
                        SPUtils.putBoolean(Constant.EVERYDAY_POP_BOOLEAN, false, context);
                    }
                    break;
                case 2:
                    if (isChecked) {
                        SPUtils.putBoolean(Constant.VOICE_SEARCH_BOOLEAN, true, context);
                    } else {
                        SPUtils.putBoolean(Constant.VOICE_SEARCH_BOOLEAN, false, context);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * 设置Slider
     */
    private void setSlider(Slider slider, final int type) {
        //获取之前设置的进度
        speedValue = SPUtils.getString(Constant.SPEED, "50", context);
        pitchValue = SPUtils.getString(Constant.PITCH, "50", context);
        volumeValue = SPUtils.getString(Constant.VOLUME, "50", context);
        //设置进度
        sliderSpeed.setValue(Float.parseFloat(speedValue));
        sliderPitch.setValue(Float.parseFloat(pitchValue));
        sliderVolume.setValue(Float.parseFloat(volumeValue));

        //数值改变监听
        slider.addOnChangeListener((slider1, value, fromUser) -> {
            switch (type) {
                case 1://设置语速 范围 1~100
                    SPUtils.putString(Constant.SPEED, Float.toString(value), context);
                    break;
                case 2://设置音调  范围 1~100
                    SPUtils.putString(Constant.PITCH, Float.toString(value), context);
                    break;
                case 3://设置音量  范围 1~100
                    SPUtils.putString(Constant.VOLUME, Float.toString(value), context);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * 初始化Spinner
     */
    private void initSpinner() {
        //设置下拉数据
        nsVoicer.attachDataSource(nameList);

        //获取缓存值
        String voiceName = SPUtils.getString(Constant.VOICE_NAME, "xiaoyan", context);

        //查找在数组中的位置
        int index = Arrays.asList(arrayValue).indexOf(voiceName);
        //获取选中的值
        nsVoicer.setSelectedIndex(index);
        //选中监听
        nsVoicer.setOnSpinnerItemSelectedListener((parent, view, position, id) ->
                SPUtils.putString(Constant.VOICE_NAME, arrayValue[position], context));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

}
