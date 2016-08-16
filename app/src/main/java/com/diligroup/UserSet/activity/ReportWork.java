package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.platform.comapi.map.E;
import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.GetJobBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报  职业
 * Created by Kevin on 2016/6/16.
 */
public class ReportWork extends BaseActivity {

    //    private String[] lightWork;
//    private String[] middleWork;
//    private String[] heavyWork;

    @Bind(R.id.gv_light)
    GridView gv_light;
    @Bind(R.id.gv_middle)
    GridView gv_middle;
    @Bind(R.id.gv_heavy)
    GridView gv_heavy;
    @Bind(R.id.tv_light)
    TextView tv_light;
    @Bind(R.id.tv_middle)
    TextView tv_middle;
    @Bind(R.id.tv_heavy)
    TextView tv_heavy;
    @Bind(R.id.rl_light)
    RelativeLayout rl_light;
    @Bind(R.id.rl_middle)
    RelativeLayout rl_middle;
    @Bind(R.id.rl_heavy)
    RelativeLayout rl_heavy;
    List<GetJobBean.QlistBean> light_list;
    List<GetJobBean.ZlistBean> middle_list;
    List<GetJobBean.WlistBean> heavy_list;
//    List<String> jobName;
    List<String> light_job_name;
    List<String> middle_job_name;
    List<String> heavy_job_name;
    private String userSelect;
    private  String jobCode;
    private  WorkAdapter  adapter;
    Boolean isFrist;
    Bundle bundle;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_work;
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
        Api.getWorkType(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");

        light_job_name=new ArrayList<>();
        middle_job_name=new ArrayList<>();
        heavy_job_name=new ArrayList<>();

        gv_light.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSeclection(position);
                userSelect=light_list.get(position).getProfName();
                jobCode=light_list.get(position).getCode();

            }
        });
        gv_middle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSeclection(position);
                 userSelect=middle_list.get(position).getProfName();
                jobCode=middle_list.get(position).getCode();

            }
        });
        gv_heavy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSeclection(position);
                userSelect=heavy_list.get(position).getProfName();
                jobCode=heavy_list.get(position).getCode();

            }
        });
    }


    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("基础信息");
        title_infos.setText("您的职业?");
    }

    @OnClick(R.id.bt_commit_work)
    public void reportWorkData() {

        if (userSelect!=null){
//            ToastUtil.showLong(this, "You  work ====" + userSelect);
//            ToastUtil.showLong(this, "You  jobCode ====" + jobCode);
//            UserInfoBean.getInstance().setJob("");
            if (isFrist){
                UserInfoBean.getInstance().setJob(userSelect);
                UserInfoBean.getInstance().setJobType(jobCode);
                readyGo(ReportHeight.class,bundle);
                return;
            } readyGo(UserInfoActivity.class);

        }else{
            ToastUtil.showShort(ReportWork.this,"请选择职业");
        }


    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.GET_WORK_TYPE) {
            GetJobBean jobdata = (GetJobBean) object;
            if (jobdata.getCode().equals("000000")) {
                if (jobdata.getQlist() != null && jobdata.getQlist().size() > 0) {
                    light_list = jobdata.getQlist();
                    rl_light.setVisibility(View.VISIBLE);
                    gv_light.setVisibility(View.VISIBLE);
                    tv_light.setText("轻体力");
                    getLightJob(light_list);
                    adapter=new WorkAdapter(light_job_name);
                    gv_light.setAdapter(adapter);
                }
                if (jobdata.getZlist() != null && jobdata.getZlist().size() > 0) {
                    middle_list = jobdata.getZlist();
                    rl_middle.setVisibility(View.VISIBLE);
                    gv_middle.setVisibility(View.VISIBLE);
                    tv_middle.setText("中等体力");
                    getMiddleJob(middle_list);
                    adapter=new WorkAdapter(middle_job_name);
                    gv_middle.setAdapter(adapter);
                }
                if (jobdata.getWlist() != null && jobdata.getWlist().size() > 0) {
                    heavy_list = jobdata.getWlist();
                    rl_heavy.setVisibility(View.VISIBLE);
                    gv_heavy.setVisibility(View.VISIBLE);
                    tv_heavy.setText("重体力");
                    getHeavyJob(heavy_list);
                    adapter=new WorkAdapter(heavy_job_name);
                    gv_heavy.setAdapter(adapter);
                }
            }


        }
    }

    private void getLightJob(List<GetJobBean.QlistBean> list) {

        for (int i = 0; i < list.size(); i++) {
            light_job_name.add(list.get(i).getProfName());
        }

    }

    private void getMiddleJob(List<GetJobBean.ZlistBean> list) {

        for (int i = 0; i < list.size(); i++) {
            middle_job_name.add(list.get(i).getProfName());
        }

    }

    private void getHeavyJob(List<GetJobBean.WlistBean> list) {
        for (int i = 0; i < list.size(); i++) {
            heavy_job_name.add(list.get(i).getProfName());
        }

    }

    private class WorkAdapter extends BaseAdapter {
        LayoutInflater mInflater;
        List<String> listJob;

        @Override
        public int getCount() {


            if (listJob.size()%4==1){
                return listJob.size()+3;
            }
            if (listJob.size()%4==2){
                return listJob.size()+2;
            }
            if (listJob.size()%4==3){
                return listJob.size()+1;
            }
            return listJob.size();
        }
        private int clickTemp = -1;
        //标识选择的Item
        public void setSeclection(int position) {
            clickTemp = position;
        }
        WorkAdapter(List<String> jobList) {

            this.listJob = jobList;
            mInflater = LayoutInflater.from(ReportWork.this);

        }


        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.grid_item, null);
                holder.tv_work = (TextView) convertView.findViewById(R.id.tv_work_name);
//                holder.rl_gv= (RelativeLayout) convertView.findViewById(R.id.rl_gv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position<listJob.size()){
                holder.tv_work.setText(listJob.get(position));
            }

//            if (clickTemp == position) {
//                holder.rl_gv.setBackgroundColor(Color.parseColor("#DDDDDD"));
//            } else {
//                holder.rl_gv.setBackgroundColor(Color.TRANSPARENT);
//            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView tv_work;
        RelativeLayout  rl_gv;
    }
}
