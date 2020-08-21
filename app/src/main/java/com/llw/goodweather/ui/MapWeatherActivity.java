package com.llw.goodweather.ui;

import android.graphics.Point;
import android.os.Bundle;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.llw.goodweather.R;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地图天气
 */
public class MapWeatherActivity extends BaseActivity {

    @BindView(R.id.map_view)
    MapView mapView;//地图控件
    @BindView(R.id.btn_auto_location)
    FloatingActionButton btnAutoLocation;

    private LocationClient mLocationClient;//定位
    private BaiduMap mBaiduMap;//百度地图

    private Marker marker;//标点也可以说是覆盖物
    private BitmapDescriptor bitmap;//标点的图标
    private double markerLatitude = 0;//标点纬度
    private double markerLongitude = 0;//标点经度
    private double latitude;//定位纬度
    private double longitude;//定位经度


    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.transparencyBar(context);//透明状态栏
        StatusBarUtil.StatusBarLightMode(context);//状态栏黑色字体

        initView();//初始化控件
        initLocation();//初始化定位
        initMapOnClick();//初始化地图点击
    }

    private void initView() {
        mBaiduMap = mapView.getMap();//赋值对象
        mapView.removeViewAt(1);// 删除百度地图Logo
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


    @OnClick(R.id.btn_auto_location)
    public void onViewClicked() {
        markerLatitude = 0;
        markerLongitude = 0;
        initLocation();
        marker.remove();//清除标点
    }

    /**
     * 定位SDK监听,
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
                btnAutoLocation.hide();
            } else {//标点定位
                latitude = markerLatitude;
                longitude = markerLongitude;
                btnAutoLocation.show();
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

            MapStatus.Builder builder = new MapStatus.Builder()//创建地图状态构造器
                    .target(latLng)//设置地图中心点，传入经纬度对象
                    .zoom(13.0f);//设置地图缩放级别 13 表示  比例尺/2000米 2公里

            //改变地图状态，使用地图状态更新工厂中的新地图状态方法，传入状态构造器
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
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
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();// 退出时销毁定位
        mBaiduMap.setMyLocationEnabled(false);// 关闭定位图层
        mapView.onDestroy();// 在activity执行onDestroy时必须调用mMapView.onDestroy()
    }

}
