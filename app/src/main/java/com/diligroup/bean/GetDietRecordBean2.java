package com.diligroup.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10.
 */
public class GetDietRecordBean2 extends CommonBean {

    /**
     * afternoon : [{"carbohydrates":10540,"dishesCode":"H102001001","dishesName":"冬笋炒肉丝","energyKc":153896,"fat":8830,"imageUrl":"","mealType":2,"num":1,"protein":11440,"storeId":1,"time":1470758400000,"userId":1,"wayType":2,"weight":200}]
     * even : []
     * morn : []
     * totalCount : 0
     */

    private int totalCount;
    /**
     * carbohydrates : 10540
     * dishesCode : H102001001
     * dishesName : 冬笋炒肉丝
     * energyKc : 153896
     * fat : 8830
     * imageUrl :
     * mealType : 2
     * num : 1
     * protein : 11440
     * storeId : 1
     * time : 1470758400000
     * userId : 1
     * wayType : 2
     * weight : 200
     */

    private List<AfternoonBean> afternoon;
    private List<?> even;
    private List<?> morn;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AfternoonBean> getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(List<AfternoonBean> afternoon) {
        this.afternoon = afternoon;
    }

    public List<?> getEven() {
        return even;
    }

    public void setEven(List<?> even) {
        this.even = even;
    }

    public List<?> getMorn() {
        return morn;
    }

    public void setMorn(List<?> morn) {
        this.morn = morn;
    }

    public static class AfternoonBean {
        private int carbohydrates;
        private String dishesCode;
        private String dishesName;
        private int energyKc;
        private int fat;
        private String imageUrl;
        private int mealType;
        private int num;
        private int protein;
        private int storeId;
        private long time;
        private int userId;
        private int wayType;
        private int weight;

        public int getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(int carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public String getDishesCode() {
            return dishesCode;
        }

        public void setDishesCode(String dishesCode) {
            this.dishesCode = dishesCode;
        }

        public String getDishesName() {
            return dishesName;
        }

        public void setDishesName(String dishesName) {
            this.dishesName = dishesName;
        }

        public int getEnergyKc() {
            return energyKc;
        }

        public void setEnergyKc(int energyKc) {
            this.energyKc = energyKc;
        }

        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMealType() {
            return mealType;
        }

        public void setMealType(int mealType) {
            this.mealType = mealType;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getProtein() {
            return protein;
        }

        public void setProtein(int protein) {
            this.protein = protein;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWayType() {
            return wayType;
        }

        public void setWayType(int wayType) {
            this.wayType = wayType;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
