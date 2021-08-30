package com.llw.goodweather;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.llw.goodweather.utils.APKVersionInfoUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.GlideUtil;
import com.llw.goodweather.utils.SPUtils;
import com.llw.mvplibrary.BaseApplication;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.newnet.NetworkApi;
import com.llw.mvplibrary.utils.ActivityManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;
import com.uc.crashsdk.export.CrashApi;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.umcrash.UMCrash;
import com.umeng.umcrash.UMCrashCallback;

import org.litepal.LitePal;

/**
 * 项目管理
 *
 * @author llw
 */
public class WeatherApplication extends BaseApplication {

    /**
     * 应用实例
     */
    public static WeatherApplication weatherApplication;
    private static Context context;
    private static ActivityManager activityManager;

    private static Activity sActivity;

    public static Context getMyContext() {
        return weatherApplication == null ? null : weatherApplication.getApplicationContext();
    }

    private Handler myHandler;

    public Handler getMyHandler() {
        return myHandler;
    }

    public void setMyHandler(Handler handler) {
        myHandler = handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        activityManager = new ActivityManager();
        context = getApplicationContext();
        weatherApplication = this;


        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                sActivity = activity;

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        //初始化网络框架
        NetworkApi.init(new NetworkRequiredInfo(this));

        //初始化数据库
        LitePal.initialize(this);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);

        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        CrashReport.initCrashReport(getApplicationContext(), "d3637c0f25", true);
        //配置讯飞语音SDK
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=6018c2cb");

        //用户是否同意隐私政策
        boolean isAgree = SPUtils.getBoolean(Constant.AGREE, false,context);
        if (isAgree) {
            //友盟SDK初始化
            UMConfigure.init(this, Constant.U_MENG_APPKEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        } else {
            //预初始化
            UMConfigure.preInit(this, Constant.U_MENG_APPKEY,"Umeng");
        }
        //友盟配置
        umengConfig();
    }

    /**
     * 友盟配置
     */
    private void umengConfig() {
        //设置App版本
        UMCrash.setAppVersion(
                APKVersionInfoUtils.getVerName(this),
                APKVersionInfoUtils.getSubVersion(this), Build.ID);

        //针对于Native崩溃信息采集
//        final Bundle customInfo = new Bundle();
//        customInfo.putBoolean("mCallNativeDefaultHandler",true);
//        CrashApi.getInstance().updateCustomInfo(customInfo);

        //崩溃回调
        UMCrash.registerUMCrashCallback(new UMCrashCallback(){
            @Override
            public String onCallback(){
                return "App程序崩溃了";
            }
        });
    }


    public static ActivityManager getActivityManager() {
        return activityManager;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.darker_gray, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
