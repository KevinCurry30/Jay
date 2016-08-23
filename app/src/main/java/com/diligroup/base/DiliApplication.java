package com.diligroup.base;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.diligroup.other.LocationService;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by Kevin on 2016/7/4.
 */
public class DiliApplication extends Application {
    public LocationService locationService;
    private static DiliApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        locationService = new LocationService(getApplicationContext());
        SDKInitializer.initialize(this);
        //微信 appid appsecret
        PlatformConfig.setWeixin(Constant.WX_APPID, Constant.WX_APPSECRET);
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo(Constant.WB_APPID,Constant.WB_KEY);
        //支付宝分享 appId
        PlatformConfig.setAlipay("2016081601755137");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(Constant.QQ_APPID,Constant.QQ_KEY);

        CrashHandler.getInstance().setCustomCrashInfo(this);

//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",APP_ID,APP_PRIVATE_KEY,"json","GBK",ALIPAY_PUBLIC_KEY);
//        alipayClient.execute()
    }
    public static Context getContext() {
        return instance;
    }
}
