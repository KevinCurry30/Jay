package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.diligroup.R;
import com.diligroup.UserSet.ListitemAdapter;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.GetJiaoQinBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报特殊人群
 * Created by Kevin on 2016/6/20.
 */
public class ReportSpecial extends BaseActivity {
    @Bind(R.id.bt_report_special)
    Button bt_specail;
    @Bind(R.id.bt_jump_special)
    Button bt_later_report;
    Boolean isFrist;
    Bundle bundle;

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        tv_title.setText("特殊人群");
        title_infos.setText("请选择你当前状态");
        isShowBack(true);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_special;
    }

    @Bind(R.id.lv_special)
    ListView lv_special;
    GetJiaoQinBean specialBean;

    private List<String> id_list;
    List<GetJiaoQinBean.ListBean> hisList;

    @OnClick(R.id.bt_report_special)
    public void ReprotSpecial() {

        String s = id_list.toString().replaceAll(" ", "");
        String s2 = s.substring(1, s.length() - 1);
        LogUtils.e("特殊人群======" + s2);
        if (isFrist) {
            UserInfoBean.getInstance().setSpecialCrowdCode(s2);
            readyGo(ReportOther.class, bundle);
        } else {
            Map map = new HashMap();
            map.put("specialCrowdCode", s2);
            Api.updataUserInfo(map, this);
            readyGo(UserInfoActivity.class);
            this.finish();

        }

    }

    @OnClick(R.id.bt_jump_special)
    public void jumpSpecial() {

        UserInfoBean.getInstance().setSpecialCrowdCode("");
        readyGo(ReportOther.class);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("特殊人群");
        title_infos.setText("请选择您现在所在的状态");
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        Api.getSpecial(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist) {
            bt_specail.setText("下一步");
            bt_later_report.setVisibility(View.INVISIBLE);
        }
        bt_later_report.setVisibility(View.GONE);
        id_list = new ArrayList<>();
        hisList = new ArrayList<>();
        lv_special.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListitemAdapter.ViewHolder holder = (ListitemAdapter.ViewHolder) view.getTag();
//                holder.cb.toggle();
                holder.cb.setEnabled(true);
                if (holder.cb.isChecked()){
                    id_list.add(holder.foodId);
                }else {
                    removeUnChecked(holder.foodId);
                }
            }
        });
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    public void removeUnChecked(String foodId) {
        if (id_list.size() > 0) {
            for (int i = 0; i < id_list.size(); i++) {
                if (id_list.get(i).equals(foodId)) {
                    id_list.remove(foodId);
                }
            }
        }
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action == Action.GET_SPECIAL && object != null) {
            specialBean = (GetJiaoQinBean) object;
            if (specialBean.getCode().equals("000000")) {
                hisList = specialBean.getList();
                ListitemAdapter adapter = new ListitemAdapter(this, hisList);
                lv_special.setAdapter(adapter);
            }
        }
    }
}
