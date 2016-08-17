package com.diligroup.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/25.
 */
public class UserBeanFromService extends CommonBean implements Serializable{


    /**
     * alipay :
     * birthday : 1971年-12月-12日
     * email :
     * height : 159 CM
     * homeAdd :
     * homeCityCode :
     * homeDistrictId :
     * homeProvinceCode :
     * lastLoginTime : 1471426143989
     * microblog :
     * mobileNum : 18600089822
     * nationCode :
     * password :
     * qq :
     * registerTime : 1471425346152
     * resouce : 1
     * sex : 0
     * status : 1
     * userCode :
     * userId : 11
     * userName :
     * weChat :
     */

    private UserBean user;
    /**
     * allergyName : 香菜
     * carbohydrates : 258.75
     * chronicDiseaseCode : 240003
     * chronicDiseaseNames : 糖尿病
     * currentAdd : 大望路1
     * currentCityCode : 430000
     * currentDistrictId : 430281
     * currentProvinceCode : 430000
     * energyKC : 1800.0
     * fat : 50.0
     * headPhotoAdd : http://192.168.100.67/images.ypp2015.com/ypp/upload/img/IMG_20160807_104838.jpg
     * id : 2
     * job :
     * jobName :
     * jobType : 15001
     * jobTypeName :
     * otherReq : 250002
     * otherReqName : 增肌
     * periodEndTime : 2016-08-15
     * periodNum : 28
     * periodStartTime : 2016-08-10
     * protein : 55.0
     * reqType : 0
     * specialCrowdCode : 200002
     * specialCrowdName : 孕中期
     * storeId : 8
     * storeName : 测试08
     * tabooCode : 40005
     * tabooNames :
     * targetWeight : 60
     * tasteCode :
     * tasteNames :
     * userId : 11
     * weight : 52 Kg
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
        private String alipay;
        private String birthday;
        private String email;
        private String height;
        private String homeAdd;
        private String homeCityCode;
        private String homeDistrictId;
        private String homeProvinceCode;
        private long lastLoginTime;
        private String microblog;
        private String mobileNum;
        private String nationCode;
        private String password;
        private String qq;
        private long registerTime;
        private String resouce;
        private int sex;
        private String status;
        private String userCode;
        private int userId;
        private String userName;
        private String weChat;

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

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

        public String getHomeCityCode() {
            return homeCityCode;
        }

        public void setHomeCityCode(String homeCityCode) {
            this.homeCityCode = homeCityCode;
        }

        public String getHomeDistrictId() {
            return homeDistrictId;
        }

        public void setHomeDistrictId(String homeDistrictId) {
            this.homeDistrictId = homeDistrictId;
        }

        public String getHomeProvinceCode() {
            return homeProvinceCode;
        }

        public void setHomeProvinceCode(String homeProvinceCode) {
            this.homeProvinceCode = homeProvinceCode;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMicroblog() {
            return microblog;
        }

        public void setMicroblog(String microblog) {
            this.microblog = microblog;
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

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }
    }

    public static class UserDetailBean {
        private String allergyName;
        private double carbohydrates;
        private String chronicDiseaseCode;
        private String chronicDiseaseNames;
        private String currentAdd;
        private String currentCityCode;
        private String currentDistrictId;
        private String currentProvinceCode;
        private double energyKC;
        private double fat;
        private String headPhotoAdd;
        private int id;
        private String job;
        private String jobName;
        private String jobType;
        private String jobTypeName;
        private String otherReq;
        private String otherReqName;
        private String periodEndTime;
        private String periodNum;
        private String periodStartTime;
        private double protein;
        private String reqType;
        private String specialCrowdCode;
        private String specialCrowdName;
        private int storeId;
        private String storeName;
        private String tabooCode;
        private String tabooNames;
        private String targetWeight;
        private String tasteCode;
        private String tasteNames;
        private int userId;
        private String weight;

        public String getAllergyName() {
            return allergyName;
        }

        public void setAllergyName(String allergyName) {
            this.allergyName = allergyName;
        }

        public double getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(double carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        public String getChronicDiseaseCode() {
            return chronicDiseaseCode;
        }

        public void setChronicDiseaseCode(String chronicDiseaseCode) {
            this.chronicDiseaseCode = chronicDiseaseCode;
        }

        public String getChronicDiseaseNames() {
            return chronicDiseaseNames;
        }

        public void setChronicDiseaseNames(String chronicDiseaseNames) {
            this.chronicDiseaseNames = chronicDiseaseNames;
        }

        public String getCurrentAdd() {
            return currentAdd;
        }

        public void setCurrentAdd(String currentAdd) {
            this.currentAdd = currentAdd;
        }

        public String getCurrentCityCode() {
            return currentCityCode;
        }

        public void setCurrentCityCode(String currentCityCode) {
            this.currentCityCode = currentCityCode;
        }

        public String getCurrentDistrictId() {
            return currentDistrictId;
        }

        public void setCurrentDistrictId(String currentDistrictId) {
            this.currentDistrictId = currentDistrictId;
        }

        public String getCurrentProvinceCode() {
            return currentProvinceCode;
        }

        public void setCurrentProvinceCode(String currentProvinceCode) {
            this.currentProvinceCode = currentProvinceCode;
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

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getJobType() {
            return jobType;
        }

        public void setJobType(String jobType) {
            this.jobType = jobType;
        }

        public String getJobTypeName() {
            return jobTypeName;
        }

        public void setJobTypeName(String jobTypeName) {
            this.jobTypeName = jobTypeName;
        }

        public String getOtherReq() {
            return otherReq;
        }

        public void setOtherReq(String otherReq) {
            this.otherReq = otherReq;
        }

        public String getOtherReqName() {
            return otherReqName;
        }

        public void setOtherReqName(String otherReqName) {
            this.otherReqName = otherReqName;
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

        public double getProtein() {
            return protein;
        }

        public void setProtein(double protein) {
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

        public String getSpecialCrowdName() {
            return specialCrowdName;
        }

        public void setSpecialCrowdName(String specialCrowdName) {
            this.specialCrowdName = specialCrowdName;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getTabooCode() {
            return tabooCode;
        }

        public void setTabooCode(String tabooCode) {
            this.tabooCode = tabooCode;
        }

        public String getTabooNames() {
            return tabooNames;
        }

        public void setTabooNames(String tabooNames) {
            this.tabooNames = tabooNames;
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

        public String getTasteNames() {
            return tasteNames;
        }

        public void setTasteNames(String tasteNames) {
            this.tasteNames = tasteNames;
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

