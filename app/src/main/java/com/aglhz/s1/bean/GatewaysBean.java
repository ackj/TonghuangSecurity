package com.aglhz.s1.bean;

import java.util.List;

/**
 * Author： Administrator on 2017/8/9 0009.
 * Email： liujia95me@126.com
 */
public class GatewaysBean extends BaseBean{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fid : qbfvfdvd
         * isCurrent : 1
         * isManager : 1
         * isOnline : 0
         * name : S1智能网关
         * residence : {"addr":"惠州江北凯宾斯基C座","fid":"irifirkfk","name":"凯宾斯基"}
         * status : 0
         */

        private String fid;
        private int isCurrent;
        private int isManager;
        private int isOnline;
        private String name;
        private ResidenceBean residence;
        private int status;

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public int getIsCurrent() {
            return isCurrent;
        }

        public void setIsCurrent(int isCurrent) {
            this.isCurrent = isCurrent;
        }

        public int getIsManager() {
            return isManager;
        }

        public void setIsManager(int isManager) {
            this.isManager = isManager;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ResidenceBean getResidence() {
            return residence;
        }

        public void setResidence(ResidenceBean residence) {
            this.residence = residence;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public static class ResidenceBean {
            /**
             * addr : 惠州江北凯宾斯基C座
             * fid : irifirkfk
             * name : 凯宾斯基
             */

            private String addr;
            private String fid;
            private String name;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
