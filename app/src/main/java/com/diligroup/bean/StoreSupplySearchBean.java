package com.diligroup.bean;

import java.util.List;

/**
 * Created by hjf on 2016/8/11.
 */
public class StoreSupplySearchBean extends CommonBean {
    private JsonBean json;
    /**
     * json : {"dishesSupplyDtlList":[{"accessoriesList":[],"dishesCode":"H204002016","dishesName":"黄瓜汁","dishesUrl":"","ingredients":{"cCode":"V03010","createTime":1470880114568,"creatorId":0,"dishesCode":"H204002016","dishesCompId":17405,"foodName":"黄瓜","netWeight":5,"priority":1,"proMethod":"","proMethodId":"120009","rawWeight":5.26,"remark":"","status":"1","type":"1","yieldRate":95},"num":10,"proWeight":5,"weight":0.5}],"mealTypeList":[{"code":"20001","createTime":1470880114517,"dictId":0,"dictName":"早餐","dictType":"","isShow":"1","isSpecial":"1","priority":0,"remark":"","status":"1"},{"code":"20002","createTime":1470880114517,"dictId":0,"dictName":"午餐","dictType":"","isShow":"1","isSpecial":"1","priority":0,"remark":"","status":"1"},{"code":"20003","createTime":1470880114517,"dictId":0,"dictName":"晚餐","dictType":"","isShow":"1","isSpecial":"1","priority":0,"remark":"","status":"1"}],"tmplDateList":["2016-08-10","2016-08-11","2016-08-12","2016-08-13","2016-08-14","2016-08-15","2016-08-16"]}
     * totalCount : 0
     */

    private int totalCount;

    public JsonBean getJson() {
        return json;
    }

