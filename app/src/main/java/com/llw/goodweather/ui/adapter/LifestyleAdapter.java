package com.llw.goodweather.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.llw.goodweather.db.bean.LifestyleResponse;
import com.llw.goodweather.databinding.ItemLifestyleRvBinding;

import java.util.List;

/**
 * 生活指数 数据适配器
 */
public class LifestyleAdapter extends RecyclerView.Adapter<LifestyleAdapter.ViewHolder> {

    private final List<LifestyleResponse.DailyBean> dailyBeans;

    public LifestyleAdapter(List<LifestyleResponse.DailyBean> dailyBeans) {
        this.dailyBeans = dailyBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLifestyleRvBinding binding = ItemLifestyleRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LifestyleResponse.DailyBean dailyBean = dailyBeans.get(position);
        holder.binding.tvLifestyle.setText(dailyBean.getName() + "：" + dailyBean.getText());
    }

    @Override
    public int getItemCount() {
        return dailyBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemLifestyleRvBinding binding;

        public ViewHolder(@NonNull ItemLifestyleRvBinding lifestyleRvBinding) {
            super(lifestyleRvBinding.getRoot());
            binding = lifestyleRvBinding;
        }
    }
}
