package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.CityPicker;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报籍贯
 */
public class ReportWhere extends BaseActivity {
    @Bind(R.id.select_where)
    CityPicker cityPicker;
    String select_city;
    Boolean isFrist;
    Bundle bundle;
    @Bind(R.id.bt_ok_where)
    Button bt_where;
    @Bind(R.id.bt_later_where)
    Button bt_later_report;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_where;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }



    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("籍贯");
        title_infos.setText("请选择您的籍贯");
    }

    @Override
    protected void onNetworkDisConnected() {

    }
    @Override
    protected void initViewAndData() {
        isShowBack(true);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");

        if (isFrist) {
            bt_where.setText("下一步");
            bt_later_report.setVisibility(View.GONE);
        }
//        cityPicker.set
        select_city= cityPicker.getCity_string();
        cityPicker.setOnSelectingListener(new CityPicker.OnSelectingListener() {
            @Override
            public void selected(boolean selected) {
                if (selected){
                    select_city= cityPicker.getCity_string();
                }
            }
        });
    }

    @OnClick(R.id.bt_ok_where)
    public void reportWhere(){
//        ToastUtil.showShort(this,select_city);
        if (isFrist){
//            UserInfoBean.getInstance().setHomeAddress(select_city);
            readyGo(ReportAddress.class,bundle);
            return;
        }
        Map map =new HashMap();
        map.put("homeAdd",select_city);
        Api.updataUserInfo(map,this);
        readyGo(UserInfoActivity.class);
        this.finish();


    }
    @OnClick(R.id.bt_later_where)
    public void reportLater(){
        UserInfoBean.getInstance().setHomeAddress("");
        readyGo(UserInfoActivity.class);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
