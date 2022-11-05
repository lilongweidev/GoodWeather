package com.llw.goodweather.ui;

import com.google.android.material.slider.Slider;
import com.llw.goodweather.R;
import com.llw.goodweather.databinding.ActivitySettingBinding;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.mvplibrary.base.vb.BaseVBActivity;
import com.llw.mvplibrary.view.SwitchButton;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 应用设置页面
 *
 * @author llw
 */
public class SettingActivity extends BaseVBActivity<ActivitySettingBinding> {

    //播报人
    private final List<String> nameList = new LinkedList<>(Arrays.asList(
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
    public void initData() {
        //白色状态栏
        StatusBarUtil.setStatusBarColor(context, R.color.white);
        //黑色字体
        StatusBarUtil.StatusBarLightMode(context);
        Back(binding.toolbar);
        //设置Switch
        setSwitch(binding.wbEveryday,1);
        setSwitch(binding.wbVoiceSearch,2);

        //初始化Spinner
        initSpinner();

        //设置Slider
        setSlider(binding.sliderSpeed, 1);
        setSlider(binding.sliderPitch, 2);
        setSlider(binding.sliderVolume, 3);
    }

    /**
     * 设置Switch
     */
    private void setSwitch(SwitchButton switchButton, final int type) {
        binding.wbEveryday.setChecked(SPUtils.getBoolean(Constant.EVERYDAY_POP_BOOLEAN, true, context));
        binding.wbVoiceSearch.setChecked(SPUtils.getBoolean(Constant.VOICE_SEARCH_BOOLEAN, true, context));

        switchButton.setOnCheckedChangeListener((view, isChecked) -> {
            switch (type) {
                case 1:
                    SPUtils.putBoolean(Constant.EVERYDAY_POP_BOOLEAN, isChecked, context);
                    break;
                case 2:
                    SPUtils.putBoolean(Constant.VOICE_SEARCH_BOOLEAN, isChecked, context);
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
        binding.sliderSpeed.setValue(Float.parseFloat(speedValue));
        binding.sliderPitch.setValue(Float.parseFloat(pitchValue));
        binding.sliderVolume.setValue(Float.parseFloat(volumeValue));

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
        binding.nsVoicer.attachDataSource(nameList);

        //获取缓存值
        String voiceName = SPUtils.getString(Constant.VOICE_NAME, "xiaoyan", context);

        //查找在数组中的位置
        int index = Arrays.asList(arrayValue).indexOf(voiceName);
        //获取选中的值
        binding.nsVoicer.setSelectedIndex(index);
        //选中监听
        binding.nsVoicer.setOnSpinnerItemSelectedListener((parent, view, position, id) ->
                SPUtils.putString(Constant.VOICE_NAME, arrayValue[position], context));
    }
}
