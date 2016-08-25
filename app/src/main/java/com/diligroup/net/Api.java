package com.diligroup.net;

import com.diligroup.base.Constant;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.utils.UserManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * 支付宝等第三方平台登陆接口
     * https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2016081601755137&redirect_uri=http://192.168.100.67:8180/tmpl/guide.html?
     *
     * @param callback
     */
    public static void threePartlogin(String plantForm, String uid, RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.RegistCode);
        map.put("type", "add");
        if (plantForm.equals("qq")) {
            map.put("qq", uid);
        } else if (plantForm.equals("wx")) {
            map.put("weChat", uid);
        } else if (plantForm.equals("wb")) {
            map.put("microblog", uid);
        } else if (plantForm.equals("alipay")) {
            map.put("alipay", uid);
        }
        RequestManager.getInstance().getAsync(Action.THIRD_PART_LOGIN, map, callback);
    }
    public static void alipaylogin(String plantForm, String uid, RequestManager.ResultCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("app_id","2016081601755137");
        map.put("redirect_uri", "http://192.168.100.67:8180/tmpl/guide.html?");
        RequestManager.getInstance().postAsync(Action.ALIPAY_LOGIN, map, callback);
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
     *
     * @param storeId transCode:C0110
     *                type:add
     *                userId: (用户ID - 选填)
     *                storeId: (门店ID - 必填)
     *                evalType: (评价类型 - 必填 1:菜品评价 2:服务评价)
     *                dishesCode: (菜品名称 - 必填)
     *                supplyDate (供应时间 - 必填)
     *                mealType: (餐别 - 必填)
     *                content: (内容 - 必填)
     *                tasteLevel: (口味评分 - 选填)
     *                costLevel: (性价比评分 - 选填)
     *                serverLevel: (服务评分 - 选填)
     *                orderNum: (订单号 - 选填)
     *                imageAdd: (晒图地址 - 选填)
     *                replyId: (回复人ID - 选填)
     *                parentId: (回复评论ID - 选填)
     *                String serverLevel,String orderNum,String imageAdd,String replyId,String parentId,String isAllBanner,
     */

    public static void dishVarietyEvaluate(String userId, String storeId, String evalType, String dishesCode, String supplyDate, String mealType, String content, String tasteLevel, String costLevel,String serviceStar, RequestManager.ResultCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("transCode", TransCode.DISHVARIETYEVALUATE);
        map.put("type", "add");
        map.put("userId", userId);
        map.put("storeId", storeId);
        map.put("evalType", evalType);
        map.put("dishesCode", dishesCode);
        map.put("supplyDate", supplyDate);
        map.put("mealType", mealType);
        map.put("content", content);
        map.put("tasteLevel", tasteLevel);
        map.put("costLevel", costLevel);
        map.put("serverLevel",serviceStar);
//        map.put("orderNum",orderNum);
//        map.put("imageAdd",imageAdd);
//        map.put("replyId",replyId);
//        map.put("parentId",parentId);

        RequestManager.getInstance().postAsync(Action.BANNER, map, callback);
    }
    /**
     * 修改密码
     */
    public static void modifyPsd(String phoneNum, String newPsd, RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.ModifyCode);
        map.put("type", "app_uPassword");
        map.put("mobileNum", phoneNum);
        map.put("newPassword", newPsd);
        RequestManager.getInstance().getAsync(Action.MODIFY, map, callback);
    }
    /**
     * 退出登陆
     */
    public static void loginOut(String phoneNum, RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.LoginOut);
        map.put("type", "app_logOut");
        map.put("mobileNum", phoneNum);
        RequestManager.getInstance().getAsync(Action.LOGINOUT,map,callback);
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
        RequestManager.getInstance().postAsync(Action.SET_INFOS,map,callback);
    }
    /**
     * 生理期，完善信息
     *String periodNum = "";			// 生理期间隔天数
     String periodStartTime = "";			// 生理期开始时间
     String periodEndTime = "";
     * @param
     */
    public static void perfectInfoAfterPeriod(String userId, String periodNum,String periodStartTime,String periodEndTime, RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.LoginCode);
        map.put("type", "update");
        map.put("userId ", userId);
        map.put("periodNum", periodNum);
        map.put("periodStartTime",periodStartTime);
        map.put("periodEndTime",periodEndTime);
        RequestManager.getInstance().getAsync(Action.SET_INFOS, map, callback);
    }
    /**
     * 第三方登录，完善信息
     *
     * @param
     */
    public static void perfectInfoAfterThirdLogin(String userId, String headPhotoAdd,String nickName,String sex, RequestManager.ResultCallback callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("transCode", TransCode.LoginCode);
        map.put("type", "update");
        map.put("userId ", userId);
        map.put("headPhotoAdd", headPhotoAdd);
        map.put("userName",nickName);
        map.put("sex",sex);
        RequestManager.getInstance().postAsync(Action.SET_INFOS, map, callback);
    }
    /**
     * 获取职业信息分类
     */
    public static void getWorkType(RequestManager.ResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GetWorkType);
        map.put("type", "findAll1");
        map.put("status", "1");
        RequestManager.getInstance().getAsync(Action.GET_WORK_TYPE,map,callback);

    }

    /**
     * 获取 籍贯信息
     */
    public static void getCity(RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.GetCity);
        map.put("type", "appAll");
        RequestManager.getInstance().getAsync(Action.GET_WHERE,map,callback);
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
     * 获取 过敏食材 大分类
     */
