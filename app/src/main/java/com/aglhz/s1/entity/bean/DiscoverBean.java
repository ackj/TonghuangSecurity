package com.aglhz.s1.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/11/21 0021 17:50.
 * Email: liujia95me@126.com
 */

public class DiscoverBean extends BaseBean {

    /**
     * data : {"advs":[{"cover":"http://hygn.image.alimmdn.com/discovery/index/20171125162804040471.jpg@300h_600w_1e_1c","description":"http://www.aglhz.com","fid":"01e7e027-718f-4320-b02c-946174f54784","link":"https://www.audi.cn","title":"奥迪S3"},{"cover":"http://hygn.image.alimmdn.com/discovery/index/20171125162741291856.jpg@300h_600w_1e_1c","description":"http://www.aglhz.com","fid":"f9b82ca6-c237-48f6-bffc-36d8b130f3e9","link":"https://www.audi.cn","title":"奥迪"}],"buttons":[{"icon":"http://hygn.image.alimmdn.com/discovery/index/20171125162857251979.png","name":"在家"},{"icon":"http://hygn.image.alimmdn.com/discovery/index/20171125163017910137.png","name":"留言"},{"icon":"http://hygn.image.alimmdn.com/discovery/index/20171125162922309184.png","name":"离家"},{"icon":"http://hygn.image.alimmdn.com/discovery/index/20171125162949689985.png","name":"撤防"}],"news":[{"content":"http://www.aglhz.com","creater":"光能安防","fid":"938998c9-97e1-4e25-9b20-487c8d599a72","photo":"http://hygn.image.alimmdn.com/discovery/index/20171125163115629858.png","time":"2017-11-25 16:31:16","title":"社区新闻"},{"content":"http://www.aglhz.com","creater":"光能安防","fid":"ace22e26-030e-49a6-8147-e7cb5456eb5b","photo":"http://hygn.image.alimmdn.com/discovery/index/20171125163140383327.png","time":"2017-11-25 16:31:40","title":"光能安防"}],"notices":[{"description":"主机[G211EU6B1000316]布防由离家布防变更为撤防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:49:17","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由撤防变更为离家布防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:49:09","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由在家布防变更为撤防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:49:07","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由撤防变更为在家布防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:49:03","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由离家布防变更为撤防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:45:09","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由撤防变更为离家布防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:45:05","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由在家布防变更为撤防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:45:02","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由撤防变更为在家布防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:44:58","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由离家布防变更为撤防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:32:08","title":"主机布防状态变更通知","type":"gw_defense_status"},{"description":"主机[G211EU6B1000316]布防由在家布防变更为离家布防状态","gatewayName":"网关","gatewayNo":"G211EU6B1000316","time":"2017-11-25 17:27:43","title":"主机布防状态变更通知","type":"gw_defense_status"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AdvsBean> advs;
        private List<ButtonsBean> buttons;
        private List<NewsBean> news;
        private List<NoticesBean> notices;

        public List<AdvsBean> getAdvs() {
            return advs;
        }

        public void setAdvs(List<AdvsBean> advs) {
            this.advs = advs;
        }

        public List<ButtonsBean> getButtons() {
            return buttons;
        }

        public void setButtons(List<ButtonsBean> buttons) {
            this.buttons = buttons;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<NoticesBean> getNotices() {
            return notices;
        }

        public void setNotices(List<NoticesBean> notices) {
            this.notices = notices;
        }

        public static class AdvsBean {
            /**
             * cover : http://hygn.image.alimmdn.com/discovery/index/20171125162804040471.jpg@300h_600w_1e_1c
             * description : http://www.aglhz.com
             * fid : 01e7e027-718f-4320-b02c-946174f54784
             * link : https://www.audi.cn
             * title : 奥迪S3
             */

            private String cover;
            private String description;
            private String fid;
            private String link;
            private String title;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ButtonsBean {
            /**
             * icon : http://hygn.image.alimmdn.com/discovery/index/20171125162857251979.png
             * name : 在家
             */

            private String icon;
            private String name;

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
        }

        public static class NewsBean {
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

        public static class NoticesBean {
            /**
             * description : 主机[G211EU6B1000316]布防由离家布防变更为撤防状态
             * gatewayName : 网关
             * gatewayNo : G211EU6B1000316
             * time : 2017-11-25 17:49:17
             * title : 主机布防状态变更通知
             * type : gw_defense_status
             */

            private String description;
            private String gatewayName;
            private String gatewayNo;
            private String time;
            private String title;
            private String type;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getGatewayName() {
                return gatewayName;
            }

            public void setGatewayName(String gatewayName) {
                this.gatewayName = gatewayName;
            }

            public String getGatewayNo() {
                return gatewayNo;
            }

            public void setGatewayNo(String gatewayNo) {
                this.gatewayNo = gatewayNo;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
