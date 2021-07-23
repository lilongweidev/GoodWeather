package com.llw.mvplibrary.newnet.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 自定义Observer
 *
 * @author llw
 */
public abstract class BaseObserver<T> implements Observer<T> {

    //开始
    @Override
    public void onSubscribe(Disposable d) {

    }

    //继续
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    //异常
    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    //完成
    @Override
    public void onComplete() {

    }

    //成功
    public abstract void onSuccess(T t);

    //失败
    public abstract void onFailure(Throwable e);
}
