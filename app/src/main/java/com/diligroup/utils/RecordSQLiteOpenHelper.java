package com.diligroup.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diligroup.bean.HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjf on 2016/8/11.
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String STORESUPPLY_TABLE_NAME = "storesupply";
    public static final String CUSTOMER_TABLE_NAME = "customer";
    public static final int VERSION = 1;
    private static final String KEY_ID = "id ";
    private static final String KEY_NAME = "foodname";
    private static final String KEY_GRADE = "mainFood";//主要配料
    private static final String IMAGE_URL = "imageurl";
    public static final String TIME = "time";

    Context context;
    //建表语句
    private  final String CREATE_TABLE = "create table " + STORESUPPLY_TABLE_NAME + "(" + KEY_ID +
            "integer primary key autoincrement," + KEY_NAME + " text not null," +
            KEY_GRADE + " text not null, "+TIME+ " timestamp, "+ IMAGE_URL+" vachar);";

    //建表语句
    private  final String CREATE_TABLE_2 = "create table " + CUSTOMER_TABLE_NAME + "(" + KEY_ID +
            "integer primary key autoincrement," + KEY_NAME + " text not null," +
            KEY_GRADE + " text not null, "+TIME+" timestamp, "+ IMAGE_URL+" vachar);";


    public RecordSQLiteOpenHelper(Context context) {

        super(context, STORESUPPLY_TABLE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_2);
        ToastUtil.showLong(context,"数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STORESUPPLY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
/* 插入一条数据*/
    public void addFood(DishesSupplyDtlListBean food , String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(getFoodCounts(tableName)==6){
        deleteItem(food,food.getDishesName(),tableName);
        }

        //使用ContentValues添加数据
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getDishesName());
        values.put(KEY_GRADE, food.getIngredients().getFoodName());
        values.put(TIME,System.currentTimeMillis());
        values.put(IMAGE_URL,food.getDishesUrl());
;        db.insert(tableName, null, values);
        db.close();
    }
    /* 查询一条数据*/
    public DishesSupplyDtlListBean selectFood(String name,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor对象返回查询结果
        Cursor cursor = db.query(tableName, new String[]{KEY_ID, KEY_NAME, KEY_GRADE,TIME,IMAGE_URL},
                KEY_NAME + "=?", new String[]{name}, null, null, null, null);


        DishesSupplyDtlListBean student = null;
        //注意返回结果有可能为空
        if (cursor.moveToFirst()) {
//            student=new DishesSupplyDtlListBean(cursor.getInt(0),cursor.getString(1), cursor.getString(2));
            student = new DishesSupplyDtlListBean();
            student.setDishesName(cursor.getString(1));
            student.setMainFood(cursor.getString(2));
        }
        cursor.close();
        return student;
    }
    /* 查询总数量*/
    public int getFoodCounts(String tableName) {
        String selectQuery = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor.getCount();
    }

    //查找所有bean list
    public List<DishesSupplyDtlListBean> getALllFoods(String tableName) {
        //select * from table where .... order by XX desc limit 0,20;
        List<DishesSupplyDtlListBean> studentList = new ArrayList<DishesSupplyDtlListBean>();

        String selectQuery = "SELECT * FROM " + tableName+" order by time desc limit 0,6;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DishesSupplyDtlListBean student = new DishesSupplyDtlListBean();
//                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setDishesName(cursor.getString(1));
                student.setMainFood(cursor.getString(2));
                student.setImagesURL(cursor.getString(4));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return studentList;
    }

    //更新表中的莫一条数据
    public void updateFood(DishesSupplyDtlListBean student,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, student.getDishesName());
//        values.put(KEY_GRADE, student.getIngredients()==null ? "":student.getIngredients().getFoodName());
//        values.put(TIME,System.currentTimeMillis());
//        values.put(IMAGE_URL,student.getDishesUrl());
//        int temp=db.update(tableName, values, KEY_NAME + "=?", new String[]{String.valueOf(student.getDishesName())});
//        update test_table set age='28',hight='203' where name='ll'"
        db.execSQL("update "+tableName +" set time="+System.currentTimeMillis()+" where foodname="+student.getDishesName());
        db.close();

    }
    /* 删除表里面一条数据*/
    public void deleteItem(DishesSupplyDtlListBean student,String name,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, KEY_NAME + "=?", new String[]{name});
    }

    /**
     * 清空表中所有的数据
     */
    public void deleteAll(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+tableName);
        db.close();
    }
    /**
     * 表中添加一字段
     */
//    public void insertColumn(String time){
//        //alter table student add column name varchar;
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("alter table "+STORESUPPLY_TABLE_NAME+" add column "+time+" (varchar);");
//        db.execSQL("alter table "+ CUSTOMER_TABLE_NAME+" add column "+time+" (varchar);");
//        db.close();
//    }
}

