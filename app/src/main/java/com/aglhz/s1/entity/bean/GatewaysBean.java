package com.aglhz.s1.entity.bean;

import android.os.Parcel;

import java.util.List;

/**
 * Author： Administrator on 2017/8/9 0009.
 * Email： liujia95me@126.com
 */
public class GatewaysBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements android.os.Parcelable {
        /**
         * defenseStatus : cancel
         * defenseStatusDes : 当前处于撤防状态,仅24小时防线的探测器处于防御状态，其他探测器均处于未设防状态
         * fid : f420fa1a-97c4-44fa-a608-144ac54c21bc
         * isCurrent : 0
         * isManager : 0
         * isOnline : 0
         * name : 754
         * no : G111EU6B1000754
         * residence : {"addr":"惠阳淡水万联广场街道","fid":"21831c1f-e8f1-4b88-9d01-2688aa798b10","lat":"22.78348","lng":"114.46327","name":""}
         * status : 0
         */

        private String defenseStatus;
        private String defenseStatusDes;
        private String fid;
        private int isCurrent;
        private int isManager;
        private int isOnline;
        private String name;
        private String no;
        private ResidenceBean residence;
        private int status;

        public String getDefenseStatus() {
            return defenseStatus;
        }

        public void setDefenseStatus(String defenseStatus) {
            this.defenseStatus = defenseStatus;
        }

        public String getDefenseStatusDes() {
            return defenseStatusDes;
        }

        public void setDefenseStatusDes(String defenseStatusDes) {
            this.defenseStatusDes = defenseStatusDes;
        }

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

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
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

        public static class ResidenceBean implements android.os.Parcelable {
            /**
             * addr : 惠阳淡水万联广场街道
             * fid : 21831c1f-e8f1-4b88-9d01-2688aa798b10
             * lat : 22.78348
             * lng : 114.46327
             * name :
             */

            private String addr;
            private String fid;
            private String lat;
            private String lng;
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

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.addr);
                dest.writeString(this.fid);
                dest.writeString(this.lat);
                dest.writeString(this.lng);
                dest.writeString(this.name);
            }

            public ResidenceBean() {
            }

            protected ResidenceBean(Parcel in) {
                this.addr = in.readString();
                this.fid = in.readString();
                this.lat = in.readString();
                this.lng = in.readString();
                this.name = in.readString();
            }

            public static final Creator<ResidenceBean> CREATOR = new Creator<ResidenceBean>() {
                @Override
                public ResidenceBean createFromParcel(Parcel source) {
                    return new ResidenceBean(source);
                }

                @Override
                public ResidenceBean[] newArray(int size) {
                    return new ResidenceBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.defenseStatus);
            dest.writeString(this.defenseStatusDes);
            dest.writeString(this.fid);
            dest.writeInt(this.isCurrent);
            dest.writeInt(this.isManager);
            dest.writeInt(this.isOnline);
            dest.writeString(this.name);
            dest.writeString(this.no);
            dest.writeParcelable(this.residence, flags);
            dest.writeInt(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.defenseStatus = in.readString();
            this.defenseStatusDes = in.readString();
            this.fid = in.readString();
            this.isCurrent = in.readInt();
            this.isManager = in.readInt();
            this.isOnline = in.readInt();
            this.name = in.readString();
            this.no = in.readString();
            this.residence = in.readParcelable(ResidenceBean.class.getClassLoader());
            this.status = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
