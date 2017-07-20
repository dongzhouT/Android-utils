package com.haierac.biz.cp.cloudplatformandroid.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Administrator on 2016/7/22.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface MyPrefs {
    @DefaultString("nothing")
    String detail();
}
