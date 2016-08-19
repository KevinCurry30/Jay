package com.diligroup.bean;

import java.util.List;

/**
 * 获取 饮食记录
 * Created by Administrator on 2016/8/10.
 */
public class GetDietRecordBean extends CommonBean {

    /**
     * afternoon : [{"carbohydrates":"1.27","dishesCode":"H201003005","dishesName":"双色馒头","energyKc":" 5.78","fat":" 0.03","imageUrl":"","mealType":1,"num":1,"protein":"0.17","storeId":0,"time":1471536000000,"userId":11,"wayType":1,"weight":""}]
     * even : [{"carbohydrates":"1.27","dishesCode":"H201003005","dishesName":"双色馒头","energyKc":" 5.78","fat":" 0.03","imageUrl":"","mealType":1,"num":1,"protein":"0.17","storeId":0,"time":1471536000000,"userId":11,"wayType":1,"weight":""}]
     * morn : [{"carbohydrates":"1.27","dishesCode":"H201003005","dishesName":"双色馒头","energyKc":" 5.78","fat":" 0.03","imageUrl":"","mealType":1,"num":1,"protein":"0.17","storeId":0,"time":1471536000000,"userId":11,"wayType":1,"weight":""}]
     * totalCount : 0
     */

    private int totalCount;
    /**
     * carbohydrates : 1.27
     * dishesCode : H201003005
     * dishesName : 双色馒头
     * energyKc :  5.78
     * fat :  0.03
     * imageUrl :
     * mealType : 1
     * num : 1
     * protein : 0.17
     * storeId : 0
     * time : 1471536000000
     * userId : 11
     * wayType : 1
     * weight :
     */

    private List<AfternoonBean> afternoon;
    /**
     * carbohydrates : 1.27
     * dishesCode : H201003005
     * dishesName : 双色馒头
     * energyKc :  5.78
     * fat :  0.03
     * imageUrl :
     * mealType : 1
     * num : 1
     * protein : 0.17
     * storeId : 0
     * time : 1471536000000
     * userId : 11
     * wayType : 1
     * weight :
     */

    private List<EvenBean> even;
    /**
     * carbohydrates : 1.27
     * dishesCode : H201003005
     * dishesName : 双色馒头
     * energyKc :  5.78
     * fat :  0.03
     * imageUrl :
     * mealType : 1
     * num : 1
     * protein : 0.17
     * storeId : 0
     * time : 1471536000000
     * userId : 11
     * wayType : 1
     * weight :
     */

    private List<MornBean> morn;

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

    public List<EvenBean> getEven() {
        return even;
    }

    public void setEven(List<EvenBean> even) {
        this.even = even;
    }

    public List<MornBean> getMorn() {
        return morn;
    }

    public void setMorn(List<MornBean> morn) {
        this.morn = morn;
    }

    public static class AfternoonBean {
        private String carbohydrates;
        private String dishesCode;
        private String dishesName;
        private String energyKc;
        private String fat;
        private String imageUrl;
        private int mealType;
        private int num;
        private String protein;
        private int storeId;
        private long time;
        private int userId;
        private int wayType;
        private String weight;

        public String getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(String carbohydrates) {
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

        public String getEnergyKc() {
            return energyKc;
        }

        public void setEnergyKc(String energyKc) {
            this.energyKc = energyKc;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
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

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }

    public static class EvenBean {
        private String carbohydrates;
        private String dishesCode;
        private String dishesName;
        private String energyKc;
        private String fat;
        private String imageUrl;
        private int mealType;
        private int num;
        private String protein;
        private int storeId;
        private long time;
        private int userId;
        private int wayType;
        private String weight;

        public String getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(String carbohydrates) {
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

        public String getEnergyKc() {
            return energyKc;
        }

        public void setEnergyKc(String energyKc) {
            this.energyKc = energyKc;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
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

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }

    public static class MornBean {
        private String carbohydrates;
        private String dishesCode;
        private String dishesName;
        private String energyKc;
        private String fat;
        private String imageUrl;
        private int mealType;
        private int num;
        private String protein;
        private int storeId;
        private long time;
        private int userId;
        private int wayType;
        private String weight;

        public String getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(String carbohydrates) {
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

        public String getEnergyKc() {
            return energyKc;
        }

        public void setEnergyKc(String energyKc) {
            this.energyKc = energyKc;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
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

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
