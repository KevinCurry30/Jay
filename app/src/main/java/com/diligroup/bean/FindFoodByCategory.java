package com.diligroup.bean;

import java.util.List;

/**
 * Created by hjf on 2016/8/10.
 */
public class FindFoodByCategory extends CommonBean {
    /**
     * dishesLibList : [{"allergens":"奶酪、小麦","ash":49,"ca":8960,"carbohydrates":808,"carotene":3600,"cholesterol":110,"createDate":"2016-08-10 12:09:10","createTime":1470802150462,"creatorId":1,"cu":4.7,"dishesCode":"H202003001","dishesLibId":2647,"dishesName":"培根三明治","energyKC":6930,"fat":250,"fe":63,"fiber":12,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":3030,"mg":1180,"mn":10.8,"na":6673,"nickAcid":28,"p":4710,"priority":2647,"proWeight":5,"protein":374,"remark":"","riboflavin":10.7,"se":99.3,"sortCode":"230100","status":"1","thiamine":2.3,"va":2120,"vc":200,"ve":13.3,"yesuan":523,"zn":83.7},{"allergens":"小麦、鸡蛋、奶酪、西红柿","ash":38.5,"ca":4550,"carbohydrates":492,"carotene":0,"cholesterol":2980,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":4.35,"dishesCode":"H202003002","dishesLibId":2648,"dishesName":"番茄酱鸡蛋三明治","energyKC":4515,"fat":168,"fe":41,"fiber":13.5,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":6730,"mg":680,"mn":6.25,"na":3779.5,"nickAcid":42,"p":3435,"priority":2648,"proWeight":5,"protein":271,"remark":"","riboflavin":6.35,"se":115.6,"sortCode":"230100","status":"1","thiamine":1.85,"va":1930,"vc":0,"ve":38.1,"yesuan":670,"zn":48.7},{"allergens":"奶酪、西红柿、小麦","ash":47.5,"ca":4330,"carbohydrates":814,"carotene":2750,"cholesterol":655,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":3.95,"dishesCode":"H202003003","dishesLibId":2649,"dishesName":"英式三明治","energyKC":6885,"fat":266.5,"fe":52,"fiber":8.5,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":3570,"mg":750,"mn":9.1,"na":8408.5,"nickAcid":69,"p":3335,"priority":2649,"proWeight":5,"protein":316,"remark":"","riboflavin":5.75,"se":91.8,"sortCode":"230100","status":"1","thiamine":3.55,"va":1450,"vc":95,"ve":17.15,"yesuan":253.5,"zn":56},{"allergens":"小麦、牛奶、玉米","ash":8,"ca":619,"carbohydrates":1117.8,"carotene":0,"cholesterol":45,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":2.6,"dishesCode":"H202003004","dishesLibId":2650,"dishesName":"瑞士奶油卷","energyKC":4862,"fat":17.5,"fe":36.8,"fiber":10.9,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":1329,"mg":319,"mn":8.45,"na":153.9,"nickAcid":14.7,"p":1291,"priority":2650,"proWeight":5,"protein":69.8,"remark":"","riboflavin":0.98,"se":35.42,"sortCode":"230100","status":"1","thiamine":1.61,"va":72,"vc":3,"ve":9.63,"yesuan":120,"zn":10.06},{"allergens":"小麦、杏仁、黄油、鸡蛋","ash":46.3,"ca":2055,"carbohydrates":748.9,"carotene":0,"cholesterol":9367,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":12.41,"dishesCode":"H202002001","dishesLibId":2651,"dishesName":"意大利歌剧蛋糕","energyKC":11676,"fat":789.5,"fe":71.7,"fiber":90.5,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":4403,"mg":2197,"mn":16.29,"na":2152,"nickAcid":13,"p":3184,"priority":2651,"proWeight":5,"protein":483.3,"remark":"","riboflavin":10.09,"se":401.6,"sortCode":"230100","status":"1","thiamine":3.85,"va":3510,"vc":260,"ve":221.9,"yesuan":2129,"zn":67.98},{"allergens":"奶酪、鸡蛋黄、奶油、小麦","ash":23.3,"ca":4268,"carbohydrates":394.1,"carotene":80,"cholesterol":2064,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":2.12,"dishesCode":"H202002002","dishesLibId":2652,"dishesName":"提拉米苏","energyKC":5359,"fat":352.4,"fe":24.2,"fiber":1.1,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":1022,"mg":389,"mn":2,"na":3719.2,"nickAcid":7.8,"p":2004,"priority":2652,"proWeight":5,"protein":154.1,"remark":"","riboflavin":4.9,"se":48.38,"sortCode":"230100","status":"1","thiamine":0.71,"va":1829,"vc":3,"ve":16.61,"yesuan":0,"zn":39.91},{"allergens":"小麦、杏仁、鸡蛋","ash":58.6,"ca":1502,"carbohydrates":1684.7,"carotene":0,"cholesterol":5850,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":12.2,"dishesCode":"H202002003","dishesLibId":2653,"dishesName":"沙河蛋糕","energyKC":10126,"fat":265.3,"fe":54.1,"fiber":91.7,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":4298,"mg":934,"mn":12.01,"na":1451.4,"nickAcid":17.6,"p":4893,"priority":2653,"proWeight":5,"protein":340.1,"remark":"","riboflavin":5.42,"se":233.07,"sortCode":"230100","status":"1","thiamine":2.94,"va":2428,"vc":78,"ve":108.31,"yesuan":1334.3,"zn":37.18},{"allergens":"小麦、牛奶、鸡蛋黄、黄油、杏仁","ash":24.1,"ca":1973,"carbohydrates":669,"carotene":0,"cholesterol":10600,"createDate":"2016-08-10 12:09:10","createTime":1470802150463,"creatorId":1,"cu":5.06,"dishesCode":"H202002004","dishesLibId":2654,"dishesName":"维也纳巧克力杏仁蛋糕","energyKC":11274,"fat":880,"fe":70.2,"fiber":18,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":3421,"mg":855,"mn":11.58,"na":1254.2,"nickAcid":18,"p":3282,"priority":2654,"proWeight":5,"protein":187.7,"remark":"","riboflavin":3.2,"se":210.62,"sortCode":"230100","status":"1","thiamine":3.8,"va":2724,"vc":4,"ve":48.3,"yesuan":125.5,"zn":38.27},{"allergens":"小麦、杏仁、鸡蛋、鸡蛋清","ash":36.5,"ca":1390,"carbohydrates":1046,"carotene":0,"cholesterol":5850,"createDate":"2016-08-10 12:09:10","createTime":1470802150464,"creatorId":1,"cu":8.3,"dishesCode":"H202002005","dishesLibId":2655,"dishesName":"树根蛋糕","energyKC":8570,"fat":323.5,"fe":67.5,"fiber":50.5,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":4365,"mg":1405,"mn":12.7,"na":2168,"nickAcid":14,"p":2595,"priority":2655,"proWeight":5,"protein":417.5,"remark":"","riboflavin":9,"se":318.15,"sortCode":"230100","status":"1","thiamine":3.3,"va":2340,"vc":130,"ve":120.15,"yesuan":1399.5,"zn":41.2},{"allergens":"奶油、小麦、乳制品、鸡蛋、黄油","ash":33.5,"ca":1442,"carbohydrates":1528.5,"carotene":0,"cholesterol":4281,"createDate":"2016-08-10 12:09:10","createTime":1470802150464,"creatorId":1,"cu":12.9,"dishesCode":"H202002006","dishesLibId":2656,"dishesName":"榴莲奶酪蛋糕","energyKC":15655,"fat":949,"fe":88.6,"fiber":42,"isCultureFit":"Y","isPropertyFit":"Y","isSpaceFit":"Y","k":7050,"mg":1106,"mn":31.44,"na":3159.2,"nickAcid":41.6,"p":4719,"priority":2656,"proWeight":5,"protein":293.5,"remark":"","riboflavin":3.37,"se":177.7,"sortCode":"230100","status":"1","thiamine":6.16,"va":3713,"vc":4,"ve":61.75,"yesuan":883.6,"zn":40.13}]
     * totalCount : 85
     */

