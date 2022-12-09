package com.llw.goodweather.utils;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Collections;
import java.util.Set;

/**
 * MMKV工具类
 */
public class MVUtils {

    private static MVUtils mInstance;
    private static MMKV mmkv;

    public MVUtils() {
        mmkv = MMKV.defaultMMKV();
    }

    public static MVUtils getInstance() {
        if (mInstance == null) {
            synchronized (MVUtils.class) {
                if (mInstance == null) {
                    mInstance = new MVUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 写入基本数据类型缓存
     *
     * @param key    键
     * @param object 值
     */
    public static void put(String key, Object object) {
        if (object instanceof String) {
            mmkv.encode(key, (String) object);
        } else if (object instanceof Integer) {
            mmkv.encode(key, (Integer) object);
        } else if (object instanceof Boolean) {
            mmkv.encode(key, (Boolean) object);
        } else if (object instanceof Float) {
            mmkv.encode(key, (Float) object);
        } else if (object instanceof Long) {
            mmkv.encode(key, (Long) object);
        } else if (object instanceof Double) {
            mmkv.encode(key, (Double) object);
        } else if (object instanceof byte[]) {
            mmkv.encode(key, (byte[]) object);
        } else {
            mmkv.encode(key, object.toString());
        }
    }

    public static void putSet(String key, Set<String> sets) {
        mmkv.encode(key, sets);
    }

    public static void putParcelable(String key, Parcelable obj) {
        mmkv.encode(key, obj);
    }

    public static Integer getInt(String key) {
        return mmkv.decodeInt(key, 0);
    }

    public static Integer getInt(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    public static Double getDouble(String key) {
        return mmkv.decodeDouble(key, 0.00);
    }

    public static Double getDouble(String key, double defaultValue) {
        return mmkv.decodeDouble(key, defaultValue);
    }

    public static Long getLong(String key) {
        return mmkv.decodeLong(key, 0L);
    }

    public static Long getLong(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        return mmkv.decodeBool(key, false);
    }

    public static Boolean getBoolean(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    public static Float getFloat(String key) {
        return mmkv.decodeFloat(key, 0F);
    }

    public static Float getFloat(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    public static byte[] getBytes(String key) {
        return mmkv.decodeBytes(key);
    }

    public static byte[] getBytes(String key, byte[] defaultValue) {
        return mmkv.decodeBytes(key, defaultValue);
    }

    public static String getString(String key) {
        return mmkv.decodeString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    public static Set<String> getStringSet(String key) {
        return mmkv.decodeStringSet(key, Collections.emptySet());
    }

    public static Parcelable getParcelable(String key) {
        return mmkv.decodeParcelable(key, null);
    }

    /**
     * 移除某个key对
     *
     * @param key
     */
    public static void removeKey(String key) {
        mmkv.removeValueForKey(key);
    }

    /**
     * 清除所有key
     */
    public static void clearAll() {
        mmkv.clearAll();
    }
}

