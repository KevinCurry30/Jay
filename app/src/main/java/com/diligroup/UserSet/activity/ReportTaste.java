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
import com.diligroup.bean.CommonBean;
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
 * 上报 口味
 */
public class ReportTaste extends BaseActivity {
    @Bind(R.id.lv_taste)
    ListView lv_taste;
    GetJiaoQinBean tasteBean;
    Boolean isFrist;
    Bundle bundle;
    private List<String> id_list;
    List<GetJiaoQinBean.ListBean> tasteBean_list;
    @Bind(R.id.bt_jump_taste)
    Button bt_later_report;
    @Bind(R.id.bt_report_taste)
    Button bt_taste;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_taste;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("口味");
        title_infos.setText("请选择您的口味喜好");
    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        Api.getTaste(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        if (isFrist){
            isFrist = bundle.getBoolean("isFrist");
            bt_taste.setText("下一步");
            bt_later_report.setVisibility(View.INVISIBLE);
        }
        bt_later_report.setVisibility(View.GONE);
        lv_taste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListitemAdapter.ViewHolder holder= (ListitemAdapter.ViewHolder) view.getTag();
                holder.cb.setEnabled(true);

                if (holder.cb.isChecked()){
                    id_list.add(holder.foodId);
                }else {
                    removeUnChecked(holder.foodId);
                }
            }
        });
    }
    public void removeUnChecked(String foodId) {
        if (id_list.size()>0){
            for (int i=0;i<id_list.size();i++){
                if (id_list.get(i).equals(foodId)){
                    id_list.remove(foodId);
                }
            }
        }
    }
    int selectCount;
    @OnClick(R.id.bt_report_taste)
    public void ReporTaste() {
        String s=id_list.toString().replaceAll(" ","");
        String s2= s.substring(1,s.length()-1);
        LogUtils.e("口味======"+s2);

        if (isFrist){
            UserInfoBean.getInstance().setTaste(s2);
            readyGo(ReportHistory.class,bundle);
            return;
        }
        selectCount=id_list.size();
        Map map =new HashMap();
        map.put("tasteCode",s2);
        Api.updataUserInfo(map,this);
//       readyGo(UserInfoActivity.class);
//        this.finish();


    }
    @OnClick(R.id.bt_jump_taste)
    public void jumpTaste() {
//        String s=id_list.toString().replaceAll(" ","");
//        String s2= s.substring(1,s.length()-1);
//        ToastUtil.showShort(ReportTaste.this,s2);
        UserInfoBean.getInstance().setTaste("");
        readyGo(ReportHistory.class);

    }
    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object!=null&&action==Action.GET_TASTE){
            tasteBean= (GetJiaoQinBean) object;
            id_list=new ArrayList<>();
            tasteBean_list = tasteBean.getList();
            ListitemAdapter adapter = new ListitemAdapter(this,tasteBean_list);
            lv_taste.setAdapter(adapter);
        }
        if (action==Action.UPDATA_USERINFO&&object!=null){
            CommonBean commonBean= (CommonBean) object;
            if (commonBean.getCode().equals("000000")){
                Intent intent=new Intent();
                intent.putExtra("taste",String.valueOf(selectCount));
                setResult(0x30,intent);
                this.finish();

            }
        }
    }
}
