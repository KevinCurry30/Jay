package com.diligroup.After.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.diligroup.utils.ShareUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.ListViewForScrollView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;


/**
 * 餐后评价
 * Created by Kevin on 2016/7/4.
 */
public class AfterFragment extends BaseFragment implements RequestManager.ResultCallback {
    @Bind(R.id.home_preDay)
    TextView homePreDay;//前一天
    @Bind(R.id.home_today)
    TextView homeToday;//今天日期
    @Bind(R.id.home_weekday)
    TextView homeWeekday;//今天是哪一天，周几
    @Bind(R.id.home_nextDay)
    TextView homeNextDay;//后一天
    String currentDay;//今天日期字符串
    List<GetDietRecordBean.MornBean> mornBeanList;
    List<GetDietRecordBean.AfternoonBean> afternoonBeanList;
    List<GetDietRecordBean.EvenBean> dinnerBeanList;
    @Bind(R.id.lv_breakfast)
    ListViewForScrollView lv_breakfast;
    @Bind(R.id.lv_lunch)
    ListViewForScrollView lv_lunch;
    @Bind(R.id.lv_dinner)
    ListViewForScrollView lv_dinner;
    @Bind(R.id.ll_no_record)
    LinearLayout ll_NoRecord;
    @Bind(R.id.sl_recorde)
    ScrollView sl_Record;
    @Bind(R.id.tv_kcal_breakfast)
    TextView tv_kcal_breakfast;
    @Bind(R.id.tv_kcal_lunch)
    TextView tv_kcal_lunch;
    @Bind(R.id.tv_kcal_dinner)
    TextView tv_kcal_dinner;
    @Bind(R.id.after_canlendar)
            LinearLayout after_calendar;
    String today;
    LunchAdapter mornAdapter;
    LunchAdapter lunchAdapter;
    LunchAdapter dinnerAdapter;

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public int getLayoutXml() {
        return R.layout.fragment_after;
    }

    @Override
    public void setViews() {
        currentDay = DateUtils.getCurrentDate();
        homeToday.setText(currentDay);
        homeWeekday.setText("(今天 " + DateUtils.getWeekDay() + ")");
        today = currentDay.replace("年", "").replace("月", "").replace("日", "").trim();
        Api.getDietRecord(today, this);

    }

