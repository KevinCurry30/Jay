package com.diligroup.net;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hjf on 2016/6/27.
 */
public class Api {

    /**
     * 验证登陆后接口
     *
     * @param callback
     */
    public static void login(String mobileNum, String password, RequestManager.ResultCallback callback) {
        //密码md5加密
//        String encryptPassword = HashEncrypt.encode(HashEncrypt.CryptType.MD5, password);
        Map<String, Object> map = new HashMap<>();
        map.put("transCode", TransCode.LoginCode);
        map.put("type", "app_login");
        map.put("mobileNum", mobileNum);
        map.put("password", password);
        RequestManager.getInstance().postAsync(Action.LOGIN, map, callback);
    }

    /**
     * 注册接口
     * 交易码transCode: C0100
     * 交易类型type：add
     *
     * @param callback
     */
    public static void register(String mobileNum, String password, RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.RegistCode);
        map.put("type", "add");
        map.put("mobileNum", mobileNum);
        map.put("password", password);
        RequestManager.getInstance().getAsync(Action.REGISTER, map, callback);
    }

    /**
     * 获取手机验证码
     *  找回密码的验证码
     * @param phoneNum
     */
    public static void getCode(String phoneNum, String codeTpye,RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.SMSCode);
        map.put("type", "sendPhoneMes");
        map.put("mobileNum", phoneNum);
        map.put("mesType","1");
        map.put("bizType",codeTpye);
        RequestManager.getInstance().getAsync(Action.SMSCODE,map,callback);
    }
    /**
     * 获取首页轮播图
     * @param storeId
     *
     */

    public static void getBanner(String storeId,String isAllBanner, RequestManager.ResultCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("transCode", TransCode.BannerCode);
        map.put("type", "store");
        map.put("storeId", storeId);
        map.put("rotateWay",isAllBanner);
        RequestManager.getInstance().postAsync(Action.BANNER,map,callback);
    }
    /**
     * 菜品评价
     * @param storeId
     *transCode:C0110
    type:add
    userId: (用户ID - 选填)
    storeId: (门店ID - 必填)
    evalType: (评价类型 - 必填 1:菜品评价 2:服务评价)
    dishesCode: (菜品名称 - 必填)
    supplyDate (供应时间 - 必填)
    mealType: (餐别 - 必填)
    content: (内容 - 必填)
    tasteLevel: (口味评分 - 选填)
    costLevel: (性价比评分 - 选填)
    serverLevel: (服务评分 - 选填)
    orderNum: (订单号 - 选填)
    imageAdd: (晒图地址 - 选填)
    replyId: (回复人ID - 选填)
    parentId: (回复评论ID - 选填)
    String serverLevel,String orderNum,String imageAdd,String replyId,String parentId,String isAllBanner,
     */

    public static void dishVarietyEvaluate  (String userId,String storeId,String evalType,String dishesCode,String supplyDate,String mealType,String content,String tasteLevel,String costLevel, RequestManager.ResultCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("transCode", TransCode.DISHVARIETYEVALUATE);
        map.put("type", "add");
        map.put("userId", userId);
        map.put("storeId", storeId);
        map.put("evalType",evalType  );
        map.put("dishesCode",dishesCode);
        map.put("supplyDate",supplyDate);
        map.put("mealType",mealType);
        map.put("content",content);
        map.put("tasteLevel",tasteLevel);
        map.put("costLevel",costLevel);
//        map.put("serverLevel",serverLevel);
//        map.put("orderNum",orderNum);
//        map.put("imageAdd",imageAdd);
//        map.put("replyId",replyId);
//        map.put("parentId",parentId);

        RequestManager.getInstance().postAsync(Action.BANNER,map,callback);
    }
    /**
     * 修改密码
     */
    public static void modifyPsd(String phoneNum, String newPsd, RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.ModifyCode);
        map.put("type", "app_uPassword");
        map.put("mobileNum", phoneNum);
        map.put("newPassword",newPsd);
        RequestManager.getInstance().getAsync(Action.MODIFY,map,callback);
    }
    /**
     * 退出登陆
     */
    public static void loginOut(String phoneNum, RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.LoginOut);
        map.put("type", "app_logOut");
        map.put("mobileNum", phoneNum);
        RequestManager.getInstance().getAsync(Action.LOGINOUT,map,callback);
    }
    /**
     * 修改用户信息
     */
    public  static  void updataUserInfo(){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.updataUserInfos);
        map.put("type", "update");
        map.put("type", "update");

    }
    /**
     * 上传头像
     * @param fileName png jpeg
     */
    public  static  void upLoadPicture(String byteString ,String fileName,RequestManager.ResultCallback callback){
        Map<String,Object> map = new HashMap<>();
        map.put("byteString",byteString);
        map.put("fileName", fileName);
        RequestManager.getInstance().postAsync(Action.UPLOAD_PHOTO,map,callback);
    }
    /**
     * 上传头像完成后，完善信息
     * @param
     */
    public  static  void perfectInfoAfterUpLoad(String userId ,String headPhotoAdd,RequestManager.ResultCallback callback){
        Map<String, Object> map = new HashMap<>();
        map.put("transCode",TransCode.LoginCode);
        map.put("type","update");
        map.put("userId ",userId );
        map.put("headPhotoAdd", headPhotoAdd);
        RequestManager.getInstance().postAsync(Action.REPORT_USERINFOS,map,callback);
    }
    /**
     * 获取职业信息分类
     */
    public static void getWorkType(RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GetWorkType);
        map.put("type", "findAll1");
//        map.put("status", "1");
        RequestManager.getInstance().getAsync(Action.GET_WORK_TYPE,map,callback);

    }
    /**
     * P获取 饮食禁忌 食物 list
     */
    public static void getNoEatFood(RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GET_NOEAT_FOOD);
        map.put("type", "findAll");
        map.put("dictType","4");
//        map.put("currentPage", "1");
        map.put("status", "1");
//        map.put("pageSize", "10");
        RequestManager.getInstance().getAsync(Action.GET_NO_EAT,map,callback);


    }
    /**
     * 获取 过敏食材
     */
    public static void getAllergyFood(RequestManager.ResultCallback  callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GET_ALLERGY_FOOD);
        map.put("type", "findAllAllergy");
//        map.put("dictType","7");
//        map.put("currentPage", "1");
////        map.put("status", "1");
//        map.put("pageSize", "10");
        RequestManager.getInstance().getAsync(Action.GET_ALLERGY,map,callback);

    }

    /**
     * 获取 口味列表
     * @param callback
     */
    public static void getTaste(RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GET_TASTE);
        map.put("type", "findAll");
        map.put("dictType","7");
        map.put("currentPage", "1");
//        map.put("status", "1");
        map.put("pageSize", "10");
        RequestManager.getInstance().getAsync(Action.GET_TASTE,map,callback);
    }
    /**
     * 获取 特殊人群
     */
    public static void getSpecial(RequestManager.ResultCallback  callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GET_SPECIAL);
        map.put("type", "findAll");
        map.put("dictType","20");
        map.put("currentPage", "1");
//        map.put("status", "1");
        map.put("pageSize", "10");
        RequestManager.getInstance().getAsync(Action.GET_SPECIAL,map,callback);

    }

    /**
     * 获取健康史 api
     */
    public static void getHistory(RequestManager.ResultCallback callback){

        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GET_HISTORY);
        map.put("type", "findAll");
        map.put("dictType","24");
        map.put("currentPage", "1");
//        map.put("status", "1");
        map.put("pageSize", "10");
        RequestManager.getInstance().getAsync(Action.GET_HISTORY,map,callback);
    }
}
