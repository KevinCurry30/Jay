package com.diligroup.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjf on 2016/7/13.
 */
public class DeviceUtils {


    private static String mImei, mMacAddress, mImsi;

    /**
     * 获取设备Model，可以用来判断设备型号
     *
     * @return
     */
    public static String getBuildModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取系统型号，eg：android_OS_4.1
     *
     * @return
     */
    public static String getVersionRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取SDK版本号
     *
     * @return
     */
    public static int getSdkInt() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取imei号，如果可以获取到，直接返回，如果无法获取，或是获取到的不是imei，则返回null
     * <ul>
     * <strong>Attentions:</strong>
     * <li>You should add <strong>android.permission.READ_PHONE_STATE</strong>
     * in manifest</li>
     * </ul>
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        if (!TextUtils.isEmpty(mImei)) {
            return mImei;
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        mImei = tm.getDeviceId();


        // 如果不是imei号，则返回空
        if (!isImei(mImei)) {
            mImei = null;
            return null;
        }
        return mImei;
    }

    /**
     * 判断是否是imei号
     *
     * @param imei
     * @return
     */
    private static boolean isImei(String imei) {
        if (TextUtils.isEmpty(imei)) {
            return false;
        }

        // 检查是否都是数字且在15个及以上
        // 使用正则表达式, 更直观
        Pattern pattern = Pattern.compile("[\\d]{15,}");
        Matcher matcher = pattern.matcher(imei);
        if (matcher.matches()) {
            return true;
        }

        return false;
    }

    /**
     * 获取mac地址
     * <ul>
     * <strong>Attentions:</strong>
     * <li>You should add <strong>android.permission.ACCESS_WIFI_STATE</strong>
     * in manifest</li>
     * </ul>
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        if (!TextUtils.isEmpty(mMacAddress)) {
            return mMacAddress;
        }
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        mMacAddress = info.getMacAddress();
        return mMacAddress;
    }

    /**
     * 获取手机制造商信息
     *
     * @return
     */
    public static String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取运营商sim卡imsi号
     */

    public static String getImsi(Context context) {
        if (!TextUtils.isEmpty(mImsi)) {
            return mImsi;
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        mImsi = tm.getSubscriberId();//获取手机IMSI号

        return mImsi;
    }

    public static String getVersion(Context context) {
        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "4.3";
        }
    }

    /**
     * 获得通知蓝的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 拨打电话
     * <li>You should add <strong>android.permission.ACCESS_WIFI_STATE</strong> in
     * manifest</li>
     * @param phoneNumber
     */
    public static void callPhone(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }

}
