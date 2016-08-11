package com.diligroup.UserSet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.CityPicker;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报 现住地
 */
public class ReportAddress extends BaseActivity {
    @Bind(R.id.select_address)
    CityPicker select_address;
    String now_address;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_address;
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("现居住地");
        title_infos.setText("请选择您的常居住地");
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }
    @OnClick(R.id.bt_ok_address)
    public void reprotAddress(){
//        ToastUtil.showShort(this,now_address);
        UserInfoBean.getInstance().setHomeAddress(now_address);
        readyGo(ReportTaste.class);
    }

    @OnClick(R.id.bt_later_address)
    public void jumpAddress(){

        UserInfoBean.getInstance().setHomeAddress("");
        readyGo(ReportTaste.class);
    }
    @Override
    protected void initViewAndData() {
        isShowBack(true);
        select_address.setOnSelectingListener(new CityPicker.OnSelectingListener() {
            @Override
            public void selected(boolean selected) {
                if (selected){
                    now_address= select_address.getCity_string();
                }
            }
        });
    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
