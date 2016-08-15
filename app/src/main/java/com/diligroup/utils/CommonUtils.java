package com.diligroup.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.diligroup.R;
import com.diligroup.view.DividerItemDecoration;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

import java.lang.reflect.Field;

/**
 * Created by 郝九凤 on 2016/7/7.
 */
public class CommonUtils {
    /**
     * 验证手机号格式
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9;新增4，7
    */
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
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

    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    public static void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                2f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                2f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                2f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }

    public static void showCategoryIcon(int code, ImageView dishIcon, boolean isSelected) {
        switch (code) {
            case 10001://烘焙
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.baking_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.bading_normal);
                }
                break;
            case 10002://坚果
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.nut_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.nut_normal);
                }
                break;
            case 10003://冷热饮
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.hotandcode_drink_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.hotandcodedrindk_normal);
                }
                break;
            case 10004://凉荤菜
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.cold_meal_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.cold_meal_normal);
                }
                break;
            case 10005://凉素菜
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.cold_vegetable_dish_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.cold_vegetable_dish_normal);
                }
                break;
            case 10006://热菜半荤
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.semi_hot_meat_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.semi_hot_meat_normal);
                }
                break;
            case 10007://热菜大荤
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.dish_meat_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.dish_meat_normal);
                }
                break;
            case 10008://热素菜
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.hot_su_food_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.hot_su_food_normal);
                }
                break;
            case 10009://水果
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.fruits_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.fruit_normal);
                }

                break;
            case 10010://糖粥
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.soup_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.soup_normal);
                }
                break;
            case 10011://主食
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.staple_food_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.staple_food_normal);
                }
                break;
            case 10012://酱腌小菜
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.staple_food_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.staple_food_normal);
                }
                break;
            case 10013://调味酱汁
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.tiaoweizhi_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.tiaoweizhi_normal);
                }
                break;
            case 10014://其他类
                if (isSelected) {
                    dishIcon.setImageResource(R.mipmap.staple_food_pressed);
                } else {
                    dishIcon.setImageResource(R.mipmap.staple_food_normal);
                }
                break;
        }
    }
    public static void initRerecyelerView(Context mContext,RecyclerView leftListView){
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mContext, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setWidth(CommonUtils.px2dip(mContext, 1));
        leftListView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        leftListView.setHasFixedSize(true);//保持固定大小，提高性能
    }
}
