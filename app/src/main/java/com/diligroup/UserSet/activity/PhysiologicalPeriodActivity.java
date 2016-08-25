package com.diligroup.UserSet.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.diligroup.Home.HomeActivity;
import com.diligroup.R;
import com.diligroup.UserSet.calendar.CalendarAdapter;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.DateUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 上报生理周期
 */
public class PhysiologicalPeriodActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.currentMonth)
    TextView currentMonth;
    /**
     * 当前的年月，现在日历顶端
     */
    @Bind(R.id.prevMonth)
    ImageView prevMonth;
    @Bind(R.id.nextMonth)
    ImageView nextMonth;
    @Bind(R.id.flipper)
    ViewFlipper flipper;
    @Bind(R.id.add)
    TextView add;//“+“
    @Bind(R.id.reduce)
    TextView reduce;//"-"
    @Bind(R.id.cycle_num)
    TextView cycle_num;//周期数
    @Bind(R.id.nextstep)
    Button nextStep;//下一步
    @Bind(R.id.say_laater)
    Button say_latter;//稍后再说
    @Bind(R.id.cycle_title_info)
    TextView cycle_title_info;
    @Bind(R.id.sunday)
    TextView sunday;
    @Bind(R.id.monday)
    TextView monday;
    @Bind(R.id.tuesday)
    TextView tuesday;
    @Bind(R.id.wednesday)
    TextView wednesday;
    @Bind(R.id.thursday)
    TextView thursday;
    @Bind(R.id.friday)
    TextView friday;
    @Bind(R.id.saturday)
    TextView saturday;
    @Bind(R.id.physiological_layout)
    RelativeLayout physiological_layout;//日历选择需要gone掉的头布局
    @Bind(R.id.gone_fl)
    FrameLayout gone_fl;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";

    /**
     * 每次添加gridview到viewflipper中时给的标记
     */
    private int gvFlag = 0;
    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private GridView gridView = null;
    private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)


    HashMap<String, View> tempList = new HashMap<>();
    ArrayList<String> selectDate = new ArrayList<>();
    ArrayList<String> templateDateList;
    ArrayList<String> dayList = new ArrayList<>();
    Intent mIntent;
    private Typeface typeFace;
    private boolean isFromHome;//
    private String homeDate;
    int cycles;//生理期
    private boolean isFromMy;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_physiological_period;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        ButterKnife.bind(this);
        tv_title.setText("您的生理期");
        cycle_title_info.setText("请选择上次生理的开始和结束时间");
        ivBack.setVisibility(View.VISIBLE);

        isFromHome = getIntent().getBooleanExtra("isFromHome", false);
        //是否来自用户中心
        isFromMy = getIntent().getBooleanExtra("isFromMy", false);
        homeDate = getIntent().getStringExtra("currentDate");
        templateDateList = (ArrayList<String>) getIntent().getSerializableExtra("dateList");
        setViews();
        setListeners();
    }

    private void setViews() {
        if (isFromMy) {
            nextStep.setText("确定");
            say_latter.setVisibility(View.GONE);
        } else {
            nextStep.setText("下一步");
            say_latter.setVisibility(View.VISIBLE);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        if (isFromHome) {
            physiological_layout.setVisibility(View.GONE);
            gone_fl.setVisibility(View.GONE);
            nextStep.setVisibility(View.GONE);
            say_latter.setVisibility(View.GONE);
            currentDate = DateUtils.dateFormatChagee(homeDate);
            tv_title.setText("选择门店供应菜品的日期");
        } else {
            currentDate = sdf.format(date); // 当期日期
        }
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);

        gestureDetector = new GestureDetector(this, new MyGestureListener());
        flipper.removeAllViews();
        if (isFromHome) {
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, isFromHome, templateDateList);
        } else {
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        }
        addGridView();
        gridView.setAdapter(calV);
        flipper.addView(gridView, 0);
        addTextToTopTextView(currentMonth);

        sunday.setTypeface(typeFace);
        monday.setTypeface(typeFace);
        tuesday.setTypeface(typeFace);
        wednesday.setTypeface(typeFace);
        thursday.setTypeface(typeFace);
        friday.setTypeface(typeFace);
        saturday.setTypeface(typeFace);
    }

    private void setListeners() {
        prevMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
        nextStep.setOnClickListener(this);

        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        say_latter.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.nextMonth: // 下一个月
                enterNextMonth(gvFlag);
                break;
            case R.id.prevMonth: // 上一个月
                enterPrevMonth(gvFlag);
                break;
            case R.id.nextstep:
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
                mIntent = new Intent();
                if (selectDate.size() == 0) {
                    ToastUtil.showLong(this, "请选择正确的生理周期");
                    return;
                }
                try {
                    if (formatter.parse(selectDate.get(0)).compareTo(formatter.parse(selectDate.get(1))) > 0) {
                        String temp = selectDate.get(0);
                        selectDate.set(0, selectDate.get(1).substring(5));
                        selectDate.set(1, temp.substring(5));
                    } else {
                        selectDate.set(0, selectDate.get(0).substring(5));
                        selectDate.set(1, selectDate.get(1).substring(5));
                    }
                    mIntent.putExtra("cycle", selectDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //如果是第一次录入用户信息，需要跳转reportOtheractivity 按钮文字展示“下一步” 否则按钮文字展示确定
                if (!isFromMy) {
                    startActivity(new Intent(this, ReportOther.class));
                    UserInfoBean.getInstance().setPeriodStartTime(selectDate.get(0));
                    UserInfoBean.getInstance().setPeriodEndTime(selectDate.get(1));
                    UserInfoBean.getInstance().setPeriodNum(cycle_num + "");
                    finish();
                } else {
                    Map map=new HashMap();
                    map.put("periodStartTime",selectDate.get(0));
                    map.put("periodEndTime",selectDate.get(1));
                    map.put("periodNum","");
                    Api.updataUserInfo(map,this);
                    setResult(10, mIntent);
                    this.finish();
//                    Api.perfectInfoAfterPeriod(Constant.userId + "", cycle_num + "", selectDate.get(0), selectDate.get(1), this);
                }
                break;
            case R.id.add:
                cycles = Integer.parseInt(cycle_num.getText().toString().trim()) + 1;
                cycle_num.setText(cycles + "");
                break;
            case R.id.reduce:
                if (Integer.parseInt(cycle_num.getText().toString().trim()) > 1) {
                    cycles = Integer.parseInt(cycle_num.getText().toString().trim()) - 1;
                    cycle_num.setText(cycles + "");
                } else {
                    ToastUtil.showLong(this, "亲：你的填写有误哦！");
                }
                break;
            case R.id.say_laater:
                //上传所有用户输入信息
                Api.setUserInfo(this);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 移动到下一个月
     *
     * @param gvFlag
     */
    private void enterNextMonth(int gvFlag) {
        addGridView(); // 添加一个gridView
        jumpMonth++; // 下一个月

        if (isFromHome) {
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, isFromHome, templateDateList);
        } else {
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        }
        gridView.setAdapter(calV);
        addTextToTopTextView(currentMonth); // 移动到下一月后，将当月显示在头标题中
        gvFlag++;
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
        flipper.showNext();
        flipper.removeViewAt(0);
    }

    /**
     * 移动到上一个月
     *
     * @param gvFlag
     */
    private void enterPrevMonth(int gvFlag) {
        addGridView(); // 添加一个gridView
        jumpMonth--; // 上一个月

//        calV = new CalendarAdapter(this, this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        if (isFromHome) {
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, isFromHome, templateDateList);
        } else {
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        }
        gridView.setAdapter(calV);
        gvFlag++;
        addTextToTopTextView(currentMonth); // 移动到上一月后，将当月显示在头标题中
        flipper.addView(gridView, gvFlag);

        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
        flipper.showPrevious();
        flipper.removeViewAt(0);
    }

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        // 取得屏幕的宽度和高度
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        gridView = new GridView(this);
        gridView.setNumColumns(7);
