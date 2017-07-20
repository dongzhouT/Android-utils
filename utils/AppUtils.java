package com.haierac.biz.cp.cloudplatformandroid.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.haierac.biz.cp.cloudplatformandroid.R;
import com.haierac.biz.cp.cloudplatformandroid.utils.dbhelper.DeviceBeanDaoHelper;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2016/7/19.
 */
public class AppUtils {
    /**
     * 绑定edittext和imageview，清除edittext内容
     *
     * @param editText
     * @param imageView
     */
    public static void bindEditTextClear(final EditText editText, final ImageView imageView) {
        if (null == editText || null == imageView) {
            return;
        }
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (TextUtils.isEmpty(editText.getText())) {
                        imageView.setVisibility(View.INVISIBLE);
                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private static long lastClickTime = 0;

    //防止连续点击
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 返回两位数字，8->08
     *
     * @param num
     * @return
     */
    public static String getFormatNum(int num) {
        if (num > 9 || num < 0) {
            return num + "";
        } else {
            return "0" + num;
        }
    }

    /**
     * 返回10位时间戳  上传服务器用：本地生成时间戳为13位
     *
     * @param time
     * @return
     */
    public static long getTime10(long time) {
        if ((time + "").length() == 13) {
            return time / 1000;
        } else {
            return time;
        }
    }

    /**
     * 返回13位时间戳 解析服务器数据用：服务器返回10位
     *
     * @param time
     * @return
     */
    public static long getTime13(long time) {
        if ((time + "").length() == 10) {
            return time * 1000;
        } else {
            return time;
        }
    }

    //drawable 着色
    public static void setImageViewColor(ImageView view, int colorResId) {
        //mutate()
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        ColorStateList colorStateList = ColorStateList.valueOf(view.getResources().getColor(colorResId));
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }

    public static void setImageGray(ImageView view, int colorResId) {
        if (view.isEnabled())
            return;
        setImageViewColor(view, colorResId);
    }

    public static void doLogOut(Context context) {
        PreferencesUtils.putString(context, AppConstants.PREF_ACCESSTOKEN, "");
        PreferencesUtils.putString(context, AppConstants.PREF_CHATTOKEN, "");
        PreferencesUtils.putBoolean(context, AppConstants.PREF_CHATISKICKED, false);
        DeviceBeanDaoHelper.clear();
    }

    //打开交互，和客服聊天
    public static void doInteraction(Context context) {
        CSCustomServiceInfo.Builder builder = new CSCustomServiceInfo.Builder();
        builder.nickName("用户");
        builder.nickName(PreferencesUtils.getString(context, AppConstants.PREF_LOGINNAME, ""));
        builder.email(PreferencesUtils.getString(context, AppConstants.PREF_EMAIL, ""));
        builder.mobileNo(PreferencesUtils.getString(context, AppConstants.PREF_TEL, ""));
        RongIM.getInstance().startCustomerServiceChat(context,
                AppConstants.RONGYUN_ID, "在线客服", builder.build());
    }

    public static void doGroupChat(Context context) {
        String groupId = PreferencesUtils.getString(context, AppConstants.PREF_GROUPID, "groupId1");
        // TODO: 2017/7/17  test code
//        groupId = "9527";
        RongIM.getInstance().startGroupChat(context, groupId, context.getString(R.string.qunliao));
    }

    /**
     * 返回融云用户信息
     *
     * @param context 上下文
     * @return
     */
    public static UserInfo getUserInfo(Context context) {
        String id = PreferencesUtils.getString(context, AppConstants.PREF_USERID, "");
        //融云：先判断真实姓名是否为空，若为空则用登录名。
        String name = PreferencesUtils.getString(context, AppConstants.PREF_TRUENAME, "");
        if (AppUtils.isMockMode(context)) {
            name = context.getString(R.string.string_youke);
            id = getUniqueImei(context);
        } else {
            if (TextUtils.isEmpty(name)) {
                name = PreferencesUtils.getString(context, AppConstants.PREF_LOGINNAME, "");
            }
        }
        String uri = "http://182.92.175.40/images/header.png";
        UserInfo info = new UserInfo(id, name, Uri.parse(uri));
        return info;
    }

    /**
     * 返回融云社群信息
     *
     * @param context
     * @return
     */
    public static Group getGroup(Context context) {
        final String groupId = PreferencesUtils.getString(context, AppConstants.PREF_GROUPID, "groupId1");
        String name = context.getString(R.string.qunliao);
        Uri uri = Uri.parse("http://182.92.175.40/images/header.png");
        return new Group(groupId, name, uri);
    }

    //获取设备唯一识别码
    public static String getUniqueImei(Context context) {
        String appid = PreferencesUtils.getString(context, AppConstants.PREF_APPID, "");
        if (TextUtils.isEmpty(appid)) {
            try {
                //Android6.0 API23以上读取不到手机IMEI
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                appid = tm.getDeviceId();
                if (TextUtils.isEmpty(appid)) {
                    //如果获取不到手机的IMEI
                    appid = Md5Utils.encode(System.currentTimeMillis() + "" + Math.round(Math.random() * 1000));
                    PreferencesUtils.putString(context, AppConstants.PREF_APPID, appid);
                } else {
                    PreferencesUtils.putString(context, AppConstants.PREF_APPID, appid);
                }
            } catch (Exception e) {
                appid = Md5Utils.encode(System.currentTimeMillis() + "" + Math.round(Math.random() * 1000));
                PreferencesUtils.putString(context, AppConstants.PREF_APPID, appid);
            }
        }
        return appid;
    }

    /**
     * 检测email格式
     *
     * @param email 邮件地址
     * @return true:格式正确 false:格式不正确
     */
    public static boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
//        else {
//            return true;
//        }
        //[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?
        String pattern = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        return email.matches(pattern);
    }

    /**
     * 校验密码，至少6位。重置、注册、找回密码
     *
     * @param pass 密码
     * @return true 密码有效;false 无效
     */
    public static boolean checkPass(String pass) {
        if (TextUtils.isEmpty(pass)) {
            return false;
        }
        return pass.length() >= 6;
    }

    /**
     * 验证手机号，11位数字
     *
     * @param phone 手机号码
     * @return true 密码有效;false 无效
     */
    public static boolean checkPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        String pattern = "^\\d{11}$";
        return phone.matches(pattern);
//        return phone.length() != 11;
    }

    /**
     * 判断当前是否是体验登录
     *
     * @param context 上下文
     * @return true 是体验登录
     */
    public static boolean isMockMode(Context context) {
        return PreferencesUtils.getBoolean(context, AppConstants.PREF_IS_MOCK, false);
    }

}
