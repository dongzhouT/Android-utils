package com.haierac.biz.cp.cloudplatformandroid.utils.dbhelper;

import android.text.TextUtils;
import android.util.Log;

import com.haierac.biz.cp.cloudplatformandroid.net.entity.DeviceUtils;
import com.haierac.biz.cp.cloudplatformandroid.net.entity.ProjectInfoResultBean;
import com.haierac.biz.cp.cloudplatformandroid.net.entity.RealStatusResultBean;
import com.cloud.bean.DeviceBean;

import java.util.ArrayList;
import java.util.List;

import com.haierac.biz.cp.cloudplatformandroid.utils.ParseUtils;

/**
 * Created by Administrator on 2016/8/16.
 */
public class UserDeviceManager {
    String userId;
    String deviceId;
    DeviceBean currentDevice;
    List<DeviceBean> deviceBeanList;
    private static UserDeviceManager instance;

    public List<DeviceBean> getDeviceBeanList() {
        if (deviceBeanList == null) {
            deviceBeanList = DeviceBeanDaoHelper.getInstance().getAllDevices();
            Log.e("cloudtag", "getDeviceBeanList: " + "deviceBeanList fench again!!!!!!");
        }
        return deviceBeanList;
    }

    private UserDeviceManager() {
        deviceBeanList = new ArrayList<>();
    }

    public static UserDeviceManager getInstance() {
        if (instance == null) {
            instance = new UserDeviceManager();
            instance.deviceBeanList = DeviceBeanDaoHelper.getInstance().getAllDevices();
        }
        return instance;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDeviceAndUserId(String _deviceId, String _userId) {
        this.deviceId = _deviceId;
        this.userId = _userId;
    }

    public DeviceBean getCurrentDevice() {
        return getDeviceById(this.deviceId);
    }

    public DeviceBean getDeviceById(String _deviceId) {
        for (DeviceBean bean : deviceBeanList) {
            if (_deviceId.equals(bean.getDeviceId())) {
                return bean;
            }
        }
        return null;
    }

    public static DeviceBean getDevice() {
        return instance.getCurrentDevice();
    }

    public void setDeviceList(List<ProjectInfoResultBean.CxfDetailBean.DeviceListBean> beanList) {
        if (deviceBeanList == null) {
            deviceBeanList = new ArrayList<>();
        }
        deviceBeanList.clear();
        for (ProjectInfoResultBean.CxfDetailBean.DeviceListBean bean : beanList) {
            DeviceBean deviceBean = new DeviceBean();
            deviceBean.setDeviceId(bean.getId());
            deviceBean.setDeviceName(bean.getDeviceName());
            deviceBean.setStatus(bean.getStatus());
            deviceBean.setFault(DeviceUtils.hasFault(bean.getStatus()) ? "1" : "0");
            deviceBean.setWarning(DeviceUtils.hasWarning(bean.getStatus()) ? "1" : "0");
            deviceBean.setRunning(DeviceUtils.isRunning(bean.getStatus()) ? "1" : "0");
            deviceBean.setSetTemp(bean.getTemp());
            deviceBeanList.add(deviceBean);
        }
    }

    /**
     * 2分钟之内的是有效的数据,是否超时
     *
     * @param _deviceId
     * @return
     */
    public boolean isDeviceDataExpired(String _deviceId) {
        String lastUpdate = getDeviceById(_deviceId).getLastupdate();
        if (TextUtils.isEmpty(lastUpdate)) {
            return true;
        }
        long lastUpdateTime = ParseUtils.parseLong(lastUpdate);
        return (System.currentTimeMillis() - lastUpdateTime > 1000 * 60 * 2);
    }

    public boolean isCurrentDeviceValid() {
        return isDeviceDataExpired(this.deviceId);
    }

    public RealStatusResultBean.DataBean getRealBean(String deviceId) {
        RealStatusResultBean.DataBean bean = new RealStatusResultBean.DataBean();
        DeviceBean currentDevice = getDeviceById(deviceId);
        bean.setDeviceId(currentDevice.getDeviceId());
        bean.setStatus(currentDevice.getStatus());
        bean.setWorkMode(currentDevice.getWorkMode());
        bean.setSetTemp(currentDevice.getSetTemp());
//        bean.setDeviceId();
        return bean;
    }

    public void updateCxfBeanList(List<ProjectInfoResultBean.CxfDetailBean.DeviceListBean> list) {
        for (ProjectInfoResultBean.CxfDetailBean.DeviceListBean bean : list) {
            updateCxfBean(bean);
        }
    }

    public void updateCxfBean(ProjectInfoResultBean.CxfDetailBean.DeviceListBean deviceListBean) {
        if (null == deviceListBean) {
            return;
        }
        String deviceId = deviceListBean.getId();
        DeviceBean bean = new DeviceBean();
        bean.setDeviceId(deviceId);
        bean.setStatus(deviceListBean.getStatus());
//        bean.setDeviceName(deviceListBean.getDeviceName());
        bean.setWorkMode(deviceListBean.getMode());
        bean.setSetTemp(deviceListBean.getTemp());
        if (null == getDeviceById(deviceId)) {
            deviceBeanList.add(bean);
            //保存到数据库
            DeviceBeanDaoHelper.addData(bean);
            return;
        }

        if (isDeviceDataExpired(deviceId)) {
            //超过2分钟
            int length = deviceBeanList.size();
            for (int i = 0; i < length; i++) {
                if (deviceBeanList.get(i).getDeviceId().equals(deviceId)) {
                    deviceBeanList.set(i, bean);
                    DeviceBeanDaoHelper.addData(bean);
                    break;
                }
            }
        } else {
            for (DeviceBean devBean : deviceBeanList) {
                if (deviceId.equals(devBean.getDeviceId())) {
                    deviceListBean.setStatus(devBean.getStatus());
                    deviceListBean.setMode(devBean.getWorkMode());
                    deviceListBean.setTemp(devBean.getSetTemp());
                    break;
                }
            }
        }
    }

    public void updateRealBean(RealStatusResultBean.DataBean dataBean) {
        if (null == dataBean) {
            return;
        }
        String deviceId = dataBean.getDeviceId();
        DeviceBean bean = new DeviceBean();
        bean.setDeviceId(deviceId);
        bean.setStatus(dataBean.getStatus());
        bean.setWorkMode(dataBean.getWorkMode());
        bean.setSetTemp(dataBean.getSetTemp());
        if (null == getDeviceById(deviceId)) {
            deviceBeanList.add(bean);
            DeviceBeanDaoHelper.addData(bean);
            return;
        }

        if (isDeviceDataExpired(deviceId)) {
            //超过2分钟
            int length = deviceBeanList.size();
            for (int i = 0; i < length; i++) {
                if (deviceBeanList.get(i).getDeviceId().equals(deviceId)) {
                    deviceBeanList.set(i, bean);
                    DeviceBeanDaoHelper.addData(bean);
                    break;
                }
            }
        } else {
            for (DeviceBean devBean : deviceBeanList) {
                if (deviceId.equals(devBean.getDeviceId())) {
                    dataBean.setStatus(devBean.getStatus());
                    dataBean.setWorkMode(devBean.getWorkMode());
                    dataBean.setSetTemp(devBean.getSetTemp());
                    break;
                }
            }
        }
    }

    public void setLastUpdateTime(long time) {
        getCurrentDevice().setLastupdate(time + "");
    }

}
