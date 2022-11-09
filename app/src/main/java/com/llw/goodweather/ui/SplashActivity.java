package com.llw.goodweather.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.llw.goodweather.MainActivity;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.BiYingImgResponse;
import com.llw.goodweather.contract.SplashContract;
import com.llw.goodweather.databinding.ActivitySplashBinding;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.bean.Country;
import com.llw.mvplibrary.mvp.MvpVBActivity;
import com.llw.mvplibrary.utils.SizeUtils;
import com.llw.mvplibrary.view.dialog.AlertDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.commonsdk.UMConfigure;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 欢迎页
 *
 * @author llw
 */
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends MvpVBActivity<ActivitySplashBinding, SplashContract.SplashPresenter> implements SplashContract.ISplashView {
    /**
     * 权限请求框架
     */
    private RxPermissions rxPermissions;
    private static AlertDialog privacyPolicyDialog;

    @Override
    public void initData() {
        //透明状态栏
        StatusBarUtil.transparencyBar(context);
        agreePrivacyPolicy();
    }

    /**
     * 权限判断
     */
    private void permissionVersion() {
        //6.0或6.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //动态权限申请
            permissionsRequest();
        } else {//6.0以下
            //发现只要权限在AndroidManifest.xml中注册过，均会认为该权限granted  提示一下即可
            ToastUtils.showShortToast(this, "你的手机版本在Android6.0以下，不需要动态申请权限。");
        }
    }

    /**
     * 动态权限申请  使用这个框架需要制定JDK版本，建议用1.8
     */
    @SuppressLint("CheckResult")
    private void permissionsRequest() {
        //实例化这个权限请求框架，否则会报错
        rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {//申请成功
                        //得到权限可以进入APP
                        //请求版本更新
                        mPresent.getAppInfo();
                        //获取必应壁纸
                        mPresent.biying();
                    } else {//申请失败
                        finish();
                        ToastUtils.showShortToast(this, "权限未开启");
                    }
                });
    }

    private List<Country> list;

    /**
     * 初始化世界国家及地区数据
     */
    private void initCountryData() {
        list = LitePal.findAll(Country.class);
        //有数据了
        if (list.size() > 0) {
            goToMain();
            //第一次加载
        } else {
            InputStreamReader is = null;
            try {
                is = new InputStreamReader(getAssets().open("world_country.csv"), "UTF-8");
                BufferedReader reader = new BufferedReader(is);
                reader.readLine();
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] result = line.split(",");
                    Country country = new Country();
                    country.setName(result[0]);
                    country.setCode(result[1]);
                    country.save();
                }
                goToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 进入主页面
     */
    @SuppressLint("NewApi")
    private void goToMain() {
        new Handler().postDelayed(() -> {
            finish();
            startActivity(new Intent(context, MainActivity.class));
        }, 1000);
    }

    @Override
    protected SplashContract.SplashPresenter createPresent() {
        return new SplashContract.SplashPresenter();
    }

    /**
     * 获取APP最新版本信息返回
     *
     * @param response
     */
    @Override
    public void getAppInfoResult(AppVersion response) {
        if (response != null) {
            AppVersion appVersion = new AppVersion();
            //应用名称
            appVersion.setName(response.getName());
            //应用版本 对应code
            appVersion.setVersion(response.getVersion());
            //应用版本名
            appVersion.setVersionShort(response.getVersionShort());
            //更新日志
            appVersion.setChangelog(response.getChangelog());
            //更新地址
            appVersion.setUpdate_url(response.getUpdate_url());
            //安装地址
            appVersion.setInstall_url(response.getInstall_url());
            //APK大小
            appVersion.setAppSize(String.valueOf(response.getBinary().getFsize()));

            //添加数据前先判断是否已经有数据了
            if (LitePal.find(AppVersion.class, 1) != null) {
                appVersion.update(1);
            } else {
                //保存添加数据
                appVersion.save();
            }

        }
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
            String biyingUrl = "http://cn.bing.com" + response.getImages().get(0).getUrl();
            SPUtils.putString(Constant.EVERYDAY_TIP_IMG,biyingUrl,context);

            //加载世界国家数据到本地数据库,已有则不加载
            initCountryData();
        } else {
            ToastUtils.showShortToast(context, "未获取到必应的图片");
        }
    }

    @Override
    public void getDataFailed() {
        Log.d("Network Error", "网络异常");
    }


    /**
     * 同意隐私政策
     */
    private void agreePrivacyPolicy() {
        boolean isAgree = SPUtils.getBoolean(Constant.AGREE, false,context);
        if (isAgree) {
            //权限请求判断
            permissionVersion();
        } else {
            //隐私协议
            showPrivacyPolicyDialog();
        }
    }

    /**
     * 显示隐私政策入口弹窗
     */
    public void showPrivacyPolicyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()
                .setContentView(R.layout.dialog_privacy_policy)
                .setCancelable(true)
                .setWidthAndHeight(SizeUtils.dp2px(SplashActivity.this, 280), LinearLayout.LayoutParams.WRAP_CONTENT)
                .setOnClickListener(R.id.tv_privacy_policy, v -> {
                    startActivity(new Intent(this, PrivacyPolicyActivity.class));
                }).setOnClickListener(R.id.tv_no_used, v -> {
                    ToastUtils.showShortToast(context,"不同意隐私政策，无法正常使用App，请退出App，重新进入。");
                    privacyPolicyDialog.dismiss();
                }).setOnClickListener(R.id.tv_agree, v -> {
                    //已同意隐私政策
                    SPUtils.putBoolean(Constant.AGREE, true,context);
                    //友盟SDK初始化
                    UMConfigure.init(this, Constant.U_MENG_APPKEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
                    //权限请求判断
                    permissionVersion();
                    privacyPolicyDialog.dismiss();
                });
        privacyPolicyDialog = builder.create();
        privacyPolicyDialog.show();
    }

}
