package com.aglhz.s1.entity.bean;

import android.os.Parcel;

import java.util.ArrayList;
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

    public static class DataBean implements android.os.Parcelable {
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

        public static class ResidenceBean implements android.os.Parcelable {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.addr);
                dest.writeString(this.fid);
                dest.writeString(this.name);
            }

            public ResidenceBean() {
            }

            protected ResidenceBean(Parcel in) {
                this.addr = in.readString();
                this.fid = in.readString();
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
            dest.writeString(this.fid);
            dest.writeInt(this.isCurrent);
            dest.writeInt(this.isManager);
            dest.writeInt(this.isOnline);
            dest.writeString(this.name);
            dest.writeParcelable(this.residence, flags);
            dest.writeInt(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.fid = in.readString();
            this.isCurrent = in.readInt();
            this.isManager = in.readInt();
            this.isOnline = in.readInt();
            this.name = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeList(this.data);
    }

    public GatewaysBean() {
    }

    protected GatewaysBean(Parcel in) {
        super(in);
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Creator<GatewaysBean> CREATOR = new Creator<GatewaysBean>() {
        @Override
        public GatewaysBean createFromParcel(Parcel source) {
            return new GatewaysBean(source);
        }

        @Override
        public GatewaysBean[] newArray(int size) {
            return new GatewaysBean[size];
        }
    };
}
