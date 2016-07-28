package com.diligroup.UserSet.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报  职业
 * Created by Kevin on 2016/6/16.
 */
public class ReportWork extends BaseAcitvity  {

    //    @Bind(R.id.id_flowlayout)
//    TagFlowLayout tag_layout1;
//    @Bind(R.id.id_flowlayout2)
//    TagFlowLayout tag_layout2;
//    @Bind(R.id.id_flowlayout3)
//    TagFlowLayout tag_layout3;
    private String[] lightWork;
    private String[] middleWork;
    private String[] heavyWork;
    private String userSelect;
    @Bind(R.id.gv_light)
    GridView gv_light;
    @Bind(R.id.gv_middle)
    GridView gv_middle;
    @Bind(R.id.gv_heavy)
    GridView gv_heavy;

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
        initData();

        gv_light.setAdapter(new WorkAdapter(lightWork));
        gv_middle.setAdapter(new WorkAdapter(middleWork));
        gv_heavy.setAdapter(new WorkAdapter(heavyWork));
        gv_light.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userSelect=lightWork[position];
            }
        });
        gv_middle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userSelect=middleWork[position];

            }
        });
        gv_heavy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userSelect=heavyWork[position];

            }
        });
    }

    private void initData() {
        heavyWork = new String[]{"工人", "军人", "运动员", "艺人"};
        middleWork = new String[]{"销售", "采购", "服务", "学生", "司机"};
        lightWork = new String[]{"老板", "教师", "工程师", "设计师", "作家", "文职人员", "其他"};
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("选择职业");
        title_infos.setText("您的职业?");
    }

    @OnClick(R.id.bt_commit_work)
    public void reportWorkData() {
        ToastUtil.showLong(this, "You  work ====" + userSelect);
    }



    private class WorkAdapter extends BaseAdapter {
        LayoutInflater mInflater;
        String[] work_array;

        @Override
        public int getCount() {
            return work_array.length;
        }

        WorkAdapter(String[] array) {
            this.work_array = array;
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
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.grid_item, null);
                holder.tv_work = (TextView) convertView.findViewById(R.id.tv_work_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_work.setText(work_array[position]);
            return convertView;
        }
    }

    class ViewHolder {
        TextView tv_work;
    }
}
