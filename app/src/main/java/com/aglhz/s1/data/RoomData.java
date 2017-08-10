package com.aglhz.s1.data;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.RoomBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/31 0031 11:18.
 * Email: liujia95me@126.com
 */

public class RoomData {
    private static List<RoomBean> allRooms;//所有房间
    private static LinkedList<RoomBean> alreadyAddRooms;//已添加的房间
    private static RoomData INSTANCE;

    public static RoomData getInstance() {
        if (INSTANCE == null) {
            synchronized (RoomData.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RoomData();
                    allRooms = new ArrayList<>();
                    alreadyAddRooms = new LinkedList<>();
                    initData();
                }
            }
        }
        return INSTANCE;
    }

    private static void initData() {
        allRooms.add(new RoomBean(R.mipmap.ic_logo, "大厅"));
        allRooms.add(new RoomBean(R.mipmap.ic_logo, "厨房"));
        allRooms.add(new RoomBean(R.mipmap.ic_logo, "厕所"));
        allRooms.add(new RoomBean(R.mipmap.ic_logo, "书房"));

        alreadyAddRooms.addAll(allRooms);
        alreadyAddRooms.addLast(new RoomBean(R.drawable.ic_add_security_140px, "添加房间"));
    }

    public List<RoomBean> getAllRooms() {
        return allRooms;
    }

    public List<RoomBean> getAlreadyAddRooms() {
        return alreadyAddRooms;
    }

    public void addRoom(RoomBean bean) {
        alreadyAddRooms.addFirst(bean);
    }

}
