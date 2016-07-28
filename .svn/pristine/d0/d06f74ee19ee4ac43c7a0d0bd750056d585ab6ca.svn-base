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
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        CrashHandler.getInstance().setCustomCrashInfo(this);
    }
    public static Context getContext() {
        return instance;
    }
}
