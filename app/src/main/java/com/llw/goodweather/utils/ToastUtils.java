package com.llw.goodweather.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 消息提示工具类
 *
 * @author llw
 */
public class ToastUtils {

    /**
     * 长消息
     *
     * @param context 上下文参数
     * @param llw     内容
     */
    public static void showLongToast(Context context, CharSequence llw) {
        Toast.makeText(context.getApplicationContext(), llw, Toast.LENGTH_LONG).show();
    }

    /**
     * 短消息
     *
     * @param context 上下文参数
     * @param llw     内容
     */
    public static void showShortToast(Context context, CharSequence llw) {
        Toast.makeText(context.getApplicationContext(), llw, Toast.LENGTH_SHORT).show();
    }
}
