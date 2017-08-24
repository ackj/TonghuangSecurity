package com.aglhz.s1.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/8/9 0009 15:55.
 * Email: liujia95me@126.com
 */

public class RoomsBean extends BaseBean {

    /**
     * data : {"roomList":[{"fid":"100001","name":"测试房间","type":"hall"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RoomListBean> roomList;

        public List<RoomListBean> getRoomList() {
            return roomList;
        }

        public void setRoomList(List<RoomListBean> roomList) {
            this.roomList = roomList;
        }

        public static class RoomListBean {
            /**
             * fid : 100001
             * name : 测试房间
             * type : hall
             */

            private String fid;
            private String name;
            private String type;
            private int index;

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
