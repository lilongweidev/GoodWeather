package com.llw.mvplibrary.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.llw.mvplibrary.R;

public class LineProgressbar extends View {

    private Paint mPaint;//画笔

    private float mPaintWidth = 6f;//初始画笔宽度

    private int mProgressbarWidth;//控件外边框宽度

    private int mProgressbarHeight;//控件外边框高度

    private int mPercent = 0;//已转化为0至100范围的当前进度，随动画时间改变而改变

    public LineProgressbar(Context context) {
        super(context);
    }

    @SuppressLint("Recycle")
    public LineProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.LineProgressbar);
        mProgressbarWidth = (int) array.getDimension(R.styleable.LineProgressbar_progressbar_width, 100);
        mProgressbarHeight = (int) array.getDimension(R.styleable.LineProgressbar_progressbar_height, 10);
    }

    public LineProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mProgressbarWidth, mProgressbarHeight);
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();

        //绘制背景
        mPaint.setColor(getResources().getColor(R.color.arc_bg_color));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mPaintWidth);
        RectF frameRectF = new RectF(mPaintWidth, mPaintWidth, mProgressbarWidth - mPaintWidth, mProgressbarHeight - mPaintWidth);
        canvas.drawRoundRect(frameRectF, 15, 15, mPaint);

        //填充内部进度
        mPaint.setPathEffect(null);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        //内部进度填充长度，随动画时间改变而改变
        float percent = (float) mPercent / 100f;
        RectF progressRectF = new RectF(mPaintWidth, mPaintWidth, mPaintWidth + percent * (mProgressbarWidth - 2 * mPaintWidth - 2), mProgressbarHeight - mPaintWidth);
        canvas.drawRoundRect(progressRectF, 15, 15, mPaint);
    }

    public void setProgress(String progress, int maxProgress) {

        int percent = 0;

        //得出当前progress占最大进度值百分比（0-100）
        if (progress.contains(".")) {//float或者double类型
            percent = ((int) Float.parseFloat(progress) * 10) * 100 / (maxProgress * 10);
        } else {//int类型
            percent = Integer.parseInt(progress) * 100 / maxProgress;
        }

        if (percent < 0) {
            percent = 0;
        }
        if (percent > 100) {
            percent = 100;
        }

        //属性动画
        ValueAnimator animator = ValueAnimator.ofInt(0, percent);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mPercent = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }
}
