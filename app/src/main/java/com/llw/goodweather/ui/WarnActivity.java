package com.llw.goodweather.ui;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.llw.goodweather.R;
import com.llw.goodweather.bean.WarningResponse;
import com.llw.goodweather.utils.DateUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.WeatherUtil;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.utils.RecyclerViewAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.llw.mvplibrary.utils.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 灾害预警详情信息页面
 *
 * @author llw
 */
public class WarnActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    public void initData(Bundle savedInstanceState) {
        //透明状态栏
        StatusBarUtil.transparencyBar(context);
        Back(toolbar);
        WarningResponse data = new Gson().fromJson(getIntent().getStringExtra("warnBodyString"), WarningResponse.class);
        WarnAdapter mAdapter = new WarnAdapter(R.layout.item_warn_list, data.getWarning());
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        runLayoutAnimation(rv);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_warn;
    }

    /**
     * 内部适配器
     */
    public class WarnAdapter extends BaseQuickAdapter<WarningResponse.WarningBean, BaseViewHolder> {

        public WarnAdapter(int layoutResId, @Nullable List<WarningResponse.WarningBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WarningResponse.WarningBean item) {

            TextView tvTime = helper.getView(R.id.tv_time);
            String time = DateUtils.updateTime(item.getPubTime());
            tvTime.setText("预警发布时间：" + WeatherUtil.showTimeInfo(time) + time);

            helper.setText(R.id.tv_city, item.getSender())//地区
                    .setText(R.id.tv_type_name_and_level,
                            item.getTypeName() + item.getLevel() + "预警")//预警类型名称和等级
                    .setText(R.id.tv_content, item.getText());//预警详情内容

        }
    }
}
