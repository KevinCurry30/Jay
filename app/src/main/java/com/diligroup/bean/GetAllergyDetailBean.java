package com.diligroup.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class GetAllergyDetailBean extends CommonBean {

    /**
     * list : [{"allergyType1":"谷类","allergyType2":"小麦","allergyName":"小麦","id":1,"status":"1"},{"allergyType1":"谷类","allergyType2":"小麦","allergyName":"面筋","id":2,"status":"1"},{"allergyType1":"谷类","allergyType2":"玉米","allergyName":"玉米","id":3,"status":"1"},{"allergyType1":"谷类","allergyType2":"大麦","allergyName":"大麦","id":4,"status":"1"}]
     * totalCount : 4
     */

    private int totalCount;
    /**
     * allergyType1 : 谷类
     * allergyType2 : 小麦
     * allergyName : 小麦
     * id : 1
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
        private String allergyType1;
        private String allergyType2;
        private String allergyName;
        private int id;
        private String status;

        public String getAllergyType1() {
            return allergyType1;
        }

        public void setAllergyType1(String allergyType1) {
            this.allergyType1 = allergyType1;
        }

        public String getAllergyType2() {
            return allergyType2;
        }

        public void setAllergyType2(String allergyType2) {
            this.allergyType2 = allergyType2;
        }

        public String getAllergyName() {
            return allergyName;
        }

        public void setAllergyName(String allergyName) {
            this.allergyName = allergyName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
