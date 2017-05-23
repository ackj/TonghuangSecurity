package com.aglhz.s1.database;

/**
 * Author: LiuJia on 2017/5/6 0006 19:44.
 * Email: liujia95me@126.com
 */

public class ItemBean {

    public static final int TYPE_PROVINCE = 1;
    public static final int TYPE_CITY = 2;
    public static final int TYPE_COUNTY = 3;
    public static final int TYPE_STREET = 4;

    public int id;
    public int type;
    public String name;
    public boolean isSelect;
    public int selectPosition;

    public String getTableName() {
        String tableName = null;
        switch (type) {
            case TYPE_PROVINCE:
                tableName = "Province";
                break;
            case TYPE_CITY:
                tableName = "City";
                break;
            case TYPE_COUNTY:
                tableName = "County";
                break;
            case TYPE_STREET:
                tableName = "Street";
                break;
        }
        return tableName;
    }

    public String getNextTableName() {
        String tableName = null;
        switch (type) {
            case TYPE_PROVINCE:
                tableName = "City";
                break;
            case TYPE_CITY:
                tableName = "County";
                break;
            case TYPE_COUNTY:
                tableName = "Street";
                break;
            case TYPE_STREET:
                break;
        }
        return tableName;
    }

    public String getCoumnName() {
        String coumnName = null;
        switch (type) {
            case TYPE_PROVINCE:
                coumnName = "province_id";
                break;
            case TYPE_CITY:
                coumnName = "city_id";
                break;
            case TYPE_COUNTY:
                coumnName = "county_id";
                break;
        }
        return coumnName;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
