package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.GetJobBean;
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
 * 上报  职业
 * Created by Kevin on 2016/6/16.
 */
public class ReportWork extends BaseActivity {

    @Bind(R.id.bt_commit_work)
    Button bt_ok_work;

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
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    List<GetJobBean.QlistBean> light_list;
    List<GetJobBean.ZlistBean> middle_list;
    List<GetJobBean.WlistBean> heavy_list;
    //    List<String> jobName;
    List<String> light_job_name;
    List<String> middle_job_name;
    List<String> heavy_job_name;
    private String userSelect;
    private String jobCode;
    public WorkAdapter adapter1;
    public WorkAdapter adapter2;

    public WorkAdapter adapter3;
    String jobType;
    Boolean isFrist;
    Bundle bundle;
    ViewHolder holder;

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

        tv_title.setText("基础信息");
        title_infos.setText("您的职业?");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Api.getWorkType(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist){
            bt_ok_work.setText("下一步");
        }
        light_job_name = new ArrayList<>();
        middle_job_name = new ArrayList<>();
        heavy_job_name = new ArrayList<>();

        gv_light.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter2.setSeclection(-1);
                adapter2.notifyDataSetChanged();
                adapter3.setSeclection(-1);
                adapter3.notifyDataSetChanged();
                adapter1.setSeclection(position);
//                holder = (ViewHolder) view.getTa  g();
//                holder.tv_work.setTextColor(getResources().getColor(R.color.title_color));

//                ／／这句是通知adapter改变选中的position
//                adapter.clearSelection(position);
//                ／／关键是这一句，激情了，它可以让listview改动过的数据重新加载一遍，以达到你想要的效果
                adapter1.notifyDataSetChanged();
                if (position<light_list.size()){
                    userSelect = light_list.get(position).getProfName();
                    jobType= light_list.get(position).getLaborCode();
                    jobCode = light_list.get(position).getCode();
                }



            }
        });
        gv_middle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                holder.tv_work.setTextColor(getResources().getColor(R.color.black));
                adapter1.setSeclection(-1);
                adapter1.notifyDataSetChanged();
                adapter3.setSeclection(-1);
                adapter3.notifyDataSetChanged();


                adapter2.setSeclection(position);
                adapter2.notifyDataSetChanged();
//                holder = (ViewHolder) view.getTag();
//                holder.tv_work.setTextColor(getResources().getColor(R.color.title_color));
                if (position<middle_list.size()){
                    userSelect = middle_list.get(position).getProfName();
                    jobType= middle_list.get(position).getLaborCode();

                    jobCode = middle_list.get(position).getCode();
                }


            }
        });
        gv_heavy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                holder.tv_work.setTextColor(getResources().getColor(R.color.black));
                adapter1.setSeclection(-1);
                adapter1.notifyDataSetChanged();
                adapter2.setSeclection(-1);
                adapter2.notifyDataSetChanged();
                adapter3.setSeclection(position);
                adapter3.notifyDataSetChanged();

//                holder = (ViewHolder) view.getTag();
//                holder.tv_work.setTextColor(getResources().getColor(R.color.title_color));
                if (position<heavy_list.size()){
                    userSelect = heavy_list.get(position).getProfName();
                    jobType= heavy_list.get(position).getLaborCode();

                    jobCode = heavy_list.get(position).getCode();
                }


            }
        });
    }

    @OnClick(R.id.bt_commit_work)
    public void reportWorkData() {

        if (userSelect != null) {
//            ToastUtil.showLong(this, "You  work ====" + userSelect);
//            ToastUtil.showLong(this, "You  jobCode ====" + jobCode);
//            UserInfoBean.getInstance().setJob("");
            LogUtils.e("职业======"+userSelect);
            LogUtils.e("职业Code======"+jobCode);
            LogUtils.e("职业Type======"+jobType);
            if (isFrist) {
//                UserInfoBean.getInstance().setJob(userSelect);

                UserInfoBean.getInstance().setJobType(jobType);
                UserInfoBean.getInstance().setJob(jobCode);
                readyGo(ReportHeight.class, bundle);
                return;
            }
            Map map =new HashMap();
            map.put("jobType",jobType);
            map.put("job",jobCode);
            Api.updataUserInfo(map,this);


        } else {
            ToastUtil.showShort(ReportWork.this, "请选择职业");
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
                    adapter1 = new WorkAdapter(light_job_name);
                    gv_light.setAdapter(adapter1);
                }
                if (jobdata.getZlist() != null && jobdata.getZlist().size() > 0) {
                    middle_list = jobdata.getZlist();
                    rl_middle.setVisibility(View.VISIBLE);
                    gv_middle.setVisibility(View.VISIBLE);
                    tv_middle.setText("中等体力");
                    getMiddleJob(middle_list);
                      adapter2 = new WorkAdapter(middle_job_name);
                    gv_middle.setAdapter(adapter2);
                }
                if (jobdata.getWlist() != null && jobdata.getWlist().size() > 0) {
                    heavy_list = jobdata.getWlist();
                    rl_heavy.setVisibility(View.VISIBLE);
                    gv_heavy.setVisibility(View.VISIBLE);
                    tv_heavy.setText("重体力");
                    getHeavyJob(heavy_list);
                    adapter3 = new WorkAdapter(heavy_job_name);
                    gv_heavy.setAdapter(adapter3);
                }
            }
        }
        if (object!=null&&action==Action.UPDATA_USERINFO){
            CommonBean  commonBean= (CommonBean) object;
            if (commonBean.getCode().equals("000000")){
                Intent intent=new Intent();
                intent.putExtra("job",userSelect);
                setResult(0x20,intent);
                this.finish();
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

    public class WorkAdapter extends BaseAdapter {
        LayoutInflater mInflater;
        List<String> listJob;
        private int selectedPosition=0;
//        ／／这句是把listview的点击position,传递过来
        public void clearSelection(int position) {
            selectedPosition = position;
        }
        @Override
        public int getCount() {
            if (listJob.size() % 4 == 1) {
                return listJob.size() + 3;
            }
            if (listJob.size() % 4 == 2) {
                return listJob.size() + 2;
            }
            if (listJob.size() % 4 == 3) {
                return listJob.size() + 1;
            }
            return listJob.size();
        }

        private int clickTemp = -1;

        //标识选择的Item
        public void setSeclection(int position) {
            clickTemp = position;
        }

        public WorkAdapter(List<String> jobList) {
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
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.grid_item, null);
                holder.tv_work = (TextView) convertView.findViewById(R.id.tv_work_name);
//                holder.rl_gv= (RelativeLayout) convertView.findViewById(R.id.rl_gv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            if (position==clickTemp){
//                holder.tv_work.setTextColor(getResources().getColor(R.color.title_color));
//            }
            if (position < listJob.size()) {
                holder.tv_work.setText(listJob.get(position));
            }
//            ／／判断点击了哪个item,然后判断，让他的textview变色
            if(clickTemp==position){
                holder.tv_work.setTextColor(getResources().getColor(R.color.title_color));
            }else{
                holder.tv_work.setTextColor(getResources().getColor(R.color.black));
            }
            return convertView;
        }
    }

    public class ViewHolder {
        public TextView tv_work;
    }
}
