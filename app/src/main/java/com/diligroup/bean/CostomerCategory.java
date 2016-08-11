package com.diligroup.bean;

import java.util.List;

/**
 * Created by hjf on 2016/8/9.
 */
public class CostomerCategory extends CommonBean {
    /**
     * list : [{"code":"10001","createTime":1470704178839,"creatorId":0,"dictId":1,"dictName":"烘焙","dictType":"1","isShow":"1","isSpecial":"1","priority":1,"remark":"","status":"1"},{"code":"10002","createTime":1470704178839,"creatorId":0,"dictId":2,"dictName":"坚果类","dictType":"1","isShow":"1","isSpecial":"1","priority":2,"remark":"","status":"1"},{"code":"10003","createTime":1470704178839,"creatorId":0,"dictId":3,"dictName":"冷热饮","dictType":"1","isShow":"1","isSpecial":"1","priority":3,"remark":"","status":"1"},{"code":"10004","createTime":1470704178839,"creatorId":0,"dictId":4,"dictName":"凉荤菜","dictType":"1","isShow":"1","isSpecial":"1","priority":4,"remark":"","status":"1"},{"code":"10005","createTime":1470704178839,"creatorId":0,"dictId":5,"dictName":"凉素菜","dictType":"1","isShow":"1","isSpecial":"1","priority":5,"remark":"","status":"1"},{"code":"10006","createTime":1470704178839,"creatorId":0,"dictId":6,"dictName":"热菜半荤","dictType":"1","isShow":"1","isSpecial":"1","priority":6,"remark":"","status":"1"},{"code":"10007","createTime":1470704178839,"creatorId":0,"dictId":7,"dictName":"热菜大荤","dictType":"1","isShow":"1","isSpecial":"1","priority":7,"remark":"","status":"1"},{"code":"10008","createTime":1470704178839,"creatorId":0,"dictId":8,"dictName":"热素菜","dictType":"1","isShow":"1","isSpecial":"1","priority":8,"remark":"","status":"1"},{"code":"10009","createTime":1470704178839,"creatorId":0,"dictId":9,"dictName":"水果","dictType":"1","isShow":"1","isSpecial":"1","priority":9,"remark":"","status":"1"},{"code":"10010","createTime":1470704178839,"creatorId":0,"dictId":10,"dictName":"soup_pressed","dictType":"1","isShow":"1","isSpecial":"1","priority":10,"remark":"","status":"1"},{"code":"10011","createTime":1470704178839,"creatorId":0,"dictId":11,"dictName":"主食","dictType":"1","isShow":"1","isSpecial":"1","priority":11,"remark":"","status":"1"}]
     * totalCount : 0
     */

    private int totalCount;
    /**
     * code : 10001
     * createTime : 1470704178839
     * creatorId : 0
     * dictId : 1
     * dictName : 烘焙
     * dictType : 1
     * isShow : 1
     * isSpecial : 1
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
