package com.diligroup.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class GetFoodTypeBean extends CommonBean {
    public String foodTypeName;
    public boolean isClicked;
    public String foodTypeId;

    public String getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(String foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
    //    /**
//     * list : [{"allergyType1":"谷类","status":"1"},{"allergyType1":"谷类11","status":"1"},{"allergyType1":"豆类","status":"1"}]
//     * totalCount : 0
//     */
//
//    private int totalCount;
//    /**
//     * allergyType1 : 谷类
//     * status : 1
//     */
//
//    private List<ListBean> list;
//
//    public int getTotalCount() {
//        return totalCount;
//    }
//
//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }
//
//    public List<ListBean> getList() {
//        return list;
//    }
//
//    public void setList(List<ListBean> list) {
//        this.list = list;
//    }
//
//    public static class ListBean {
//        private String allergyType1;
//        private String status;
//
//        public String getAllergyType1() {
//            return allergyType1;
//        }
//
//        public void setAllergyType1(String allergyType1) {
//            this.allergyType1 = allergyType1;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//    }
}
