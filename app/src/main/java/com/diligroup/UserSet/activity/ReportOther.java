package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diligroup.Home.HomeActivity;
import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;

import android.view.ViewGroup.LayoutParams;

import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.CheckableFrameLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.Request;

public class ReportOther extends BaseActivity {
//    @Bind(R.id.cb_jianzhi)
//    CheckBox cb_jianzhi;
//    @Bind(R.id.cb_zengji)
//    CheckBox cb_zengji;
//    @Bind(R.id.cb_bugai)
//    CheckBox cb_bugai;
//    @Bind(R.id.cb_butie)
//    CheckBox cb_butie;
//    @Bind(R.id.cb_huyan)
//    CheckBox cb_huyan;

    @Bind(R.id.cf_jianzhi)
    CheckableFrameLayout cf_jianzhi;
    @Bind(R.id.cf_zengji)
    CheckableFrameLayout cf_zengji;
    @Bind(R.id.cf_bugai)
    CheckableFrameLayout cf_bugai;
    @Bind(R.id.cf_butie)
    CheckableFrameLayout cf_butie;
    @Bind(R.id.cf_huyan)
    CheckableFrameLayout cf_huyan;
    String otherTarget = "";
    @Bind(R.id.pop_jianfei)
    LinearLayout linear_jianzhi;

    @Bind(R.id.tv_zengji)
    TextView tv_zengji;
    //目标体重
    @Bind(R.id.weight_target)
    TextView target_weight;

    @Bind(R.id.weightRuler)
    HorizontalScrollView weightRuler;
    @Bind(R.id.ll_ruler)
    LinearLayout ll_ruler;
    int beginWeight;
    private int screenWidth;
    @Bind(R.id.tv_weight_now)
    TextView tv_now;
    @Bind(R.id.tv_time_need)
    TextView time_need;
    @Bind(R.id.tv_cost_total)
    TextView tv_cost_total;
    @Bind(R.id.tv_day_cost)
    TextView tv_day_cost;
    Boolean isFrist;
    Bundle bundle;
    @Bind(R.id.bt_report_user)
    Button bt_other;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.text_other;
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
        target_weight.setText("50");
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (!isFrist) {
            bt_other.setText("确定");
        }
        tv_now.setText(UserInfoBean.getInstance().getWeight());
        cf_jianzhi.setEnabled(true);
        cf_zengji.setEnabled(true);
        cf_bugai.setEnabled(true);
        cf_butie.setEnabled(true);
        cf_huyan.setEnabled(true);


        cf_jianzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf_jianzhi.toggle();
                if (cf_jianzhi.isChecked()){
                    otherTarget = "1";
                    showPopJian();
                } else {
                    hidePop();
                    otherTarget = "";
                }
            }
        });
        cf_zengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf_zengji.toggle();
                if (cf_zengji.isChecked()){
                    otherTarget = "2";
                    tv_zengji.setVisibility(View.VISIBLE);
                } else {
                    otherTarget = "";
                    tv_zengji.setVisibility(View.GONE);
                }
            }
        });
        cf_bugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf_bugai.toggle();
                if (cf_bugai.isChecked()){
                    otherTarget = "0";
                } else {
                    otherTarget = "";
                }
            }
        });
        cf_butie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf_butie.toggle();
                if (cf_butie.isChecked()){
                    otherTarget = "0";
                } else {
                    otherTarget = "";
                }
            }
        });
        cf_huyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cf_huyan.toggle();
                if (cf_huyan.isChecked()){
                    otherTarget = "0";
                } else {
                    otherTarget = "";
                }
            }
        });

//        cb_jianzhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    otherTarget = "1";
//                    showPopJian();
//                } else {
//                    hidePop();
//                    otherTarget = "";
//                }
//            }
//        });
//        cb_zengji.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    otherTarget = "2";
//                    tv_zengji.setVisibility(View.VISIBLE);
//                } else {
//                    otherTarget = "";
//                    tv_zengji.setVisibility(View.GONE);
//                }
//            }
//        });
//        cb_bugai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    otherTarget = "0";
//                } else {
//                    otherTarget = "";
//                }
//            }
//        });
//
//        cb_butie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    otherTarget = "0";
//                } else {
//                    otherTarget = "";
//                }
//            }
//        });
//        cb_huyan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    otherTarget = "0";
//                } else {
//                    otherTarget = "";
//                }
//            }
//        });
        weightRuler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
//                double scrollx = weightRuler.getScrollX() / 20;
//                double value = scrollx / 10;
//                target_weight.setText(String.valueOf(beginWeight
//                        + value));
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                double scrollx = weightRuler.getScrollX() / 20;
                                double value = scrollx / 10;
                                Log.e("Value=========", String.valueOf(scrollx));
                                Log.e("Double==Value=========", String.valueOf(value));
                                target_weight.setText(String.valueOf(beginWeight
                                        + value));
                            }
                        }, 1000);
                        break;
                }
                return false;
            }
        });
    }

    private void showPopJian() {
        linear_jianzhi.setVisibility(View.VISIBLE);
    }

    private void hidePop() {
        linear_jianzhi.setVisibility(View.GONE);
    }

    private boolean is_first = true;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (is_first) {
            screenWidth = weightRuler.getWidth();
            constructRuler();
            is_first = false;
    }
    }
    private void constructRuler() {
        beginWeight = 40;
        View leftview = LayoutInflater.from(this).inflate(
                R.layout.blankhrulerunit, null);
        leftview.setLayoutParams(new LayoutParams(screenWidth / 2,
                LayoutParams.MATCH_PARENT));
        ll_ruler.addView(leftview);
        for (int i = 0; i < 20; i++) {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.hrulerunit, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(200,
                    LayoutParams.MATCH_PARENT));
            TextView tv = (TextView) view.findViewById(R.id.hrulerunit);
            tv.setText(String.valueOf(beginWeight + i));

            ll_ruler.addView(view);
        }
        View rightview = LayoutInflater.from(this).inflate(
                R.layout.blankhrulerunit, null);
        rightview.setLayoutParams(new LayoutParams(screenWidth / 2,
                LayoutParams.MATCH_PARENT));
        ll_ruler.addView(rightview);
    }

    @OnClick(R.id.bt_report_user)
    public void reportOther() {
        if (isFrist) {
            UserInfoBean.getInstance().setOtherReq(otherTarget);
            UserInfoBean.getInstance().setTargetWeight(String.valueOf(target_weight));
            LogUtils.e("otherTarget==========" + otherTarget);
            Api.setUserInfo(this);
            readyGo(HomeActivity.class);
            this.finish();
        } else {
            if (otherTarget != null) {
                Map map = new HashMap();
                map.put("reqType", otherTarget);
                map.put("targetWeight", target_weight);
                Api.updataUserInfo(map,this);
                this.finish();
            }

        }

    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
                if (action==Action.SET_INFOS&&object!=null){
                    CommonBean  commonBean= (CommonBean) object;
                    if (commonBean.getCode().equals("000000")){

                    }
                }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll();
            }
        }, 100);
    }

    private void scroll() {
        weightRuler.smoothScrollTo((1970 - beginWeight) * 20, 0);
    }

}
