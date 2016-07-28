package com.diligroup.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证码bean
 * Created by Kevin on 2016/7/20.
 */
public class ProvingCodeBean extends CommonBean {

    /**
     * id : 140214390093754271
     *         resultFlag : true
     *        smsCode : 646559
     */

    public SendResponseBean sendResponse;
    public class SendResponseBean {
        String id;
        boolean  resultFlag;
        String smsCode;
        /**
         *错误码
         */
        private String errCode;
        /**
         *错误原因
         */
        private String errMes;

        /**
         *账号剩余短信条数
         */
        private Integer num;

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrMes() {
            return errMes;
        }

        public void setErrMes(String errMes) {
            this.errMes = errMes;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isResultFlag() {
            return resultFlag;
        }

        public void setResultFlag(boolean resultFlag) {
            this.resultFlag = resultFlag;
        }

        public String getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(String smsCode) {
            this.smsCode = smsCode;
        }
    }
}
