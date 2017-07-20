package com.haierac.biz.cp.cloudplatformandroid.utils;

/**
 * Created by Administrator on 2016/7/18.
 */
public class AppConstants {
    public static final String APPCHARSET = "UTF-8";
    //request params 请求参数
    public static final String ACCESSTOKEN = "accessToken";
    public static final String LOGINNAME = "loginName";
    public static final String TEL = "tel";
    public static final String PASSWORD = "passWord";
    public static final String DEVICEID = "deviceId";
    public static final String HOTLINE = "4006-999-999";
    public static final String SERVICE_URL = "http://service.haier.com";
    public static final String TYPE = "type";
    public static final String STARTTIME = "startTime";
    public static final String ENDTIME = "endTime";
    public static final String CONTROLDATA = "controlData";
    public static final String IDENTIFYCODE = "identifyCode";
    public static final String NEWPASSWORD = "newPassWord";
    public static final String ORIGPASSWORD = "origPassWord";
    public static final String PAGE = "page";
    public static final String ROWS = "rows";

//    public static final String ENDTIME = "endTime";
//    public static final String ENDTIME = "endTime";


    //PREFS
    public static final String PREF_LOGINNAME_LAST = "pref_loginname_last";//上次登录用户（非体验用户）
    public static final String PREF_IS_MOCK = "pref_is_mock";//是否是体验用户登录
    public static final String PREF_GUEST_NOTICE = "PREF_GUEST_NOTICE";//体验用户登录提醒
    public static final String PREF_LOGINNAME = "pref_loginName";//登录名
    public static final String PREF_PASSWORD = "pref_passWord";
    public static final String PREF_USERID = "pref_userId";
    public static final String PREF_TEL = "pref_tel";
    public static final String PREF_EMAIL = "pref_email";
    public static final String PREF_ACCESSTOKEN = "pref_accessToken";
    public static final String PREF_PROJECT_DETAIL = "pref_project_detail";
    public static final String PREF_CASE = "pref_case";
    public static final String PREF_PRODUCT_NEWS = "pref_product_news";
    public static final String PREF_NAMELIST = "pref_namelist";
    public static final String PREF_IDLIST = "pref_idlist";
    public static final String PREF_DEVICEID = "pref_deviceid";
    public static final String PREF_DEVICENAME = "pref_devicename";
    public static final String PREF_MUL_CONTROLMODE = "pref_mul_controlmode";//多联机控制模式
    public static final String PREF_MUL_HOTCOOLMODE = "pref_mul_hotcoolmode";//多联机冷热模式
    public static final String PREF_MUL_SAVEENERGY = "pref_mul_saveenergy";//多联机一键节能
    public static final String PREF_MUL_CASE = "pref_mul_case";//多联机经典案例
    public static final String PREF_MUL_PRODUCT_NEWS = "pref_mul_product_news";//多联机产品动态

    public static final String PREF_TRUENAME = "pref_truename";//真实姓名
    public static final String PREF_COMPANY = "pref_company";//公司名称


    public static final long weekTime = 3600 * 24 * 7 * 1000L;

    public static final String[] MONTH = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};


    public static enum Colors {
        red, redDark, yellow, blue, blueDark, gray
    }

    public static enum energyType {
        day, week, month
    }

    public static final String COLORRED = "#ff0000";
    public static final String COLORREDDARK = "#ff4c4c";
    public static final String COLORYELLOW = "#ffd400";
    public static final String COLORBLUE = "#4cc2ff";
    public static final String COLORBLUEDARK = "#0247d3";
    public static final String COLORGRAY = "#757575";

    //模式
    public static final int MODE_COOL = 0;
    public static final int MODE_HOT = 1;
    public static final int MODE_HUM = 3;
    public static final int MODE_FANS = 4;
    public static final int MODE_AUTO = 5;

    // 风速
    public static final int WIND_HIGH = 0;
    public static final int WIND_MID = 1;
    public static final int WIND_LOW = 2;
    public static final int WIND_AUTO = 3;

    //多联机控制模式
    public static final String MULTI_CTRL_DEFAULT = "0";//后入优先
    public static final String MULTI_CTRL_CENTRALIZE = "1";//集中控制
    public static final String MULTI_CTRL_FORCE = "2";//强制

    //多联机冷热模式
    public static final String MULTI_COHO_DEFAULT = "0";//不限定
    public static final String MULTI_COHO_COOL = "1";//单冷
    public static final String MULTI_COHO_HOT = "2";//单热

    //融云客服ID
    public static final String PREF_CHATISKICKED = "chatIsKicked";//是否被踢掉
    public static final String PREF_CHATTOKEN = "chatToken";
    public static final String PREF_GROUPID = "groupId";
    public static final String PREF_APPID = "pref_appid";//手机唯一标识

    public static final String RONGYUN_ID = "KEFU149440681221248";
    public static final String PREF_UPDATEHASCHECKED = "pref_checkforupdate";//版本在线更新标记。

    // TODO: 2017/7/13
    // add by tao:安装完apk，token清空一次，让用户重新登陆。修复注册后用户真实姓名没有保存的问题
    public static final String PREF_TOKEN_CLEARED = "pref_token_cleared";
}
