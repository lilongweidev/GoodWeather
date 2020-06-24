package com.llw.mvplibrary.utils;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.llw.mvplibrary.R;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义弹窗
 */
public class LiWindow {
    private LiWindow mLiWindow;
    private PopupWindow mPopupWindow;
    private LayoutInflater inflater;
    private View mView;
    private Context mContext;
    private WindowManager show;
    WindowManager.LayoutParams context;
    private Map<String,Object> mMap = new HashMap<>();

    public Map<String, Object> getmMap() {
        return mMap;
    }


    private float bgAlpha = 1f;
    private boolean bright = false;

    private static final long DURATION = 500;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;


    public LiWindow(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        mLiWindow = this;
    }

    public LiWindow(Context context, Map<String,Object> map){
        this.mContext = context;
        this.mMap = map;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 右侧显示  自适应大小
     * @param mView
     */
    public void showRightPopupWindow(View mView) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT , true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗  true为关闭
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.AnimationRightFade); //设置动画
        mPopupWindow.showAtLocation(mView, Gravity.RIGHT,0 ,0);
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }

    /**
     * 右侧显示  高度占满父布局
     * @param mView
     */
    public void showRightPopupWindowMatchParent(View mView) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT , true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗  true为关闭
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.AnimationRightFade); //设置动画
        mPopupWindow.showAtLocation(mView, Gravity.RIGHT,0 ,0);
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }


    /**
     * 底部显示
     * @param mView
     */
    public void showBottomPopupWindow(View mView,PopupWindow.OnDismissListener onDismissListener) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗  true为关闭
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.AnimationBottomFade); //设置动画
        mPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(onDismissListener);
    }

    /**
     * 中间显示
     * @param mView
     */
    public void showCenterPopupWindow(View mView,int width,int height,boolean focusable) {
        mPopupWindow = new PopupWindow(mView,
                width, height, focusable);
        mPopupWindow.setContentView(mView);
        mPopupWindow.setAnimationStyle(R.style.AnimationCenterFade); //设置动画
        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);
        mPopupWindow.update();
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }


    public static void setBackgroundAlpha(float bgAlpha,Context mContext){
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }


    /**
     * 设置弹窗动画
     * @param animId
     * @return showPopu
     */
    public LiWindow setAnim(int animId) {
        if (mPopupWindow != null) {
            mPopupWindow.setAnimationStyle(animId);
        }
        return mLiWindow;
    }

    //弹窗消失时关闭阴影
    public PopupWindow.OnDismissListener closeDismiss = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            WindowManager.LayoutParams nomal = ((Activity)mContext).getWindow().getAttributes();
            nomal.alpha = 1f;
            ((Activity)mContext).getWindow().setAttributes(nomal);
        }
    };



    public void closePopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }



    /*
        使用方法
    *   LiWindow liWindow = new LiWindow(MainActivity.this);
        View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.center_layout,null);
        liWindow.showCenterPopupWindow(mView);
    * */
}
