package com.llw.mvplibrary.base.vb;

import android.os.Bundle;

/**
 * UI回调接口
 */
public interface UiVBCallback {

    default void onRegister(){}

    //初始化savedInstanceState
    default void initBeforeView(Bundle savedInstanceState) {}

    //初始化
    void initData();
}
