package com.diligroup.UserSet;

import android.widget.ImageView;

import com.diligroup.R;

/**
 * Created by Administrator on 2016/8/11.
 */
public class SetItemSelector {

    public static void setSelector(int position,boolean  isClicked,ImageView iv_icon){
        switch (position){
            case 0:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_gulei_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_gulei_normal);
                }
                break;
            case 1:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_dou_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_dou_normal);
                }
                break;
            case 2:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_shucai_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_shucai_normal);
                }
                break;
            case 3:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_fruit_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_fruit_normal);
                }                 break;

            case 4:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_jianguo_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_jianguo_normal);
                }                break;

            case 5:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_milk_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_milk_normal);
                }                break;

            case 6:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_egg_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_egg_normal);
                }                break;

            case 7:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_fish_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_fish_normal);
                }
                break;
            case 8:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_seafood_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_seafood_normal);
                }
                break;
            case 9:
                if (isClicked){
                    iv_icon.setImageResource(R.drawable.iv_tiaoliao_pressed);
                }else {
                    iv_icon.setImageResource(R.drawable.iv_tiaoliao_normal);
                }
                break;
        }
    }
}
