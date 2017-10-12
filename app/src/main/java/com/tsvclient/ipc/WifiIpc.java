package com.tsvclient.ipc;

import cn.itsite.abase.log.ALog;

/**
 * 由金安科技公司提供，其路径必须为com.tsvclient.ipc.WifiIpc,否则无法调用其native方法。
 */
public class WifiIpc {
    public static String VERSION = "1.2";

    static {
        System.loadLibrary("tsvipc");//载入本地库
    }

    native private static String TSV_C_SendXmlCommand(String ipaddr, int port, int cmdId, int cmdIndex, String xmlData, String version);

    public static String TSV_C_SendXmlCommand(String ipaddr, int port, int cmdId, int cmdIndex, String xmlData) {
        ALog.e("ipc", "TSV_C_SendXmlCommand ip=" + ipaddr + " port=" + port + " version=" + VERSION);
        return TSV_C_SendXmlCommand(ipaddr, port, cmdId, cmdIndex, xmlData, VERSION);
    }
}
