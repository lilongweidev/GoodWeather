package com.llw.goodweather.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.DailyAdapter;
import com.llw.goodweather.adapter.SevenDailyAdapter;
import com.llw.goodweather.adapter.TodayDetailAdapter;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.LifestyleResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.bean.TodayDetailBean;
import com.llw.goodweather.bean.WarningResponse;
import com.llw.goodweather.contract.MapWeatherContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.goodweather.utils.WeatherUtil;
import com.llw.mvplibrary.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

/**
 * 地图天气
 */
public class MapWeatherActivity extends MvpActivity<MapWeatherContract.MapWeatherPresenter>
        implements MapWeatherContract.IMapWeatherView {

    @BindView(R.id.map_view)
    MapView mapView;//地图控件
    @BindView(R.id.btn_auto_location)
    FloatingActionButton btnAutoLocation;//重新定位按钮
    @BindView(R.id.tv_city)
    TextView tvCity;//城市
    @BindView(R.id.iv_weather)
    ImageView ivWeather;//天气状态图片描述
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;//温度
    @BindView(R.id.tv_weather_state_tv)
    TextView tvWeatherStateTv;//天气状态文字描述
    @BindView(R.id.tv_wind_info)
    TextView tvWindInfo;//风力风向
    @BindView(R.id.rv_today_detail)
    RecyclerView rvTodayDetail;//今日详情数据列表
    @BindView(R.id.rv_seven_day_daily)
    RecyclerView rvSevenDayDaily;//七天天气预报列表
    @BindView(R.id.tv_more_daily)
    TextView tvMoreDaily;//15日天气预报数据
    @BindView(R.id.tv_uvIndex)
    TextView tvUvIndex;//紫外线强度
    @BindView(R.id.tv_humidity)
    TextView tvHumidity;//湿度
    @BindView(R.id.tv_pressure)
    TextView tvPressure;//大气压
    @BindView(R.id.tv_today_info)
    TextView tvTodayInfo;//今日天气简要描述
    @BindView(R.id.tv_air)
    TextView tvAir;//空气质量
    @BindView(R.id.bottom_sheet_ray)
    RelativeLayout bottomSheetRay;//底部拖动布局


    private LocationClient mLocationClient;//定位
    private BaiduMap mBaiduMap;//百度地图

    private Marker marker;//标点也可以说是覆盖物
    private BitmapDescriptor bitmap;//标点的图标
    private double markerLatitude = 0;//标点纬度
    private double markerLongitude = 0;//标点经度
    private double latitude;//定位纬度
    private double longitude;//定位经度

    private GeoCoder geoCoder;//百度地址解析

    private String locationId;//城市id
    private List<DailyResponse.DailyBean> dailyList = new ArrayList<>();//七天天气预报
    List<TodayDetailBean> todayDetailList = new ArrayList<>();//当前天气详情

    private SevenDailyAdapter mSevenDailyAdapter;//七天天气预报适配器

    private String dayInfo;//今天白天天气描述
    private String nightInfo;//今天晚上天气描述

    private BottomSheetBehavior bottomSheetBehavior;//底部控件


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();//初始化控件
        initLocation();//初始化定位
        initMapOnClick();//初始化地图点击
    }

    private void initView() {
        StatusBarUtil.transparencyBar(context);//透明状态栏
        StatusBarUtil.StatusBarLightMode(context);//状态栏黑色字体
        mBaiduMap = mapView.getMap();//赋值对象
        mapView.removeViewAt(1);// 删除百度地图Logo
        mapView.showScaleControl(false);//隐藏比例尺
        mapView.showZoomControls(false);//隐藏缩放按钮
        geoCoder = GeoCoder.newInstance();//赋值
        geoCoder.setOnGetGeoCodeResultListener(onGetGeoCoderResultListener);//反编码结果监听

        mSevenDailyAdapter = new SevenDailyAdapter(R.layout.item_seven_day_daily_list, dailyList);
        rvSevenDayDaily.setLayoutManager(new LinearLayoutManager(context));
        rvSevenDayDaily.setAdapter(mSevenDailyAdapter);

        /*获取behavior 控制bottomsheet的 显示 与隐藏*/
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetRay);
        /*bottomSheet 的 状态改变 根据不同的状态 做不同的事情*/
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED://收缩
                        //手动定位时,收缩就要显示浮动按钮,因为这时候你可以操作地图了
                        if (markerLatitude != 0) {//自动定位
                            btnAutoLocation.show();//显示自动定位按钮
                        }
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED://展开
                        //手动定位时,展开就要隐藏浮动按钮,因为这时候你是没有办法操作地图的
                        if (markerLatitude != 0) {//自动定位
                            btnAutoLocation.hide();//隐藏自动定位按钮
                        }
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                /*slideOffset bottomSheet 的 移动距离*/
            }
        });

    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mBaiduMap.setMyLocationEnabled(true);//开启定位图层
        mLocationClient = new LocationClient(context);//定位初始化

        MyLocationListener listener = new MyLocationListener();//创建定位监听器
        mLocationClient.registerLocationListener(listener);//注册定位监听，否则监听无效

        LocationClientOption option = new LocationClientOption();//创建定位设置
        option.setOpenGps(true);//打开GPS
        option.setCoorType("bd09ll");//设置坐标类型  可以设置BD09LL和GCJ02两种坐标
        option.setScanSpan(0);//设置扫描间隔，单位是毫秒，0  则表示只定位一次，设置毫秒不能低于1000，也就是1秒

        mLocationClient.setLocOption(option);//传入定位设置
        mLocationClient.start();//开始定位

    }

    /**
     * 初始化地图点击
     */
    private void initMapOnClick() {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            //地图内 Poi 单击事件回调函数  那么poi是什么呢？你可以当做兴趣点，
            // 比如我想知道我当前所在地有那些餐厅，那么餐厅就是poi，
            // 而你点击这个poi就会拿到详情的信息数据，当然不在我的业务需求之内，所以只做解释
            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }

            //地图单击事件回调函数
            @Override
            public void onMapClick(LatLng latLng) {
                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka);// 设置marker图标
                //通过LatLng获取经纬度
                markerLatitude = latLng.latitude;//获取纬度
                markerLongitude = latLng.longitude;//获取经度
                mBaiduMap.clear();//清除之前的图层

                MarkerOptions options = new MarkerOptions()//创建标点marker设置对象
                        .position(latLng)//设置标点的定位
                        .icon(bitmap);//设置标点图标

                marker = (Marker) mBaiduMap.addOverlay(options);//在地图上显示标点
                //点击地图之后重新定位
                initLocation();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_map_weather;
    }

    @Override
    protected MapWeatherContract.MapWeatherPresenter createPresent() {
        return new MapWeatherContract.MapWeatherPresenter();
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.btn_auto_location,R.id.tv_more_daily})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_auto_location://重新定位
                markerLatitude = 0;
                markerLongitude = 0;
                marker.remove();//清除标点
                initLocation();
                break;
            case R.id.tv_more_daily://15日天气预报详情
                Intent intent = new Intent(context, MoreDailyActivity.class);
                intent.putExtra("locationId", locationId);
                intent.putExtra("cityName", tvCity.getText().toString());
                startActivity(intent);
                break;
        }

    }

    /**
     * 定位SDK监听
     */
    private class MyLocationListener implements BDLocationListener {

        /**
         * 监听返回数据 MapView 销毁后不在处理新接收的位置
         *
         * @param bdLocation 定位信息
         */
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mapView == null) {//做null处理，避免APP崩溃
                return;
            }

            if (markerLatitude == 0) {//自动定位
                latitude = bdLocation.getLatitude();
                longitude = bdLocation.getLongitude();
                btnAutoLocation.hide();//隐藏自动定位按钮
            } else {//标点定位
                latitude = markerLatitude;
                longitude = markerLongitude;
                btnAutoLocation.show();//显示自动定位按钮
            }

            MyLocationData locationData = new MyLocationData.Builder()//定位构造器
                    .accuracy(bdLocation.getRadius())//设置定位数据的精度信息，单位：米
                    .direction(bdLocation.getDirection())//设置定位数据的方向信息
                    .latitude(latitude)//设置定位数据的纬度
                    .longitude(longitude)//设置定位数据的经度
                    .build();//构建生成定位数据对象
            mBaiduMap.setMyLocationData(locationData);//设置定位数据，只有开启定位图层之后才会生效

            //创建一个经纬度构造对象，传入定位返回的经纬度，Latitude是纬度，Longitude是经度,一对经纬度值代表地球上一个地点。
            LatLng latLng = new LatLng(latitude, longitude);

            //根据经纬度进行反编码
            geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng).pageNum(0).pageSize(100));

            MapStatus.Builder builder = new MapStatus.Builder()//创建地图状态构造器
                    .target(latLng)//设置地图中心点，传入经纬度对象
                    .zoom(13.0f);//设置地图缩放级别 13 表示  比例尺/2000米 2公里

            //改变地图状态，使用地图状态更新工厂中的新地图状态方法，传入状态构造器
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        }
    }

    /**
     * 编码结果监听
     */
    private OnGetGeoCoderResultListener onGetGeoCoderResultListener = new OnGetGeoCoderResultListener() {
        /**
         * 编码结果返回  就是通过具体位置信息获取坐标
         * @param geoCodeResult  编码返回结果
         */
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

        }

        /**
         * 反编码结果返回  就是通过坐标获取具体位置信息
         * @param reverseGeoCodeResult 反编码返回结果
         */
        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            if (reverseGeoCodeResult == null
                    || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                // 没有检测到结果
                return;
            }
            //需要的地址信息就在AddressComponent 里
            ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
            mPresent.searchCity(addressDetail.district);//搜索城市的id

        }
    };


    /**
     * 搜索城市返回
     *
     * @param response
     */
    @Override
    public void getNewSearchCityResult(Response<NewSearchCityResponse> response) {
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            if (response.body().getLocation() != null && response.body().getLocation().size() > 0) {
                tvCity.setText(response.body().getLocation().get(0).getName());//城市
                locationId = response.body().getLocation().get(0).getId();//城市Id
                showLoadingDialog();
                mPresent.nowWeather(locationId);//查询实况天气
                mPresent.dailyWeather(locationId);//查询天气预报
                mPresent.airNowWeather(locationId);//空气质量
            } else {
                ToastUtils.showShortToast(context, "数据为空");
            }
        } else {
            tvCity.setText("查询城市失败");
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 实况天气返回
     *
     * @param response
     */
    @Override
    public void getNowResult(Response<NowResponse> response) {
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {//200则成功返回数据
            NowResponse data = response.body();
            if (data != null) {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
                tvTemperature.setText(response.body().getNow().getTemp() + "°");//温度
                tvTemperature.setTypeface(typeface);//使用字体
                tvWeatherStateTv.setText(data.getNow().getText());//天气状态文字描述
                WeatherUtil.changeIcon(ivWeather, Integer.parseInt(data.getNow().getIcon()));
                tvWindInfo.setText(data.getNow().getWindDir() + data.getNow().getWindScale() + "级");

                tvHumidity.setText(data.getNow().getHumidity() + "%");//湿度
                tvPressure.setText(data.getNow().getPressure() + "hPa");//大气压

                //今日详情
                todayDetailList.clear();
                todayDetailList.add(new TodayDetailBean(R.mipmap.icon_today_temp, data.getNow().getFeelsLike() + "°", "体感温度"));
                todayDetailList.add(new TodayDetailBean(R.mipmap.icon_today_precip, data.getNow().getPrecip() + "mm", "降水量"));
                todayDetailList.add(new TodayDetailBean(R.mipmap.icon_today_humidity, data.getNow().getHumidity() + "%", "湿度"));
                todayDetailList.add(new TodayDetailBean(R.mipmap.icon_today_pressure, data.getNow().getPressure() + "hPa", "大气压强"));
                todayDetailList.add(new TodayDetailBean(R.mipmap.icon_today_vis, data.getNow().getVis() + "KM", "能见度"));
                todayDetailList.add(new TodayDetailBean(R.mipmap.icon_today_cloud, data.getNow().getCloud() + "%", "云量"));

                TodayDetailAdapter adapter = new TodayDetailAdapter(R.layout.item_today_detail, todayDetailList);
                rvTodayDetail.setLayoutManager(new GridLayoutManager(context, 3));
                rvTodayDetail.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            } else {
                ToastUtils.showShortToast(context, "暂无实况天气数据");
            }
        } else {//其他状态返回提示文字
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 未来七天天气预报数据返回
     *
     * @param response
     */
    @Override
    public void getDailyResult(Response<DailyResponse> response) {
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            List<DailyResponse.DailyBean> data = response.body().getDaily();
            if (data != null && data.size() > 0) {//判空处理

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getFxDate().equals(DateUtils.getNowDate())) {//今天
                        dayInfo = data.get(i).getTextDay();
                        nightInfo = data.get(i).getTextNight();
                        tvUvIndex.setText(WeatherUtil.uvIndexInfo(data.get(i).getUvIndex()));//紫外线强度
                    }
                }
                dailyList.clear();//添加数据之前先清除
                dailyList.addAll(data);//添加数据
                mSevenDailyAdapter.notifyDataSetChanged();//刷新列表
            } else {
                ToastUtils.showShortToast(context, "天气预报数据为空");
            }
        } else {//异常状态码返回
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getCode()));
        }
    }

    /**
     * 空气质量数据返回
     *
     * @param response
     */
    @Override
    public void getAirNowResult(Response<AirNowResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode().equals(Constant.SUCCESS_CODE)) {
            AirNowResponse.NowBean data = response.body().getNow();
            if (response.body().getNow() != null) {
                tvAir.setText("AQI " + data.getCategory());
                tvTodayInfo.setText("今天白天" + dayInfo + ",晚上" + nightInfo + "，现在" + tvTemperature.getText().toString() + "," + WeatherUtil.apiToTip(data.getCategory()));
            } else {
                ToastUtils.showShortToast(context, "空气质量数据为空");
            }
        }
    }

    /**
     * 错误返回
     */
    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, "连接超时，稍后尝试。");
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();//生命周期管理
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();//生命周期管理
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();// 退出时销毁定位
        mBaiduMap.setMyLocationEnabled(false);// 关闭定位图层
        mapView.onDestroy();// 在activity执行onDestroy时必须调用mMapView.onDestroy()
    }

}


