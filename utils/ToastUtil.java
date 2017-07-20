package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ToastUtil {
    static Toast mToast = null;

    public static void showToast(Context context, int Stringid) {
        showToast(context, context.getString(Stringid));
    }

    public static void showToast(Context context, String string) {
        try {
            if (null == mToast) {
                mToast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(string);
            }
            mToast.show();
        } catch (Exception e) {
            Log.e("FileManager: ", "show toast error");
        }
    }
}