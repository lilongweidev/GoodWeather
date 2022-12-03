package com.llw.library.base;

import androidx.viewbinding.ViewBinding;

/**
 * 访问网络的Activity
 *
 * @param <VB>
 */
public abstract class NetworkActivity<VB extends ViewBinding> extends BaseVBActivity<VB> {

    @Override
    public void initData() {
        onCreate();
        onObserveData();
    }

    protected abstract void onCreate();

    protected abstract void onObserveData();
}
