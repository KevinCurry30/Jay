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
        }
    }
}
