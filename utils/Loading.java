package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.haierac.biz.cp.cloudplatformandroid.R;

/**
 * Loading框 显示/关闭， 半透明
 *
 * @author Eric
 */
public class Loading {

    private static Dialog dlgLoading;

    public static void show(Context context) {
        show(context, "");
    }

    public static void show(Context context, DialogInterface.OnCancelListener onCancelListener) {
        show(context, "", onCancelListener);
    }

    public static void show(Context context, String message) {
        show(context, message, null);
    }

    public static void show(Context context, String message, DialogInterface.OnCancelListener onCancelListener) {
        close();
        View view = LayoutInflater.from(context).inflate(R.layout.loading, null);
        TextView textViewMsg = (TextView) view.findViewById(R.id.textview_loading);
        if (!TextUtils.isEmpty(message) && textViewMsg != null) {
            textViewMsg.setText(message);
        }
//        textViewMsg.setText(message + context.getClass().getSimpleName());
        dlgLoading = new Dialog(context, R.style.dialog);
        dlgLoading.setContentView(view);
        dlgLoading.setCancelable(true);
        dlgLoading.setCanceledOnTouchOutside(false);
        if (onCancelListener != null) {
            dlgLoading.setOnCancelListener(onCancelListener);
        }
        dlgLoading.show();
    }


    public static void close() {
        if (dlgLoading != null) {
            Log.e("loading", dlgLoading.getContext().getClass().getSimpleName() + "," + Thread.currentThread().getName());
            try {
                dlgLoading.dismiss();
            } catch (Exception e) {
            }
            dlgLoading = null;
        }
    }

    public static boolean isShowing() {
        return dlgLoading != null && dlgLoading.isShowing();
    }
}
