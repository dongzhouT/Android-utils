package com.haierac.biz.cp.cloudplatformandroid.utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/8/18.
 */
public class EventBusUtils {
    public static void register(Object context) {
        if (!EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().register(context);
        }
    }

    public static void unregister(Object context) {
        if (EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().unregister(context);
        }
    }

    @Subscribe
    public void onEvent(Object obj) {

    }
}