    public void setJson(JsonBean json) {
        this.json = json;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static class JsonBean {
        /**
         * accessoriesList : []
         * dishesCode : H204002016
         * dishesName : 黄瓜汁
         * dishesUrl :
         * ingredients : {"cCode":"V03010","createTime":1470880114568,"creatorId":0,"dishesCode":"H204002016","dishesCompId":17405,"foodName":"黄瓜","netWeight":5,"priority":1,"proMethod":"","proMethodId":"120009","rawWeight":5.26,"remark":"","status":"1","type":"1","yieldRate":95}
         * num : 10.0
         * proWeight : 5.0
         * weight : 0.5
         */

        private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> dishesSupplyDtlList;
        /**
         * code : 20001
         * createTime : 1470880114517
         * dictId : 0
         * dictName : 早餐
         * dictType :
         * isShow : 1
         * isSpecial : 1
         * priority : 0
         * remark :
         * status : 1
         */

        private List<String> tmplDateList;

        public List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> getDishesSupplyDtlList() {
            return dishesSupplyDtlList;
        }

        public void setDishesSupplyDtlList(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> dishesSupplyDtlList) {
            this.dishesSupplyDtlList = dishesSupplyDtlList;
        }
        public List<String> getTmplDateList() {
            return tmplDateList;
        }

        public void setTmplDateList(List<String> tmplDateList) {
            this.tmplDateList = tmplDateList;
        }
//
//        public static class DishesSupplyDtlListBean {
//            private String dishesCode;
//            private String dishesName;
//            private String dishesUrl;
//            /**
//             * cCode : V03010
//             * createTime : 1470880114568
//             * creatorId : 0
//             * dishesCode : H204002016
//             * dishesCompId : 17405
//             * foodName : 黄瓜
//             * netWeight : 5.0
//             * priority : 1
//             * proMethod :
//             * proMethodId : 120009
//             * rawWeight : 5.26
//             * remark :
//             * status : 1
//             * type : 1
//             * yieldRate : 95
//             */
//
//            private IngredientsBean ingredients;
//            private double num;
//            private double proWeight;
//            private double weight;
//            private List<?> accessoriesList;
//
//            public String getDishesCode() {
//                return dishesCode;
//            }
//
//            public void setDishesCode(String dishesCode) {
//                this.dishesCode = dishesCode;
//            }
//
//            public String getDishesName() {
//                return dishesName;
//            }
//
//            public void setDishesName(String dishesName) {
//                this.dishesName = dishesName;
//            }
//
//            public String getDishesUrl() {
//                return dishesUrl;
//            }
//
//            public void setDishesUrl(String dishesUrl) {
//                this.dishesUrl = dishesUrl;
//            }
//
//            public IngredientsBean getIngredients() {
//                return ingredients;
//            }
//
//            public void setIngredients(IngredientsBean ingredients) {
//                this.ingredients = ingredients;
//            }
//
//            public double getNum() {
//                return num;
//            }
//
//            public void setNum(double num) {
//                this.num = num;
//            }
//
//            public double getProWeight() {
//                return proWeight;
//            }
//
//            public void setProWeight(double proWeight) {
//                this.proWeight = proWeight;
//            }
//
//            public double getWeight() {
//                return weight;
//            }
//
//            public void setWeight(double weight) {
//                this.weight = weight;
//            }
//
//            public List<?> getAccessoriesList() {
//                return accessoriesList;
//            }
//
//            public void setAccessoriesList(List<?> accessoriesList) {
//                this.accessoriesList = accessoriesList;
//            }
//
//            public static class IngredientsBean {
//                private String cCode;
//                private long createTime;
//                private int creatorId;
//                private String dishesCode;
//                private int dishesCompId;
//                private String foodName;
//                private double netWeight;
//                private int priority;
//                private String proMethod;
//                private String proMethodId;
//                private double rawWeight;
//                private String remark;
//                private String status;
//                private String type;
//                private int yieldRate;
//
//                public String getCCode() {
//                    return cCode;
//                }
//
//                public void setCCode(String cCode) {
//                    this.cCode = cCode;
//                }
//
//                public long getCreateTime() {
//                    return createTime;
//                }
//
//                public void setCreateTime(long createTime) {
//                    this.createTime = createTime;
//                }
//
//                public int getCreatorId() {
//                    return creatorId;
//                }
//
//                public void setCreatorId(int creatorId) {
//                    this.creatorId = creatorId;
//                }
//
//                public String getDishesCode() {
//                    return dishesCode;
//                }
//
//                public void setDishesCode(String dishesCode) {
//                    this.dishesCode = dishesCode;
//                }
//
//                public int getDishesCompId() {
//                    return dishesCompId;
//                }
//
//                public void setDishesCompId(int dishesCompId) {
//                    this.dishesCompId = dishesCompId;
//                }
//
//                public String getFoodName() {
//                    return foodName;
//                }
//
//                public void setFoodName(String foodName) {
//                    this.foodName = foodName;
//                }
//
//                public double getNetWeight() {
//                    return netWeight;
//                }
//
//                public void setNetWeight(double netWeight) {
//                    this.netWeight = netWeight;
//                }
//
//                public int getPriority() {
//                    return priority;
//                }
//
//                public void setPriority(int priority) {
//                    this.priority = priority;
//                }
//
//                public String getProMethod() {
//                    return proMethod;
//                }
//
//                public void setProMethod(String proMethod) {
//                    this.proMethod = proMethod;
//                }
//
//                public String getProMethodId() {
//                    return proMethodId;
//                }
//
//                public void setProMethodId(String proMethodId) {
//                    this.proMethodId = proMethodId;
//                }
//
//                public double getRawWeight() {
//                    return rawWeight;
//                }
//
//                public void setRawWeight(double rawWeight) {
//                    this.rawWeight = rawWeight;
//                }
//
//                public String getRemark() {
//                    return remark;
//                }
//
//                public void setRemark(String remark) {
//                    this.remark = remark;
//                }
//
//                public String getStatus() {
//                    return status;
//                }
//
//                public void setStatus(String status) {
//                    this.status = status;
//                }
//
//                public String getType() {
//                    return type;
//                }
//
//                public void setType(String type) {
//                    this.type = type;
//                }
//
//                public int getYieldRate() {
//                    return yieldRate;
//                }
//
//                public void setYieldRate(int yieldRate) {
//                    this.yieldRate = yieldRate;
//                }
//            }
//        }

    }
}
