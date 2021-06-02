package com.llw.mvplibrary.base;


import java.lang.ref.WeakReference;

/**
 * Presenter基类 操作视图View
 * @param <V>
 */
public class BasePresenter<V extends BaseView> {
    private WeakReference<V> mWeakReference;


    /**
     * 关联view
     * @param v
     */
    public void attach(V v){
        mWeakReference=new WeakReference<V>(v);
    }

    /**
     * 分离view
     * @param v
     */
    public void detach(V v){
        if (mWeakReference!=null){
            mWeakReference.clear();
            mWeakReference=null;
        }
    }

    /**
     * 获取view
     * @return
     */
    public V getView(){
        if (mWeakReference!=null){
            return mWeakReference.get();
        }
        return null;
    }

}
