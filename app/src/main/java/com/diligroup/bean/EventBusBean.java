package com.diligroup.bean;

import java.io.Serializable;

/**
 * Created by hjf on 2016/7/7.
 */
public class EventBusBean implements Serializable{
    private int code;
    private String detailInfo;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
