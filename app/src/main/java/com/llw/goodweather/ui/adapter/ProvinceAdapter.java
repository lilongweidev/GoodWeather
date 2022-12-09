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
 * 省/直辖市 数据适配器
 */
public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {

    private final List<Province> provinces;

    private AdministrativeClickCallback administrativeClickCallback;//视图点击

    public ProvinceAdapter(List<Province> provinces) {
        this.provinces = provinces;
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
                administrativeClickCallback.onAdministrativeItemClick(v, viewHolder.getAdapterPosition(), AdministrativeType.PROVINCE);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvText.setText(provinces.get(position).getProvinceName());
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemTextRvBinding binding;

        public ViewHolder(@NonNull ItemTextRvBinding itemTextRvBinding) {
            super(itemTextRvBinding.getRoot());
            binding = itemTextRvBinding;
        }
    }
}
