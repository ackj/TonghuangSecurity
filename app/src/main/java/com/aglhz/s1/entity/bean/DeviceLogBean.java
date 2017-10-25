package com.aglhz.s1.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/8/23 0023 11:01.
 * Email: liujia95me@126.com
 */

public class DeviceLogBean extends BaseBean {

    /**
     * data : {"logs":[{"des":"报警-地址：广东省惠州市惠城区惠州大道9号佳兆业中心佳兆业中心1期","fid":"a6b4a757-cd5a-4173-8e21-1c2a962bc990","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:45","title":"燃气站SOS报警","type":"gw_alarm_sos"},{"des":"主机布防由撤防变更为撤防状态","fid":"da4d46d9-3e77-4969-9694-39e1f145f21b","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:45","title":"主机布防状态变更通知","type":"gw_defense_satus"},{"des":"主机布防由撤防变更为撤防状态","fid":"d3886000-8673-46bf-b6cf-8058fddb03e2","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:38","title":"主机布防状态变更通知","type":"gw_defense_satus"},{"des":"报警-地址：广东省惠州市惠城区惠州大道9号佳兆业中心佳兆业中心1期","fid":"2fa77c7a-446b-452c-8912-c1e6fa224c13","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:37","title":"燃气站SOS报警","type":"gw_alarm_sos"},{"des":"主机布防由撤防变更为撤防状态","fid":"191a3f92-d0cc-473b-b9d5-4c3f86e76e15","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:33","title":"主机布防状态变更通知","type":"gw_defense_satus"},{"des":"报警-地址：广东省惠州市惠城区惠州大道9号佳兆业中心佳兆业中心1期","fid":"13e249cc-e986-4c0e-beed-87ae6eb82505","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:32","title":"燃气站SOS报警","type":"gw_alarm_sos"},{"des":"主机布防由撤防变更为撤防状态","fid":"ab6e074a-2509-4bdf-8b5e-c3c7be703a4e","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:22","title":"主机布防状态变更通知","type":"gw_defense_satus"},{"des":"报警-地址：广东省惠州市惠城区惠州大道9号佳兆业中心佳兆业中心1期","fid":"d7980771-e9bd-4b29-af68-2d97526cd1e1","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:21","title":"燃气站SOS报警","type":"gw_alarm_sos"},{"des":"主机布防由撤防变更为撤防状态","fid":"49a29151-1005-4521-b100-91d8e7178da9","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:10","title":"主机布防状态变更通知","type":"gw_defense_satus"},{"des":"报警-地址：广东省惠州市惠城区惠州大道9号佳兆业中心佳兆业中心1期","fid":"09532afe-e152-4659-b270-6eb5eccea33f","gatewayName":"300-kerree","gatewayNo":"G211EU6B1000300","logtime":"2017-10-11 16:31:09","title":"燃气站SOS报警","type":"gw_alarm_sos"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<LogsBean> logs;

        public List<LogsBean> getLogs() {
            return logs;
        }

        public void setLogs(List<LogsBean> logs) {
            this.logs = logs;
        }

        public static class LogsBean {
            /**
             * des : 报警-地址：广东省惠州市惠城区惠州大道9号佳兆业中心佳兆业中心1期
             * fid : a6b4a757-cd5a-4173-8e21-1c2a962bc990
             * gatewayName : 300-kerree
             * gatewayNo : G211EU6B1000300
             * logtime : 2017-10-11 16:31:45
             * title : 燃气站SOS报警
             * type : gw_alarm_sos
             */

            private String des;
            private String fid;
            private String gatewayName;
            private String gatewayNo;
            private String logtime;
            private String title;
            private String type;

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
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

            public String getLogtime() {
                return logtime;
            }

            public void setLogtime(String logtime) {
                this.logtime = logtime;
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
