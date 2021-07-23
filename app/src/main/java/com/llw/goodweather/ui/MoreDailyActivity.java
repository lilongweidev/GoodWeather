package com.llw.goodweather.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.R;
import com.llw.goodweather.adapter.MoreDailyAdapter;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.contract.MoreDailyContract;
import com.llw.goodweather.utils.CodeToStringUtils;
import com.llw.goodweather.utils.Constant;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.RecyclerViewScrollHelper;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

/**
 * 更多天气预报
 *
 * @author llw
 */
public class MoreDailyActivity extends MvpActivity<MoreDailyContract.MoreDailyPresenter> implements MoreDailyContract.IMoreDailyView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;

    /**
     * 数据实体
     */
    List<DailyResponse.DailyBean> mList = new ArrayList<>();
    /**
     * 适配器
     */
    MoreDailyAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        //透明状态栏
        StatusBarUtil.transparencyBar(context);
        showLoadingDialog();
        initList();
        Back(toolbar);
    }

    /**
     * 初始化列表
     */
    private void initList() {
        mAdapter = new MoreDailyAdapter(R.layout.item_more_daily_list, mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        rv.setOnFlingListener(null);//避免抛异常
        //滚动对齐，使RecyclerView像ViewPage一样，一次滑动一项,居中
        snapHelper.attachToRecyclerView(rv);
        rv.setAdapter(mAdapter);
        tvTitle.setText(getIntent().getStringExtra("cityName"));
        mPresent.dailyWeather(getIntent().getStringExtra("locationId"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_more_daily;
    }

    @Override
    protected MoreDailyContract.MoreDailyPresenter createPresent() {
        return new MoreDailyContract.MoreDailyPresenter();
    }

    /**
     * 更多预报天气返回值
     *
     * @param response
     */
    @Override
    public void getMoreDailyResult(DailyResponse response) {
        dismissLoadingDialog();
        if (response.getCode().equals(Constant.SUCCESS_CODE)) {
            List<DailyResponse.DailyBean> data = response.getDaily();
            //判空处理
            if (data != null && data.size() > 0) {
                //添加数据之前先清除
                mList.clear();
                //添加数据
                mList.addAll(data);
                //刷新列表
                mAdapter.notifyDataSetChanged();

                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getFxDate().equals(DateUtils.getNowDate())) {
                        //渲染完成后，定位到今天，因为和风天气预报有时候包括了昨天，有时候又不包括，搞得我很被动
                        RecyclerViewScrollHelper.scrollToPosition(rv, i);
                    }
                }

            } else {
                ToastUtils.showShortToast(context, "天气预报数据为空");
            }
        } else {//异常状态码返回
            ToastUtils.showShortToast(context, CodeToStringUtils.WeatherCode(response.getCode()));
        }
    }

    /**
     * 其他异常返回
     */
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "更多天气数据获取异常");
    }

}
