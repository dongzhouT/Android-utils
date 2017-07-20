package com.haierac.biz.cp.cloudplatformandroid.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.haierac.biz.cp.cloudplatformandroid.R;
import com.haierac.biz.cp.cloudplatformandroid.activity.MainActivity_;

/**
 * Description:显示指定组件的对话框,并跳转至指定的Activity
 *
 * @author maoyun0903@163.com
 * @version 1.0
 */
public class DialogUtil {


    public static void showDialog(final Context ctx
            , String msg, boolean goHome) {
        // 创建一个AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
        if (goHome) {
            builder.setPositiveButton("确定", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(ctx, MainActivity_.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    ctx.startActivity(i);
                }
            });
        } else {
            builder.setPositiveButton("确定", null);
        }
        builder.create().show();
    }

    public static void showDialog(Context ctx
            , String msg, String positiveMsg, OnClickListener positiveListener
            , String negativeMsg, OnClickListener negativeListener
    ) {
        // 创建一个AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
        if (!TextUtils.isEmpty(positiveMsg)) {
            builder.setPositiveButton(positiveMsg, positiveListener);
        }
        if (!TextUtils.isEmpty(negativeMsg)) {
            builder.setNegativeButton(negativeMsg, negativeListener);
        }
        builder.create().show();
    }

    // 定义一个显示指定组件的对话框
    public static void showDialog(Context ctx, View view) {
        new AlertDialog.Builder(ctx)
                .setView(view).setCancelable(false)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }

    public static void showDialog(Context ctx,
                                  View view, String msg, String positiveMsg, OnClickListener positiveListener
            , String negativeMsg, OnClickListener negativeListener) {
        // 创建一个AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setView(view)
                .setMessage(msg)
                .setCancelable(false);
        if (!TextUtils.isEmpty(positiveMsg)) {
            builder.setPositiveButton(positiveMsg, positiveListener);
        }
        if (!TextUtils.isEmpty(negativeMsg)) {
            builder.setNegativeButton(negativeMsg, negativeListener);
        }
        builder.create().show();

    }

    public static Dialog createTwoBtnConfirmDialog(Context context,
                                                   CharSequence messageStr, String leftBtnText,
                                                   String rightBtnText, View.OnClickListener leftBtnListener,
                                                   View.OnClickListener rightBtnListener) {
        return createTwoBtnConfirmDialog(context, -1, messageStr, leftBtnText, rightBtnText, leftBtnListener, rightBtnListener);
    }

    public static Dialog createTwoBtnConfirmDialog(Context context,
                                                   int imgRes, CharSequence messageStr, String leftBtnText,
                                                   String rightBtnText, View.OnClickListener leftBtnListener,
                                                   View.OnClickListener rightBtnListener) {
        Dialog dialog = new Dialog(context, R.style.dialog);
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.dialog_img);
        TextView textViewMessage = (TextView) rootView.findViewById(R.id.dialog_message);
        TextView textViewLeftBtn = (TextView) rootView.findViewById(R.id.dialog_left);
        TextView textViewRightBtn = (TextView) rootView.findViewById(R.id.dialog_right);
        if (imgRes == -1) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(imgRes);
        }
        textViewMessage.setText(messageStr);
        textViewLeftBtn.setText(leftBtnText);
        textViewRightBtn.setText(rightBtnText);
        textViewLeftBtn.setOnClickListener(leftBtnListener);
        textViewRightBtn.setOnClickListener(rightBtnListener);
        dialog.setContentView(rootView);
        return dialog;
    }

    /**
     * 创建一个确定按钮的弹窗
     *
     * @param context     上下文
     * @param messageStr  提示内容
     * @param btnListener 按键动作，可以为null
     * @return
     */
    public static Dialog createOneBtnConfirmDialog(Context context,
                                                   CharSequence messageStr,
                                                   View.OnClickListener btnListener
    ) {
        return createOneBtnConfirmDialog(context, -1,
                messageStr, context.getString(R.string.string_confirm_one_ok), btnListener);
    }

    public static Dialog createOneBtnConfirmDialog(Context context,
                                                   CharSequence messageStr,
                                                   String btnText, View.OnClickListener btnListener
    ) {
        return createOneBtnConfirmDialog(context, -1, messageStr, btnText, btnListener);
    }

    public static Dialog createOneBtnConfirmDialog(Context context,
                                                   int imgRes, CharSequence messageStr,
                                                   String btnText, View.OnClickListener btnListener
    ) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_onebtn, null);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.dialog_img);
        TextView textViewMessage = (TextView) rootView.findViewById(R.id.dialog_message);
        TextView textViewBtn = (TextView) rootView.findViewById(R.id.dialog_btn);
        if (imgRes == -1) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(imgRes);
        }
        textViewMessage.setText(messageStr);
        textViewBtn.setText(btnText);
        if (btnListener == null) {
            textViewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        } else {
            textViewBtn.setOnClickListener(btnListener);
        }

        dialog.setContentView(rootView);
        return dialog;
    }

    public static ProgressDialog buildProgressDialog(Context context, String msg, final Runnable cancelRunnable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        if (cancelRunnable != null)
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    cancelRunnable.run();
                }
            });
        return progressDialog;
    }

    /**
     * 交互dialog
     *
     * @param context
     * @param kefuCount   客服未读消息数
     * @param shequnCount 群聊未读消息数
     * @return
     */
    public static Dialog createChatDialog(final Context context, int kefuCount, int shequnCount) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_chat, null);
        view.findViewById(R.id.id_kefu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.doInteraction(context);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.id_qunliao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.doGroupChat(context);
                dialog.dismiss();
            }
        });
        ImageView kefuNotice = (ImageView) view.findViewById(R.id.iv_dialog_kefu_notice);
        ImageView shequnNotice = (ImageView) view.findViewById(R.id.iv_dialog_shequn_notice);
        kefuNotice.setVisibility(kefuCount == 0 ? View.GONE : View.VISIBLE);
        shequnNotice.setVisibility(shequnCount == 0 ? View.GONE : View.VISIBLE);
        dialog.setContentView(view);
        return dialog;
    }

    public static Dialog createChatDialog(final Context context) {
        return createChatDialog(context, 0, 0);
    }

    public static AlertDialog createKickedConfirmDialog(Context context,
                                                        CharSequence messageStr,
                                                        String btnText, final OnClickListener btnListener
    ) {
//        Dialog dialog = new Dialog(context, R.style.dialog);
//        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_onebtn, null);
//        ImageView imageView = (ImageView) rootView.findViewById(R.id.dialog_img);
//        TextView textViewMessage = (TextView) rootView.findViewById(R.id.dialog_message);
//        TextView textViewBtn = (TextView) rootView.findViewById(R.id.dialog_btn);
//        imageView.setVisibility(View.GONE);
//        textViewMessage.setText(messageStr);
//        textViewBtn.setText(btnText);
//        textViewBtn.setOnClickListener(btnListener);
//        dialog.setContentView(rootView);

//        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.setTitle("提示").setMessage(messageStr).setPositiveButton(btnText, btnListener).create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}