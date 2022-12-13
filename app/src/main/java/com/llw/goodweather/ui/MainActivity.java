package com.llw.goodweather.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.llw.goodweather.Constant;
import com.llw.goodweather.R;
import com.llw.goodweather.location.GoodLocation;
import com.llw.goodweather.ui.adapter.DailyAdapter;
import com.llw.goodweather.ui.adapter.LifestyleAdapter;
import com.llw.goodweather.db.bean.DailyResponse;
import com.llw.goodweather.db.bean.LifestyleResponse;
import com.llw.goodweather.db.bean.NowResponse;
import com.llw.goodweather.db.bean.SearchCityResponse;
import com.llw.goodweather.databinding.ActivityMainBinding;
import com.llw.goodweather.location.LocationCallback;
import com.llw.goodweather.utils.CityDialog;
import com.llw.goodweather.utils.EasyDate;
import com.llw.goodweather.utils.GlideUtils;
import com.llw.goodweather.utils.MVUtils;
import com.llw.goodweather.viewmodel.MainViewModel;
import com.llw.library.base.NetworkActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NetworkActivity<ActivityMainBinding> implements LocationCallback, CityDialog.SelectedCityCallback {

    //权限数组
    private final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求权限意图
    private ActivityResultLauncher<String[]> requestPermissionIntent;

    private MainViewModel viewModel;

    //天气预报数据和适配器
    private final List<DailyResponse.DailyBean> dailyBeanList = new ArrayList<>();
    private final DailyAdapter dailyAdapter = new DailyAdapter(dailyBeanList);
    //生活指数数据和适配器
    private final List<LifestyleResponse.DailyBean> lifestyleList = new ArrayList<>();
    private final LifestyleAdapter lifestyleAdapter = new LifestyleAdapter(lifestyleList);
    //城市弹窗
    private CityDialog cityDialog;
    //定位
    private GoodLocation goodLocation;
    //菜单
    private Menu mMenu;
    //城市信息来源标识  0: 定位， 1: 切换城市
    private int cityFlag = 0;

    /**
     * 注册意图
     */
    @Override
    public void onRegister() {
        //请求权限意图
        requestPermissionIntent = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            boolean fineLocation = Boolean.TRUE.equals(result.get(Manifest.permission.ACCESS_FINE_LOCATION));
            boolean writeStorage = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            if (fineLocation && writeStorage) {
                startLocation();//权限已经获取到，开始定位
            }
        });
    }

    /**
     * 初始化
     */
    @Override
    protected void onCreate() {
        //沉浸式
        setFullScreenImmersion();
        //初始化定位
        initLocation();
        //请求权限
        requestPermission();
        //初始化视图
        initView();
        //绑定ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //获取城市数据
        viewModel.getAllCity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //更新壁纸
        updateBgImage(MVUtils.getBoolean(Constant.USED_BING), MVUtils.getString(Constant.BING_URL));
    }

    /**
     * 初始化页面视图
     */
    private void initView() {
        setToolbarMoreIconCustom(binding.materialToolbar);
        binding.rvDaily.setLayoutManager(new LinearLayoutManager(this));
        binding.rvDaily.setAdapter(dailyAdapter);
        binding.rvLifestyle.setLayoutManager(new LinearLayoutManager(this));
        binding.rvLifestyle.setAdapter(lifestyleAdapter);
    }

    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu = menu;
        //根据cityFlag设置重新定位菜单项是否显示
        mMenu.findItem(R.id.item_relocation).setVisible(cityFlag == 1);
        //根据使用必应壁纸的状态，设置item项是否选中
        mMenu.findItem(R.id.item_bing).setChecked(MVUtils.getBoolean(Constant.USED_BING));
        return true;
    }

    /**
     * 菜单选项选中
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_switching_cities:
                if (cityDialog != null) cityDialog.show();
                break;
            case R.id.item_relocation:
                startLocation();//点击重新定位item时，再次定位一下。
                break;
            case R.id.item_bing:
                item.setChecked(!item.isChecked());
                MVUtils.put(Constant.USED_BING, item.isChecked());
                String bingUrl = MVUtils.getString(Constant.BING_URL);
                //更新壁纸
                updateBgImage(item.isChecked(), bingUrl);
                break;
        }
        return true;
    }

    /**
     * 更新壁纸
     *
     * @param usedBing 使用必应壁纸
     * @param bingUrl  必应壁纸URL
     */
    private void updateBgImage(boolean usedBing, String bingUrl) {
        if (usedBing && !bingUrl.isEmpty()) {
            GlideUtils.loadImg(this, bingUrl, binding.layRoot);
        } else {
            binding.layRoot.setBackground(ContextCompat.getDrawable(this, R.drawable.main_bg));
        }
    }

    private void startLocation() {
        cityFlag = 0;
        goodLocation.startLocation();
    }

    /**
     * 自定义Toolbar的图标
     */
    public void setToolbarMoreIconCustom(Toolbar toolbar) {
        if (toolbar == null) return;
        toolbar.setTitle("");
        Drawable moreIcon = ContextCompat.getDrawable(toolbar.getContext(), R.drawable.ic_round_add_32);
        if (moreIcon != null) toolbar.setOverflowIcon(moreIcon);
        setSupportActionBar(toolbar);
    }

    /**
     * 数据观察
     */
    @Override
    protected void onObserveData() {
        if (viewModel != null) {
            //城市数据返回
            viewModel.searchCityResponseMutableLiveData.observe(this, searchCityResponse -> {
                List<SearchCityResponse.LocationBean> location = searchCityResponse.getLocation();
                if (location != null && location.size() > 0) {
                    String id = location.get(0).getId();
                    //根据cityFlag设置重新定位菜单项是否显示
                    mMenu.findItem(R.id.item_relocation).setVisible(cityFlag == 1);
                    //获取到城市的ID
                    if (id != null) {
                        //通过城市ID查询城市实时天气
                        viewModel.nowWeather(id);
                        //通过城市ID查询天气预报
                        viewModel.dailyWeather(id);
                        //通过城市ID查询生活指数
                        viewModel.lifestyle(id);
                    }
                }
            });
            //实况天气返回
            viewModel.nowResponseMutableLiveData.observe(this, nowResponse -> {
                NowResponse.NowBean now = nowResponse.getNow();
                if (now != null) {
                    binding.tvInfo.setText(now.getText());
                    binding.tvTemp.setText(now.getTemp());
                    binding.tvUpdateTime.setText("最近更新时间：" + EasyDate.greenwichupToSimpleTime(nowResponse.getUpdateTime()));

                    binding.tvWindDirection.setText("风向     " + now.getWindDir());//风向
                    binding.tvWindPower.setText("风力     " + now.getWindScale() + "级");//风力
                    binding.wwBig.startRotate();//大风车开始转动
                    binding.wwSmall.startRotate();//小风车开始转动
                }
            });
            //天气预报返回
            viewModel.dailyResponseMutableLiveData.observe(this, dailyResponse -> {
                List<DailyResponse.DailyBean> daily = dailyResponse.getDaily();
                if (daily != null) {
                    if (dailyBeanList.size() > 0) {
                        dailyBeanList.clear();
                    }
                    dailyBeanList.addAll(daily);
                    dailyAdapter.notifyDataSetChanged();
                }
            });
            //生活指数返回
            viewModel.lifestyleResponseMutableLiveData.observe(this, lifestyleResponse -> {
                List<LifestyleResponse.DailyBean> daily = lifestyleResponse.getDaily();
                if (daily != null) {
                    if (lifestyleList.size() > 0) {
                        lifestyleList.clear();
                    }
                    lifestyleList.addAll(daily);
                    lifestyleAdapter.notifyDataSetChanged();
                }
            });
            //获取本地城市数据返回
            viewModel.cityMutableLiveData.observe(this, provinces -> {
                //城市弹窗初始化
                cityDialog = CityDialog.getInstance(MainActivity.this, provinces);
                cityDialog.setSelectedCityCallback(this);
            });
            //错误信息返回
            viewModel.failed.observe(this, this::showLongMsg);
        }
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        //因为项目的最低版本API是23，所以肯定需要动态请求危险权限，只需要判断权限是否拥有即可
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //开始权限请求
            requestPermissionIntent.launch(permissions);
            return;
        }
        startLocation();//拥有权限，开始定位
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        goodLocation = GoodLocation.getInstance(this);
        goodLocation.setCallback(this);
    }

    /**
     * 接收定位信息
     *
     * @param bdLocation 定位数据
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        String city = bdLocation.getCity();             //获取城市
        String district = bdLocation.getDistrict();     //获取区县
        if (viewModel != null && district != null) {
            //显示当前定位城市
            binding.tvCity.setText(district);
            //搜索城市
            viewModel.searchCity(district);
        } else {
            Log.e("TAG", "district: " + district);
        }
    }

    /**
     * 选中城市
     *
     * @param cityName 城市名称
     */
    @Override
    public void selectedCity(String cityName) {
        cityFlag = 1;//切换城市
        //搜索城市
        viewModel.searchCity(cityName);
        //显示所选城市
        binding.tvCity.setText(cityName);
    }
}