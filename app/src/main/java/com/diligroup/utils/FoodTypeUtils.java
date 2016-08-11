package com.diligroup.utils;

import com.diligroup.bean.GetFoodTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class FoodTypeUtils {
    public static List<GetFoodTypeBean> GetFoodTypeList() {
        List<GetFoodTypeBean>  foodTypeList=new ArrayList<>();
       GetFoodTypeBean  guleiBean=new GetFoodTypeBean();
        guleiBean.setFoodTypeName("谷类");
        guleiBean.setFoodTypeId("0");
        guleiBean.setClicked(false);
        foodTypeList.add(guleiBean);
        GetFoodTypeBean  douleiBean=new GetFoodTypeBean();
        douleiBean.setFoodTypeName("豆类");
        douleiBean.setFoodTypeId("1");
        douleiBean.setClicked(false);
        foodTypeList.add(douleiBean);
//        type_list.add("谷类");
//        type_list.add("豆类");
//        type_list.add("蔬菜类");
//        type_list.add("水果类");
//        type_list.add("坚果类");
//        type_list.add("乳制品类");
//        type_list.add("蛋类");
//        type_list.add("鱼类");
//        type_list.add("海鲜类");
//        type_list.add("调料类");

        return foodTypeList;
    }
}
