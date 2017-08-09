package com.aglhz.s1.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/4/27 0027 09:03.
 * Email: liujia95me@126.com
 */

public class SecurityBean {

    /**
     * data : {"gateway":{"fid":"2d68f281-825d-48f9-941f-41d1c73eacec","name":"主机754","defenseStatusDes":"当前处于在家布防状态","defenseStatus":"home","status":0,"isOnline":0,"isManager":1,"isCurrent":1,"residence":{"fid":"irifirkfk","name":"凯宾斯基","addr":"惠州江北凯宾斯基C座"}},"gatewayList":[],"subDevices":[]}
     * other : {"code":200,"message":"data success","time":"","currpage":0,"next":"","forward":"","refresh":"","first":""}
     */

    private DataBean data;
    private OtherBean other;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * gateway : {"fid":"2d68f281-825d-48f9-941f-41d1c73eacec","name":"主机754","defenseStatusDes":"当前处于在家布防状态","defenseStatus":"home","status":0,"isOnline":0,"isManager":1,"isCurrent":1,"residence":{"fid":"irifirkfk","name":"凯宾斯基","addr":"惠州江北凯宾斯基C座"}}
         * gatewayList : []
         * subDevices : []
         */

        private GatewayBean gateway;
        private List<?> gatewayList;
        private List<?> subDevices;

        public GatewayBean getGateway() {
            return gateway;
        }

        public void setGateway(GatewayBean gateway) {
            this.gateway = gateway;
        }

        public List<?> getGatewayList() {
            return gatewayList;
        }

        public void setGatewayList(List<?> gatewayList) {
            this.gatewayList = gatewayList;
        }

        public List<?> getSubDevices() {
            return subDevices;
        }

        public void setSubDevices(List<?> subDevices) {
            this.subDevices = subDevices;
        }

        public static class GatewayBean {
            /**
             * fid : 2d68f281-825d-48f9-941f-41d1c73eacec
             * name : 主机754
             * defenseStatusDes : 当前处于在家布防状态
             * defenseStatus : home
             * status : 0
             * isOnline : 0
             * isManager : 1
             * isCurrent : 1
             * residence : {"fid":"irifirkfk","name":"凯宾斯基","addr":"惠州江北凯宾斯基C座"}
             */

            private String fid;
            private String name;
            private String defenseStatusDes;
            private String defenseStatus;
            private int status;
            private int isOnline;
            private int isManager;
            private int isCurrent;
            private ResidenceBean residence;

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

            public String getDefenseStatusDes() {
                return defenseStatusDes;
            }

            public void setDefenseStatusDes(String defenseStatusDes) {
                this.defenseStatusDes = defenseStatusDes;
            }

            public String getDefenseStatus() {
                return defenseStatus;
            }

            public void setDefenseStatus(String defenseStatus) {
                this.defenseStatus = defenseStatus;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(int isOnline) {
                this.isOnline = isOnline;
            }

            public int getIsManager() {
                return isManager;
            }

            public void setIsManager(int isManager) {
                this.isManager = isManager;
            }

            public int getIsCurrent() {
                return isCurrent;
            }

            public void setIsCurrent(int isCurrent) {
                this.isCurrent = isCurrent;
            }

            public ResidenceBean getResidence() {
                return residence;
            }

            public void setResidence(ResidenceBean residence) {
                this.residence = residence;
            }

            public static class ResidenceBean {
                /**
                 * fid : irifirkfk
                 * name : 凯宾斯基
                 * addr : 惠州江北凯宾斯基C座
                 */

                private String fid;
                private String name;
                private String addr;

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

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }
            }
        }
    }

    public static class OtherBean {
        /**
         * code : 200
         * message : data success
         * time :
         * currpage : 0
         * next :
         * forward :
         * refresh :
         * first :
         */

        private int code;
        private String message;
        private String time;
        private int currpage;
        private String next;
        private String forward;
        private String refresh;
        private String first;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCurrpage() {
            return currpage;
        }

        public void setCurrpage(int currpage) {
            this.currpage = currpage;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }
    }
}
