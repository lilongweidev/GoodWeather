package com.llw.goodweather.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.CommonlyCityAdapter;
import com.llw.goodweather.adapter.CommonlyCityAddAdapter;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.contract.SearchCityContract;
import com.llw.goodweather.databinding.ActivityCommonlyUsedCityBinding;
import com.llw.goodweather.eventbus.SearchCityEvent;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.SPUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.bean.ResidentCity;
import com.llw.mvplibrary.mvp.MvpVBActivity;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用城市
 *
 * @author llw
 */
public class CommonlyUsedCityActivity extends MvpVBActivity<ActivityCommonlyUsedCityBinding, SearchCityContract.SearchCityPresenter>
        implements SearchCityContract.ISearchCityView {

    CommonlyCityAdapter mAdapter;//常用城市列表适配器
    List<NewSearchCityResponse.LocationBean> mList = new ArrayList<>();
    CommonlyCityAddAdapter mAdapterAdd;//搜索城市列表适配器
    List<ResidentCity> cityList;//常用城市列表

    @Override
    public void initData() {
        StatusBarUtil.setStatusBarColor(context, R.color.white);//白色状态栏
        StatusBarUtil.StatusBarLightMode(context);//黑色字体
        Back(binding.toolbar);

        initCityList();//初始化常用城市列表

        initQueryAddList();//初始化搜索城市列表

        initEdit();//初始化输入框

        binding.ivClearSearch.setOnClickListener(v -> {
            binding.editQuery.setText("");//置为空
            initHideOrShow();
        });
    }

    /**
     * 初始化搜索要添加的城市列表
     */
    private void initQueryAddList() {
        mAdapterAdd = new CommonlyCityAddAdapter(R.layout.item_commonly_city_add_list, mList);
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(context));
        binding.rvSearch.setAdapter(mAdapterAdd);
        //点击item时保存到数据库中，同时传递数据到主页面查询出天气
        mAdapterAdd.addChildClickViewIds(R.id.item_add_city);//添加点击事件
        mAdapterAdd.setOnItemChildClickListener((adapter, view, position) -> QueryWeather(position));
    }

    /**
     * 添加城市列表item，点击保存数据并发送事件
     *
     * @param position
     */
    private void QueryWeather(int position) {
        ResidentCity residentCity = new ResidentCity();
        residentCity.setLocation(mList.get(position).getName());//地区／城市名称
        residentCity.setParent_city(mList.get(position).getAdm2());//该地区／城市的上级城市
        residentCity.setAdmin_area(mList.get(position).getAdm1());//该地区／城市所属行政区域
        residentCity.setCnty(mList.get(position).getCountry());//该地区／城市所属国家名称

        residentCity.save();//保存数据到数据库中
        if (residentCity.save()) {//保存成功
            //然后使用之前在搜索城市天气中写好的代码
            SPUtils.putString(Constant.LOCATION, mList.get(position).getName(), context);
            //发送消息
            EventBus.getDefault().post(new SearchCityEvent(mList.get(position).getName(),
                    mList.get(position).getAdm2()));
            finish();
        } else {//保存失败
            ToastUtils.showShortToast(context, "添加城市失败");
        }
    }

    /**
     * 初始化搜索输入框 ,输入后马上查询数据，不需要额外点击，同时查询到数据之后隐藏默认城市列表
     */
    private void initEdit() {
        binding.editQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {//输入后，显示清除按钮
                    binding.ivClearSearch.setVisibility(View.VISIBLE);
                    mAdapterAdd.changTxColor(s.toString());
                    mPresent.newSearchCity(s.toString());//搜索城市  V7  模糊搜索返回十条数据
                } else {//隐藏和显示控件
                    initHideOrShow();

                }
            }
        });
    }

    /**
     * 根据常用城市数据来进行页面控件显示/隐藏
     */
    private void initHideOrShow() {
        binding.ivClearSearch.setVisibility(View.GONE);//隐藏清除输入框内容的图标
        binding.rvSearch.setVisibility(View.GONE);//隐藏搜索结果列表
        if (cityList != null && cityList.size() > 0) {//有数据
            binding.rvCommonlyUsed.setVisibility(View.VISIBLE);//显示常用城市列表
            binding.layNormal.setVisibility(View.GONE);//隐藏没有数据时的布局
        } else {//没数据
            binding.rvCommonlyUsed.setVisibility(View.GONE);//隐藏常用城市列表
            binding.layNormal.setVisibility(View.VISIBLE);//显示没有数据时的布局
        }
    }

    /**
     * 初始化常用城市列表数据
     */
    private void initCityList() {
        //查询ResidentCity表中所有数据
        cityList = LitePal.findAll(ResidentCity.class);

        if (cityList.size() > 0 && cityList != null) {
            mAdapter = new CommonlyCityAdapter(R.layout.item_commonly_city_list, cityList);

            binding.rvCommonlyUsed.setLayoutManager(new LinearLayoutManager(context));
            binding.rvCommonlyUsed.setAdapter(mAdapter);

            mAdapter.addChildClickViewIds(R.id.tv_city_name, R.id.iv_delete);//添加点击事件
            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.tv_city_name:
                        SPUtils.putString(Constant.LOCATION, cityList.get(position).getLocation(), context);
                        //发送消息
                        EventBus.getDefault().post(new SearchCityEvent(cityList.get(position).getLocation(),
                                cityList.get(position).getParent_city()));
                        finish();
                        break;
                    case R.id.iv_delete://删除
                        LitePal.delete(ResidentCity.class, cityList.get(position).getId());//删除指定id
                        initCityList();
                        //删除数据后判断一下显示和隐藏的控件
                        initHideOrShow();
                        break;
                }
            });
            mAdapter.notifyDataSetChanged();
        } else {
            binding.rvCommonlyUsed.setVisibility(View.GONE);
            binding.layNormal.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected SearchCityContract.SearchCityPresenter createPresent() {
        return new SearchCityContract.SearchCityPresenter();
    }

    /**
     * 搜索城市天气 V7
     *
     * @param response
     */
    @Override
    public void getNewSearchCityResult(NewSearchCityResponse response) {
        dismissLoadingDialog();
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<NewSearchCityResponse.LocationBean> data = response.getLocation();
            if (data != null && data.size() > 0) {
                binding.rvCommonlyUsed.setVisibility(View.GONE);//隐藏常用城市列表
                mList.clear();
                mList.addAll(response.getLocation());
                mAdapterAdd.notifyDataSetChanged();
                binding.rvSearch.setVisibility(View.VISIBLE);//显示搜索城市列表
                binding.layNormal.setVisibility(View.GONE);
            } else {
                ToastUtils.showShortToast(context, "没有找到相关城市");
            }
        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 网络异常返回处理
     */
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();//关闭弹窗
        ToastUtils.showShortToast(context, "网络异常");
    }
}
