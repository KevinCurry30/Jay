package com.diligroup.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class GetCheckStateUtils {
    public static List<String> selectList=new ArrayList<>();
    public static void addSelect(String  select){
        selectList.add(select);
    }

    public static void removeSelect(String unSelect){
        if (selectList.size()>0){
            for (int i = 0; i < selectList.size(); i++) {
                if (selectList.get(i).equals(unSelect)) {
                    selectList.remove(unSelect);
                }
            }
        }
    }
    public static boolean  getSpecialState(int position)
    {
        return  false;
    }
    public static void saveSpecialState(int  position,boolean isCheck){

    }

}
