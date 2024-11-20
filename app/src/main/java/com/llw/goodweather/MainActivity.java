package com.llw.goodweather;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.gson.Gson;
import com.llw.goodweather.adapter.AreaAdapter;
import com.llw.goodweather.adapter.CityAdapter;
import com.llw.goodweather.adapter.DailyAdapter;
import com.llw.goodweather.adapter.HourlyAdapter;
import com.llw.goodweather.adapter.MainChangeCommonlyCityAdapter;
import com.llw.goodweather.adapter.MinutePrecAdapter;
import com.llw.goodweather.adapter.ProvinceAdapter;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.CityResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.LifestyleResponse;
import com.llw.goodweather.bean.MinutePrecResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.bean.WarningResponse;
import com.llw.goodweather.contract.WeatherContract;
import com.llw.goodweather.databinding.ActivityMainBinding;
import com.llw.goodweather.eventbus.SearchCityEvent;
import com.llw.goodweather.ui.AboutUsActivity;
import com.llw.goodweather.ui.CommonlyUsedCityActivity;
import com.llw.goodweather.ui.MapWeatherActivity;
import com.llw.goodweather.ui.MoreAirActivity;
import com.llw.goodweather.ui.MoreDailyActivity;
import com.llw.goodweather.ui.MoreLifestyleActivity;
import com.llw.goodweather.ui.SearchCityActivity;
import com.llw.goodweather.ui.SettingActivity;
import com.llw.goodweather.ui.WallPaperActivity;
import com.llw.goodweather.ui.WarnActivity;
import com.llw.goodweather.ui.WorldCityActivity;
import com.llw.goodweather.utils.APKVersionInfoUtils;
import com.llw.goodweather.utils.AppStartUpUtils;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.SpeechUtil;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.goodweather.utils.WeatherUtil;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.bean.ResidentCity;
import com.llw.mvplibrary.mvp.MvpVBActivity;
import com.llw.mvplibrary.utils.AnimationUtil;
import com.llw.mvplibrary.utils.LiWindow;
import com.llw.mvplibrary.utils.SizeUtils;
import com.llw.mvplibrary.view.dialog.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.llw.mvplibrary.utils.RecyclerViewAnimation.runLayoutAnimation;
import static com.llw.mvplibrary.utils.RecyclerViewAnimation.runLayoutAnimationRight;

