package com.aglhz.s1.entity.bean;

/**
 * Created by leguang on 2017/8/11 0011.
 * Email：langmanleguang@qq.com
 */

public class NotificationBean {

    /**
     * body : {"after_open":"go_app","play_lights":"true","play_sound":"true","play_vibrate":"true","text":"学习传感器测试消息d2","ticker":"学习传感器测试消息t1","title":"学习传感器测试消息t1"}
     * display_type : notification
     * extra : {"address":"燃气泄漏报警通知 地址：凯宾斯基C座801","des":"学习红外探测器成功","name":"主机364","sensorId":1,"status":1,"type":"sensor_learn"}
     * msg_id : uu5fw0g150243698910200
     * random_min : 0
     */

    private BodyBean body;
    private String display_type;
    private ExtraBean extra;
    private String msg_id;
    private int random_min;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public int getRandom_min() {
        return random_min;
    }

    public void setRandom_min(int random_min) {
        this.random_min = random_min;
    }

    public static class BodyBean {
        /**
         * after_open : go_app
         * play_lights : true
         * play_sound : true
         * play_vibrate : true
         * text : 学习传感器测试消息d2
         * ticker : 学习传感器测试消息t1
         * title : 学习传感器测试消息t1
         */

        private String after_open;
        private String play_lights;
        private String play_sound;
        private String play_vibrate;
        private String text;
        private String ticker;
        private String title;

        public String getAfter_open() {
            return after_open;
        }

        public void setAfter_open(String after_open) {
            this.after_open = after_open;
        }

        public String getPlay_lights() {
            return play_lights;
        }

        public void setPlay_lights(String play_lights) {
            this.play_lights = play_lights;
        }

        public String getPlay_sound() {
            return play_sound;
        }

        public void setPlay_sound(String play_sound) {
            this.play_sound = play_sound;
        }

        public String getPlay_vibrate() {
            return play_vibrate;
        }

        public void setPlay_vibrate(String play_vibrate) {
            this.play_vibrate = play_vibrate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ExtraBean {
        /**
         * address : 燃气泄漏报警通知 地址：凯宾斯基C座801
         * des : 学习红外探测器成功
         * name : 主机364
         * sensorId : 1
         * status : 1
         * type : sensor_learn
         */

        private String address;
        private String des;
        private String name;
        private int sensorId;
        private int status;
        private String type;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSensorId() {
            return sensorId;
        }

        public void setSensorId(int sensorId) {
            this.sensorId = sensorId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
