package com.meilun.security.smart.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/11/25 0025 18:21.
 * Email: liujia95me@126.com
 */

public class NewsBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : http://www.aglhz.com
         * creater : 光能安防
         * fid : 938998c9-97e1-4e25-9b20-487c8d599a72
         * photo : http://hygn.image.alimmdn.com/discovery/index/20171125163115629858.png
         * time : 2017-11-25 16:31:16
         * title : 社区新闻
         */

        private String content;
        private String creater;
        private String fid;
        private String photo;
        private String time;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreater() {
            return creater;
        }

        public void setCreater(String creater) {
            this.creater = creater;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