/**
 * 主页面
 *
 * @author llw
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends MvpVBActivity<ActivityMainBinding, WeatherContract.WeatherPresenter>
        implements WeatherContract.IWeatherView, View.OnScrollChangeListener, View.OnClickListener {

    private boolean changeCityState = false;//常用城市列表  收缩状态  false 收缩  true 展开

    private boolean isChangeCity = false;//是否可以展开，如果没有添加常用城市，自然不能展开

    private boolean flag = true;//图标显示标识,true显示，false不显示,只有定位的时候才为true,切换城市和常用城市都为false

    //定位器
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    //V7 版本
    private List<DailyResponse.DailyBean> dailyListV7 = new ArrayList<>();//天气预报数据列表
    private DailyAdapter mAdapterDailyV7;//天气预报适配器
    private List<HourlyResponse.HourlyBean> hourlyListV7 = new ArrayList<>();//逐小时天气预报数据列表
    private HourlyAdapter mAdapterHourlyV7;//逐小时预报适配器

    private List<MinutePrecResponse.MinutelyBean> minutelyList = new ArrayList<>();//分钟级降水数据列表
    private MinutePrecAdapter mAdapterMinutePrec;//分钟级降水适配器

    private boolean state = false;//分钟级降水数据 收缩状态  false 收缩  true 展开

    private List<String> list;//字符串列表
    private List<CityResponse> provinceList;//省列表数据
    private List<CityResponse.CityBean> citylist;//市列表数据
    private List<CityResponse.CityBean.AreaBean> arealist;//区/县列表数据
    private ProvinceAdapter provinceAdapter;//省数据适配器
    private CityAdapter cityAdapter;//市数据适配器
    private AreaAdapter areaAdapter;//县/区数据适配器
    private String provinceTitle;//标题
    private LiWindow liWindow;//自定义弹窗

    private String district = null;//区/县  改为全局的静态变量,方便更换城市之后也能进行下拉刷新
    private String city;//市 国控站点数据  用于请求空气质量

    private String locationId = null;//城市id，用于查询城市数据  V7版本 中 才有
    private String stationName = null;//空气质量站点 查询空气质量站点才需要

    private String lon = null;//经度
    private String lat = null;//纬度

    //右上角的弹窗
    private PopupWindow mPopupWindow;
    private AnimationUtil animUtil;
    private float bgAlpha = 1f;
    private boolean bright = false;
    private static final long DURATION = 500;//0.5s
    private static final float START_ALPHA = 0.7f;//开始透明度
    private static final float END_ALPHA = 1f;//结束透明度

    public boolean flagOther = false;//跳转其他页面时才为true
    public boolean searchCityData = false;//搜索城市是否传递数据回来
    private String warnBodyString = null;//灾害预警数据字符串

    private AlertDialog updateAppDialog = null;//应用更新提示弹窗

    private int OPEN_LOCATION = 9527;//进入手机定位设置页面标识

    private AlertDialog everyDayTipDialog = null;//每日提醒弹窗

    private String dialogTemp = null;//弹窗中的今日温度
    private String dialogWeatherState = null;//弹窗中的天气状态
    private int dialogWeatherStateCode = 0;//弹窗中的天气状态码
    private String dialogPrecipitation = null;//弹窗中的降水预告
    private int dialogTempHeight = 0;//弹窗中今日最高温
    private int dialogTempLow = 0;//弹窗中今日最低温

    //主页面切换城市列表适配器
    private MainChangeCommonlyCityAdapter changeCityAdapter;
    //常用城市切换列表
    private List<ResidentCity> residentCityList = new ArrayList<>();

    private String dateDetailStr, tempStr, tempMaxMin, precStr, airStr;
    //灾害预警内容
    private String warnStr = "";
    //播放的内容
    private String voiceStr = "";


    //数据初始化
    @Override
    public void initData() {
        StatusBarUtil.transparencyBar(context);//透明状态栏
        initList();//天气预报列表初始化

        if (isOpenLocationServiceEnable()) {
            //tvCity.setEnabled(false);//不可点击
            startLocation();//开始定位
        } else {
            //tvCity.setEnabled(true);//可以点击
            ToastUtils.showShortToast(context, "(((φ(◎ロ◎;)φ)))，你好像忘记打开定位功能了");
            binding.tvCity.setText("打开定位");
        }
        //由于这个刷新框架默认是有下拉和上拉，但是上拉没有用到，为了不造成误会，我这里禁用上拉
        binding.refresh.setEnableLoadMore(false);
        //初始化弹窗
        mPopupWindow = new PopupWindow(this);
        animUtil = new AnimationUtil();

        EventBus.getDefault().register(this);//注册

        binding.scrollView.setOnScrollChangeListener(this);//指定当前页面，不写则滑动监听无效
        //加载常用城市
        loadingCommonlyUsedCity();
        //初始化语音播报
        SpeechUtil.init(context);

        //添加监听事件
        binding.ivMap.setOnClickListener(this);
        binding.ivAdd.setOnClickListener(this);
        binding.tvWarn.setOnClickListener(this);
        binding.ivVoiceBroadcast.setOnClickListener(this);
        binding.tvCity.setOnClickListener(this);
        binding.tvMoreDaily.setOnClickListener(this);
        binding.tvMoreAir.setOnClickListener(this);
        binding.tvMoreLifestyle.setOnClickListener(this);
        binding.tvPrecMore.setOnClickListener(this);
        binding.fabVoiceSearch.setOnClickListener(this);
    }

    /**
     * 检查APP版本
     */
    private void checkAppVersion() {
        AppVersion appVersion = LitePal.find(AppVersion.class, 1);//读取第一条数据
        Log.d("appVersion", new Gson().toJson(appVersion.getVersionShort()));

        if (AppStartUpUtils.isTodayFirstStartApp(context)) {//今天第一次打开APP
            if (!appVersion.getVersionShort().equals(APKVersionInfoUtils.getVerName(context))) {//提示更新
                //更新提示弹窗
                showUpdateAppDialog(appVersion.getInstall_url(), appVersion.getChangelog());
            }
            //设置每日提示弹窗
            setTipDialog();
        }
    }

    /**
     * 手机是否开启位置服务，如果没有开启那么App将不能使用定位功能
     */
    private boolean isOpenLocationServiceEnable() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    /**
     * 应用更新提示弹窗
     *
     * @param downloadUrl 下载地址
     * @param updateLog   更新日志
     */
    private void showUpdateAppDialog(String downloadUrl, String updateLog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setText(R.id.tv_update_info, updateLog)
                .setContentView(R.layout.dialog_update_app_tip)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 270), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tv_cancel, v -> {//取消
                    updateAppDialog.dismiss();
                }).setOnClickListener(R.id.tv_fast_update, v -> {//立即更新
                    //下载Apk
                    ToastUtils.showShortToast(context, "正在后台下载，下载后会自动安装");
                    downloadApk(downloadUrl);
                    updateAppDialog.dismiss();
                });
        updateAppDialog = builder.create();
        updateAppDialog.show();
    }

    /**
     * 设置每日提示弹窗
     */
    private void setEveryDayTipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(false)
                .setText(R.id.tv_week, DateUtils.getWeekOfDate(new Date()))//星期
                .setText(R.id.tv_weather_state, dialogWeatherState)//天气状态
                .setText(R.id.tv_precipitation, dialogPrecipitation)//降水预告
                .setText(R.id.tv_temp_difference,
                        WeatherUtil.differenceTempTip(dialogTempHeight, dialogTempLow))//温差提示信息
                .setContentView(R.layout.dialog_everyday_tip)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 270), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.iv_close, v -> {//关闭
                    everyDayTipDialog.dismiss();
                });
        if (everyDayTipDialog == null) {
            everyDayTipDialog = builder.create();
        }
        //温度
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        TextView temp = everyDayTipDialog.getView(R.id.tv_temperature);
        temp.setTypeface(typeface);
        temp.setText(dialogTemp + "℃");
        //设置天气状态图标
        ImageView weatherStateIcon = everyDayTipDialog.getView(R.id.iv_weather_state);
        WeatherUtil.changeIcon(weatherStateIcon, dialogWeatherStateCode);

        //不再弹出
        MaterialCheckBox cb = everyDayTipDialog.getView(R.id.cb_no_pop_up);
        cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                SPUtils.putBoolean(Constant.EVERYDAY_POP_BOOLEAN, false, context);
            } else {
                SPUtils.putBoolean(Constant.EVERYDAY_POP_BOOLEAN, true, context);
            }
        });
        everyDayTipDialog.show();
    }

    /**
     * 清除APK
     *
     * @param apkName
     * @return
     */
    public static File clearApk(String apkName) {
        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), apkName);
        if (apkFile.exists()) {
            apkFile.delete();
        }
        return apkFile;
    }

    /**
     * 下载APK
     *
     * @param downloadUrl 下载地址
     */
    private void downloadApk(String downloadUrl) {
        clearApk("GoodWeather.apk");
        //下载管理器 获取系统下载服务
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        //设置运行使用的网络类型，移动网络或者Wifi都可以
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        //设置是否允许漫游
        request.setAllowedOverRoaming(true);
        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downloadUrl));
        request.setMimeType(mimeString);
        //设置下载时或者下载完成时，通知栏是否显示
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载新版本");
        request.setVisibleInDownloadsUi(true);//下载UI
        //sdcard目录下的download文件夹
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "GoodWeather.apk");
        //将下载请求放入队列
        downloadManager.enqueue(request);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SearchCityEvent event) {//接收
        //重新加载数据
        loadingCommonlyUsedCity();

        flag = false;//切换城市得到的城市不属于定位，因此这里隐藏定位图标
        //V7版本中需要先获取到城市ID ,在结果返回值中再进行下一步的数据查询
        mPresent.newSearchCity(event.mLocation);//相应事件时
    }

    /**
     * 更换壁纸
     */
    private void updateWallpaper() {
        String imgUrl = SPUtils.getString(Constant.WALLPAPER_URL, null, context);
        Glide.with(context).load(imgUrl != null ? imgUrl : R.drawable.img_5).into(binding.bg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoadingDialog();//在数据请求之前放在加载等待弹窗，返回结果后关闭弹窗
        flagOther = SPUtils.getBoolean(Constant.FLAG_OTHER_RETURN, false, context);
        if (flagOther) {
            //取出缓存
            district = SPUtils.getString(Constant.DISTRICT, "", context);
            city = SPUtils.getString(Constant.CITY, "", context);
            //V7版本中需要先获取到城市ID ,在结果返回值中再进行下一步的数据查询
            //其他页面返回时
            mPresent.newSearchCity(district);
        } else {
            dismissLoadingDialog();
        }

        updateWallpaper();

        //是否显示语音搜索按钮
        if (SPUtils.getBoolean(Constant.VOICE_SEARCH_BOOLEAN, true, context)) {
            binding.fabVoiceSearch.show();
        } else {
            binding.fabVoiceSearch.hide();
        }
    }

    //绑定Presenter ，这里不绑定会报错
    @Override
    protected WeatherContract.WeatherPresenter createPresent() {
        return new WeatherContract.WeatherPresenter();
    }


    /**
     * 初始化 天气预报 和 逐小时 数据列表
     */
    private void initList() {
        /**   V7 版本   **/
        //天气预报  7天
        mAdapterDailyV7 = new DailyAdapter(R.layout.item_weather_forecast_list, dailyListV7);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(mAdapterDailyV7);
        //天气预报列表item点击事件
        mAdapterDailyV7.addChildClickViewIds(R.id.item_forecast);
        mAdapterDailyV7.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                DailyResponse.DailyBean data = dailyListV7.get(position);
                showForecastWindow(data);
            }
        });

        //逐小时天气预报  24小时
        mAdapterHourlyV7 = new HourlyAdapter(R.layout.item_weather_hourly_list, hourlyListV7);
        LinearLayoutManager managerHourly = new LinearLayoutManager(context);
        managerHourly.setOrientation(RecyclerView.HORIZONTAL);//设置列表为横向
        binding.rvHourly.setLayoutManager(managerHourly);
        binding.rvHourly.setAdapter(mAdapterHourlyV7);
        //逐小时天气预报列表item点击事件
        mAdapterHourlyV7.addChildClickViewIds(R.id.item_hourly);//添加点击事件
        mAdapterHourlyV7.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                //赋值
                HourlyResponse.HourlyBean data = hourlyListV7.get(position);
                //小时天气详情弹窗
                showHourlyWindow(data);
            }
        });
        //分钟级降水
        mAdapterMinutePrec = new MinutePrecAdapter(R.layout.item_prec_detail_list, minutelyList);
        GridLayoutManager managerMinutePrec = new GridLayoutManager(context, 2);
        managerMinutePrec.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvPrecDetail.setLayoutManager(managerMinutePrec);
        binding.rvPrecDetail.setAdapter(mAdapterMinutePrec);
    }

    /**
     * 显示天气预报详情弹窗
     *
     * @param data
     */
    private void showForecastWindow(DailyResponse.DailyBean data) {
        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_forecast_detail, null);
        TextView tv_datetime = view.findViewById(R.id.tv_datetime);
        TextView tv_tmp_max = view.findViewById(R.id.tv_tmp_max);//最高温
        TextView tv_tmp_min = view.findViewById(R.id.tv_tmp_min);//最低温
        TextView tv_uv_index = view.findViewById(R.id.tv_uv_index);//紫外线强度
        TextView tv_cond_txt_d = view.findViewById(R.id.tv_cond_txt_d);//白天天气状态
        TextView tv_cond_txt_n = view.findViewById(R.id.tv_cond_txt_n);//晚上天气状态
        TextView tv_wind_deg = view.findViewById(R.id.tv_wind_deg);//风向360角度
        TextView tv_wind_dir = view.findViewById(R.id.tv_wind_dir);//风向
        TextView tv_wind_sc = view.findViewById(R.id.tv_wind_sc);//风力
        TextView tv_wind_spd = view.findViewById(R.id.tv_wind_spd);//风速
        TextView tv_cloud = view.findViewById(R.id.tv_cloud);//云量  V7 新增
        TextView tv_hum = view.findViewById(R.id.tv_hum);//相对湿度
        TextView tv_pres = view.findViewById(R.id.tv_pres);//大气压强
        TextView tv_pcpn = view.findViewById(R.id.tv_pcpn);//降水量
        TextView tv_vis = view.findViewById(R.id.tv_vis);//能见度

        tv_datetime.setText(data.getFxDate() + "   " + DateUtils.Week(data.getFxDate()));//时间日期
        tv_tmp_max.setText(data.getTempMax() + "℃");
        tv_tmp_min.setText(data.getTempMin() + "℃");
        tv_uv_index.setText(data.getUvIndex());
        tv_cond_txt_d.setText(data.getTextDay());
        tv_cond_txt_n.setText(data.getTextNight());
        tv_wind_deg.setText(data.getWind360Day() + "°");
        tv_wind_dir.setText(data.getWindDirDay());
        tv_wind_sc.setText(data.getWindScaleDay() + "级");
        tv_wind_spd.setText(data.getWindSpeedDay() + "公里/小时");
        tv_cloud.setText(data.getCloud() + "%");//V7 版本中，新增 云量
        tv_hum.setText(data.getHumidity() + "%");
        tv_pres.setText(data.getPressure() + "hPa");
        tv_pcpn.setText(data.getPrecip() + "mm");
        tv_vis.setText(data.getVis() + "km");
        liWindow.showCenterPopupWindow(view, SizeUtils.dp2px(context, 300), SizeUtils.dp2px(context, 500), true);

    }

    /**
     * 显示小时详情天气信息弹窗
     *
     * @param data
     */
    private void showHourlyWindow(HourlyResponse.HourlyBean data) {
        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_hourly_detail, null);
        TextView tv_time = view.findViewById(R.id.tv_time);//时间
        TextView tv_tem = view.findViewById(R.id.tv_tem);//温度
        TextView tv_cond_txt = view.findViewById(R.id.tv_cond_txt);//天气状态描述
        TextView tv_wind_deg = view.findViewById(R.id.tv_wind_deg);//风向360角度
        TextView tv_wind_dir = view.findViewById(R.id.tv_wind_dir);//风向
        TextView tv_wind_sc = view.findViewById(R.id.tv_wind_sc);//风力
        TextView tv_wind_spd = view.findViewById(R.id.tv_wind_spd);//风速
        TextView tv_hum = view.findViewById(R.id.tv_hum);//相对湿度
        TextView tv_pres = view.findViewById(R.id.tv_pres);//大气压强
        TextView tv_pop = view.findViewById(R.id.tv_pop);//降水概率
        TextView tv_dew = view.findViewById(R.id.tv_dew);//露点温度
        TextView tv_cloud = view.findViewById(R.id.tv_cloud);//云量

        String time = DateUtils.updateTime(data.getFxTime());

        tv_time.setText(WeatherUtil.showTimeInfo(time) + time);
        tv_tem.setText(data.getTemp() + "℃");
        tv_cond_txt.setText(data.getText());
        tv_wind_deg.setText(data.getWind360() + "°");
        tv_wind_dir.setText(data.getWindDir());
        tv_wind_sc.setText(data.getWindScale() + "级");
        tv_wind_spd.setText(data.getWindSpeed() + "公里/小时");
        tv_hum.setText(data.getHumidity() + "%");
        tv_pres.setText(data.getPressure() + "hPa");
        tv_pop.setText(data.getPop() + "%");
        tv_dew.setText(data.getDew() + "℃");
        tv_cloud.setText(data.getCloud() + "%");
        liWindow.showCenterPopupWindow(view, SizeUtils.dp2px(context, 300), SizeUtils.dp2px(context, 400), true);
    }

    //定位
    private void startLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setIsNeedAddress(true);
        //可选，设置是否需要最新版本的地址信息。默认不需要，即参数为false
        option.setNeedNewVersionRgc(true);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mLocationClient.setLocOption(option);
        //启动定位
        mLocationClient.start();

    }


    /**
     * 城市弹窗
     */
    private void showCityWindow() {
        provinceList = new ArrayList<>();
        citylist = new ArrayList<>();
        arealist = new ArrayList<>();
        list = new ArrayList<>();
        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_city_list, null);
        ImageView areaBack = (ImageView) view.findViewById(R.id.iv_back_area);
        ImageView cityBack = (ImageView) view.findViewById(R.id.iv_back_city);
        TextView windowTitle = (TextView) view.findViewById(R.id.tv_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        liWindow.showRightPopupWindow(view);//显示弹窗
        initCityData(recyclerView, areaBack, cityBack, windowTitle);//加载城市列表数据
    }

    /**
     * 省市县数据渲染
     *
     * @param recyclerView 列表
     * @param areaBack     区县返回
     * @param cityBack     市返回
     * @param windowTitle  窗口标题
     */
    private void initCityData(RecyclerView recyclerView, ImageView areaBack, ImageView cityBack, TextView windowTitle) {
        //初始化省数据 读取省数据并显示到列表中
        try {
            //读取城市数据
            InputStream inputStream = getResources().getAssets().open("City.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuffer.append(lines);
                lines = bufferedReader.readLine();
            }

            final JSONArray Data = new JSONArray(stringBuffer.toString());
            //循环这个文件数组、获取数组中每个省对象的名字
            for (int i = 0; i < Data.length(); i++) {
                JSONObject provinceJsonObject = Data.getJSONObject(i);
                String provinceName = provinceJsonObject.getString("name");
                CityResponse response = new CityResponse();
                response.setName(provinceName);
                provinceList.add(response);
            }

            //定义省份显示适配器
            provinceAdapter = new ProvinceAdapter(R.layout.item_city_list, provinceList);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(provinceAdapter);
            provinceAdapter.notifyDataSetChanged();
            //runLayoutAnimationRight(recyclerView);//动画展示
            provinceAdapter.addChildClickViewIds(R.id.item_city);//添加点击事件
            provinceAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                try {
                    //返回上一级数据
                    cityBack.setVisibility(View.VISIBLE);
                    cityBack.setOnClickListener(v -> {
                        recyclerView.setAdapter(provinceAdapter);
                        provinceAdapter.notifyDataSetChanged();
                        cityBack.setVisibility(View.GONE);
                        windowTitle.setText("中国");
                    });

                    //根据当前位置的省份所在的数组位置、获取城市的数组
                    JSONObject provinceObject = Data.getJSONObject(position);
                    windowTitle.setText(provinceList.get(position).getName());
                    provinceTitle = provinceList.get(position).getName();
                    final JSONArray cityArray = provinceObject.getJSONArray("city");

                    //更新列表数据
                    if (citylist != null) {
                        citylist.clear();
                    }

                    for (int i = 0; i < cityArray.length(); i++) {
                        JSONObject cityObj = cityArray.getJSONObject(i);
                        String cityName = cityObj.getString("name");
                        CityResponse.CityBean response = new CityResponse.CityBean();
                        response.setName(cityName);
                        citylist.add(response);
                    }

                    cityAdapter = new CityAdapter(R.layout.item_city_list, citylist);
                    LinearLayoutManager manager1 = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(manager1);
                    recyclerView.setAdapter(cityAdapter);
                    cityAdapter.notifyDataSetChanged();
                    //runLayoutAnimationRight(recyclerView);
                    cityAdapter.addChildClickViewIds(R.id.item_city);//添加点击事件
                    cityAdapter.setOnItemChildClickListener((adapter12, view12, position12) -> {
                        try {
                            //返回上一级数据
                            areaBack.setVisibility(View.VISIBLE);
                            areaBack.setOnClickListener(v -> {
                                recyclerView.setAdapter(cityAdapter);
                                cityAdapter.notifyDataSetChanged();
                                areaBack.setVisibility(View.GONE);
                                windowTitle.setText(provinceTitle);
                                arealist.clear();
                            });
                            //获取区/县的上级 市，用于请求空气质量数据API接口
                            city = citylist.get(position12).getName();
                            //根据当前城市数组位置 获取地区数据
                            windowTitle.setText(citylist.get(position12).getName());
                            JSONObject cityJsonObj = cityArray.getJSONObject(position12);
                            JSONArray areaJsonArray = cityJsonObj.getJSONArray("area");
                            if (arealist != null) {
                                arealist.clear();
                            }
                            if (list != null) {
                                list.clear();
                            }
                            for (int i = 0; i < areaJsonArray.length(); i++) {
                                list.add(areaJsonArray.getString(i));
                            }
                            Log.i("list", list.toString());
                            for (int j = 0; j < list.size(); j++) {
                                CityResponse.CityBean.AreaBean response = new CityResponse.CityBean.AreaBean();
                                response.setName(list.get(j).toString());
                                arealist.add(response);
                            }
                            areaAdapter = new AreaAdapter(R.layout.item_city_list, arealist);
                            LinearLayoutManager manager2 = new LinearLayoutManager(context);

                            recyclerView.setLayoutManager(manager2);
                            recyclerView.setAdapter(areaAdapter);
                            areaAdapter.notifyDataSetChanged();
                            runLayoutAnimationRight(recyclerView);

                            areaAdapter.addChildClickViewIds(R.id.item_city);//添加点击事件
                            areaAdapter.setOnItemChildClickListener((adapter1, view1, position1) -> {
                                showLoadingDialog();
                                district = arealist.get(position1).getName();//选中的区/县赋值给这个全局变量

                                //V7版本中需要先获取到城市ID ,在结果返回值中再进行下一步的数据查询
                                mPresent.newSearchCity(district);//切换城市时

                                flag = false;//切换城市得到的城市不属于定位，因此这里隐藏定位图标
                                liWindow.closePopupWindow();//关闭弹窗
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * 滑动监听
     *
     * @param v          滑动视图本身
     * @param scrollX    滑动后的X轴位置
     * @param scrollY    滑动后的Y轴位置
     * @param oldScrollX 之前的X轴位置
     * @param oldScrollY 之前的Y轴位置
     */
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY > oldScrollY) {
            //laySlideArea.getMeasuredHeight() 表示控件的绘制高度
            if (scrollY > binding.laySlideArea.getMeasuredHeight()) {
                String tx = binding.tvCity.getText().toString();
                if (tx.contains("定位中")) {
                    //因为存在网络异常问题，总不能你没有城市，还给你改变UI吧
                    binding.tvTitle.setText("城市天气");
                } else {
                    //改变TextView的显示文本
                    binding.tvTitle.setText(tx);
                }
            }
        }

        if (scrollY < oldScrollY) {
            if (scrollY < binding.laySlideArea.getMeasuredHeight()) {
                //改回原来的
                binding.tvTitle.setText("城市天气");
            }
        }
    }

    /**
     * 进入更多数据页面
     *
     * @param clazz 要进入的页面
     */
    private void goToMore(Class<?> clazz) {
        if (locationId == null) {
            ToastUtils.showShortToast(context, "很抱歉，未获取到相关更多信息");
        } else {
            Intent intent = new Intent(context, clazz);
            intent.putExtra("locationId", locationId);
            intent.putExtra("stationName", stationName);//只要locationId不为空，则cityName不会为空,只判断一次即可
            intent.putExtra("cityName", binding.tvCity.getText().toString());
            startActivity(intent);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_map://地图天气
                if (!isOpenLocationServiceEnable()) {
                    ToastUtils.showShortToast(context, "打开位置信息才能进入地图天气");
                    return;
                }
                startActivity(new Intent(context, MapWeatherActivity.class));
                break;
            case R.id.iv_add://更多功能弹窗
                showAddWindow();//更多功能弹窗
                toggleBright();//计算动画时间
                break;
            case R.id.tv_warn://灾害预警，不一定当前城市就有这个预警，如果有预警的话就可以进入查看详情
                SPUtils.putBoolean(Constant.FLAG_OTHER_RETURN, false, context);//onViewCli缓存标识
                Intent intent = new Intent(context, WarnActivity.class);
                intent.putExtra("warnBodyString", warnBodyString);
                startActivity(intent);
                break;
            case R.id.iv_voice_broadcast://语音播报
                SpeechUtil.startVoiceBroadcast(voiceStr, binding.tvBroadcastState);
                break;
            case R.id.tv_city://当用户没有打开GPS定位时，则可以点击这个TextView进行重新定位
                if (isOpenLocationServiceEnable()) {//已开启定位
                    if (binding.tvCity.getText().toString().contains("定位")) {
                        binding.tvCity.setText("定位中");
                        startLocation();//开始定位
                    } else {
                        if (isChangeCity) {
                            //有数据
                            if (changeCityState) {//收缩
                                binding.rvChangeCity.setVisibility(View.GONE);
                                changeCityState = false;
                            } else {//展开
                                binding.rvChangeCity.setVisibility(View.VISIBLE);
                                changeCityState = true;
                            }
                        }

                    }
                } else {//未开启
                    //跳转到系统定位设置
                    startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), OPEN_LOCATION);
                }
                break;
            case R.id.tv_more_daily://更多天气预报
                goToMore(MoreDailyActivity.class);
                break;
            case R.id.tv_more_air://更多空气质量信息
                goToMore(MoreAirActivity.class);
                break;
            case R.id.tv_more_lifestyle://更多生活建议
                goToMore(MoreLifestyleActivity.class);
                break;
            case R.id.tv_prec_more://分钟级降水详情
                if (state) {//收缩
                    AnimationUtil.collapse(binding.rvPrecDetail, binding.tvPrecMore);
                    state = false;
                } else {//展开
                    AnimationUtil.expand(binding.rvPrecDetail, binding.tvPrecMore);
                    state = true;
                }
                break;
            case R.id.fab_voice_search://语音搜索
                SpeechUtil.startDictation(this, cityName -> {
                    if (cityName.isEmpty()) {
                        return;
                    }
                    //判断字符串是否包含句号
                    if (!cityName.contains("。")) {
                        //然后判断成员变量和临时变量是否一样，不一样则赋值。
                        if (!district.equals(cityName)) {
                            district = cityName;
                            Log.d("city", district);
                            //加载弹窗
                            showLoadingDialog();
                            ToastUtils.showShortToast(context, "正在搜索城市：" + district + "，请稍后...");
                            flag = false;//不属于定位，则不需要显示定位图标
                            //搜索城市
                            mPresent.newSearchCity(district);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 定位结果返回
     */
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            district = location.getDistrict();//获取区/县
            city = location.getCity();//获取市
            if (district == null) {//未获取到定位信息，请重新定位
                ToastUtils.showShortToast(context, "未获取到定位信息，请重新定位");
                binding.tvCity.setText("重新定位");
                //页面处理
            } else {
                //在数据请求之前放在加载等待弹窗，返回结果后关闭弹窗
                showLoadingDialog();
                //V7版本中需要先获取到城市ID ,在结果返回值中再进行下一步的数据查询
                mPresent.newSearchCity(city+district);//定位返回时
                //下拉刷新
                binding.refresh.setOnRefreshListener(refreshLayout -> {
                    //V7版本中需要先获取到城市ID ,在结果返回值中再进行下一步的数据查询
                    mPresent.newSearchCity(city+district);
                });
            }
        }
    }

    /**
     * 天气预报数据访问异常返回
     */
    @Override
    public void getWeatherDataFailed() {
        binding.refresh.finishRefresh();//关闭刷新
        dismissLoadingDialog();//关闭弹窗
        ToastUtils.showShortToast(context, "天气数据获取异常");
    }

    /**
     * 和风天气  V7  API
     * 通过定位到的地址 /  城市切换得到的地址  都需要查询出对应的城市id才行，所以在V7版本中，这是第一步接口
     *
     * @param response
     */
    @Override
    public void getNewSearchCityResult(NewSearchCityResponse response) {
        binding.refresh.finishRefresh();//关闭刷新
        if (mLocationClient != null) {
            mLocationClient.stop();//数据返回后关闭定位
        }
        if (response != null) {
            if (response.getCode().equals(Constant.SUCCESS_CODE)) {
                if (response.getLocation() != null && response.getLocation().size() > 0) {
                    NewSearchCityResponse.LocationBean locationBean = response.getLocation().get(0);
                    //如果是定位到当前地方则添加到常用城市
                    addCommonlyUsedCity(locationBean);
                    //日期所在地
                    dateDetailStr = "今天是" + DateUtils.getNowDateStr() + "，"
                            + DateUtils.getWeekOfDate(new Date()) + "，当前所在地：" + locationBean.getName() + "。";

                    binding.tvCity.setText(locationBean.getName());//城市
                    locationId = locationBean.getId();//城市Id
                    stationName = locationBean.getAdm2();//上级城市 也是空气质量站点
                    lon = locationBean.getLon();//经度
                    lat = locationBean.getLat();//纬度
                    mPresent.nowWarn(locationId);//灾害预警
                    mPresent.nowWeather(locationId);//查询实况天气
                    mPresent.getMinutePrec(lon + "," + lat);//分钟级降水
                    mPresent.hourlyWeather(locationId);//查询逐小时天气预报
                    mPresent.dailyWeather(locationId);//查询天气预报
                    mPresent.airNowWeather(locationId);//空气质量
                    mPresent.lifestyle(locationId);//生活指数

                } else {
                    ToastUtils.showShortToast(context, "数据为空");
                }
            } else {
                dismissLoadingDialog();
                binding.tvCity.setText("查询城市失败");
                ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
            }
        } else {
            ToastUtils.showShortToast(context, "搜索城市数据为空");
        }
    }

    /**
     * 实况天气数据返回  V7
     *
     * @param response
     */
    @Override
    public void getNowResult(NowResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {//200则成功返回数据
            //根据V7版本的原则，只要是200就一定有数据，我们可以不用做判空处理，但是，为了使程序不ANR，还是要做的，信自己得永生
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
            binding.tvTemperature.setText(response.getNow().getTemp());//温度

            tempStr = "当前温度：" + response.getNow().getTemp() + "度，天气" + response.getNow().getText() + "。";
            dialogTemp = response.getNow().getTemp();
            dialogWeatherState = response.getNow().getText();
            dialogWeatherStateCode = Integer.parseInt(response.getNow().getIcon());

            binding.tvTemperature.setTypeface(typeface);//使用字体
            if (flag) {
                binding.ivLocation.setVisibility(View.VISIBLE);//显示定位图标
            } else {
                binding.ivLocation.setVisibility(View.GONE);//显示定位图标
            }
            binding.tvWeek.setText(DateUtils.getWeekOfDate(new Date()));//星期
            binding.tvInfo.setText(response.getNow().getText());//天气状况

            String time = DateUtils.updateTime(response.getUpdateTime());//截去前面的字符，保留后面所有的字符，就剩下 22:00

            binding.tvOldTime.setText("最近更新时间：" + WeatherUtil.showTimeInfo(time) + time);
            binding.tvWindDirection.setText("风向     " + response.getNow().getWindDir());//风向
            binding.tvWindPower.setText("风力     " + response.getNow().getWindScale() + "级");//风力
            binding.wwBig.startRotate();//大风车开始转动
            binding.wwSmall.startRotate();//小风车开始转动
        } else {//其他状态返回提示文字
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 分钟级降水返回
     *
     * @param response
     */
    @Override
    public void getMinutePrecResult(MinutePrecResponse response) {
        dismissLoadingDialog();//关闭加载弹窗
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            precStr = response.getSummary() + "。";

            dialogPrecipitation = response.getSummary();//弹窗降水预告
            binding.tvPrecipitation.setText(dialogPrecipitation);
            if (response.getMinutely() != null && response.getMinutely().size() > 0) {
                minutelyList.clear();
                minutelyList.addAll(response.getMinutely());
                mAdapterMinutePrec.notifyDataSetChanged();
                checkAppVersion();//检查版本信息
            } else {
                ToastUtils.showShortToast(context, "分钟级降水数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 逐小时天气数据返回  V7
     *
     * @param response
     */
    @Override
    public void getHourlyResult(HourlyResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<HourlyResponse.HourlyBean> data = response.getHourly();
            if (data != null && data.size() > 0) {
                hourlyListV7.clear();
                hourlyListV7.addAll(data);
                mAdapterHourlyV7.notifyDataSetChanged();
                runLayoutAnimationRight(binding.rvHourly);
            } else {
                ToastUtils.showShortToast(context, "逐小时预报数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 天气预报数据返回  V7
     *
     * @param response
     */
    @Override
    public void getDailyResult(DailyResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<DailyResponse.DailyBean> data = response.getDaily();
            //判空处理
            if (data != null && data.size() > 0) {
                binding.tvTempHeight.setText(data.get(0).getTempMax() + "℃");
                binding.tvTempLow.setText(" / " + data.get(0).getTempMin() + "℃");

                tempMaxMin = "今日最高温：" + data.get(0).getTempMax() + "度，" +
                        "最低温：" + data.get(0).getTempMin() + "度。";

                dialogTempHeight = Integer.parseInt(data.get(0).getTempMax());//弹窗今日最高温
                dialogTempLow = Integer.parseInt(data.get(0).getTempMin());//弹窗今日最低温

                //添加数据之前先清除
                dailyListV7.clear();
                //添加数据
                dailyListV7.addAll(data);
                //刷新列表
                mAdapterDailyV7.notifyDataSetChanged();
                //底部动画展示
                runLayoutAnimation(binding.rv);
            } else {
                ToastUtils.showShortToast(context, "天气预报数据为空");
            }
        } else {//异常状态码返回
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 空气质量返回  V7
     *
     * @param response
     */
    @Override
    public void getAirNowResult(AirNowResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            AirNowResponse.NowBean data = response.getNow();
            if (response.getNow() != null) {

                airStr = "空气质量：" + data.getAqi() + "，空气" + data.getCategory() + "。";

                binding.rpbAqi.setMaxProgress(300);//最大进度，用于计算
                binding.rpbAqi.setMinText("0");//设置显示最小值
                binding.rpbAqi.setMinTextSize(32f);
                binding.rpbAqi.setMaxText("300");//设置显示最大值
                binding.rpbAqi.setMaxTextSize(32f);
                binding.rpbAqi.setProgress(Float.valueOf(data.getAqi()));//当前进度
                binding.rpbAqi.setArcBgColor(getColor(R.color.arc_bg_color));//圆弧的颜色
                binding.rpbAqi.setProgressColor(getColor(R.color.arc_progress_color));//进度圆弧的颜色
                binding.rpbAqi.setFirstText(data.getCategory());//空气质量描述 取值范围：优，良，轻度污染，中度污染，重度污染，严重污染
                binding.rpbAqi.setFirstTextSize(44f);//第一行文本的字体大小
                binding.rpbAqi.setSecondText(data.getAqi());//空气质量值
                binding.rpbAqi.setSecondTextSize(64f);//第二行文本的字体大小
                binding.rpbAqi.setMinText("0");
                binding.rpbAqi.setMinTextColor(getColor(R.color.arc_progress_color));

                binding.tvAirInfo.setText("空气" + data.getCategory());

                binding.tvPm10.setText(data.getPm10());//PM10
                binding.tvPm25.setText(data.getPm2p5());//PM2.5
                binding.tvNo2.setText(data.getNo2());//二氧化氮
                binding.tvSo2.setText(data.getSo2());//二氧化硫
                binding.tvO3.setText(data.getO3());//臭氧
                binding.tvCo.setText(data.getCo());//一氧化碳
            } else {
                ToastUtils.showShortToast(context, "空气质量数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 生活数据返回 V7
     *
     * @param response
     */
    @Override
    public void getLifestyleResult(LifestyleResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<LifestyleResponse.DailyBean> data = response.getDaily();
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    switch (data.get(i).getType()) {
                        case "5":
                            binding.tvUv.setText("紫外线：" + data.get(i).getText());
                            break;
                        case "8":
                            binding.tvComf.setText("舒适度：" + data.get(i).getText());
                            break;
                        case "3":
                            binding.tvDrsg.setText("穿衣指数：" + data.get(i).getText());
                            break;
                        case "9":
                            binding.tvFlu.setText("感冒指数：" + data.get(i).getText());
                            break;
                        case "1":
                            binding.tvSport.setText("运动指数：" + data.get(i).getText());
                            break;
                        case "6":
                            binding.tvTrav.setText("旅游指数：" + data.get(i).getText());
                            break;
                        case "2":
                            binding.tvCw.setText("洗车指数：" + data.get(i).getText());
                            break;
                        case "10":
                            binding.tvAir.setText("空气指数：" + data.get(i).getText());
                            break;
                        default:
                            break;
                    }
                }
                //字符串拼接
                voiceStr = dateDetailStr + tempStr + tempMaxMin + precStr +
                        airStr + binding.tvComf.getText().toString() +
                        binding.tvAir.getText().toString() + warnStr;
                //图标显示
                binding.ivVoiceBroadcast.setVisibility(View.VISIBLE);
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 灾害预警返回
     *
     * @param response
     */
    @Override
    public void getNowWarnResult(WarningResponse response) {
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<WarningResponse.WarningBean> data = response.getWarning();
            if (data != null && data.size() > 0) {
                warnBodyString = new Gson().toJson(response);
                //设置滚动标题和内容
                binding.tvWarn.setText(data.get(0).getTitle() + "   " + data.get(0).getText());
                binding.tvWarn.setVisibility(View.VISIBLE);//当灾害预警有值时，显示这个TextView
                warnStr = "灾害预警：" + data.get(0).getText();
            } else {
                //没有该城市预警有隐藏掉这个TextView
                binding.tvWarn.setVisibility(View.GONE);
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 添加到常用城市列表
     *
     * @param locationBean
     */
    private void addCommonlyUsedCity(NewSearchCityResponse.LocationBean locationBean) {
        if (flag) {//定位到的城市
            List<ResidentCity> residentCityList = LitePal.findAll(ResidentCity.class);
            if (residentCityList != null && residentCityList.size() > 0) {
                //查询要添加的城市是否已经存在
                List<ResidentCity> residentCities = LitePal
                        .where("location = ? and parent_city = ?", locationBean.getName(), locationBean.getAdm2())
                        .find(ResidentCity.class);
                if (residentCities.size() == 0) {
                    ResidentCity residentCity = new ResidentCity();
                    residentCity.setLocation(locationBean.getName());//地区／城市名称
                    residentCity.setParent_city(locationBean.getAdm2());//该地区／城市的上级城市
                    residentCity.setAdmin_area(locationBean.getAdm1());//该地区／城市所属行政区域
                    residentCity.setCnty(locationBean.getCountry());//该地区／城市所属国家名称
                    residentCity.save();//保存数据到数据库中
                }
            } else {
                ResidentCity residentCity = new ResidentCity();
                residentCity.setLocation(locationBean.getName());//地区／城市名称
                residentCity.setParent_city(locationBean.getAdm2());//该地区／城市的上级城市
                residentCity.setAdmin_area(locationBean.getAdm1());//该地区／城市所属行政区域
                residentCity.setCnty(locationBean.getCountry());//该地区／城市所属国家名称
                residentCity.save();//保存数据到数据库中
            }

        }

        //加载常用城市数据
        loadingCommonlyUsedCity();
    }

    /**
     * 加载常用城市数据
     */
    private void loadingCommonlyUsedCity() {
        residentCityList = LitePal.findAll(ResidentCity.class);
        //先判断是否有常用城市
        isChangeCity = residentCityList != null && residentCityList.size() > 0;
        //配置适配器
        changeCityAdapter = new MainChangeCommonlyCityAdapter(R.layout.item_main_city_change, residentCityList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvChangeCity.setLayoutManager(manager);
        binding.rvChangeCity.setAdapter(changeCityAdapter);
        //常用城市点击
        changeCityAdapter.addChildClickViewIds(R.id.tv_city);//添加点击事件
        changeCityAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            showLoadingDialog();
            district = residentCityList.get(position).getLocation();
            mPresent.newSearchCity(district);
            flag = false;
            //隐藏列表
            binding.rvChangeCity.setVisibility(View.GONE);
            changeCityState = false;
        });
    }

    /**
     * 设置每日弹窗
     */
    private void setTipDialog() {

        boolean isShow = SPUtils.getBoolean(Constant.EVERYDAY_POP_BOOLEAN, true, context);
        if (isShow) {
            new Handler().postDelayed(() -> {
                //当所有数据加载完成之后显示弹窗
                if (everyDayTipDialog != null) {
                    return;
                }
                //设置每日提醒弹窗
                setEveryDayTipDialog();
            }, 1000);
        }
    }


    //数据请求失败返回
    @Override
    public void getDataFailed() {
        binding.refresh.finishRefresh();//关闭刷新
        dismissLoadingDialog();//关闭弹窗
    }


    /**
     * 返回Activity的结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == OPEN_LOCATION) {//则是从手机定位页面返回
            if (isOpenLocationServiceEnable()) {//已打开
                binding.tvCity.setText("重新定位");
                binding.tvCity.setEnabled(true);//可以点击
            } else {
                ToastUtils.showShortToast(context, "有意思吗？你跳过去又不打开定位，玩呢？嗯？我也是有脾气的好伐！");
                binding.tvCity.setText("打开定位");
                binding.tvCity.setEnabled(false);//不可点击
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 页面销毁时
     */
    @Override
    public void onDestroy() {
        if (mLocationClient != null) {
            mLocationClient.stop();//数据返回后关闭定位
        }
        binding.wwBig.stop();//停止大风车
        binding.wwSmall.stop();//停止小风车
        EventBus.getDefault().unregister(this);//解注
        super.onDestroy();
    }

    /**
     * 更多功能弹窗，因为区别于我原先写的弹窗
     */
    private void showAddWindow() {
        // 设置布局文件
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.window_add, null));// 为了避免部分机型不显示，我们需要重新设置一下宽高
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));// 设置pop透明效果
        mPopupWindow.setAnimationStyle(R.style.pop_add);// 设置pop出入动画
        mPopupWindow.setFocusable(true);// 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setTouchable(true);// 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setOutsideTouchable(true);// 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.showAsDropDown(binding.ivAdd, -100, 0);// 相对于 + 号正下面，同时可以设置偏移量
        // 设置pop关闭监听，用于改变背景透明度
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {//关闭弹窗
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });
        //绑定布局中的控件
        TextView changeCity = mPopupWindow.getContentView().findViewById(R.id.tv_change_city);//切换城市
        TextView wallpaper = mPopupWindow.getContentView().findViewById(R.id.tv_wallpaper);//壁纸管理  我才是这条Gai最靓的仔
        TextView searchCity = mPopupWindow.getContentView().findViewById(R.id.tv_search_city);//城市搜索
        TextView worldCity = mPopupWindow.getContentView().findViewById(R.id.tv_world_city);//世界城市  V7
        TextView residentCity = mPopupWindow.getContentView().findViewById(R.id.tv_resident_city);//常用城市
        TextView aboutUs = mPopupWindow.getContentView().findViewById(R.id.tv_about_us);//关于我们
        TextView setting = mPopupWindow.getContentView().findViewById(R.id.tv_setting);//应用设置
        changeCity.setOnClickListener(view -> {//切换城市
            showCityWindow();
            mPopupWindow.dismiss();
        });
        wallpaper.setOnClickListener(view -> {//壁纸管理
            startActivity(new Intent(context, WallPaperActivity.class));
            mPopupWindow.dismiss();
        });
        searchCity.setOnClickListener(view -> {//城市搜索
            SPUtils.putBoolean(Constant.FLAG_OTHER_RETURN, false, context);//缓存标识
            startActivity(new Intent(context, SearchCityActivity.class));
            mPopupWindow.dismiss();
        });
        worldCity.setOnClickListener(view -> {//世界城市
            startActivity(new Intent(context, WorldCityActivity.class));
            mPopupWindow.dismiss();
        });
        residentCity.setOnClickListener(view -> {//常用城市
            SPUtils.putBoolean(Constant.FLAG_OTHER_RETURN, false, context);//缓存标识
            startActivity(new Intent(context, CommonlyUsedCityActivity.class));
            mPopupWindow.dismiss();
        });
        aboutUs.setOnClickListener(view -> {//关于我们
            startActivity(new Intent(context, AboutUsActivity.class));
            mPopupWindow.dismiss();
        });
        setting.setOnClickListener(view -> {//应用设置
            startActivity(new Intent(context, SettingActivity.class));
            mPopupWindow.dismiss();
        });
    }

    /**
     * 计算动画时间
     */
    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(progress -> {
            // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
            bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
            backgroundAlpha(bgAlpha);
        });
        animUtil.addEndListner(animator -> {
            // 在一次动画结束的时候，翻转状态
            bright = !bright;
        });
        animUtil.startAnimator();
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

    private long timeMillis;//几点时间

    /**
     * 增加一个退出应用的提示
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                ToastUtils.showShortToast(context, "再按一次退出GoodWeather");
                timeMillis = System.currentTimeMillis();
            } else {
                WeatherApplication.getActivityManager().finishAll();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
