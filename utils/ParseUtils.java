package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/13.
 */
public class ParseUtils {
    public static int parseInt(String intstr) {
        int result = 0;
        try {
            result = Integer.parseInt(intstr);
        } catch (Exception e) {
            Log.e("cloudtag parse", "parseInt: " + intstr);
            result = 0;
        }
        return result;
    }

    public static float parseFloat(String intstr) {
        float result = 0;
        try {
            result = Float.parseFloat(intstr);
        } catch (Exception e) {
            Log.e("cloudtag parse", "parseInt: " + intstr);
            result = 0;
        }
        return result;
    }
    public static long parseLong(String intstr) {
        long result = 0;
        try {
            result = Long.parseLong(intstr);
        } catch (Exception e) {
            Log.e("cloudtag parse", "parseInt: " + intstr);
            result = 0;
        }
        return result;
    }
}
