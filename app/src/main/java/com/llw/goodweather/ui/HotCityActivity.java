package com.llw.goodweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.HotCityAdapter;
import com.llw.goodweather.bean.HotCityResponse;
import com.llw.goodweather.contract.HotCityContract;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

import static com.llw.mvplibrary.utils.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 热门城市
 */
public class HotCityActivity extends MvpActivity<HotCityContract.HotCityPresenter> implements HotCityContract.IHotCityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;//标题bar
    @BindView(R.id.rv)
    RecyclerView rv;//列表

    List<HotCityResponse.HeWeather6Bean.BasicBean> mList = new ArrayList<>();
    HotCityAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoadingDialog();//加载弹窗
        StatusBarUtil.setStatusBarColor(context, R.color.orange);//白色状态栏
        Back(toolbar);//返回
        initList();//初始化列表数据
    }


    private void initList() {
        mAdapter = new HotCityAdapter(R.layout.item_hot_city_list,mList);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);
        //item 点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context,HotCityWeatherActivity.class);
                intent.putExtra("location",mList.get(position).getLocation());
                startActivity(intent);
            }
        });

        mPresent.hotCity(context);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_city;
    }

    @Override
    protected HotCityContract.HotCityPresenter createPresent() {
        return new HotCityContract.HotCityPresenter();
    }
    //返回数据处理
    @Override
    public void getHotCityResult(Response<HotCityResponse> response) {
        dismissLoadingDialog();
        if (("ok").equals(response.body().getHeWeather6().get(0).getStatus())) {
            //数据赋值
            if(response.body().getHeWeather6().get(0).getBasic()!=null && response.body().getHeWeather6().get(0).getBasic().size()>0){
                mList.clear();
                mList.addAll(response.body().getHeWeather6().get(0).getBasic());
                mAdapter.notifyDataSetChanged();
                runLayoutAnimation(rv);//刷新适配器
            }else {
                ToastUtils.showShortToast(context,"数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, response.body().getHeWeather6().get(0).getStatus());
        }
    }

    //异常返回
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "请求超时");
    }


}