    private int totalCount;
    /**
     * allergens : 奶酪、小麦
     * ash : 49.0
     * ca : 8960.0
     * carbohydrates : 808.0
     * carotene : 3600.0
     * cholesterol : 110.0
     * createDate : 2016-08-10 12:09:10
     * createTime : 1470802150462
     * creatorId : 1
     * cu : 4.7
     * dishesCode : H202003001
     * dishesLibId : 2647
     * dishesName : 培根三明治
     * energyKC : 6930.0
     * fat : 250.0
     * fe : 63.0
     * fiber : 12.0
     * isCultureFit : Y
     * isPropertyFit : Y
     * isSpaceFit : Y
     * k : 3030.0
     * mg : 1180.0
     * mn : 10.8
     * na : 6673.0
     * nickAcid : 28.0
     * p : 4710.0
     * priority : 2647
     * proWeight : 5.0
     * protein : 374.0
     * remark :
     * riboflavin : 10.7
     * se : 99.3
     * sortCode : 230100
     * status : 1
     * thiamine : 2.3
     * va : 2120.0
     * vc : 200.0
     * ve : 13.3
     * yesuan : 523.0
     * zn : 83.7
     */

    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> dishesLibList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> getDishesLibList() {
        return dishesLibList;
    }

    public void setDishesLibList(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> dishesLibList) {
        this.dishesLibList = dishesLibList;
    }

//    public static class DishesLibListBean {
//        private String allergens;
//        private String headerCode;
//
//        public boolean isShowWeight() {
//            return ShowWeight;
//        }
//
//        public void setShowWeight(boolean showWeight) {
//            this.ShowWeight = showWeight;
//        }
//
//        private boolean ShowWeight;//是否展示菜品重量
//
//        public int getNum() {
//            return num;
//        }
//
//        public void setNum(int num) {
//            this.num = num;
//        }
//
//        private int num;//多少份
//        public String getHeaderCode() {
//            return headerCode;
//        }
//
//        public void setHeaderCode(String headerCode) {
//            this.headerCode = headerCode;
//        }
//
//        public String getHeaderName() {
//            return headerName;
//        }
//
//        public void setHeaderName(String headerName) {
//            this.headerName = headerName;
//        }
//
//        private String headerName;
//        private double ash;
//        private double ca;
//        private double carbohydrates;
//        private double carotene;
//        private double cholesterol;
//        private String createDate;
//        private long createTime;
//        private int creatorId;
//        private double cu;
//        private String dishesCode;
//        private int dishesLibId;
//        private String dishesName;
//
//        public IngredientsBean getIngredients() {
//            return ingredients;
//        }
//
//        public void setIngredients(IngredientsBean ingredients) {
//            this.ingredients = ingredients;
//        }
//
//        private  IngredientsBean ingredients;
//        private double energyKC;
//        private double fat;
//        private double fe;
//        private double fiber;
//        private String isCultureFit;
//        private String isPropertyFit;
//        private String isSpaceFit;
//        private double k;
//        private double mg;
//        private double mn;
//        private double na;
//        private double nickAcid;
//        private double p;
//        private int priority;
//        private double proWeight;
//        private double protein;
//        private String remark;
//        private double riboflavin;
//        private double se;
//        private String sortCode;
//        private String status;
//        private double thiamine;
//        private double va;
//        private double vc;
//        private double ve;
//        private double yesuan;
//        private double zn;
//
//        public String getAllergens() {
//            return allergens;
//        }
//
//        public void setAllergens(String allergens) {
//            this.allergens = allergens;
//        }
//
//        public double getAsh() {
//            return ash;
//        }
//
//        public void setAsh(double ash) {
//            this.ash = ash;
//        }
//
//        public double getCa() {
//            return ca;
//        }
//
//        public void setCa(double ca) {
//            this.ca = ca;
//        }
//
//        public double getCarbohydrates() {
//            return carbohydrates;
//        }
//
//        public void setCarbohydrates(double carbohydrates) {
//            this.carbohydrates = carbohydrates;
//        }
//
//        public double getCarotene() {
//            return carotene;
//        }
//
//        public void setCarotene(double carotene) {
//            this.carotene = carotene;
//        }
//
//        public double getCholesterol() {
//            return cholesterol;
//        }
//
//        public void setCholesterol(double cholesterol) {
//            this.cholesterol = cholesterol;
//        }
//
//        public String getCreateDate() {
//            return createDate;
//        }
//
//        public void setCreateDate(String createDate) {
//            this.createDate = createDate;
//        }
//
//        public long getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(long createTime) {
//            this.createTime = createTime;
//        }
//
//        public int getCreatorId() {
//            return creatorId;
//        }
//
//        public void setCreatorId(int creatorId) {
//            this.creatorId = creatorId;
//        }
//
//        public double getCu() {
//            return cu;
//        }
//
//        public void setCu(double cu) {
//            this.cu = cu;
//        }
//
//        public String getDishesCode() {
//            return dishesCode;
//        }
//
//        public void setDishesCode(String dishesCode) {
//            this.dishesCode = dishesCode;
//        }
//
//        public int getDishesLibId() {
//            return dishesLibId;
//        }
//
//        public void setDishesLibId(int dishesLibId) {
//            this.dishesLibId = dishesLibId;
//        }
//
//        public String getDishesName() {
//            return dishesName;
//        }
//
//        public void setDishesName(String dishesName) {
//            this.dishesName = dishesName;
//        }
//
//        public double getEnergyKC() {
//            return energyKC;
//        }
//
//        public void setEnergyKC(double energyKC) {
//            this.energyKC = energyKC;
//        }
//
//        public double getFat() {
//            return fat;
//        }
//
//        public void setFat(double fat) {
//            this.fat = fat;
//        }
//
//        public double getFe() {
//            return fe;
//        }
//
//        public void setFe(double fe) {
//            this.fe = fe;
//        }
//
//        public double getFiber() {
//            return fiber;
//        }
//
//        public void setFiber(double fiber) {
//            this.fiber = fiber;
//        }
//
//        public String getIsCultureFit() {
//            return isCultureFit;
//        }
//
//        public void setIsCultureFit(String isCultureFit) {
//            this.isCultureFit = isCultureFit;
//        }
//
//        public String getIsPropertyFit() {
//            return isPropertyFit;
//        }
//
//        public void setIsPropertyFit(String isPropertyFit) {
//            this.isPropertyFit = isPropertyFit;
//        }
//
//        public String getIsSpaceFit() {
//            return isSpaceFit;
//        }
//
//        public void setIsSpaceFit(String isSpaceFit) {
//            this.isSpaceFit = isSpaceFit;
//        }
//
//        public double getK() {
//            return k;
//        }
//
//        public void setK(double k) {
//            this.k = k;
//        }
//
//        public double getMg() {
//            return mg;
//        }
//
//        public void setMg(double mg) {
//            this.mg = mg;
//        }
//
//        public double getMn() {
//            return mn;
//        }
//
//        public void setMn(double mn) {
//            this.mn = mn;
//        }
//
//        public double getNa() {
//            return na;
//        }
//
//        public void setNa(double na) {
//            this.na = na;
//        }
//
//        public double getNickAcid() {
//            return nickAcid;
//        }
//
//        public void setNickAcid(double nickAcid) {
//            this.nickAcid = nickAcid;
//        }
//
//        public double getP() {
//            return p;
//        }
//
//        public void setP(double p) {
//            this.p = p;
//        }
//
//        public int getPriority() {
//            return priority;
//        }
//
//        public void setPriority(int priority) {
//            this.priority = priority;
//        }
//
//        public double getProWeight() {
//            return proWeight;
//        }
//
//        public void setProWeight(double proWeight) {
//            this.proWeight = proWeight;
//        }
//
//        public double getProtein() {
//            return protein;
//        }
//
//        public void setProtein(double protein) {
//            this.protein = protein;
//        }
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public double getRiboflavin() {
//            return riboflavin;
//        }
//
//        public void setRiboflavin(double riboflavin) {
//            this.riboflavin = riboflavin;
//        }
//
//        public double getSe() {
//            return se;
//        }
//
//        public void setSe(double se) {
//            this.se = se;
//        }
//
//        public String getSortCode() {
//            return sortCode;
//        }
//
//        public void setSortCode(String sortCode) {
//            this.sortCode = sortCode;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public double getThiamine() {
//            return thiamine;
//        }
//
//        public void setThiamine(double thiamine) {
//            this.thiamine = thiamine;
//        }
//
//        public double getVa() {
//            return va;
//        }
//
//        public void setVa(double va) {
//            this.va = va;
//        }
//
//        public double getVc() {
//            return vc;
//        }
//
//        public void setVc(double vc) {
//            this.vc = vc;
//        }
//
//        public double getVe() {
//            return ve;
//        }
//
//        public void setVe(double ve) {
//            this.ve = ve;
//        }
//
//        public double getYesuan() {
//            return yesuan;
//        }
//
//        public void setYesuan(double yesuan) {
//            this.yesuan = yesuan;
//        }
//
//        public double getZn() {
//            return zn;
//        }
//
//        public void setZn(double zn) {
//            this.zn = zn;
//        }
//        public static  class IngredientsBean{
////            "foodName":"",
////                    "netWeight":0,
//            private String  foodName;
//
//            public String getNetWeight() {
//                return netWeight;
//            }
//
//            public void setNetWeight(String netWeight) {
//                this.netWeight = netWeight;
//            }
//
//            public String getFoodName() {
//                return foodName;
//            }
//
//            public void setFoodName(String foodName) {
//                this.foodName = foodName;
//            }
//
//            private String  netWeight;
//        }
//    }
}
