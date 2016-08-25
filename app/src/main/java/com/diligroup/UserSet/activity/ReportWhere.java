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
        return R.layout.activity_select_where;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }
    @Override
    protected void onNetworkDisConnected() {

    }
    @Override
    protected void initViewAndData() {
        tv_title.setText("籍贯");
        title_infos.setText("请选择您的籍贯");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        Api.getCity(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");

        if (isFrist) {
            bt_where.setText("下一步");
            bt_later_report.setVisibility(View.VISIBLE);
        }
//        select_city= cityPicker.getCity_string().substring(0,2);
        cityPicker.setOnSelectingListener(new CityPicker.OnSelectingListener() {
            @Override
            public void selected(boolean selected) {
                if (selected){
                    select_city= cityPicker.getCity_string().substring(0,2);
                    provinceCode=cityPicker.getProvinceCode();
                    cityCode=cityPicker.getCity_code_string();
                    counyCode=cityPicker.getCountCode();
                    LogUtils.e("select_city====="+select_city);
                    LogUtils.e("provinceCode====="+provinceCode);
                    LogUtils.e("cityCode====="+cityCode);
                    LogUtils.e("counyCode====="+counyCode);

                }
            }
        });
    }

    @OnClick(R.id.bt_ok_where)
    public void reportWhere(){
//        ToastUtil.showShort(this,select_city);
        if (isFrist){
//            UserInfoBean.getInstance().setHomeAddress(select_city);
            UserInfoBean.getInstance().setHomeProvinceCode(provinceCode);
            UserInfoBean.getInstance().setHomeCityCode(cityCode);
            UserInfoBean.getInstance().setHomeDistrictId(counyCode);
            readyGo(ReportAddress.class,bundle);
            return;
        }
        Map map =new HashMap();
        map.put("homeProvinceCode",provinceCode);
        map.put("homeCityCode",cityCode);
        map.put("homeDistrictId",counyCode);
        Api.updataUserInfo(map,this);
//        readyGo(UserInfoActivity.class);
//        this.finish();


    }
    @OnClick(R.id.bt_later_where)
    public void reportLater(){
//        UserInfoBean.getInstance().setHomeAddress("");
        readyGo(UserInfoActivity.class);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
//        if (action==Action.GET_WHERE&&object!=null){
//            GetWhereBean  getWhereBean= (GetWhereBean) object;
//
//        }
        if (object!=null&&action==Action.UPDATA_USERINFO){
            CommonBean commonBean= (CommonBean) object;
            if (commonBean.getCode().equals("000000")){
                Intent intent=new Intent();
                intent.putExtra("where",select_city);
                setResult(0x70,intent);
                this.finish();
            }
        }
    }
}
