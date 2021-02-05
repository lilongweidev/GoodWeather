package com.llw.mvplibrary.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.TextView;

import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

/**
 * 动画工具类
 * UpdateListener： 动画过程中通过添加此监听来回调数据
 * EndListener： 动画结束的时候通过此监听器来做一些处理
 * @author llw
 */
public class AnimationUtil {
    private ValueAnimator valueAnimator;
    private UpdateListener updateListener;
    private EndListener endListener;
    private long duration;
    private float start;
    private float end;
    private Interpolator interpolator = new LinearInterpolator();

    public AnimationUtil() {
        duration = 1000; //默认动画时常1s
        start = 0.0f;
        end = 1.0f;
        interpolator = new LinearInterpolator();// 匀速的插值器
    }


    public void setDuration(int timeLength) {
        duration = timeLength;
    }

    public void setValueAnimator(float start, float end, long duration) {

        this.start = start;
        this.end = end;
        this.duration = duration;

    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void startAnimator() {
        if (valueAnimator != null){
            valueAnimator = null;
        }
        valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (updateListener == null) {
                    return;
                }

                float cur = (float) valueAnimator.getAnimatedValue();
                updateListener.progress(cur);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}
            @Override
            public void onAnimationEnd(Animator animator) {
                if(endListener == null){
                    return;
                }
                endListener.endUpdate(animator);
            }
            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
        valueAnimator.start();
    }

    public void addUpdateListener(UpdateListener updateListener) {

        this.updateListener = updateListener;
    }

    public void addEndListner(EndListener endListener){
        this.endListener = endListener;
    }

    public interface EndListener {
        void endUpdate(Animator animator);
    }

    public interface UpdateListener {

        void progress(float progress);
    }

    /**
     * 展开动画
     * @param view 需要展开的View
     * @param textView 修改文本
     */
    public static void expand(final View view, final TextView textView) {
        //视图测量 传入容器的宽高测量模式
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获取视图的测量高度
        final int viewHeight = view.getMeasuredHeight();
        //设置布局参数高度
        view.getLayoutParams().height = 0;
        //视图显示
        view.setVisibility(View.VISIBLE);
        textView.setText("收起详情");

        Animation animation = new Animation() {
            /**
             * 重写动画更新函数
             * @param interpolatedTime 补插时间 计算动画进度
             * @param t
             */
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    //动画已完成
                    view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                } else {
                    //正在进行中
                    view.getLayoutParams().height = (int) (viewHeight * interpolatedTime);
                }
                view.requestLayout();
            }
        };
        animation.setDuration(600);
        //设置插值器，即动画改变速度
        animation.setInterpolator(new LinearOutSlowInInterpolator());
        view.startAnimation(animation);
    }

    /**
     * 收缩动画
     * @param view 需要收缩的View
     * @param textView 修改文本
     */
    public static void collapse(final View view,final TextView textView) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int viewHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                    textView.setText("查看详情");
                } else {
                    view.getLayoutParams().height = viewHeight - (int) (viewHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
        };
        animation.setDuration(600);
        animation.setInterpolator(new LinearOutSlowInInterpolator());
        view.startAnimation(animation);
    }

}
