package com.diligroup.bean;

import java.io.Serializable;

/**
 * Created by hjf on 2016/7/13.
 */
public class CommonBean implements Serializable{

    /**
     * code : APP_C010005
     * message : 账号密码不匹配
     */

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
