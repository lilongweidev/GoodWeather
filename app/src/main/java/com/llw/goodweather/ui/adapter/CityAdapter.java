package com.llw.goodweather.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.databinding.ItemTextRvBinding;
import com.llw.goodweather.db.bean.Province;
import com.llw.goodweather.utils.AdministrativeType;

import java.util.List;

/**
 * 市数据适配器
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private final List<Province.City> cities;

    private AdministrativeClickCallback administrativeClickCallback;//视图点击

    public CityAdapter(List<Province.City> cities) {
        this.cities = cities;
    }

    public void setAdministrativeClickCallback(AdministrativeClickCallback administrativeClickCallback) {
        this.administrativeClickCallback = administrativeClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTextRvBinding binding = ItemTextRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        //添加视图点击事件
        binding.getRoot().setOnClickListener(v -> {
            if (administrativeClickCallback != null) {
                administrativeClickCallback.onAdministrativeItemClick(v, viewHolder.getAdapterPosition(), AdministrativeType.CITY);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvText.setText(cities.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemTextRvBinding binding;

        public ViewHolder(@NonNull ItemTextRvBinding itemTextRvBinding) {
            super(itemTextRvBinding.getRoot());
            binding = itemTextRvBinding;
        }
    }
}
