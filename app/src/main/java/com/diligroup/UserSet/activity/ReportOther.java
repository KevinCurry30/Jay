package com.diligroup.UserSet.activity;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.diligroup.Home.HomeActivity;
import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.Request;

public class ReportOther extends BaseActivity {
    @Bind(R.id.cb_jianzhi)
    CheckBox cb_jianzhi;
    @Bind(R.id.cb_zengji)
    CheckBox cb_zengji;
    @Bind(R.id.cb_bugai)
    CheckBox cb_bugai;
    @Bind(R.id.cb_butie)
    CheckBox cb_butie;
    @Bind(R.id.cb_huyan)
    CheckBox cb_huyan;
    String otherTarget="";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_other;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("其他需求");
        title_infos.setText("请选择要达到的其他目的");
    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        cb_jianzhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        otherTarget="1";
                    }else{
                        otherTarget="";
                    }
            }
        });
        cb_zengji.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    otherTarget="2";
                }else{
                    otherTarget="";
                }
            }
        });  cb_bugai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    otherTarget="0";
                }else{
                    otherTarget="";
                }
            }
        });

        cb_butie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    otherTarget="0";
                }else{
                    otherTarget="";
                }
            }
        });
        cb_huyan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    otherTarget="0";
                }else{
                    otherTarget="";
                }
            }
        });

    }

    @OnClick(R.id.bt_report_user)
    public void reportOther() {
        UserInfoBean.getInstance().setOtherReq(otherTarget);
        LogUtils.e("otherTarget=========="+otherTarget);
        Api.updataUserInfo(this);
        readyGo(HomeActivity.class);
        this.finish();
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }

}
