package com.diligroup.After.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.diligroup.After.AddLunchActivity;
import com.diligroup.R;
import com.diligroup.base.BaseFragment;
import com.diligroup.bean.GetDietRecordBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;


/**
 * 餐后评价
 * Created by Kevin on 2016/7/4.
 */
public class AfterFragment extends BaseFragment implements RequestManager.ResultCallback{
    @Bind(R.id.home_preDay)
    TextView homePreDay;//前一天
    @Bind(R.id.home_today)
    TextView homeToday;//今天日期
    @Bind(R.id.home_weekday)
    TextView homeWeekday;//今天是哪一天，周几
    @Bind(R.id.home_nextDay)
    TextView homeNextDay;//后一天
    String currentDay;//今天日期字符串
    List<GetDietRecordBean.MornBean>  mornBeanList;
    List<GetDietRecordBean.AfternoonBean>  afternoonBeanList;
    List<GetDietRecordBean.EvenBean>  dinnerBeanList;
    @Bind(R.id.lv_breakfast)
    ListView lv_breakfast;
    @Bind(R.id.lv_lunch)
    ListView lv_lunch;
    @Bind(R.id.lv_dinner)
    ListView lv_dinner;
    @Bind(R.id.ll_no_record)
    LinearLayout  ll_NoRecord;
    @Bind(R.id.sl_recorde)
    ScrollView sl_Record;
    @Override
    public void onStart() {
        super.onStart();
    }

    //餐别：早餐20001，午餐20002，晚餐20003，夜宵20004，加餐	20005
    @Override
    public int getLayoutXml() {
        return R.layout.fragment_after;
    }

    @Override
    public void setViews() {
        currentDay = DateUtils.getCurrentDate();

        homeToday.setText(currentDay);
        homeWeekday.setText("(今天 " + DateUtils.getWeekDay() + ")");
        Api.getDietRecord(currentDay,this);

    }

    @Override
    public void setListeners() {

    }

    @OnClick(R.id.home_preDay)
    public void preDay() {
        String temp = DateUtils.getDate(homeToday.getText().toString().trim(), -1);
        homeToday.setText(temp.split(" ")[0]);
        if (temp.split(" ")[0].equals(currentDay)) {
            homeWeekday.setText("（今天、" + temp.split(" ")[1] + "）");
        } else {
            homeWeekday.setText("（" + temp.split(" ")[1] + "）");
        }
    }

    @OnClick(R.id.home_nextDay)
    public void nextDay() {
        String tempDay = DateUtils.getDate(homeToday.getText().toString().trim(), 1);
        homeToday.setText(tempDay.split(" ")[0]);
        if (tempDay.split(" ")[0].equals(currentDay)) {
            homeWeekday.setText("（今天、" + tempDay.split(" ")[1] + ")");
        } else {
            homeWeekday.setText("（" + tempDay.split(" ")[1] + "）");
        }
    }

    @OnClick(R.id.ll_breakfast)
    public void clickBreakfast() {
        Intent  intent=new Intent(getActivity(), AddLunchActivity.class);
        intent.putExtra("mealType","20001");
        intent.putExtra("currentDay",homeToday.getText().toString().trim());
        startActivity(intent);
    }

    @OnClick(R.id.ll_lunch)
    public void clickLunch() {
        Intent  intent=new Intent(getActivity(), AddLunchActivity.class);
        intent.putExtra("mealType","20002");
        intent.putExtra("currentDay",homeToday.getText().toString().trim());
        startActivity(intent);
    }

    @OnClick(R.id.ll_dinner)
    public void clickDinner() {
        Intent  intent=new Intent(getActivity(), AddLunchActivity.class);
        intent.putExtra("currentDay",homeToday.getText().toString().trim());
        intent.putExtra("mealType","20003");
        startActivity(intent);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {
        sl_Record.setVisibility(View.GONE);
        ll_NoRecord.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action==Action.GET_DIET_RECORD&&object!=null){
            GetDietRecordBean  dietRecordBean = (GetDietRecordBean) object;
            if (dietRecordBean.getCode().equals("C011601")){
                sl_Record.setVisibility(View.GONE);
                ll_NoRecord.setVisibility(View.VISIBLE);
                return;
            }
            if (dietRecordBean.getCode().equals("000000")){
                mornBeanList=dietRecordBean.getMorn();
                afternoonBeanList=dietRecordBean.getAfternoon();
                dinnerBeanList=dietRecordBean.getEven();

                if (afternoonBeanList!=null){
                    lv_lunch.setAdapter(new LunchAdapter());
                }
                if (mornBeanList!=null){
                    lv_lunch.setAdapter(new LunchAdapter());
                }
                if (dinnerBeanList!=null){
                    lv_lunch.setAdapter(new LunchAdapter());
                }

            }


        }

    }
    class  LunchAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return afternoonBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return afternoonBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.food_after_item,null);
                holder = new ViewHolder();
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_food_name);
                holder.tv_num = (TextView) convertView.findViewById(R.id.tv_count);
                holder.tv_Kcal = (TextView) convertView.findViewById(R.id.tv_food_kcal);
                convertView.setTag(holder);
            } else {
                // 取出holder
                holder = (ViewHolder) convertView.getTag();
            }
                holder.tv_name.setText(afternoonBeanList.get(position).getDishesName());
                holder.tv_num.setText(String.valueOf(afternoonBeanList.get(position).getNum()));
                holder.tv_Kcal.setText(String.valueOf(afternoonBeanList.get(position).getEnergyKc()));
            return convertView;
        }

    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_num;
        ImageView iv_icon;
        TextView tv_Kcal;
    }
}
