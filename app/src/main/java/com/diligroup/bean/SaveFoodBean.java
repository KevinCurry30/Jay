package com.diligroup.bean;

import java.util.List;

/**
 * Created by hjf on 2016/8/11.
 */
public class SaveFoodBean {
    public SaveFoodBean(String dishesName,String  mainFood){
        this.dishesName=dishesName;
        this.mainFood=mainFood;
    }
    public SaveFoodBean(){
    }
    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    private String dishesName;
    private int foodNums;
    private String imagesURL;//自定义搜索结果中的图片url

    public String getMainFood() {
        return mainFood;
    }

    public void setMainFood(String mainFood) {
        this.mainFood = mainFood;
    }

    private String mainFood;//自定义搜索结果中的图片url

    public List<SubFood> getAccessoriesList() {
        return accessoriesList;
    }

    public void setAccessoriesList(List<SubFood> accessoriesList) {
        this.accessoriesList = accessoriesList;
    }

    public String getImagesURL() {
        return imagesURL;
    }

    public List<SubFood> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<SubFood> ingredients) {
        this.ingredients = ingredients;
    }

    public void setImagesURL(String imagesURL) {
        this.imagesURL = imagesURL;
    }

    public int getFoodNums() {
        return foodNums;
    }

    public void setFoodNums(int foodNums) {
        this.foodNums = foodNums;
    }

    private List<SubFood> accessoriesList;//具体菜品的配料
    private List<SubFood> ingredients;//具体菜品的配料

    public static class SubFood {
        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        private String foodName;
    }
}

