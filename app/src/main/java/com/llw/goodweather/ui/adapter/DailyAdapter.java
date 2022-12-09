package com.llw.goodweather.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.db.bean.DailyResponse;
import com.llw.goodweather.databinding.ItemDailyRvBinding;
import com.llw.goodweather.utils.EasyDate;
import com.llw.goodweather.utils.WeatherUtil;

import java.util.List;

/**
 * 天气预报 数据适配器
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    private final List<DailyResponse.DailyBean> dailyBeans;

    public DailyAdapter(List<DailyResponse.DailyBean> dailyBeans) {
        this.dailyBeans = dailyBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDailyRvBinding binding = ItemDailyRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyResponse.DailyBean dailyBean = dailyBeans.get(position);
        holder.binding.tvDate.setText(EasyDate.dateSplit(dailyBean.getFxDate()) + EasyDate.getDayInfo(dailyBean.getFxDate()));
        WeatherUtil.changeIcon(holder.binding.ivStatus, Integer.parseInt(dailyBean.getIconDay()));
        holder.binding.tvHeight.setText(dailyBean.getTempMax() + "℃");
        holder.binding.tvLow.setText(" / " + dailyBean.getTempMin() + "℃");
    }

    @Override
    public int getItemCount() {
        return dailyBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemDailyRvBinding binding;

        public ViewHolder(@NonNull ItemDailyRvBinding itemTextRvBinding) {
            super(itemTextRvBinding.getRoot());
            binding = itemTextRvBinding;
        }
    }
}
