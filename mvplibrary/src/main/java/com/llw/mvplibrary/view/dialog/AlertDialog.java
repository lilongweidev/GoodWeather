package com.llw.mvplibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.llw.mvplibrary.R;

/**
 * 自定义弹窗
 */

public class AlertDialog extends Dialog {

    private AlertController mAlert;

    public AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        mAlert.setOnClickListener(viewId, onClickListener);
    }


//----------------------------------------------------------------------------------------------

    public static class Builder {
        private final AlertController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.dialog);
        }


        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }

        /**
         * 设置对话框布局
         *
         * @param view
         * @return
         */
        public Builder setContentView(View view) {
            P.mView = view;
            P.mLayoutResId = 0;
            return this;
        }

        /**
         * @param layoutId
         * @return
         */
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mLayoutResId = layoutId;
            return this;
        }

        /**
         * 设置文本
         *
         * @param viewId
         * @param text
         * @return
         */
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        /**
         * 设置文本颜色
         *
         * @param viewId
         * @param color
         * @return
         */
        public Builder setTextColor(int viewId, int color) {
            P.mTextColorArray.put(viewId, color);
            return this;
        }

        /**
         * 设置图标
         *
         * @param iconId
         * @return
         */
        public Builder setIcon(int iconId, int resId) {
            P.mIconArray.put(iconId, resId);
            return this;
        }

        /**
         * 设置图片
         *
         * @param viewId
         * @return
         */
        public Builder setBitmap(int viewId, Bitmap bitmap) {
            P.mBitmapArray.put(viewId, bitmap);
            return this;
        }

        /**
         * 设置对话框宽度占满屏幕
         *
         * @return
         */
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 对话框底部弹出
         *
         * @param isAnimation
         * @return
         */
        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 对话框右部弹出
         *
         * @param isAnimation
         * @return
         */
        public Builder fromRight(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimation = R.style.dialog_scale_anim;
            }
            P.mGravity = Gravity.RIGHT;
            return this;
        }


        /**
         * 设置对话框宽高
         *
         * @param width
         * @param heigth
         * @return
         */
        public Builder setWidthAndHeight(int width, int heigth) {
            P.mWidth = width;
            P.mHeight = heigth;
            return this;
        }

        /**
         * 设置对话框宽高
         *
         * @param width
         * @param heigth
         * @return
         */
        public Builder setWidthAndHeightMargin(int width, int heigth, int heightMargin, int widthMargin) {
            P.mWidth = width;
            P.mHeight = heigth;
            P.mHeightMargin = heightMargin;
            P.mWidthMargin = widthMargin;
            return this;
        }

        /**
         * 添加默认动画
         *
         * @return
         */
        public Builder addDefaultAnimation() {
            P.mAnimation = R.style.dialog_scale_anim;
            return this;
        }

        /**
         * 设置动画
         *
         * @param styleAnimation
         * @return
         */
        public Builder setAnimation(int styleAnimation) {
            P.mAnimation = styleAnimation;
            return this;
        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param onClickListener
         * @return
         */
        public Builder setOnClickListener(int viewId, View.OnClickListener onClickListener) {
            P.mClickArray.put(viewId, onClickListener);
            return this;
        }

        public Builder setOnLongClickListener(int viewId, View.OnLongClickListener onLongClickListener) {
            P.mLondClickArray.put(viewId, onLongClickListener);
            return this;
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }


        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }


        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }


        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        public AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
