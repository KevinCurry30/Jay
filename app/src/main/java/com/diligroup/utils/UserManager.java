package com.diligroup.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.diligroup.base.DiliApplication;

/**
 * Created by hjf on 2016/8/23.
 * 用户信息管理类
 */
public class UserManager {

    public static SharedPreferenceUtil spUtil;
    static SharedPreferences.Editor editor;
    private static UserManager instance;

    public UserManager() {
        spUtil = new SharedPreferenceUtil("user_config");
        editor = DiliApplication.getContext().getSharedPreferences("user_config", Context.MODE_PRIVATE).edit();
        editor.commit();
    }

    /**
     * 获取userId
     *
     * @return
     */
    public String getUserId() {
        String res = spUtil.getString("userId", "");
        return res;
    }

    /*
    * 获取phone
    * */
    public String getPhone() {
        String phone = spUtil.getString("phone", null);
        return phone;
    }

    /*
        *设置用户门店
        * */
    public void setStoreAddress(String storeAdress) {
        spUtil.putString("storeAdress", storeAdress);
    }

    /*
    * 获取用户门店地址
    * */
    public String getStoreAddress() {
        String storeAdress = spUtil.getString("storeAdress", null);
        return storeAdress;
    }

    /*
      *设置用户门店
      * */
    public void setStoreId(String storeId) {
        spUtil.putString("storeId", storeId);
    }

    /*
    * 获取用户门店地址
    * */
    public String getStoreId() {
        String storeId = spUtil.getString("storeId", null);
        return storeId;
    }

    /*
  * 获取用户头像
  * */
    public String getHeadUrl() {
        String headUrl = spUtil.getString("headUrl", null);
        return headUrl;
    }

    /*
* 获取用户昵称
* */
    public String getNickName() {
        String nickName = spUtil.getString("nickName", null);
        return nickName;
    }

    /*
* 获取用户性别
* */
    public String getSex() {
        String sex = spUtil.getString("sex", null);
        return sex;
    }

    //手机登录存储信息
    public void saveUser(String userId, String password, String phone, String headUrl, String nickName, String sex) {
        spUtil.putString("userId", userId);
        spUtil.putString("password", password);
        spUtil.putString("phone", phone);
        spUtil.putString("headUrl", headUrl);
        spUtil.putString("nickName", nickName);
        spUtil.putString("sex", sex);
    }

    /**
     * 清空user数据
     */
    public static void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 清空user VIPid数据
     */
    public static void clearVIPID() {
        editor.remove("userId");
        editor.commit();
    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public boolean isLogin() {
        if (!TextUtils.isEmpty(spUtil.getString("userId", ""))) {
            return true;
        } else {
            return false;
        }
    }

    public SharedPreferenceUtil getSpUtil() {
        if (spUtil != null) {
            return spUtil;
        }
        return null;
    }
}
