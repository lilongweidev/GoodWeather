package com.llw.goodweather.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.Constant;
import com.llw.goodweather.databinding.ItemMyCityRvBinding;
import com.llw.goodweather.databinding.ItemRecommendCityBinding;
import com.llw.goodweather.db.bean.MyCity;
import com.llw.goodweather.utils.MVUtils;

import java.util.List;

/**
 * 推荐城市数据适配器
 */
public class RecommendCityAdapter extends RecyclerView.Adapter<RecommendCityAdapter.ViewHolder> {

    private final List<String> cities;

    private OnClickItemCallback onClickItemCallback;//视图点击

    public RecommendCityAdapter(List<String> cities) {
        this.cities = cities;
    }

    public void setOnClickItemCallback(OnClickItemCallback onClickItemCallback) {
        this.onClickItemCallback = onClickItemCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecommendCityBinding binding = ItemRecommendCityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        //添加视图点击事件
        binding.getRoot().setOnClickListener(v -> {
            if (onClickItemCallback != null) {
                onClickItemCallback.onItemClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvRecommendCityName.setText(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemRecommendCityBinding binding;

        public ViewHolder(@NonNull ItemRecommendCityBinding itemRecommendCityBinding) {
            super(itemRecommendCityBinding.getRoot());
            binding = itemRecommendCityBinding;
        }
    }
}
