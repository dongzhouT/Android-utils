package com.haierac.biz.cp.cloudplatformandroid.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/9/1.
 */
public class StringFormatter extends DecimalFormat {
    public StringFormatter(String pattern) {
        super(pattern);
    }

    /**
     * 将传入的string类型格式化
     * @param value
     * @return
     */
    public String formatString(String value) {
        return format(ParseUtils.parseFloat(value));
    }
}
