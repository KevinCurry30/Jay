package com.diligroup.bean;

import java.util.List;

/**
 * Created by hjf on 2016/7/21.
 */
public class BannerBean extends CommonBean {
    /**
     * imageUrl : ["www1","www2","","",""]
     * totalCount : 0
     */

    private int totalCount;
    private List<String> imageUrl;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
