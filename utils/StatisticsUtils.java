package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7.
 */

public class StatisticsUtils {
    public static final String STATISTICS_ID_REG = "event_register";
    public static final String STATISTICS_ID_LOGIN = "event_login";
    public static final String STATISTICS_ID_ACTIVE = "event_active";
    public static final String STATISTICS_KEY = "statistics";

    Context mContext;

    public StatisticsUtils(Context context) {
        mContext = context;
    }

    protected void statistics(String eventId, Map param) {
        MobclickAgent.onEvent(mContext, eventId, param);
    }

    //统计登录
    public void doLoginStatistics() {
        if (!TextUtils.isEmpty(PreferencesUtils.getString(mContext, AppConstants.PREF_ACCESSTOKEN, ""))) {
            Map<String, String> params = new HashMap<>();
            String userId = PreferencesUtils.getString(mContext, AppConstants.PREF_USERID);
            String loginName = PreferencesUtils.getString(mContext, AppConstants.PREF_LOGINNAME);
            String tel = PreferencesUtils.getString(mContext, AppConstants.PREF_TEL);
            String trueName = PreferencesUtils.getString(mContext, AppConstants.PREF_TRUENAME);
            params.put(STATISTICS_KEY, userId + "_" + loginName + "_" + tel + "_" + trueName);
            statistics(STATISTICS_ID_LOGIN, params);
        }
    }

    //统计活跃度
    public void doActiveStatistics() {
        Map<String, String> params = new HashMap<>();
        if (TextUtils.isEmpty(PreferencesUtils.getString(mContext, AppConstants.PREF_ACCESSTOKEN, ""))) {
            params.put(STATISTICS_KEY, "visitor");
        } else {
            String userId = PreferencesUtils.getString(mContext, AppConstants.PREF_USERID);
            String loginName = PreferencesUtils.getString(mContext, AppConstants.PREF_LOGINNAME);
            String tel = PreferencesUtils.getString(mContext, AppConstants.PREF_TEL);
            String trueName = PreferencesUtils.getString(mContext, AppConstants.PREF_TRUENAME);
            params.put(STATISTICS_KEY, userId + "_" + loginName + "_" + tel + "_" + trueName);
        }
        statistics(STATISTICS_ID_ACTIVE, params);
    }

    //统计注册
    public void doRegisterStatistics() {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(PreferencesUtils.getString(mContext, AppConstants.PREF_ACCESSTOKEN, ""))) {
            String userId = PreferencesUtils.getString(mContext, AppConstants.PREF_USERID);
            String loginName = PreferencesUtils.getString(mContext, AppConstants.PREF_LOGINNAME);
            String tel = PreferencesUtils.getString(mContext, AppConstants.PREF_TEL);
            String trueName = PreferencesUtils.getString(mContext, AppConstants.PREF_TRUENAME);
            params.put(STATISTICS_KEY, userId + "_" + loginName + "_" + tel + "_" + trueName);
        }
        statistics(STATISTICS_ID_REG, params);
    }
}
