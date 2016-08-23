package com.diligroup.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 * Created by Administrator on 2016/8/23.
 */
public class DBCheckState  extends SQLiteOpenHelper {
    public static String DB_STATE="state.db";
    public static String TABLE_NAME = "speaialPeople"; //表名

    public DBCheckState(Context context ,int version) {
        super(context,DB_STATE,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY,"
                + "position int not null,"
                + "state int not null);";
        Log.e("table oncreate", "create table");
        db.execSQL(sql); 		//创建表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
