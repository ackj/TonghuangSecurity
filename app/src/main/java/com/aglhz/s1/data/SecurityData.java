package com.aglhz.s1.data;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.SecurityBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/15 0015 17:07.
 * Email: liujia95me@126.com
 */

public class SecurityData {

    private static List<SecurityBean> allSecuritys;//所有探测器
    private static LinkedList<SecurityBean> alreadyAddSecuritys;//已添加的探测器

    private static SecurityData INSTANCE;

    public static SecurityData getInstance() {
        if (INSTANCE == null) {
            synchronized (SecurityData.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SecurityData();
                    allSecuritys = new ArrayList<>();
                    alreadyAddSecuritys = new LinkedList<>();
                    initData();
                }
            }
        }
        return INSTANCE;
    }

    private static void initData() {
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_doortection180px, "门槛"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_infrareddetectors_180px, "红外"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_ceilinginfrared_180px, "红外对射"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_brokenglass_180px, "玻璃破碎"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_leakdetection_180px, "漏水"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_sos2_180px, "紧急按钮"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_gassolenoidvalvegswsd01_180px, "气体"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_smokedetectorgswsd02_180px, "烟雾"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_rfid_180px, "RFID键盘"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_smokedetectorgswsd04_180px, "C0"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_curtaincurtaininfrared_180px, "温度"));
        allSecuritys.add(new SecurityBean(R.drawable.ic_equipment_vibrationdetectors_180px, "震动"));

        alreadyAddSecuritys.addAll(allSecuritys);
        alreadyAddSecuritys.addLast(new SecurityBean(R.drawable.ic_add_security_140px, "添加探测器"));
    }

    public List<SecurityBean> getAllSecuritys() {
        return allSecuritys;
    }

    public List<SecurityBean> getAlreadyAddSecuritys() {
        return alreadyAddSecuritys;
    }

    public void addSecurity(SecurityBean bean) {
        alreadyAddSecuritys.addFirst(bean);
    }

}
