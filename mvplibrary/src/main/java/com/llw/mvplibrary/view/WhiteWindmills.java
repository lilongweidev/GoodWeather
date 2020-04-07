package com.llw.mvplibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.llw.mvplibrary.R;

import java.lang.ref.WeakReference;

/**
 * 白色风车
 */
public class WhiteWindmills extends View {

    /**
     * 叶片的长度
     */
    private float mBladeRadius;
    /**
     * 风车叶片旋转中心x
     */
    private int mCenterY;
    /**
     * 风车叶片旋转中心y
     */
    private int mCenterX;
    /**
     * 风车旋转中心点圆的半径
     */
    private float mPivotRadius;
    private Paint mPaint = new Paint();
    /**
     * 风车旋转时叶片偏移的角度
     */
    private int mOffsetAngle;
    private Path mPath = new Path();
    /**
     * 风车支柱顶部和底部为了画椭圆的矩形
     */
    private RectF mRect = new RectF();
    /**
     * 控件的宽
     */
    private int mWid;
    /**
     * 控件高
     */
    private int mHei;
    /**
     * 控件颜色
     */
    private int mColor;
    private MsgHandler mHandler = new MsgHandler(this);

    public WhiteWindmills(Context context) {
        this(context, null);
    }

    public WhiteWindmills(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WhiteWindmills(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WhiteWindmills);
        if (array != null) {
            mColor = array.getColor(R.styleable.WhiteWindmills_windColor, Color.WHITE);

            array.recycle();
        }
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heiMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heiMode = MeasureSpec.getMode(heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int widMeasure = MeasureSpec.getSize(widthMeasureSpec);

        mWid = widMeasure;
        mHei = heiMeasure;
        mCenterY = mWid / 2;
        mCenterX = mWid / 2;

        mPivotRadius = (float) mWid / (float) 40;
        mBladeRadius = mCenterY - 2 * mPivotRadius;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画扇叶旋转的中心
        drawPivot(canvas);

        //画扇叶
        drawWindBlade(canvas);

        //画底部支柱
        drawPillar(canvas);
    }

    /**
     * 画风车支点
     *
     * @param canvas
     */
    private void drawPivot(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCenterX, mCenterY, mPivotRadius, mPaint);
    }

    /**
     * 画叶片
     *
     * @param canvas
     */
    private void drawWindBlade(Canvas canvas) {
        canvas.save();
        mPath.reset();
        //根据偏移量画初始时画布的位置
        canvas.rotate(mOffsetAngle, mCenterX, mCenterY);
        //画三角形扇叶
        mPath.moveTo(mCenterX, mCenterY - mPivotRadius);// 此点为多边形的起点
        mPath.lineTo(mCenterX, mCenterY - mPivotRadius - mBladeRadius);
        mPath.lineTo(mCenterX + mPivotRadius, mPivotRadius + mBladeRadius * (float) 2 / (float) 3);
        mPath.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(mPath, mPaint);

        //旋转画布120度，画第二个扇叶
        canvas.rotate(120, mCenterX, mCenterY);
        canvas.drawPath(mPath, mPaint);

        //旋转画布120度，画第三个扇叶
        canvas.rotate(120, mCenterX, mCenterY);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    /**
     * 画支柱
     *
     * @param canvas
     */
    private void drawPillar(Canvas canvas) {
        mPath.reset();
        //画上下半圆之间的柱形
        mPath.moveTo(mCenterX - mPivotRadius / 2, mCenterY + mPivotRadius + mPivotRadius / 2);
        mPath.lineTo(mCenterX + mPivotRadius / 2, mCenterY + mPivotRadius + mPivotRadius / 2);
        mPath.lineTo(mCenterX + mPivotRadius, mHei - 2 * mPivotRadius);
        mPath.lineTo(mCenterX - mPivotRadius, mHei - 2 * mPivotRadius);
        mPath.close();

        //画顶部半圆
        mRect.set(mCenterX - mPivotRadius / 2, mCenterY + mPivotRadius, mCenterX + mPivotRadius / 2, mCenterY + 2 * mPivotRadius);
        mPath.addArc(mRect, 180, 180);
        //画底部半圆
        mRect.set(mCenterX - mPivotRadius, mHei - 3 * mPivotRadius, mCenterX + mPivotRadius, mHei - mPivotRadius);
        mPath.addArc(mRect, 0, 180);

        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 开始旋转
     */
    public void startRotate() {
        stop();
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    /**
     * 停止旋转
     */
    public void stop() {
        mHandler.removeMessages(0);
    }

    static class MsgHandler extends Handler {
        private WeakReference<WhiteWindmills> mView;

        MsgHandler(WhiteWindmills view) {
            mView = new WeakReference<WhiteWindmills>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            WhiteWindmills view = mView.get();
            if (view != null) {
                view.handleMessage(msg);
            }
        }
    }

    private void handleMessage(Message msg) {
        if (mOffsetAngle >= 0 && mOffsetAngle < 360) {
            mOffsetAngle = mOffsetAngle + 1;
        } else {
            mOffsetAngle = 1;
        }
        invalidate();
        startRotate();
    }
}
