package com.diligroup.net;

import com.diligroup.bean.BannerBean;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.GetFoodTypeBean;
import com.diligroup.bean.GetHistoryBean;
import com.diligroup.bean.GetJobBean;
import com.diligroup.bean.GetSpecialBean;
import com.diligroup.bean.GetJiaoQinBean;
import com.diligroup.bean.ProvingCodeBean;

/**
 * Created by cwj on 2016/4/5.
 */
public enum Action {
    /*登陆*/
    LOGIN(Urls.LOGIN),
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
    /*上报更新用户信息*/
    REPORT_USERINFOS(Urls.UPDATA_USERINFOS),
    GET_WORK_TYPE(Urls.GET_WORK_TYPE),
    GET_NO_EAT(Urls.GET_NO_EAT),
    GET_ALLERGY(Urls.GET_ALLERGY),
    GET_OTHER(Urls.BASE),
    GET_SPECIAL(Urls.BASE),
    GET_TASTE(Urls.BASE),
    GET_HISTORY(Urls.BASE);
    /**
     * 根据Action获取解析类
     *
     * @param action
     * @return
     */
    public static Class getAction(Action action) {
        switch (action) {
            case LOGIN:
                return CommonBean.class;
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
            case REPORT_USERINFOS:
                return CommonBean.class;
            case UPLOAD_PHOTO:
                return CommonBean.class;
            case GET_WORK_TYPE:
                return GetJobBean.class;
            case GET_NO_EAT:
                return GetJiaoQinBean.class;
            case GET_ALLERGY:
                return  GetFoodTypeBean.class;
            case GET_OTHER:
                return  CommonBean.class;
            case GET_SPECIAL:
                return GetJiaoQinBean.class;
            case GET_TASTE:
                return GetJiaoQinBean.class;
            case GET_HISTORY:
                return GetJiaoQinBean.class;
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
