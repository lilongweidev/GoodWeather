package com.llw.goodweather.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.llw.goodweather.MainActivity;
import com.llw.goodweather.R;
import com.llw.goodweather.contract.SplashContract;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.bean.Country;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.litepal.LitePal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import retrofit2.Response;

/**
 * 欢迎页
 */
public class SplashActivity extends MvpActivity<SplashContract.SplashPresenter> implements SplashContract.ISplashView {

    private RxPermissions rxPermissions;//权限请求框架

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.transparencyBar(context);//透明状态栏
        permissionVersion();//权限判断
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    //权限判断
    private void permissionVersion() {
        if (Build.VERSION.SDK_INT >= 23) {//6.0或6.0以上
            //动态权限申请
            permissionsRequest();
        } else {//6.0以下
            //发现只要权限在AndroidManifest.xml中注册过，均会认为该权限granted  提示一下即可
            ToastUtils.showShortToast(this, "你的版本在Android6.0以下，不需要动态申请权限。");
        }
    }

    //动态权限申请
    private void permissionsRequest() {//使用这个框架需要制定JDK版本，建议用1.8
        rxPermissions = new RxPermissions(this);//实例化这个权限请求框架，否则会报错
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {//申请成功
                        //得到权限可以进入APP
                        //加载世界国家数据到本地数据库,已有则不加载
                        initCountryData();
                        mPresent.getAppInfo();//请求版本更新
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
        if (list.size() > 0) {//有数据了
            goToMain();
        } else {//第一次加载
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
    private void goToMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }

    @Override
    protected SplashContract.SplashPresenter createPresent() {
        return new SplashContract.SplashPresenter();
    }

    /**
     * 获取APP最新版本信息返回
     * @param response
     */
    @Override
    public void getAppInfoResult(Response<AppVersion> response) {
        if(response.body() != null){
            AppVersion appVersion = new AppVersion();
            appVersion.setName(response.body().getName());//应用名称
            appVersion.setVersion(response.body().getVersion());//应用版本 对应code
            appVersion.setVersionShort(response.body().getVersionShort());//应用版本名
            appVersion.setChangelog(response.body().getChangelog());//更新日志
            appVersion.setUpdate_url(response.body().getUpdate_url());//更新地址
            appVersion.setInstall_url(response.body().getInstall_url());//安装地址
            appVersion.setAppSize(String.valueOf(response.body().getBinary().getFsize()));//APK大小
            appVersion.save();//保存数据
        }
    }

    @Override
    public void getDataFailed() {
        Log.d("Network Error","网络异常");
    }


}
