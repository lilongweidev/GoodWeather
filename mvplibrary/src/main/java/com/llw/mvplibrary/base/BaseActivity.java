package com.llw.mvplibrary.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.llw.mvplibrary.BaseApplication;
import com.llw.mvplibrary.R;
import com.llw.mvplibrary.kit.KnifeKit;
import butterknife.Unbinder;

/**
 * 用于不需要请求网络接口的Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements UiCallBack {
    private static final int FAST_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;
    protected Activity context;
    private Unbinder unbinder;
    private Dialog mDialog;//加载弹窗
    private AutoTransition autoTransition;//过渡动画
    private Animation bigShowAnim, smallHideAnim;
    protected int width;//屏幕宽度
    protected int height;//屏幕高度

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
        this.context = this;
        //添加继承这个BaseActivity的Activity
        BaseApplication.getActivityManager().addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }


        //获取屏幕宽高
        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;  //获取屏幕的宽度 像素
        height = metrics.heightPixels; //获取屏幕的高度 像素

        //放大
        bigShowAnim = AnimationUtils.loadAnimation(context, R.anim.scale_big_expand);
        //缩小
        smallHideAnim = AnimationUtils.loadAnimation(context, R.anim.scale_small_close);

        initData(savedInstanceState);


    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    //弹窗出现
    public void showLoadingDialog(){
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
    }
    //弹窗消失
    protected void dismissLoadingDialog(){
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    //返回
    protected void Back(Toolbar toolbar){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
                if(!isFastClick()) {
                    context.finish();
                }
            }
        });
    }

    // 两次点击间隔不能少于500ms
    protected static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME ) {
            flag = false;
        }
        lastClickTime = currentClickTime;

        return flag;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void beginDelayedTransition(ViewGroup view) {
        autoTransition = new AutoTransition();
        autoTransition.setDuration(500);
        TransitionManager.beginDelayedTransition(view,autoTransition);
    }

    //缩放动画
    protected void scaleAnimation(View view,String state) {


        switch (state){
            case "show":
                view.startAnimation(bigShowAnim);
                view.setVisibility(View.VISIBLE);
                break;
            case "hide":
                view.startAnimation(smallHideAnim);
                smallHideAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }

    }

    // dp 转成 px
    protected int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

    // px 转成 dp
    protected int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
