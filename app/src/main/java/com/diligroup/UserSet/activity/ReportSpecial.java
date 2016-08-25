package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.UserSet.ListitemAdapter;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.GetJiaoQinBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;

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
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_special;
    }

    @Bind(R.id.lv_special)
    ListView lv_special;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    GetJiaoQinBean specialBean;

    private List<String> id_list;
    List<GetJiaoQinBean.ListBean> hisList;
    int selectCount;

    @OnClick(R.id.bt_report_special)
    public void ReprotSpecial() {

        String s = id_list.toString().replaceAll(" ", "");
        String s2 = s.substring(1, s.length() - 1);
        LogUtils.e("特殊人群======" + s2);
        if (isFrist) {
            UserInfoBean.getInstance().setSpecialCrowdCode(s2);
            readyGo(ReportOther.class, bundle);
        } else {
            selectCount = id_list.size();
            Map map = new HashMap();
            map.put("specialCrowdCode", s2);
            Api.updataUserInfo(map, this);
//            readyGo(UserInfoActivity.class);
//            this.finish();

        }

    }

    @OnClick(R.id.bt_jump_special)
    public void jumpSpecial() {

        UserInfoBean.getInstance().setSpecialCrowdCode("");
        readyGo(ReportOther.class);
    }
    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
       iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Api.getSpecial(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        tv_title.setText("特殊人群");
        title_infos.setText("请选择你当前状态");
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
                if (holder.cb.isChecked()) {
                    id_list.add(holder.foodId);
//                    GetCheckStateUtils.addSelect(holder.foodId);
                } else {
//                    GetCheckStateUtils.removeSelect(holder.foodId);
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
//HashMap<Integer,Boolean>  hashMap;
    @Override
    public void onResponse(Request request, Action action, Object object) {

        if (action == Action.GET_SPECIAL && object != null) {
            specialBean = (GetJiaoQinBean) object;
            if (specialBean.getCode().equals("000000")) {
                hisList = specialBean.getList();
//                hashMap=new HashMap();
//                hashMap.put(0,false);
//                hashMap.put(1,true);
//                hashMap.put(2,false);
//                hashMap.put(3,true);
//                hashMap.put(4,false);
//                hashMap.put(5,true);
//                hashMap.put(6,true);
//                hashMap.put(7,true);
//                hashMap.put(8,true);
//                ListitemAdapter.setIsSelected(hashMap);
                ListitemAdapter adapter = new ListitemAdapter(this, hisList);

                lv_special.setAdapter(adapter);

//                adapter.notifyDataSetChanged();
            }
        }
        if (object != null && action == Action.UPDATA_USERINFO) {
            CommonBean commonBean = (CommonBean) object;
            if (commonBean.getCode().equals("000000")) {
                Intent intent = new Intent();
                intent.putExtra("special", String.valueOf(selectCount));
                setResult(0x110, intent);
                this.finish();
            }
        }
    }
}
