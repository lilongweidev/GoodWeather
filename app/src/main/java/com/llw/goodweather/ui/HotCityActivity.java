package com.llw.goodweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.llw.goodweather.R;
import com.llw.goodweather.adapter.HotCityAdapter;
import com.llw.goodweather.bean.HotCityResponse;
import com.llw.goodweather.bean.NewHotCityResponse;
import com.llw.goodweather.contract.HotCityContract;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.llw.mvplibrary.utils.LiWindow;
import com.llw.mvplibrary.utils.SizeUtils;

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

    @BindView(R.id.tv_title)
    TextView tvTitle;//标题
    @BindView(R.id.lay_bg)
    LinearLayout layBg;//背景



    List<HotCityResponse.HeWeather6Bean.BasicBean> mList = new ArrayList<>();
    HotCityAdapter mAdapter;

    LiWindow liWindow;
    private int type = -1;//类型判断

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(context, R.color.white);//白色底  状态栏
        Back(toolbar);//返回

        handler.sendEmptyMessageDelayed(0, 200);//延时启动
    }

    /**
     * popupWindow显示依赖activity，并且要等activity所有的生命周期方法全部执行完成才能显示
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showTypeWindow();//显示
                    break;
            }
        }
    };


    private void initList(int type) {
        mAdapter = new HotCityAdapter(R.layout.item_hot_city_list, mList, type);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);
        //item 点击事件
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, HotCityWeatherActivity.class);
                intent.putExtra("location", mList.get(position).getLocation());
                startActivity(intent);
            }
        });

    }


    /**
     * 显示选择类型弹窗
     */
    private void showTypeWindow() {
        liWindow = new LiWindow(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.window_hot_type, null);
        TextView tvInland = view.findViewById(R.id.tv_inland);//国内
        TextView tvForeign = view.findViewById(R.id.tv_foreign);//海外
        tvInland.setOnClickListener(v -> {
            type = 0;
            initList(type);
            showLoadingDialog();
            mPresent.hotCity(context, "cn");
            liWindow.closePopupWindow();
        });
        tvForeign.setOnClickListener(v -> {
            type = 1;
            initList(type);
            showLoadingDialog();
            mPresent.hotCity(context, "overseas");
            liWindow.closePopupWindow();
        });

        liWindow.showCenterPopupWindow(view, SizeUtils.dp2px(context, 280), SizeUtils.dp2px(context, 120), false);

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
            toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.icon_return_white));//返回箭头颜色
            tvTitle.setTextColor(getResources().getColor(R.color.white));//标题颜色

            if (type == 0) {//标题判断
                tvTitle.setText("国内热门城市");
                StatusBarUtil.setStatusBarColor(context, R.color.blue_one);//蓝色底状态栏
                toolbar.setBackgroundColor(getResources().getColor(R.color.blue_one));//标题  蓝色
                layBg.setBackgroundColor(getResources().getColor(R.color.shallow_blue));//背景 蓝色
            } else {
                tvTitle.setText("海外热门城市");
                StatusBarUtil.setStatusBarColor(context, R.color.orange);//橙色底  状态栏
                toolbar.setBackgroundColor(getResources().getColor(R.color.orange));//标题  橙色
                layBg.setBackgroundColor(getResources().getColor(R.color.shallow_orange));//背景  橙色
            }


            //数据赋值
            if (response.body().getHeWeather6().get(0).getBasic() != null && response.body().getHeWeather6().get(0).getBasic().size() > 0) {
                mList.clear();
                mList.addAll(response.body().getHeWeather6().get(0).getBasic());
                mAdapter.notifyDataSetChanged();
                runLayoutAnimation(rv);//刷新适配器
            } else {
                ToastUtils.showShortToast(context, "数据为空");
            }
        } else {
            ToastUtils.showShortToast(context, response.body().getHeWeather6().get(0).getStatus());
        }
    }

    @Override
    public void getNewHotCityResult(Response<NewHotCityResponse> response) {

    }

    //异常返回
    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, "请求超时");
    }


}
