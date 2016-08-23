package com.diligroup.net;

import com.diligroup.bean.BannerBean;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.CostomerCategory;
import com.diligroup.bean.CustomerSearchResultBean;
import com.diligroup.bean.FindFoodByCategory;
import com.diligroup.bean.GetAllergyDetailBean;
import com.diligroup.bean.GetCityCode;
import com.diligroup.bean.GetDietRecordBean;
import com.diligroup.bean.GetFoodDetailsBean;
import com.diligroup.bean.GetFoodTypeBean;
import com.diligroup.bean.GetJiaoQinBean;
import com.diligroup.bean.GetJobBean;
import com.diligroup.bean.GetShopBean;
import com.diligroup.bean.GetWhereBean;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.bean.ProvingCodeBean;
import com.diligroup.bean.StoreSupplySearchBean;
import com.diligroup.bean.UploadInfo;
import com.diligroup.bean.UserBeanFromService;

/**
 * Created by cwj on 2016/4/5.
 */
public enum Action {
    /*登陆*/
    LOGIN(Urls.LOGIN),
    THIRD_PART_LOGIN(Urls.REGISTER),
    ALIPAY_LOGIN(Urls.alipaytUrl),
    /*注册*/
    REGISTER(Urls.REGISTER),
    /*获取验证码*/
    SMSCODE(Urls.SMSCODE),
    /*修改密码*/
    MODIFY(Urls.MODIFYPSD),
    BANNER(Urls.GETBANNER),
    /*登出*/
    LOGINOUT(Urls.LOGINOUT),
    /*菜品评价*/
    DISEVALUATE(Urls.DISHEVALUATE),
    /*上传头像*/
    UPLOAD_PHOTO(Urls.UPLOAD_PHOTO),
    /*获取首页门店供应列表*/
    GET_HOME_LIST(Urls.GET_HOMELIST),
    /*上报更新用户信息*/
    SET_INFOS(Urls.UPDATA_USERINFOS),
    GET_WORK_TYPE(Urls.GET_WORK_TYPE),
    GET_WHERE(Urls.BASE),
    GET_NO_EAT(Urls.GET_NO_EAT),
    GET_ALLERGY(Urls.GET_ALLERGY),
    GET_OTHER(Urls.BASE),
    GET_SPECIAL(Urls.BASE),
    GET_TASTE(Urls.BASE),
    GET_HISTORY(Urls.BASE),
    GET_ALLERGY_DETAILS(Urls.BASE),
    /*自定义菜品成品分类*/
    GET_COSTOMER_FOODLIST(Urls.GET_COSTOMER_FOOD_LIST),
    /**
     * 获取饮食记录
     */
    GET_DIET_RECORD(Urls.BASE),
    /*自定义菜品搜索*/
    CUSTOMER_SEARCH(Urls.GET_COSTOMER_FOOD_LIST),
    /*添加菜品完成接口*/
    ADD_FOOD_COMPLETE(Urls.BASE),
    /*门店供应搜索*/
    STORESUPPLY_SEARCH(Urls.GET_HOMELIST),
    SELECT_USER_INFO(Urls.LOGIN),
    CUSTOMER_FIND_BY_CATEGORYID(Urls.CUSTOMER_FINDBY_CATEGORYID),
    /**
     * 获取菜品详情
     */
    GET_DETAILS(Urls.BASE),
    /**
     * 获取 门店附近的
     */
    GET_SHOP_NEARBY(Urls.BASE),
Get_CityCode(Urls.BASE),

    UPDATA_USERINFO(Urls.BASE);

    /**
     * 根据Action获取解析类
     *
     * @param action
     * @return
     */
    public static Class getAction(Action action) {
        switch (action) {
            case LOGIN:
                return UserBeanFromService.class;
            case REGISTER:
                return CommonBean.class;
            case SMSCODE:
                return ProvingCodeBean.class;
            case MODIFY:
                return CommonBean.class;
            case BANNER:
                return BannerBean.class;
            case LOGINOUT:
                return CommonBean.class;
            case DISEVALUATE:
                return CommonBean.class;
            case SET_INFOS:
                return CommonBean.class;
            case UPLOAD_PHOTO:
                return UploadInfo.class;
            case GET_WORK_TYPE:
                return GetJobBean.class;
            case GET_NO_EAT:
                return GetJiaoQinBean.class;
            case GET_ALLERGY:
                return GetFoodTypeBean.class;
            case GET_OTHER:
                return CommonBean.class;
            case GET_SPECIAL:
                return GetJiaoQinBean.class;
            case GET_TASTE:
                return GetJiaoQinBean.class;
            case GET_HISTORY:
                return GetJiaoQinBean.class;
            case GET_HOME_LIST:
                return HomeStoreSupplyList.class;
            case GET_ALLERGY_DETAILS:
                return GetAllergyDetailBean.class;
            case GET_DETAILS:
                return GetFoodDetailsBean.class;
            case GET_COSTOMER_FOODLIST:
                return CostomerCategory.class;
            case GET_DIET_RECORD:
                return  GetDietRecordBean.class;
            case CUSTOMER_SEARCH:
                return  CustomerSearchResultBean.class;
            case CUSTOMER_FIND_BY_CATEGORYID:
                return FindFoodByCategory.class;
            case STORESUPPLY_SEARCH:
                return StoreSupplySearchBean.class;
            case ADD_FOOD_COMPLETE:
                return CommonBean.class;
            case UPDATA_USERINFO:
                return  CommonBean.class;
            case GET_WHERE:
                return GetWhereBean.class;
            case GET_SHOP_NEARBY:
                return  GetShopBean.class;
            case THIRD_PART_LOGIN:
                return UserBeanFromService.class;
            case ALIPAY_LOGIN:
                return CommonBean.class;
            case SELECT_USER_INFO:
                return UserBeanFromService.class;
            case Get_CityCode:
                return GetCityCode.class;
        }
        return null;
    }

    /**
     * 获取枚举的值 url
     *
     * @param action
     * @return
     */
    public static String getUrl(Action action) {
        return action.getValue();
    }

    private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
