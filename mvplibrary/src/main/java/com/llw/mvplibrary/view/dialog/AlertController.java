package com.llw.mvplibrary.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 弹窗控制
 */
public class AlertController {
    private AlertDialog mAlertDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(AlertDialog alertDialog, Window window) {
        mAlertDialog = alertDialog;
        mWindow = window;
    }

    public void setDialogViewHelper(DialogViewHelper dialogViewHelper) {
        mViewHelper = dialogViewHelper;
    }

    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }

    public void setIcon(int viewId, int resId) {
        mViewHelper.setIcon(viewId, resId);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }


    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        mViewHelper.setOnClickListener(viewId, onClickListener);
    }

    public AlertDialog getDialog() {
        return mAlertDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

//-------------------------------------------------------------------------------------------------


    public static class AlertParams {

        public Context mContext;
        //对话框主题背景
        public int mThemeResId;

        public boolean mCancelable;

        public DialogInterface.OnCancelListener mOnCancelListener;

        public DialogInterface.OnDismissListener mOnDismissListener;

        public DialogInterface.OnKeyListener mOnKeyListener;
        //文本颜色
        public SparseArray<Integer> mTextColorArray = new SparseArray<>();

        //存放文本的更改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        //存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        //存放长按点击事件
        public SparseArray<View.OnLongClickListener> mLondClickArray = new SparseArray<>();
        //存放对话框图标
        public SparseArray<Integer> mIconArray = new SparseArray<>();
        //存放对话框图片
        public SparseArray<Bitmap> mBitmapArray = new SparseArray<>();
        //对话框布局资源id
        public int mLayoutResId;
        //对话框的view
        public View mView;
        //对话框宽度
        public int mWidth;
        //对话框高度
        public int mHeight;
        //对话框垂直外边距
        public int mHeightMargin;
        //对话框横向外边距
        public int mWidthMargin;
        //动画
        public int mAnimation;
        //对话框显示位置
        public int mGravity = Gravity.CENTER;


        public AlertParams(Context context, int themeResId) {
            mContext = context;
            mThemeResId = themeResId;
        }

        public void apply(AlertController alert) {
            //设置对话框布局
            DialogViewHelper dialogViewHelper = null;
            if (mLayoutResId != 0) {
                dialogViewHelper = new DialogViewHelper(mContext, mLayoutResId);
            }
            if (mView != null) {
                dialogViewHelper = new DialogViewHelper();
                dialogViewHelper.setContentView(mView);
            }
            if (dialogViewHelper == null) {
                throw new IllegalArgumentException("please set layout");
            }
            //将对话框布局设置到对话框

            alert.getDialog().setContentView(dialogViewHelper.getContentView());

            //设置DialogViewHelper辅助类
            alert.setDialogViewHelper(dialogViewHelper);
            //设置文本
            for (int i = 0; i < mTextArray.size(); i++) {
                alert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }
            //设置图标
            for (int i = 0; i < mIconArray.size(); i++) {
                alert.setIcon(mIconArray.keyAt(i), mIconArray.valueAt(i));
            }
            //设置点击
            for (int i = 0; i < mClickArray.size(); i++) {
                alert.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }
            //配置自定义效果，底部弹出，宽高，动画，全屏
            Window window = alert.getWindow();
            window.setGravity(mGravity);//显示位置

            if (mAnimation != 0) {
                window.setWindowAnimations(mAnimation);//设置动画
            }
            //设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            params.verticalMargin = mHeightMargin;
            params.horizontalMargin = mWidthMargin;
            window.setAttributes(params);
        }
    }
}
