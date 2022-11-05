package com.llw.mvplibrary.mvp;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.vb.BaseVBActivity;
import com.llw.mvplibrary.base.BaseView;

/**
 * 适用于需要访问网络接口的Activity
 *
 * @author llw
 */

public abstract class MvpVBActivity<T extends ViewBinding, P extends BasePresenter> extends BaseVBActivity<T> {

    protected P mPresent;

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        mPresent = createPresent();
        mPresent.attach((BaseView) this);
    }

    protected abstract P createPresent();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresent.detach((BaseView) this);
    }

}
