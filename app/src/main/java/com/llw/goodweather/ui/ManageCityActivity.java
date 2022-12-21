package com.llw.goodweather.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsController;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.llw.goodweather.Constant;
import com.llw.goodweather.databinding.ActivityManageCityBinding;
import com.llw.goodweather.db.bean.MyCity;
import com.llw.goodweather.db.bean.Province;
import com.llw.goodweather.ui.adapter.MyCityAdapter;
import com.llw.goodweather.ui.adapter.OnClickItemCallback;
import com.llw.goodweather.viewmodel.ManageCityViewModel;
import com.llw.library.base.NetworkActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理城市
 */
public class ManageCityActivity extends NetworkActivity<ActivityManageCityBinding> {

    private ManageCityViewModel viewModel;
    private final List<MyCity> myCityList = new ArrayList<>();
    private final MyCityAdapter myCityAdapter = new MyCityAdapter(myCityList);

    @Override
    protected void onCreate() {
        initView();

        viewModel = new ViewModelProvider(this).get(ManageCityViewModel.class);
        viewModel.getAllCityData();
    }

    private void initView() {
        backAndFinish(binding.toolbar);
        setStatusBar(true);
        myCityAdapter.setOnClickItemCallback(position -> {
            Intent intent = new Intent();
            intent.putExtra(Constant.CITY_RESULT, myCityList.get(position).getCityName());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
        binding.rvCity.setLayoutManager(new LinearLayoutManager(ManageCityActivity.this));
        binding.rvCity.setAdapter(myCityAdapter);
        binding.btnAddCity.setOnClickListener(v -> showMsg("添加城市"));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onObserveData() {
        viewModel.listMutableLiveData.observe(this, myCities -> {
            if (myCities != null && myCities.size() > 0) {
                myCityList.clear();
                myCityList.addAll(myCities);
                myCityAdapter.notifyDataSetChanged();
            } else {
                showMsg("空空如也");
            }
        });
    }
}