//    public static void getAllergyFood(RequestManager.ResultCallback  callback){
//        Map<String, String> map = new HashMap<>();
//        map.put("transCode", TransCode.GET_ALLERGY_FOOD);
//        map.put("type", "findAllAllergy");
////        map.put("dictType","7");
////        map.put("currentPage", "1");
//////        map.put("status", "1");
////        map.put("pageSize", "10");
//        RequestManager.getInstance().getAsync(Action.GET_ALLERGY,map,callback);
//
//    }

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
    /**
     * 获取首页门店供应列表
     */
    public static void homeStoreSupplyList(String storeId,String templateDate,String mealTypeCode,String dishesTypeCode,String currentPage,RequestManager.ResultCallback callback){

        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.HOME_LIST);
        map.put("type", "findAll");
        map.put("storeId",storeId);
        map.put("templateDate", templateDate);
        map.put("mealTypeCode", mealTypeCode);
//        map.put("dishesTypeCode", dishesTypeCode);
//        map.put("currentPage", currentPage);
//        map.put("pageSize", "10");
//        map.put("flag", "1");
        RequestManager.getInstance().getAsync(Action.GET_HOME_LIST,map,callback);
    }
    /**
     * 获取首页门店供应搜索
     */
    public static void storeSupplySearch(String custId,String templateDate,String dishesName,RequestManager.ResultCallback callback) throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("transCode", TransCode.HOME_LIST);
        map.put("type", "findAll");
        map.put("custId",custId);
        map.put("templateDate", templateDate);
        map.put("dishesName",dishesName);
        RequestManager.getInstance().getSync(Action.STORESUPPLY_SEARCH,map,callback);
    }
    /**
     * 设置用户信息
     */
    public  static  void setUserInfo(RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("userId",UserManager.getInstance().getUserId());
        map.put("transCode", TransCode.updataUserInfos);
        map.put("type","update");
        map.put("mobileNum", UserManager.getInstance().getPhone());
        map.put("job",UserInfoBean.getInstance().getJob());
        map.put("jobType ",UserInfoBean.getInstance().getJobType());
        map.put("birthday", UserInfoBean.getInstance().getBirthday());
        map.put("sex ",String.valueOf(UserInfoBean.getInstance().getSex()));
        map.put("height", UserInfoBean.getInstance().getHeight());
        map.put("weight", UserInfoBean.getInstance().getWeight());
        map.put("tabooCode", UserInfoBean.getInstance().getNoEatFood());
        map.put("allergyFood",UserInfoBean.getInstance().getAllergyFood());
        map.put("homeAdd", UserInfoBean.getInstance().getHomeDistrictId());
        map.put("currentAddress",UserInfoBean.getInstance().getCurrentAddress());
        map.put("jobType",UserInfoBean.getInstance().getJobType());
        map.put("taste",UserInfoBean.getInstance().getTaste());
        map.put("chronicDiseaseCode", UserInfoBean.getInstance().getChronicDiseaseCode());
        map.put("specialCrowdCode", UserInfoBean.getInstance().getSpecialCrowdCode());
        map.put("otherReq", UserInfoBean.getInstance().getOtherReq());
        map.put("periodStartTime",UserInfoBean.getInstance().getPeriodStartTime());
        map.put("periodEndTime", UserInfoBean.getInstance().getPeriodEndTime());
        RequestManager.getInstance().getAsync(Action.SET_INFOS,map,callback);

    }

    /**
     * 根据大分类 获取 过敏食材小分类
     */
    public static void getAllergyDetails(String  foodName ,RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("type","findAll");
        map.put("status","1");
        map.put("allergyType1",foodName);
        map.put("transCode", TransCode.GET_ALLERGY_DETAILS);
        RequestManager.getInstance().getAsync(Action.GET_ALLERGY_DETAILS,map,callback);
    }

    /**
     * 获取菜品详情
     * @param dishesCode
     * @param callback
     */
    public static void getFoodDetails(String dishesCode ,RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("type","findDetail");
        map.put("dishesCode",dishesCode );
//        map.put("allergyType1",allergyTypeId);
        map.put("transCode", TransCode.GET_DETAILS);
        RequestManager.getInstance().getAsync(Action.GET_DETAILS,map,callback);

    }

    /**
     * 获取自定义菜品成品分类
     * @param callback
     * transCode=M0038&type=findAll&dictType=1
     */
    public static void getCustomerFoodList(RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("type","findAll");
        map.put("transCode",TransCode.GET_COSTOMER_FOOD_LIST );
        map.put("dictType","1");
        RequestManager.getInstance().getAsync(Action.GET_COSTOMER_FOODLIST,map,callback);
    }

    /**
     * 获取 饮食记录
     * @param time
     * @param callback
     */
    public static void getDietRecord(String time ,RequestManager.ResultCallback  callback){
        Map<String, String> map = new HashMap<>();
        map.put("type","findAll");
        map.put("transCode",TransCode.GET_DIET_RECORD );
        map.put("time",time);

        map.put("userId",UserManager.getInstance().getUserId());
        RequestManager.getInstance().getAsync(Action.GET_DIET_RECORD,map,callback);
    }

    /**
     * 自定义搜索
     * @param cityCode
     * @param dishName
     * @param callback
     * @throws IOException
     */
    public static void customer_search(String cityCode, String dishName, RequestManager.ResultCallback  callback) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("type","findAll");
        map.put("transCode",TransCode.CUSTOMER_SEARCH );
        map.put("sortCode",cityCode);
        map.put("status","1");
        map.put("dishesName",dishName);
        RequestManager.getInstance().getAsync(Action.CUSTOMER_SEARCH,map,callback);
    }
    /**
     * 添加菜品完成接口
     * @param callback
     * @throws IOException
     */
    public static void addFoodComplete(String userId, String mealType,String list, RequestManager.ResultCallback  callback){
        Map<String, String> map = new HashMap<>();
        map.put("type", "add");
        map.put("transCode", TransCode.ADD_FOOD_COMPLETE);
        map.put("userId", userId);
        map.put("mealType", mealType.substring(mealType.length() - 1));
        map.put("list", list);
        RequestManager.getInstance().getAsync(Action.ADD_FOOD_COMPLETE, map, callback);
    }
    /**
     * 获取自定义菜品根据成品分类id查询接口
     * @param callback
     * transCode:M0021
     * sortCode=230100 省市区code
     * mealCode 餐别：早餐20001，午餐20002，晚餐20003，夜宵20004，加餐	20005
     * typeCode 成品分类编号
     */
    public static void findFoodByCategoryId(String mealType ,String typeCode ,RequestManager.ResultCallback callback){
        Map<String, String> map = new HashMap<>();
        map.put("type","findByCondition");
        map.put("transCode",TransCode.CUSTOMER_FINDBY_CATEGORY);
        map.put("currentPage","1");
        map.put("pageSize",Constant.pageSize);
        map.put("sortCode",Constant.cityCode);
        map.put("mealType",mealType);
        map.put("typeCode",typeCode);
        RequestManager.getInstance().getAsync(Action.CUSTOMER_FIND_BY_CATEGORYID,map,callback);
    }
    /**
     * 更新用户 个人信息  单条
     */
    public static void updataUserInfo(Map map,RequestManager.ResultCallback  callback){
        if (map!=null){
            map.put("transCode",TransCode.updataUserInfos);
            map.put("type","update");
            map.put("mobileNum",UserManager.getInstance().getPhone());
            map.put("userId",UserManager.getInstance().getUserId());
            RequestManager.getInstance().getAsync(Action.UPDATA_USERINFO,map,callback);

        }
    }

    /**
     * 查询第三方账号是否是已注册用户
     */
    public static void selectUserInfo(String plantForm,String uid,RequestManager.ResultCallback  callback){
        Map<String, String> map = new HashMap<>();
        if (map!=null){
            map.put("transCode",TransCode.updataUserInfos);
            map.put("type","findDetail");
            if (plantForm.equals("qq")) {
                map.put("qq", uid);
            } else if (plantForm.equals("wx")) {
                map.put("weChat", uid);
            } else if (plantForm.equals("wb")) {
                map.put("microblog", uid);
            } else if (plantForm.equals("alipay")) {
                map.put("alipay", uid);
            }
            RequestManager.getInstance().getAsync(Action.SELECT_USER_INFO,map,callback);

        }
    }

    /**
     * 获取 附近门店
     */
    public static void getShopNearBy(Map map,RequestManager.ResultCallback callback){

        map.put("transCode",TransCode.GETSHOP);
        map.put("type","findAll");
        RequestManager.getInstance().getAsync(Action.GET_SHOP_NEARBY,map,callback);

    }

    /**
     *根据 城市名字 获取 code码
     * @param cityName
     * @param callback
     */
    public static void getCityCode(String cityName,RequestManager.ResultCallback callback){
        Map  map =new HashMap();
        map.put("transCode",TransCode.GETCITYCODE);
        map.put("type","detail");
        map.put("name",cityName);
        RequestManager.getInstance().getAsync(Action.Get_CityCode,map,callback);

    }
}
