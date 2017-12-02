package com.meilun.security.smart.event;

/**
 * Author: LiuJia on 2017/12/2 0002 11:58.
 * Email: liujia95me@126.com
 */

public class EventCameraStatus {
    public String[] contactIds;
    public int[] status;

    public EventCameraStatus(String[] contactIds, int[] status) {
        this.contactIds = contactIds;
        this.status = status;
    }
}
