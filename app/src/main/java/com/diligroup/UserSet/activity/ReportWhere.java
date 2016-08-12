package com.diligroup.UserSet.activity;

import android.widget.Button;

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
 * 上报籍贯
 */
public class ReportWhere extends BaseActivity {
    @Bind(R.id.select_where)
    CityPicker cityPicker;
    String select_city;

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
        UserInfoBean.getInstance().setHomeAddress(select_city);
        readyGo(ReportAddress.class);
    }
    @OnClick(R.id.bt_later_where)
    public void reportLater(){
        UserInfoBean.getInstance().setHomeAddress("");
        readyGo(ReportAddress.class);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
