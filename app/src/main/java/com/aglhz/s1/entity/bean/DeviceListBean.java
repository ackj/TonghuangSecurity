package com.aglhz.s1.entity.bean;

import com.aglhz.s1.room.view.RoomDeviceListRVAdapter;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: LiuJia on 2017/8/20 0020 18:06.
 * Email: liujia95me@126.com
 */

public class DeviceListBean extends BaseBean {

    /**
     * data : {"subDevices":[{"category":"device_ctrl","defenseLevel":"24hour","extInfo":{"index":1,"name":"relay4","node":4,"roomId":1,"subType":4,"type":143,"userFlag":0},"icon":"","index":1,"isOline":1,"name":"relay4"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<SubDevicesBean> subDevices;

        public List<SubDevicesBean> getSubDevices() {
            return subDevices;
        }

        public void setSubDevices(List<SubDevicesBean> subDevices) {
            this.subDevices = subDevices;
        }

        public static class SubDevicesBean extends AbstractExpandableItem<DeviceOnOffBean> implements MultiItemEntity,Serializable {

            /**
             * category : device_ctrl
             * defenseLevel : 24hour
             * extInfo : {"index":1,"name":"relay4","node":4,"roomId":1,"subType":4,"type":143,"userFlag":0}
             * icon :
             * index : 1
             * isOline : 1
             * name : relay4
             */

            private String category;
            private String defenseLevel;
            private ExtInfoBean extInfo;
            private String icon;
            private int index;
            private int isOline;
            private String name;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getDefenseLevel() {
                return defenseLevel;
            }

            public void setDefenseLevel(String defenseLevel) {
                this.defenseLevel = defenseLevel;
            }

            public ExtInfoBean getExtInfo() {
                return extInfo;
            }

            public void setExtInfo(ExtInfoBean extInfo) {
                this.extInfo = extInfo;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getIsOline() {
                return isOline;
            }

            public void setIsOline(int isOline) {
                this.isOline = isOline;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public int getItemType() {
                return RoomDeviceListRVAdapter.TYPE_DEVICE;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            public static class ExtInfoBean implements Serializable{
                /**
                 * index : 1
                 * name : relay4
                 * node : 4
                 * roomId : 1
                 * subType : 4
                 * type : 143
                 * userFlag : 0
                 */

                private int index;
                private String name;
                private int node;
                private int roomId;
                private int subType;
                private int type;
                private int userFlag;

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getNode() {
                    return node;
                }

                public void setNode(int node) {
                    this.node = node;
                }

                public int getRoomId() {
                    return roomId;
                }

                public void setRoomId(int roomId) {
                    this.roomId = roomId;
                }

                public int getSubType() {
                    return subType;
                }

                public void setSubType(int subType) {
                    this.subType = subType;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getUserFlag() {
                    return userFlag;
                }

                public void setUserFlag(int userFlag) {
                    this.userFlag = userFlag;
                }
            }
        }
    }
}
