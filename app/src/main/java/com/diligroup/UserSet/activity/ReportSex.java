package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

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
    @Bind(R.id.bt_ok_sex)
    Button bt_sex;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    String  sex;
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
    protected void initViewAndData() {
        tv_title.setText("基础信息");
        title_infos.setText("您的性别?");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        bundle=     intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist){
            bt_sex.setText("下一步");
        }
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
        if (sexMark == 1 || sexMark == 0) {
            if (isFrist) {
//                UserInfoBean.getInstance().setSex(sexMark+"");
                UserInfoBean.getInstance().setSex(sex);
                readyGo(ReportBirthday.class, bundle);
            } else{
                Map<String,String>  map =new HashMap();
                map.put("sex",String.valueOf(sexMark));
                Api.updataUserInfo(map,this);
//                readyGo(UserInfoActivity.class);
//                this.finish();
            }
            LogUtils.e("性别====="+String.valueOf(sexMark));

        } else{
            ToastUtil.showShort(ReportSex.this, "请选择性别");
        }




    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
                if (action==Action.UPDATA_USERINFO&&object!=null){
                    CommonBean commonBean= (CommonBean) object;
                    if (commonBean.getCode().equals("000000")){
                        Intent intent=new Intent();
                        intent.putExtra("sex",String.valueOf(sexMark));
                        setResult(0x00,intent);
                        this.finish();

    }
}}}
