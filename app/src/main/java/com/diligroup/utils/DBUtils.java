package com.diligroup.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.diligroup.bean.CBState;

/**
 *
 * Created by Administrator on 2016/8/23.
 */
public class DBUtils {
    private DBCheckState  dbHelper;

    public DBUtils(Context mContext) {
        this.dbHelper = new DBCheckState(mContext,2);
    }
    public boolean Insert(CBState state){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql = "insert into "+DBCheckState.TABLE_NAME
                +"(position,state) values ("
                + "'"+state.getPosition()
                + "' ," + "'"+ state.getIsCheck() + "'" + ")";
        try {
            db.execSQL(sql);
            return true;
        } catch (SQLException e){
            Log.e("err", "insert failed");
            return false;
        }finally{
            db.close();
        }
    }


    /**更新数据
     * @param   , int id
     * */

    public void Update(CBState state ,int id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("position", state.getPosition());
        values.put("state", state.getIsCheck());
        int rows = db.update(dbHelper.TABLE_NAME, values, "_id=?", new String[] { id + "" });

        db.close();
    }
    /**删除数据
     * @param
     * */

    public void Delete(int id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int raw = db.delete(DBCheckState.TABLE_NAME, "_id=?", new String[]{id+""});
        db.close();
    }
}
