package com.llw.goodweather.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.llw.goodweather.databinding.DialogAddCityBinding;
import com.llw.goodweather.db.bean.SearchCityResponse;
import com.llw.goodweather.repository.SearchCityRepository;
import com.llw.goodweather.ui.adapter.OnClickItemCallback;
import com.llw.goodweather.ui.adapter.RecommendCityAdapter;
import com.llw.goodweather.ui.adapter.SearchCityAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 添加城市弹窗
 */
public class AddCityDialog {

    private static final MutableLiveData<SearchCityResponse> searchCityResponseMutableLiveData = new MutableLiveData<>();

    private static final MutableLiveData<String> failed = new MutableLiveData<>();

    private static final List<SearchCityResponse.LocationBean> beans = new ArrayList<>();

    private static final SearchCityAdapter searchCityAdapter = new SearchCityAdapter(beans);

    /**
     * 显示弹窗
     */
    public static void show(Context context, List<String> cities, SelectedCityCallback selectedCityCallback) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        DialogAddCityBinding binding = DialogAddCityBinding.inflate(LayoutInflater.from(context), null, false);
        //搜索城市适配器Item点击监听
        searchCityAdapter.setOnClickItemCallback(position -> {
            if (selectedCityCallback != null) {
                selectedCityCallback.selectedCity(beans.get(position).getName());
                dialog.dismiss();
            }
        });
        binding.rvSearchCity.setLayoutManager(new LinearLayoutManager(context));
        binding.rvSearchCity.setAdapter(searchCityAdapter);

        //输入框监听
        binding.etSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.ivClear.setVisibility(s.toString().isEmpty() ? View.INVISIBLE : View.VISIBLE);
            }
        });
        //输入框焦点监听
        binding.etSearchCity.setOnFocusChangeListener((v, hasFocus) ->
                BottomSheetBehavior.from(Objects.requireNonNull(dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)))
                        .setState(hasFocus ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED));
        //输入法的回车按钮监听
        binding.etSearchCity.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String cityName = binding.etSearchCity.getText().toString().trim();
                if (cityName.isEmpty()) {
                    Toast.makeText(context, "请输入城市名称", Toast.LENGTH_SHORT).show();
                    return false;
                }
                searchCityAdapter.changTxColor(cityName);
                BottomSheetBehavior.from(Objects.requireNonNull(dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)))
                        .setState(BottomSheetBehavior.STATE_EXPANDED);
                //搜索城市
                SearchCityRepository.getInstance().searchCity(searchCityResponseMutableLiveData, failed, cityName);
                //关闭输入法
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                // 关闭屏幕上的输入法软键盘
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return false;
        });
        //清除输入框内容按钮
        binding.ivClear.setOnClickListener(v -> {
            binding.etSearchCity.setText("");
            binding.rvRecommendCity.setVisibility(View.VISIBLE);
            binding.rvSearchCity.setVisibility(View.GONE);
        });
        //搜索城市返回
        searchCityResponseMutableLiveData.observe((LifecycleOwner) context, searchCityResponse -> {
            //有数据就隐藏推荐城市列表，否则就提示没有找到对应的城市数据
            if (searchCityResponse.getLocation() == null || searchCityResponse.getLocation().size() <= 0) {
                binding.rvRecommendCity.setVisibility(View.VISIBLE);
                binding.rvSearchCity.setVisibility(View.GONE);
                Toast.makeText(context, "未找到相应城市，请重新搜索。", Toast.LENGTH_SHORT).show();
            } else {
                binding.rvRecommendCity.setVisibility(View.GONE);
                binding.rvSearchCity.setVisibility(View.VISIBLE);
                beans.clear();
                beans.addAll(searchCityResponse.getLocation());
                searchCityAdapter.notifyDataSetChanged();
            }
        });

        RecommendCityAdapter recommendCityAdapter = new RecommendCityAdapter(cities);
        recommendCityAdapter.setOnClickItemCallback(position -> {
            if (selectedCityCallback != null) {
                selectedCityCallback.selectedCity(cities.get(position));
                dialog.dismiss();
            }
        });
        //网格布局管理器 一行3个
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        binding.rvRecommendCity.setLayoutManager(manager);
        binding.rvRecommendCity.setAdapter(recommendCityAdapter);
        binding.toolbar.setNavigationOnClickListener(v -> dialog.dismiss());

        binding.rvRecommendCity.setVisibility(View.VISIBLE);
        binding.rvSearchCity.setVisibility(View.GONE);
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    public interface SelectedCityCallback {
        void selectedCity(String cityName);
    }
}
