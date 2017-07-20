package com.haierac.biz.cp.cloudplatformandroid.utils.dbhelper;

import com.cloud.bean.DeviceBean;
import com.cloud.dao.DeviceBeanDao;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class DeviceBeanDaoHelper {
    private DeviceBeanDao deviceBeanDao;
    private static DeviceBeanDaoHelper helper;

    private DeviceBeanDaoHelper() {
        deviceBeanDao = CPDatabaseLoader.getSession().getDeviceBeanDao();
    }

    public static DeviceBeanDaoHelper getInstance() {
        if (helper == null) {
            helper = new DeviceBeanDaoHelper();
        }
        return helper;
    }

    public static void addData(DeviceBean bean) {
        getInstance().deviceBeanDao.insertOrReplace(bean);
    }

    public static List<DeviceBean> getAllDevices() {
        return getInstance().deviceBeanDao.loadAll();
    }

    public static void clear() {
        getInstance().deviceBeanDao.deleteAll();
    }

}