//        gridView.setColumnWidth(47);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        if (width == 720 && height == 1280) {
            gridView.setColumnWidth(40);
        }
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        // 去除gridView边框
        gridView.setVerticalSpacing(0);
        gridView.setHorizontalSpacing(0);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            // 将gridview中的触摸事件回传给gestureDetector

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return PhysiologicalPeriodActivity.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                // 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
//                int startPosition = calV.getStartPositon();
//                int endPosition = calV.getEndPosition();
//                if (startPosition <= position + 7 && position <= endPosition - 7) {
//                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
//                    // String scheduleLunarDay =
//                    // calV.getDateByClickItem(position).split("\\.")[1];
//                    // //这一天的阴历
//                    String scheduleYear = calV.getShowYear();
//                    String scheduleMonth = calV.getShowMonth();
//                    Toast.makeText(PhysiologicalPeriodActivity.this, scheduleYear + "-" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_LONG).show();
//                    arg1.setBackgroundResource(R.drawable.circle_red);
//
//                }

                String str = getItemClickDate(position);
                if (str == null) {//invalidate date
                    return;
                }
                if (isFromHome) {
                    mIntent = new Intent();
                    mIntent.putExtra("current", getItemClickDate(position));
                    setResult(20, mIntent);
                    finish();
                }
                if (tempList.size() == 0) {
                    tempList.put(getItemClickDate(position), arg1);//日期作为键 对应的view作为值放在map中
                    arg1.setTag("red");//

                    selectDate.add(getItemClickDate(position));
                    arg1.findViewById(R.id.tvtext).setBackgroundResource(R.drawable.circle_red);
                    ((TextView) arg1.findViewById(R.id.tvtext)).setTextColor(getResources().getColor(R.color.white));

                } else if (tempList.size() == 1) {
                    tempList.put(getItemClickDate(position), arg1);
                    arg1.setTag("green");
                    if (!selectDate.get(0).equals(getItemClickDate(position))) {//选择的不是同一个日期
                        selectDate.add(getItemClickDate(position));
                        arg1.findViewById(R.id.tvtext).setBackgroundResource(R.drawable.circle_green);
                        ((TextView) arg1.findViewById(R.id.tvtext)).setTextColor(getResources().getColor(R.color.white));

                    }
                } else if (tempList.size() == 2) {//需要判断距离哪一个日期近，然后替换哪一个日期
                    String nearDate = DateUtils.compare_date(selectDate.get(0), selectDate.get(1), getItemClickDate(position));
                    String removeDate = selectDate.remove(selectDate.get(0).equals(nearDate) ? 0 : 1);//删除的日期
                    selectDate.add(getItemClickDate(position));
                    //把相近日期背景色还原，新选择日期背景色加重显示
                    if (tempList.get(removeDate).getTag().equals("red")) {
                        arg1.findViewById(R.id.tvtext).setBackgroundResource(R.drawable.circle_red);
                        ((TextView) arg1.findViewById(R.id.tvtext)).setTextColor(getResources().getColor(R.color.white));
                        arg1.setTag("red");
                    } else if (tempList.get(removeDate).getTag().equals("green")) {
                        arg1.findViewById(R.id.tvtext).setBackgroundResource(R.drawable.circle_green);
                        ((TextView) arg1.findViewById(R.id.tvtext)).setTextColor(getResources().getColor(R.color.white));
                        arg1.setTag("green");
                    }
                    (tempList.get(removeDate)).findViewById(R.id.tvtext).setBackgroundColor(getResources().getColor(R.color.white));
                    ((TextView) (tempList.get(removeDate)).findViewById(R.id.tvtext)).setTextColor(getResources().getColor(R.color.black1));
                    tempList.remove(removeDate);
                    tempList.put(getItemClickDate(position), arg1);

                } else {
                    Toast.makeText(PhysiologicalPeriodActivity.this, "选择日期有误！！", Toast.LENGTH_LONG).show();
                }

            }
        });
        gridView.setLayoutParams(params);
    }

    /**
     * 添加头部的年份 闰哪月等信息
     *
     * @param view
     */
    public void addTextToTopTextView(TextView view) {
        typeFace = Typeface.createFromAsset(getAssets(), "fonts/FZLTCXHJW.TTF");
        StringBuffer textDate = new StringBuffer();
        // draw = getResources().getDrawable(R.drawable.top_day);
        // view.setBackgroundDrawable(draw);
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append(calV.getShowDay()).append("\t");
        view.setTypeface(typeFace);
        view.setText(textDate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.SET_INFOS) {
            CommonBean bean = (CommonBean) object;
            if (bean.getCode().equals(Constant.RESULT_SUCESS)) {
                // TODO
                if (isFromMy) {
                    setResult(10, mIntent);
                    this.finish();
                } else {
                    readyGo(HomeActivity.class);
                    finish();
                }
            }else{
                LogUtils.i("上传信息时出错了");
            }
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
            if (e1.getX() - e2.getX() > 120) {
                // 像左滑动
                enterNextMonth(gvFlag);
                return true;
            } else if (e1.getX() - e2.getX() < -120) {
                // 向右滑动
                enterPrevMonth(gvFlag);
                return true;
            }
            return false;
        }
    }

    /**
     * 根据所点击item，获取日期信息
     *
     * @param position
     * @return
     */
    public String getItemClickDate(int position) {
        int startPosition = calV.getStartPositon();
        int endPosition = calV.getEndPosition();
        String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
        if (isFromHome) {
            for (int i = 0; i < templateDateList.size(); i++) {
                String[] tempArr = templateDateList.get(i).split("-");
                if (tempArr[0].equals(calV.getShowYear()) && tempArr[1].equals(calV.getShowMonth())) {
                    dayList.add(tempArr[2]);
                }
            }
            if (startPosition <= position + 7 && position <= endPosition - 7 && dayList.contains(scheduleDay)) {
                String scheduleYear = calV.getShowYear();
                String scheduleMonth = calV.getShowMonth();
//            Toast.makeText(PhysiologicalPeriodActivity.this, scheduleYear + "-" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_LONG).show();
//                scheduleMonth = scheduleMonth.length() == 1 ? "0" + scheduleMonth : scheduleMonth;
//                scheduleDay = scheduleDay.length() == 1 ? "0" + scheduleDay : scheduleDay;
                return scheduleYear + "-" + scheduleMonth + "-" + scheduleDay;
            }
        } else if (startPosition <= position + 7 && position <= endPosition - 7) {

            // String scheduleLunarDay =
            // calV.getDateByClickItem(position).split("\\.")[1];
            // //这一天的阴历
            String scheduleYear = calV.getShowYear();
            String scheduleMonth = calV.getShowMonth();
//            Toast.makeText(PhysiologicalPeriodActivity.this, scheduleYear + "-" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_LONG).show();
//            scheduleMonth = scheduleMonth.length() == 1 ? "0" + scheduleMonth : scheduleMonth;
//            scheduleDay = scheduleDay.length() == 1 ? "0" + scheduleDay : scheduleDay;
            return scheduleYear + "-" + scheduleMonth + "-" + scheduleDay;

        }
        return null;
    }

    /**
     * 根据所点击item，获取月份信息
     *
     * @param position
     * @return
     */
    public int getItemClickMonth(int position) {
        int currentMouth = Integer.parseInt(calV.getShowMonth());
        return currentMouth;
    }

    /**
     * 根据所点击item，获取年份信息
     *
     * @param position
     * @return
     */
    public int getItemClickYear(int position) {
        int currentYear = Integer.parseInt(calV.getShowYear());
        return currentYear;
    }

    @Override
    protected void onStop() {
        super.onStop();
        jumpMonth = 0;
        jumpYear = 0;
    }
}
