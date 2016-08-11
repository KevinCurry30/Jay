package com.diligroup.bean;

/**
 * Created by Administrator on 2016/8/5.
 */
public class UserInfoFromService extends CommonBean{

    /**
     * birthday : 2016-8-5
     * email :
     * height : 50
     * homeAdd :
     * homeDistrictId : 0
     * lastLoginTime : 1470386644806
     * mobileNum : 18701336455
     * nationCode :
     * password :
     * registerTime : 1470385817719
     * resouce : 1
     * sex : 0
     * status : 1
     * userCode :
     * userId : 33
     * userName :
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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
}
