package com.diligroup.bean;

/**
 * Created by Administrator on 2016/8/8.
 */
public class GetFoodDetailsBean extends CommonBean{

    /**
     * accessorysName :
     * carbohydrates : 0.01
     * dishesCode : H101001022
     * dishesLibId : 34
     * dishesName : 豆豉蒸排骨
     * energyKC : 2.87
     * fat : 0.24
     * mainFoodName : 猪小排
     * oilName : 色拉油
     * protein : 0.17
     * seasoningsName : 精盐,老抽,五香豆豉,绵白糖,料酒
     */

    private String accessorysName;
    private double carbohydrates;
    private String dishesCode;
    private int dishesLibId;
    private String dishesName;
    private double energyKC;
    private double fat;
    private String mainFoodName;
    private String oilName;
    private double protein;
    private String seasoningsName;

    public String getAccessorysName() {
        return accessorysName;
    }

    public void setAccessorysName(String accessorysName) {
        this.accessorysName = accessorysName;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getDishesCode() {
        return dishesCode;
    }

    public void setDishesCode(String dishesCode) {
        this.dishesCode = dishesCode;
    }

    public int getDishesLibId() {
        return dishesLibId;
    }

    public void setDishesLibId(int dishesLibId) {
        this.dishesLibId = dishesLibId;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public double getEnergyKC() {
        return energyKC;
    }

    public void setEnergyKC(double energyKC) {
        this.energyKC = energyKC;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getMainFoodName() {
        return mainFoodName;
    }

    public void setMainFoodName(String mainFoodName) {
        this.mainFoodName = mainFoodName;
    }

    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public String getSeasoningsName() {
        return seasoningsName;
    }

    public void setSeasoningsName(String seasoningsName) {
        this.seasoningsName = seasoningsName;
    }
}
