package com.llw.goodweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.WorldCityAdapter;
import com.llw.goodweather.bean.WorldCityResponse;
import com.llw.goodweather.contract.WorldCityContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.mvp.MvpActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * 世界城市列表 每个国家20个Top城市
 *
 * @author llw
 */
public class WorldCityListActivity extends MvpActivity<WorldCityContract.WorldCityPresenter>
        implements WorldCityContract.IWorldCityView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    private WorldCityAdapter mCityAdapter;

    List<WorldCityResponse.TopCityListBean> mList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        //白色底  状态栏
        StatusBarUtil.setStatusBarColor(context, R.color.white);
        //状态栏 黑色字体
        StatusBarUtil.StatusBarLightMode(context);
        Back(toolbar);

        tvTitle.setText(getIntent().getStringExtra("name"));
        String code = getIntent().getStringExtra("code");
        showLoadingDialog();

        initList(code);

    }

    /**
     * 初始化数据列表
     *
     * @param code 国家/地区 代码
     */
    private void initList(String code) {
        mCityAdapter = new WorldCityAdapter(R.layout.item_city_list, mList);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mCityAdapter);
        mCityAdapter.notifyDataSetChanged();

        mCityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, WorldCityWeatherActivity.class);
                intent.putExtra("name", mList.get(position).getName());
                intent.putExtra("locationId", mList.get(position).getId());
                startActivity(intent);

            }
        });

        mPresent.worldCity(code);//请求国家数据
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_world_city_list;
    }

    @Override
    protected WorldCityContract.WorldCityPresenter createPresent() {
        return new WorldCityContract.WorldCityPresenter();
    }

    /**
     * 世界城市返回
     *
     * @param response
     */
    @Override
    public void getWorldCityResult(WorldCityResponse response) {
        dismissLoadingDialog();
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<WorldCityResponse.TopCityListBean> data = response.getTopCityList();
            if (data != null && data.size() > 0) {
                mList.clear();
                mList.addAll(data);
                mCityAdapter.notifyDataSetChanged();
            } else {
                ToastUtils.showShortToast(context, "没找到城市数据");
            }

        } else {
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 失败异常返回
     */
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "其他异常");
    }

}
