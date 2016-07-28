package com.diligroup.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by hjf on 2016/7/7.
 */
public class EventBusBean implements Serializable{
    private int code;
    private String detailInfo;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDetailInfo() {
        return detailInfo;
    }
    public EventBusBean(int code,String detailInfo){
        this.code=code;
        this.detailInfo=detailInfo;
    }
    public EventBusBean(){
    }
    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
