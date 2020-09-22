package com.llw.goodweather.view.horizonview;

/**
 * 定义滑动监听接口
 * @author hefeng
 */
public interface ScrollWatched {
    void addWatcher(ScrollWatcher watcher);
    void removeWatcher(ScrollWatcher watcher);
    void notifyWatcher(int x);
}
