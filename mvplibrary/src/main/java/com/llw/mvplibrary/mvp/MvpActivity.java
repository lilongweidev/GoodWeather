package com.llw.mvplibrary.mvp;

import android.os.Bundle;

import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;

/**
 * 适用于需要访问网络接口的Activity
 *
 * @author llw
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
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