    @Override
    public void setListeners() {
        after_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Intent mIntent= new Intent(getActivity(), PhysiologicalPeriodActivity.class);
//                mIntent.putExtra("isFromHome", true);
//                startActivityForResult(mIntent,100);
            }
        });
    }

    @OnClick(R.id.home_preDay)
    public void preDay() {
        String temp = DateUtils.getDate(homeToday.getText().toString().trim(), -1);
        homeToday.setText(temp.split(" ")[0]);
        if (temp.split(" ")[0].equals(currentDay)) {
            homeWeekday.setText("（今天、" + temp.split(" ")[1] + "）");
        } else {
            homeWeekday.setText("（" + temp.split(" ")[1] + "）");
            today = temp.replace("年", "").replace("月", "").replace("日", "").trim();
            Api.getDietRecord(today, this);

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
            today = tempDay.replace("年", "").replace("月", "").replace("日", "");
            Api.getDietRecord(today.trim(), this);
        }
    }

    @OnClick(R.id.ll_breakfast)
    public void clickBreakfast() {
        Intent intent = new Intent(getActivity(), AddLunchActivity.class);
        intent.putExtra("mealType", "20001");
        intent.putExtra("currentDay", homeToday.getText().toString().trim());
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.ll_lunch)
    public void clickLunch() {
        Intent intent = new Intent(getActivity(), AddLunchActivity.class);
        intent.putExtra("mealType", "20002");
        intent.putExtra("currentDay", homeToday.getText().toString().trim());
        startActivityForResult(intent, 2);
    }

    @OnClick(R.id.ll_dinner)
    public void clickDinner() {
        Intent intent = new Intent(getActivity(), AddLunchActivity.class);
        intent.putExtra("currentDay", homeToday.getText().toString().trim());
        intent.putExtra("mealType", "20003");
        startActivityForResult(intent, 3);
//        startActivity(intent);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {
        sl_Record.setVisibility(View.GONE);
        ll_NoRecord.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action == Action.GET_DIET_RECORD && object != null) {
            GetDietRecordBean dietRecordBean = (GetDietRecordBean) object;
            if (dietRecordBean.getCode().equals("000000")) {
                mornBeanList = dietRecordBean.getMorn();
                afternoonBeanList = dietRecordBean.getAfternoon();
                dinnerBeanList = dietRecordBean.getEven();
                if (mornBeanList.size() == 0 && afternoonBeanList.size() == 0 && dinnerBeanList.size() == 0) {
                    sl_Record.setVisibility(View.GONE);
                    ll_NoRecord.setVisibility(View.VISIBLE);
                }
                if (afternoonBeanList != null) {
                    lunchAdapter = new LunchAdapter(2);
                    lv_lunch.setAdapter(lunchAdapter);
                    double T_kcal = 0;
                    for (int i = 0; i < afternoonBeanList.size(); i++) {
                        T_kcal += Double.valueOf(afternoonBeanList.get(i).getEnergyKc());
                    }
                    tv_kcal_lunch.setText(String.valueOf(T_kcal) + "Kcal");
                    lunchAdapter.notifyDataSetChanged();
                }
                if (mornBeanList != null) {
                    mornAdapter = new LunchAdapter(1);
                    lv_breakfast.setAdapter(mornAdapter);
                    double T_kcal = 0;
                    for (int i = 0; i < mornBeanList.size(); i++) {
                        T_kcal += Double.valueOf(mornBeanList.get(i).getEnergyKc());
                    }
                    mornAdapter.notifyDataSetChanged();

                    tv_kcal_breakfast.setText(String.valueOf(T_kcal) + "Kcal");
                }
                if (dinnerBeanList != null) {
                    dinnerAdapter = new LunchAdapter(3);
                    lv_dinner.setAdapter(dinnerAdapter);
                    double T_kcal = 0;
                    for (int i = 0; i < dinnerBeanList.size(); i++) {
                        T_kcal += Double.valueOf(dinnerBeanList.get(i).getEnergyKc());
                    }
                    dinnerAdapter.notifyDataSetChanged();
                    tv_kcal_dinner.setText(String.valueOf(T_kcal) + "Kcal");
                }
            }
        }

    }

    class LunchAdapter extends BaseAdapter {
        int type;

        LunchAdapter(int mType) {
            this.type = mType;
        }

        @Override
        public int getCount() {
            if (type == 1) {
                return mornBeanList.size();
            }
            if (type == 2) {
                return afternoonBeanList.size();
            }
            if (type == 3) {
                return dinnerBeanList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (type == 1) {
                return mornBeanList.get(position);
            }
            if (type == 2) {
                return afternoonBeanList.get(position);
            }
            if (type == 3) {
                return dinnerBeanList.get(position);
            }
            return position;

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
                convertView = inflater.inflate(R.layout.food_after_item, null);
                holder = new ViewHolder();
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_food_name);
                holder.tv_num = (TextView) convertView.findViewById(R.id.tv_count);
                holder.tv_Kcal = (TextView) convertView.findViewById(R.id.tv_food_kcal);
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_after_icon);
                convertView.setTag(holder);
            } else {
                // 取出holder
                holder = (ViewHolder) convertView.getTag();
            }
            switch (type) {
                case 1:
                    Picasso.with(getActivity()).load(R.mipmap.banner_3).into(holder.iv_icon);
                    holder.tv_name.setText(mornBeanList.get(position).getDishesName());
                    holder.tv_num.setText(String.valueOf(mornBeanList.get(position).getNum()));
                    holder.tv_Kcal.setText(String.valueOf(mornBeanList.get(position).getEnergyKc()));
                    break;
                case 2:
                    Picasso.with(getActivity()).load(R.mipmap.banner_3).into(holder.iv_icon);
                    holder.tv_name.setText(afternoonBeanList.get(position).getDishesName());
                    holder.tv_num.setText(String.valueOf(afternoonBeanList.get(position).getNum()));
                    holder.tv_Kcal.setText(String.valueOf(afternoonBeanList.get(position).getEnergyKc()));
                    break;
                case 3:
                    Picasso.with(getActivity()).load(R.mipmap.banner_3).into(holder.iv_icon);
                    holder.tv_name.setText(dinnerBeanList.get(position).getDishesName());
                    holder.tv_num.setText(String.valueOf(dinnerBeanList.get(position).getNum()));
                    holder.tv_Kcal.setText(String.valueOf(dinnerBeanList.get(position).getEnergyKc()));
                    break;
            }

            return convertView;
        }

    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_num;
        ImageView iv_icon;
        TextView tv_Kcal;
    }

    //点击了分享按钮
    public void clickShare(View rootView) {
        new ShareUtils(getActivity(), "http://tuanche.com", "http://baidu.com", rootView).openSharebord("AAAA");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 10) {
            Api.getDietRecord(today, this);
//
            switch (requestCode) {
                case 1:
                    mornAdapter.notifyDataSetChanged();
//                    ToastUtil.showShort(getActivity(),"接受到了添加早餐的数据");
                    break;
                case 2:
                    lunchAdapter.notifyDataSetChanged();
                    ToastUtil.showShort(getActivity(), "接受到了添加午餐的数据");
                    break;
                case 3:
                    dinnerAdapter.notifyDataSetChanged();
//                ToastUtil.showShort(getActivity(),"接受到了添加晚餐的数据");
                    break;
            }
        }else if(resultCode==20){
            String currenStr = data.getStringExtra("current");
            int currentYear = Integer.parseInt(currenStr.split("-")[0]);
            int currentMonth = Integer.parseInt(currenStr.split("-")[1]);
            int currentDay = Integer.parseInt(currenStr.split("-")[2]);
            homeToday.setText(currentYear + "年" + currentMonth + "月" + currentDay + "日");
            if (DateUtils.isToday(currentYear, currentMonth, currentDay)) {
                homeWeekday.setText("(今天 " + DateUtils.getWeekdayOfMonth(currentYear, currentMonth, currentDay) + ")");
            } else {
                homeWeekday.setText("(" + DateUtils.getWeekdayOfMonth(currentYear, currentMonth, currentDay) + ")");
            }
        }
    }
}
