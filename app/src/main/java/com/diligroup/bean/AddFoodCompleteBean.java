package com.diligroup.bean;

/**
 * Created by hjf on 2016/8/15.
 * 添加菜品完成调用接口入参bean
 */
public class AddFoodCompleteBean {
    //dishesCode：菜品编码,
//    weight:总重量;
//    wayType“:菜品来源（1自定义，2来自门店）
//    imageUrl:菜品图片路径
//    dishesName:菜品名称
    private String dishesCode;
    private String weight;
    private String wayType;
    private String imageUrl;
    private String dishesName;

    public String getDishesCode() {
        return dishesCode;
    }

    public void setDishesCode(String dishesCode) {
        this.dishesCode = dishesCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWayType() {
        return wayType;
    }

    public void setWayType(String wayType) {
        this.wayType = wayType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }
}
