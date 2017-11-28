package com.meilun.security.smart.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/9/18 0018 09:27.
 * Email: liujia95me@126.com
 */

public class CameraBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * deviceId : 5971076
         * fid : f39d5857-7ba1-41d0-b5c8-329cb7e598b9
         * gateway : G211EU6B1000111
         * icon : http://agl.image.alimmdn.com/smartHome/subDeviceType/e_shexiangtou.png
         * name : 5971076
         * password : 123
         */

        private String deviceId;
        private String fid;
        private String gateway;
        private String icon;
        private String name;
        private String password;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
