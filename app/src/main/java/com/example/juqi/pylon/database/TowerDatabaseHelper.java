package com.example.juqi.pylon.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ylj_y on 2016/10/25.
 */
public class TowerDatabaseHelper extends SQLiteOpenHelper {
    //把建表语句定义为字符串常量，在execSQL执行时，能在创建数据库的同时，创建数据表
    public static final String CREATE_TOWER = "create table Tower ("
            + "_id integer primary key autoincrement,"
            + "lineName text,"
            + "towerNum text,"
            + "defectType text,"
            + "defectLevel text,"
            + "content text,"
            + "discoveryDate text,"
            + "recordPerson text,"
            + "isDel text,"
            + "delPerson text,"
            + "isPMS text,"
            + "voltageLevel text,"
            + "delTime text)";
    private Context mContext;

    //构造器
    public TowerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TOWER);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
