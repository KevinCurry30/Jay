package com.diligroup.net;

/**
 * Created by hjf on 2016/6/27 0027.
 */
public class Urls {
//    private static final String HOST="http://192.168.100.67:8181/gateway";

    private static final String HOST="http://192.168.101.70:8180/gateway";
    public  static String BASE=HOST + "/dis/prepose.action";

    /*登录*/
    public static String LOGIN = HOST + "/dis/prepose.action";
    /*注册*/
    public static String REGISTER = HOST + "/dis/prepose.action";

    /*获取验证码*/
    public static String SMSCODE = HOST + "/dis/prepose.action";
    /*修改密码*/
    public static String MODIFYPSD=HOST+"/dis/prepose.action";
    public static String LOGINOUT=HOST+"/dis/prepose.action";

    /*获取首页轮播图  */
    public static String GETBANNER=HOST+"/dis/prepose.action";
    public static String UPDATA_USERINFOS=HOST+"/dis/prepose.action";
    public static String DISHEVALUATE=HOST+"/dis/prepose.action";
    /* 上传头像 */
    public static String UPLOAD_PHOTO=HOST+"/dis/uploadByteArray.action";
    /*获取  公共职业信息列表数据*/
    public static String GET_WORK_TYPE=HOST+"/dis/prepose.action";
    /*获取  饮食禁忌的 食材*/
    public static String GET_NO_EAT=HOST+"/dis/prepose.action";
    /*获取过敏食材*/
    public static String GET_ALLERGY=HOST+"/dis/prepose.action";
}
