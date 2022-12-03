package com.llw.library.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ViewBinding基类
 *
 * @param <VB>
 */
public abstract class BaseVBActivity<VB extends ViewBinding> extends BaseActivity {

    protected VB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onRegister();
        super.onCreate(savedInstanceState);
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            try {
                Class<VB> clazz = (Class<VB>) ((ParameterizedType) type).getActualTypeArguments()[0];
                //反射
                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                binding = (VB) method.invoke(null, getLayoutInflater());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setContentView(binding.getRoot());
        }
        initData();
    }

    protected void onRegister() {

    }

    protected abstract void initData();
}
