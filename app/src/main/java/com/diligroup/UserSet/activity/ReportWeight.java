package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.diligroup.view.WheelView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报体重
 */
public class ReportWeight extends BaseActivity {
    @Bind(R.id.wheel_weight)
    WheelView wheelView;
    String select_weight;
    Boolean isFrist;
    Bundle bundle;
    @Bind(R.id.bt_ok_weight)
    Button bt_weight;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_weight;
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
        title_infos.setText("您的体重?");
       ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist){
            bt_weight.setText("下一步");
        }
        wheelView.setOffset(3);
        if (UserInfoBean.getInstance().getSex()!=null&&UserInfoBean.getInstance().getSex().equals("0")){
            wheelView.setSeletion(5);
            select_weight="50";
        }

        if (UserInfoBean.getInstance().getSex()!=null&&UserInfoBean.getInstance().getSex().equals("1")){
            wheelView.setSeletion(25);
            select_weight="70";
        }
        wheelView.setItems(Arrays.asList(getResources().getStringArray(R.array.weight)));
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                select_weight = item.replace(" ","").replace("Kg","");

            }
        });
    }

    @OnClick(R.id.bt_ok_weight)
    public void reportWeight() {
//        ToastUtil.showShort(this, select_weight);
        LogUtils.e("体重======"+select_weight);
        if (isFrist){
            UserInfoBean.getInstance().setHeight(select_weight.trim());
            readyGo(ReportNoeat.class,bundle);
        }else{
            Map map =new HashMap();
            map.put("weight",select_weight);
            Api.updataUserInfo(map,this);
            readyGo(UserInfoActivity.class);
            this.finish();

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
                intent.putExtra("weight",select_weight);
                setResult(0x40,intent);
                this.finish();

            }
        }
    }
}
