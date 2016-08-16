package com.diligroup.bean;

import java.util.List;

/**
 * 市
 * Created by Administrator on 2016/8/15.
 */
public class CityBean extends CommonBean {

    /**
     * area :
     * areaCode :
     * areaType : 2
     * createTime : 20160506
     * creatorId : 1
     * districtId : 3727
     * name : 汕头市
     * parentCode : 440000
     * priority : 1
     * remark :
     * sortCode : 440500
     * status : 1
     */

    private List<DistrictListBean> districtList;

    public List<DistrictListBean> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictListBean> districtList) {
        this.districtList = districtList;
    }

    public static class DistrictListBean {
        private String area;
        private String areaCode;
        private String areaType;
        private int createTime;
        private int creatorId;
        private int districtId;
        private String name;
        private String parentCode;
        private int priority;
        private String remark;
        private String sortCode;
        private String status;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAreaType() {
            return areaType;
        }

        public void setAreaType(String areaType) {
            this.areaType = areaType;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentCode() {
            return parentCode;
        }

        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSortCode() {
            return sortCode;
        }

        public void setSortCode(String sortCode) {
            this.sortCode = sortCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
