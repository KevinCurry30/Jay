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
import com.diligroup.view.CityPicker;

import java.util.HashMap;
import java.util.Map;

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
    Boolean isFrist;
    Bundle bundle;
    @Bind(R.id.bt_later_address)
    Button bt_later_report;
    @Bind(R.id.bt_ok_address)
    Button bt_address;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    String provinceCode;
    String cityCode;
    String counyCode;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_address;
    }


    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @OnClick(R.id.bt_ok_address)
    public void reprotAddress() {
        if (isFrist) {
            UserInfoBean.getInstance().setCurrentProvinceCode(provinceCode);
            UserInfoBean.getInstance().setCurrentCityCode(cityCode);
            UserInfoBean.getInstance().setCurrentDistrictId(counyCode);
            bt_address.setText("下一步");
            bt_later_report.setVisibility(View.VISIBLE);
            readyGo(ReportTaste.class, bundle);
        } else {
            Map map = new HashMap();
            map.put("currentProvinceCode",provinceCode);
            map.put("currentCityCode",cityCode);
            map.put("currentDistrictId",counyCode);
            Api.updataUserInfo(map, this);

//            map.put("currentAddress", now_address);
//            readyGo(UserInfoActivity.class);
//            this.finish();

        }

    }

    @OnClick(R.id.bt_later_address)
    public void jumpAddress() {
        readyGo(UserInfoActivity.class);
    }

    @Override
    protected void initViewAndData() {
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title.setText("现居住地");
        title_infos.setText("请选择您的常居住地");
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist) {
            bt_address.setText("下一步");
            bt_later_report.setVisibility(View.GONE);
        }
        select_address.setOnSelectingListener(new CityPicker.OnSelectingListener() {
            @Override
            public void selected(boolean selected) {
                if (selected) {
                    now_address = select_address.getCity_string().substring(0,2);
                    provinceCode=select_address.getProvinceCode();
                    cityCode=select_address.getCity_code_string();
                    counyCode=select_address.getCountCode();

                    LogUtils.e("provinceCode====="+provinceCode);
                    LogUtils.e("cityCode====="+cityCode);
                    LogUtils.e("counyCode====="+counyCode);
                }
            }
        });
    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.UPDATA_USERINFO) {
            CommonBean commonBean = (CommonBean) object;
            if (commonBean.getCode().equals("000000")) {
                Intent intent = new Intent();
                intent.putExtra("address", now_address);
                setResult(0x80, intent);
                this.finish();
            }
        }
    }
}
