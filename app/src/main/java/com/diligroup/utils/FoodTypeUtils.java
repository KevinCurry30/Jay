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
        GetFoodTypeBean  shucaiBean=new GetFoodTypeBean();
        shucaiBean.setFoodTypeName("蔬菜类");
        shucaiBean.setFoodTypeId("2");
        shucaiBean.setClicked(false);
        foodTypeList.add(shucaiBean);
        GetFoodTypeBean  fruitBean=new GetFoodTypeBean();
        fruitBean.setFoodTypeName("水果类");
        fruitBean.setFoodTypeId("3");
        fruitBean.setClicked(false);
        foodTypeList.add(fruitBean);
        GetFoodTypeBean  nutBean=new GetFoodTypeBean();
        nutBean.setFoodTypeName("坚果类");
        nutBean.setFoodTypeId("4");
        nutBean.setClicked(false);
        foodTypeList.add(nutBean);
        GetFoodTypeBean  milkBean=new GetFoodTypeBean();
        milkBean.setFoodTypeName("乳制品类");
        milkBean.setFoodTypeId("5");
        milkBean.setClicked(false);
        foodTypeList.add(milkBean);
        GetFoodTypeBean  eggBean=new GetFoodTypeBean();
        eggBean.setFoodTypeName("蛋类");
        eggBean.setFoodTypeId("6");
        eggBean.setClicked(false);
        foodTypeList.add(eggBean);
        GetFoodTypeBean  fishBean=new GetFoodTypeBean();
        fishBean.setFoodTypeName("鱼类");
        fishBean.setFoodTypeId("7");
        fishBean.setClicked(false);
        foodTypeList.add(fishBean);
        GetFoodTypeBean  seaFoodBean=new GetFoodTypeBean();
        seaFoodBean.setFoodTypeName("海鲜类");
        seaFoodBean.setFoodTypeId("8");
        eggBean.setClicked(false);
        foodTypeList.add(seaFoodBean);
        GetFoodTypeBean  tiaoliaoBean=new GetFoodTypeBean();
        tiaoliaoBean.setFoodTypeName("调料类");
        tiaoliaoBean.setFoodTypeId("9");
        tiaoliaoBean.setClicked(false);
        foodTypeList.add(tiaoliaoBean);
     return foodTypeList;
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


    }
}
