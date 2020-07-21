package com.llw.goodweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.CountryAdapter;
import com.llw.goodweather.adapter.WorldCityAdapter;
import com.llw.goodweather.bean.HotCityResponse;
import com.llw.goodweather.bean.NewHotCityResponse;
import com.llw.goodweather.contract.HotCityContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.bean.Country;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.llw.mvplibrary.utils.LiWindow;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

/**
 * 世界城市  列举世界上两百多个国家其中包括地区，每个国家20个热门城市
 */
public class WorldCityActivity extends MvpActivity<HotCityContract.HotCityPresenter>
        implements HotCityContract.IHotCityView {

    CountryAdapter mAdapter;
    List<Country> mList;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;

    private String countryName;

    WorldCityAdapter mCityAdapter;
    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(context, R.color.white);//白色底  状态栏
        StatusBarUtil.StatusBarLightMode(context);//黑色字体
        Back(toolbar);
        initList();
    }


    private void initList() {
        mList = LitePal.findAll(Country.class);
        mAdapter = new CountryAdapter(R.layout.item_country_list, mList);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showLoadingDialog();
                countryName = mList.get(position).getName();
                mPresent.newHotCity(mList.get(position).getCode());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_world_city;
    }

    @Override
    protected HotCityContract.HotCityPresenter createPresent() {
        return new HotCityContract.HotCityPresenter();
    }

    /**
     * 城市弹窗
     */
    private void showCityWindow(String countryName,List<NewHotCityResponse.TopCityListBean> list) {
        LiWindow liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_world_city_list, null);
        TextView windowTitle = (TextView) view.findViewById(R.id.tv_title);
        windowTitle.setText(countryName);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_city);
        liWindow.showRightPopupWindowMatchParent(view);//显示弹窗

        mCityAdapter = new WorldCityAdapter(R.layout.item_city_list,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCityAdapter);

        mCityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, WorldCityWeatherActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("locationId",list.get(position).getId());
                startActivity(intent);
                liWindow.closePopupWindow();
            }
        });
    }


    @Override
    public void getHotCityResult(Response<HotCityResponse> response) {

    }

    @Override
    public void getNewHotCityResult(Response<NewHotCityResponse> response) {
        dismissLoadingDialog();
        if(response.body().getStatus().equals(Constant.SUCCESS_CODE)){

            List<NewHotCityResponse.TopCityListBean> data = response.body().getTopCityList();
            if(data != null && data.size()>0){
                showCityWindow(countryName,data);
            }else {
                ToastUtils.showShortToast(context,"没找到城市数据");
            }
        }else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.body().getStatus()));
        }
    }

    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context,"其他异常");
    }



}
