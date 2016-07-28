package com.diligroup.shop;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;

import butterknife.OnClick;
import okhttp3.Request;

/**
 * 用户手动 选择 门店地址
 * Created by Kevin on 2016/7/11.
 */
public class SelectShopByUser extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.select_shop;
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("选择门店");
        title_infos.setText("选择门店");
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
    }
    @OnClick(R.id.bt_report_shop)
    public void reportShop(){

    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
