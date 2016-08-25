package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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

/***
 * 食物禁忌上报
 */
public class ReportNoeat extends BaseActivity {

    @Bind(R.id.list_no_eat)
    ListView lv_noeat;
    List<GetJiaoQinBean.ListBean> datalist;
//    StringBuilder builder;
    List<String> noeat_list;
    GetJiaoQinBean getJiaoQinBean;
    Boolean isFrist;
    Bundle bundle;
    @Bind(R.id.bt_ok_noeat)
    Button bt_noEat;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    datalist = getJiaoQinBean.getList();
                    ListitemAdapter listitemAdapter=new ListitemAdapter(ReportNoeat.this, datalist);
                    lv_noeat.setAdapter(listitemAdapter);
                    addClicked();
                    break;
            }
        }


    };


    public void addClicked() {
        lv_noeat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListitemAdapter.ViewHolder holder = (ListitemAdapter.ViewHolder) view.getTag();
                holder.cb.setEnabled(true);
//                holder.cb.toggle();// 点哪个  就改变哪个checkbox 状态
                if (holder.cb.isChecked()) {
//                    ToastUtil.showShort(ReportNoeat.this, "Checked" + holder.foodId);
                    noeat_list.add(holder.foodId);
                } else {
//                    ToastUtil.showShort(ReportNoeat.this, "UnChecked" + holder.foodId);
                    removeUnChecked(holder.foodId);
                }
            }
        });
    }
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_noeat;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }


    @Override
    protected void initViewAndData() {
        tv_title.setText("饮食禁忌");
        title_infos.setText("请选择您的饮食禁忌");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Api.getNoEatFood(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist){
            bt_noEat.setText("下一步");
        }
        datalist = new ArrayList<>();
        noeat_list = new ArrayList<>();

    }

    @OnClick(R.id.bt_ok_noeat)
    public void reportNoeat() {

        String s = noeat_list.toString().replaceAll(" ", "");
        String s2 = s.substring(1, s.length() - 1);
        LogUtils.e("饮食禁忌======" + s2);
        if (isFrist){
            if (!TextUtils.isEmpty(s2)) {
                UserInfoBean.getInstance().setNoEatFood(s2);
                readyGo(ReportAllergy.class,bundle);
            }
        }else{
            Map map =new HashMap();
            map.put("tabooCode",s2);
            Api.updataUserInfo(map,this);
//            readyGo(UserInfoActivity.class);
//            this.finish();

        }
    }

    public void removeUnChecked(String foodId) {
        if (noeat_list.size() > 0) {
            for (int i = 0; i < noeat_list.size(); i++) {
                if (noeat_list.get(i).equals(foodId)) {
                    noeat_list.remove(foodId);
                }
            }
        }
    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.GET_NO_EAT) {
            getJiaoQinBean = (GetJiaoQinBean) object;
            mHandler.sendEmptyMessage(1);
        }
        if (action==Action.UPDATA_USERINFO&&object!=null){
            CommonBean  commonBean= (CommonBean) object;
            if (commonBean.getCode().equals("000000")){
                Intent intent=new Intent();
                intent.putExtra("noeat",String.valueOf(noeat_list.size()));
                setResult(0x50,intent);
                this.finish();
            }
        }
    }
}
