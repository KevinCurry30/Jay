package com.diligroup.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class GetJiaoQinBean extends CommonBean {

    /**
     * list : [{"code":"260001","createTime":1469177978705,"creatorId":0,"dictId":246,"dictName":"猪肉类","dictType":"26","isShow":"1","isSpecial":"0","priority":1,"remark":"","status":"1"},{"code":"260002","createTime":1469177978706,"creatorId":0,"dictId":247,"dictName":"水产类","dictType":"26","isShow":"1","isSpecial":"0","priority":1,"remark":"","status":"1"}]
     * totalCount : 0
     */

    private int totalCount;
    /**
     * code : 260001
     * createTime : 1469177978705
     * creatorId : 0
     * dictId : 246
     * dictName : 猪肉类
     * dictType : 26
     * isShow : 1
     * isSpecial : 0
     * priority : 1
     * remark :
     * status : 1
     */

    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String code;
        private long createTime;
        private int creatorId;
        private int dictId;
        private String dictName;
        private String dictType;
        private String isShow;
        private String isSpecial;
        private int priority;
        private String remark;
        private String status;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public int getDictId() {
            return dictId;
        }

        public void setDictId(int dictId) {
            this.dictId = dictId;
        }

        public String getDictName() {
            return dictName;
        }

        public void setDictName(String dictName) {
            this.dictName = dictName;
        }

        public String getDictType() {
            return dictType;
        }

        public void setDictType(String dictType) {
            this.dictType = dictType;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getIsSpecial() {
            return isSpecial;
        }

        public void setIsSpecial(String isSpecial) {
            this.isSpecial = isSpecial;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
