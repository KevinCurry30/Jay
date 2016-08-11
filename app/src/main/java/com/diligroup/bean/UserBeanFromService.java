package com.diligroup.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/25.
 */
public class UserBeanFromService extends CommonBean implements Serializable{
//public static UserBeanFromService  singleton;
//    public static UserBeanFromService  getInstance(){
//        if (singleton==null){
//            singleton=new UserBeanFromService();
//        }
//        return singleton;
//    }
    /**
     * birthday : 2006-71-1
     * email :
     * height : 70
     * homeAdd :
     * homeDistrictId : 0
     * lastLoginTime : 1470299226177
     * mobileNum : 18600089822
     * nationCode :
     * password :
     * registerTime : 1470021982033
     * resouce : 1
     * sex : 0
     * status : 1
     * userCode :
     * userId : 11
     * userName :
     */

    private UserBean user;
    /**
     * carbohydrates : 0
     * chronicDiseaseCode : 240003
     * currentAdd :
     * currentDistrictId : 0
     * energyKJ : 0
     * fat : 0
     * headPhotoAdd :
     * id : 2
     * job :
     * jobType :
     * otherReq :
     * periodEndTime :
     * periodNum :
     * periodStartTime :
     * protein : 0
     * reqType : 0
     * specialCrowdCode :
     * storeId : 0
     * tabooCode :
     * targetWeight :
     * tasteCode :
     * userId : 11
     * weight :
     */

    private UserDetailBean userDetail;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserDetailBean getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailBean userDetail) {
        this.userDetail = userDetail;
    }

    public static class UserBean {
        private String birthday;
        private String email;
        private String height;
        private String homeAdd;
        private int homeDistrictId;
        private long lastLoginTime;
        private String mobileNum;
        private String nationCode;
        private String password;
        private long registerTime;
        private String resouce;
        private int sex;
        private String status;
        private String userCode;
        private int userId;
        private String userName;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getHomeAdd() {
            return homeAdd;
        }

        public void setHomeAdd(String homeAdd) {
            this.homeAdd = homeAdd;
        }

        public int getHomeDistrictId() {
            return homeDistrictId;
        }

        public void setHomeDistrictId(int homeDistrictId) {
            this.homeDistrictId = homeDistrictId;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMobileNum() {
            return mobileNum;
        }

        public void setMobileNum(String mobileNum) {
            this.mobileNum = mobileNum;
        }

        public String getNationCode() {
            return nationCode;
        }

        public void setNationCode(String nationCode) {
            this.nationCode = nationCode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }

        public String getResouce() {
            return resouce;
        }

        public void setResouce(String resouce) {
            this.resouce = resouce;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class UserDetailBean {
        private int carbohydrates;
        private String chronicDiseaseCode;
        private String currentAdd;
        private int currentDistrictId;
        private int energyKJ;
        private int fat;
        private String headPhotoAdd;
        private int id;
        private String job;
        private String jobType;
        private String otherReq;
        private String periodEndTime;
        private String periodNum;
        private String periodStartTime;
        private int protein;
        private String reqType;
        private String specialCrowdCode;
        private int storeId;
        private String tabooCode;
        private String targetWeight;
        private String tasteCode;
        private int userId;
        private String weight;

        public int getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(int carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public String getChronicDiseaseCode() {
            return chronicDiseaseCode;
        }

        public void setChronicDiseaseCode(String chronicDiseaseCode) {
            this.chronicDiseaseCode = chronicDiseaseCode;
        }

        public String getCurrentAdd() {
            return currentAdd;
        }

        public void setCurrentAdd(String currentAdd) {
            this.currentAdd = currentAdd;
        }

        public int getCurrentDistrictId() {
            return currentDistrictId;
        }

        public void setCurrentDistrictId(int currentDistrictId) {
            this.currentDistrictId = currentDistrictId;
        }

        public int getEnergyKJ() {
            return energyKJ;
        }

        public void setEnergyKJ(int energyKJ) {
            this.energyKJ = energyKJ;
        }

        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public String getHeadPhotoAdd() {
            return headPhotoAdd;
        }

        public void setHeadPhotoAdd(String headPhotoAdd) {
            this.headPhotoAdd = headPhotoAdd;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getJobType() {
            return jobType;
        }

        public void setJobType(String jobType) {
            this.jobType = jobType;
        }

        public String getOtherReq() {
            return otherReq;
        }

        public void setOtherReq(String otherReq) {
            this.otherReq = otherReq;
        }

        public String getPeriodEndTime() {
            return periodEndTime;
        }

        public void setPeriodEndTime(String periodEndTime) {
            this.periodEndTime = periodEndTime;
        }

        public String getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(String periodNum) {
            this.periodNum = periodNum;
        }

        public String getPeriodStartTime() {
            return periodStartTime;
        }

        public void setPeriodStartTime(String periodStartTime) {
            this.periodStartTime = periodStartTime;
        }

        public int getProtein() {
            return protein;
        }

        public void setProtein(int protein) {
            this.protein = protein;
        }

        public String getReqType() {
            return reqType;
        }

        public void setReqType(String reqType) {
            this.reqType = reqType;
        }

        public String getSpecialCrowdCode() {
            return specialCrowdCode;
        }

        public void setSpecialCrowdCode(String specialCrowdCode) {
            this.specialCrowdCode = specialCrowdCode;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getTabooCode() {
            return tabooCode;
        }

        public void setTabooCode(String tabooCode) {
            this.tabooCode = tabooCode;
        }

        public String getTargetWeight() {
            return targetWeight;
        }

        public void setTargetWeight(String targetWeight) {
            this.targetWeight = targetWeight;
        }

        public String getTasteCode() {
            return tasteCode;
        }

        public void setTasteCode(String tasteCode) {
            this.tasteCode = tasteCode;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}

