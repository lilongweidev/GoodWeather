package com.llw.goodweather.utils;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppStartUpUtils {
    /**
     * 判断是否是首次启动
     * @param context
     * @return
     */
    public static boolean isFirstStartApp(Context context) {
        Boolean isFirst = SPUtils.getBoolean(Constant.APP_FIRST_START, true, context);
        if (isFirst) {// 第一次
            SPUtils.putBoolean(Constant.APP_FIRST_START, false, context);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是今日首次启动APP
     * @param context
     * @return
     */
    public static boolean isTodayFirstStartApp(Context context) {

        String saveDate = SPUtils.getString(Constant.START_UP_APP_TIME, "2020-08-27", context);
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (!saveDate.equals(todayDate)) {//第一次打开
            SPUtils.putString(Constant.START_UP_APP_TIME, todayDate, context);
            return true;
        } else {
            return false;
        }

    }
}
