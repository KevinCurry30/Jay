package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.diligroup.Home.HomeActivity;
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
 * 上报健康史
 */
public class ReportHistory extends BaseActivity {
    @Bind(R.id.lv_history)
    ListView lv_history;
    GetJiaoQinBean historyBean;
    private List<String> id_list;
    List<GetJiaoQinBean.ListBean> hisList;
    @Bind(R.id.bt_jump_history)
    Button bt_later_report;
    Boolean isFrist;
    Bundle bundle;

    @OnClick(R.id.bt_report_history)
    public void ReportHisty() {
        String s = id_list.toString().replaceAll(" ", "");
        String s2 = s.substring(1, s.length() - 1);
        LogUtils.e("健康史======" + s2);

        if (isFrist) {
            UserInfoBean.getInstance().setChronicDiseaseCode(s2);
            readyGo(ReportSpecial.class, bundle);
        } else {
            Map map = new HashMap();
            map.put("chronicDiseaseCode", s2);
            Api.updataUserInfo(map, this);
            readyGo(UserInfoActivity.class);
        }


    }

    @OnClick(R.id.bt_jump_history)
    public void jumpHistory() {
        UserInfoBean.getInstance().setChronicDiseaseCode("");
        readyGo(HomeActivity.class);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("健康史");
        title_infos.setText("请选择您的历史健康记录");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_history;
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
        Api.getHistory(this);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist) {
            bt_later_report.setVisibility(View.VISIBLE);
        }
        bt_later_report.setVisibility(View.INVISIBLE);
        hisList = new ArrayList<>();
        lv_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListitemAdapter.ViewHolder holder= (ListitemAdapter.ViewHolder) view.getTag();
                holder.cb.setEnabled(true);
//                holder.cb.toggle();

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
        if (object != null && action == Action.GET_HISTORY) {
            historyBean = (GetJiaoQinBean) object;
            id_list = new ArrayList<>();
            hisList = historyBean.getList();
            ListitemAdapter adapter = new ListitemAdapter(this, hisList);
            lv_history.setAdapter(adapter);
        }
    }
}
