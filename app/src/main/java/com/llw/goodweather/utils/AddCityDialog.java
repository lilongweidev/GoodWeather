package com.llw.goodweather.utils;

import android.content.Context;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.llw.goodweather.databinding.DialogAddCityBinding;
import com.llw.goodweather.ui.adapter.RecommendCityAdapter;
import java.util.List;

/**
 * 添加城市弹窗
 */
public class AddCityDialog {

    /**
     * 显示弹窗
     */
    public static void show(Context context, List<String> cities, SelectedCityCallback selectedCityCallback) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        DialogAddCityBinding binding = DialogAddCityBinding.inflate(LayoutInflater.from(context), null, false);
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
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    public interface SelectedCityCallback {
        void selectedCity(String cityName);
    }
}
