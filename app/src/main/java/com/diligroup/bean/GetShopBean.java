package com.diligroup.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class GetShopBean extends CommonBean {

    /**
     * address : 北京市朝阳区和乔大厦
     * contactName : 张朝阳
     * createDate : 2016-08-23 08:53:38
     * createTime : 1471913618330
     * creatorId : 2
     * custGroupId : 50006
     * custGroupName :
     * custId : 1000218
     * custName : 地利集团
     * districtId : 110105
     * districtName :
     * email : dili@163.com
     * gpsLatitude :
     * gpsLongitude :
     * isDefault : 0
     * mobile : 13699999999
     * name : 测试05
     * phone : 010-222222222
     * scustId : 1000218
     * storeId : 5
     * storeStatus : 2
     */

    private List<StoreCustListBean> storeCustList;

    public List<StoreCustListBean> getStoreCustList() {
        return storeCustList;
    }

    public void setStoreCustList(List<StoreCustListBean> storeCustList) {
        this.storeCustList = storeCustList;
    }

    public static class StoreCustListBean {
        private String address;
        private String contactName;
        private String createDate;
        private long createTime;
        private int creatorId;
        private int custGroupId;
        private String custGroupName;
        private String custId;
        private String custName;
        private int districtId;
        private String districtName;
        private String email;
        private String gpsLatitude;
        private String gpsLongitude;
        private String isDefault;
        private String mobile;
        private String name;
        private String phone;
        private int scustId;
        private int storeId;
        private String storeStatus;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public int getCustGroupId() {
            return custGroupId;
        }

        public void setCustGroupId(int custGroupId) {
            this.custGroupId = custGroupId;
        }

        public String getCustGroupName() {
            return custGroupName;
        }

        public void setCustGroupName(String custGroupName) {
            this.custGroupName = custGroupName;
        }

        public String getCustId() {
            return custId;
        }

        public void setCustId(String custId) {
            this.custId = custId;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGpsLatitude() {
            return gpsLatitude;
        }

        public void setGpsLatitude(String gpsLatitude) {
            this.gpsLatitude = gpsLatitude;
        }

        public String getGpsLongitude() {
            return gpsLongitude;
        }

        public void setGpsLongitude(String gpsLongitude) {
            this.gpsLongitude = gpsLongitude;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getScustId() {
            return scustId;
        }

        public void setScustId(int scustId) {
            this.scustId = scustId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getStoreStatus() {
            return storeStatus;
        }

        public void setStoreStatus(String storeStatus) {
            this.storeStatus = storeStatus;
        }
    }
}
