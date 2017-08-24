package com.aglhz.s1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.itsite.abase.log.ALog;

public class DBManager {

    private static final String ASSETS_NAME = "area.db";
    private static final String DB_NAME = "area.db";
    private static final int BUFFER_SIZE = 1024;
    private String DB_PATH;
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + mContext.getPackageName() + File.separator + "databases" + File.separator;
        copyDBFile();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void copyDBFile() {
        File dir = new File(DB_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()) {
            InputStream is;
            OutputStream os;
            try {
                is = mContext.getResources().getAssets().open(ASSETS_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public List<ItemBean> query(ItemBean queryBean, String table) {
        ALog.d("DBManager", "-------------begin-------------");
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        table = table == null ? queryBean.getNextTableName() : table;
        ALog.d("DBManager", "itemBean:" + queryBean + " --- table:" + table);
        if (table == null) return null;

        String[] columns = {"id", "name"};
        String selection = null;
        String[] selectionArgs = null;
        if (queryBean != null) {
            selection = queryBean.getCoumnName() + "=?";
            selectionArgs = new String[]{queryBean.id + ""};
        }
        ALog.d("DBManager", "table:" + table + " --- selection:" + selection + " --- colums:" + columns + " --- selectionArgs:" + selectionArgs);
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);

        List<ItemBean> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            ItemBean itemBean = new ItemBean();
            itemBean.id = cursor.getInt(cursor.getColumnIndex("id"));
            itemBean.name = cursor.getString(cursor.getColumnIndex("name"));
            if (table.equals("Province")) {
                itemBean.type = ItemBean.TYPE_PROVINCE;
            } else if (table.equals("City")) {
                itemBean.type = ItemBean.TYPE_CITY;
            } else if (table.equals("County")) {
                itemBean.type = ItemBean.TYPE_COUNTY;
            } else if (table.equals("Street")) {
                itemBean.type = ItemBean.TYPE_STREET;
            }
            data.add(itemBean);
            ALog.d("DBManager", "---id:" + itemBean.id + " ---name:" + itemBean.name);
        }
        cursor.close();
        db.close();
        return data;
    }

    //查询
//    public City query(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
//        City city = null;
//        try {
//            String table = "city";
//            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
//            if (cursor.moveToFirst()) {
//                String parentCity = cursor.getString(cursor
//                        .getColumnIndex("parent"));
//                String phoneCode = cursor.getString(cursor.getColumnIndex("phone_code"));
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
//                String cityID = cursor.getString(cursor.getColumnIndex("posID"));
//                String areaCode = cursor.getString(cursor.getColumnIndex("area_code"));
//                city = new City(parentCity, name, pinyin, phoneCode, cityID, areaCode);
//                cursor.moveToNext();
//                cursor.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return city;
//    }
}