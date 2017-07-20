package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/2/22.
 */

public class LoggerUtils {
    public static final int LOG_DEBUG = 11;
    public static final int LOG_RELEASE = 0;
    //debug  | release
    private static int type = LOG_RELEASE;

    public static void e(String tag, String msg) {
        if (type >= Log.ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (type >= Log.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (type >= Log.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (type >= Log.VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static int getType() {
        return type;
    }


    /**
     * 设置log显示级别
     *
     * @param type LOG_DEBUG:全部显示 LOG_RELEASE:都不显示
     */
    public static void setType(int type) {
        LoggerUtils.type = type;
    }
}
