package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.BirthdayUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.view.WheelView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报 生日
 */
public class ReportBirthday extends BaseActivity {

    //    @Bind(R.id.data_select)
//    DatePicker data_select;
    @Bind(R.id.wv_height)
    WheelView wheelView;
    @Bind(R.id.wv_height2)
    WheelView wheelView2;
    @Bind(R.id.wv_height3)
    WheelView wheelView3;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    Boolean isFrist;
    Bundle bundle;
//    int currentYear;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_birthday;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {
    }

    String year;
    String month;
    String day;
    String brithday;

    @Override
    protected void initViewAndData() {
        ivBack.setVisibility(View.VISIBLE);
        tv_title.setText("基础信息");
        title_infos.setText("您的生日？");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        WindowManager wm = this.getWindowManager();
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist) {
            bt_birthday.setText("下一步");
        }
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        final int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);

        ViewGroup.LayoutParams params = wheelView.getLayoutParams();
        params.height = wheelView.getHeight();
        params.width = wm.getDefaultDisplay().getWidth() / 3;
        wheelView.setLayoutParams(params);
        wheelView2.setLayoutParams(params);
        wheelView3.setLayoutParams(params);
        wheelView.setOffset(3);
        wheelView2.setOffset(3);
        wheelView3.setOffset(3);

        wheelView.setItems(BirthdayUtils.getYears());

        wheelView2.setItems(BirthdayUtils.getMonth());
        wheelView3.setItems(BirthdayUtils.get31Day());
//        currentYear = year;
//        data_select.updateDate(year, month, day);

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                year = item.replace("年", "");
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });

        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                month = item.replace("月", "");
                if (item.equals("1月") || item.equals("3月") || item.equals("5月") || item.equals("7月") || item.equals("8月") || item.equals("10月") || item.equals("12月")) {
//                    wheelView3.setItems(BirthdayUtils.get31Day());
//                    wheelView3.notify();
                }
                if (item.equals("4月") || item.equals("6月") || item.equals("9月") || item.equals("11月")) {
//                    wheelView3.setItems(BirthdayUtils.get30Day());
//                    wheelView3.notify();
                }
                if (item.equals("2月")) {
//                    wheelView3.setItems(BirthdayUtils.get29Day());
//                    wheelView3.notify();
                }
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                day = item.replace("日", "");
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    @Bind(R.id.bt_getTime)
    Button bt_birthday;

    @OnClick(R.id.bt_getTime)
    public void getBirthday() {
        brithday = year + "-" + month + "-" + day;
        LogUtils.e("生日======" + brithday);
        if (isFrist) {

            UserInfoBean.getInstance().setBirthday(brithday.trim());
            readyGo(ReportWork.class, bundle);
        } else {
            Map map = new HashMap();
            map.put("birthday", brithday);
            Api.updataUserInfo(map, this);
//            readyGo(UserInfoActivity.class);
//            this.finish();

        }
//        ToastUtil.showShort(ReportBirthday.this, "您选择的日期是：" + data_select.getYear() + "年" + (data_select.getMonth() + 1) + "月" + data_select.getDayOfMonth() + "日。");
//        int old = currentYear - data_select.getYear();
//        ToastUtil.showShort(ReportBirthday.this,"你当前"+old+"岁");

    }
    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action == Action.UPDATA_USERINFO && object != null) {
            CommonBean commonBean = (CommonBean) object;
            if (commonBean.getCode().equals("000000")) {
                Intent intent = new Intent();
                intent.putExtra("brithday", brithday);
                setResult(0x10, intent);
                this.finish();

            }
        }
    }
}
