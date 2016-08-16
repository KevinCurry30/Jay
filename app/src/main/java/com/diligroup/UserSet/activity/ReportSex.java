package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报性别
 */
public class ReportSex extends BaseActivity {

    private int sexMark;
    @Bind(R.id.ib_boy)
    CheckBox cb_boy;
    @Bind(R.id.ib_gril)
    CheckBox cb_girl;
    Boolean isFrist;
    Bundle bundle;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_sex;
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
        tv_title.setText("基础信息");
        title_infos.setText("您的性别?");
    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");

        sexMark = 3;
        cb_boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_girl.setChecked(false);
                    sexMark = 1;
                } else {
                    sexMark = 3;
                }
            }
        });
        cb_girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_boy.setChecked(false);
                    sexMark = 0;
                } else {
                    sexMark = 3;
                }
            }
        });
    }

    @OnClick(R.id.bt_ok_sex)
    public void reportSex() {
        if (isFrist) {
            if (sexMark == 1 || sexMark == 0) {
                UserInfoBean.getInstance().setSex(sexMark);
                readyGo(ReportBirthday.class, bundle);
                return;
            }
            ToastUtil.showShort(ReportSex.this, "请选择性别");
        }
        readyGo(UserInfoActivity.class);

    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
