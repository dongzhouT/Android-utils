package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.webkit.JavascriptInterface;

import com.haierac.biz.cp.cloudplatformandroid.interfaces.OnLotteryWebViewInit;
import com.haierac.biz.cp.cloudplatformandroid.interfaces.OnWebViewItemClickListener;

/**
 * Created by Administrator on 2016/8/12.
 */
public class JsInterface {
    OnWebViewItemClickListener listener;
    OnLotteryWebViewInit lotteryListener;

    public JsInterface(OnWebViewItemClickListener listener) {
        this.listener = listener;
    }

    public JsInterface(OnLotteryWebViewInit lotteryListener) {
        this.lotteryListener = lotteryListener;
    }

    @JavascriptInterface
    public void loadDetail(String url, String title) {
        if (listener != null) {
            listener.onItemClick(url, title);
        }
    }

    @JavascriptInterface
    public void onLotteryInit(String userId, String status) {
        if (lotteryListener != null) {
            lotteryListener.onWebInit(userId, status);
        }
    }
}
