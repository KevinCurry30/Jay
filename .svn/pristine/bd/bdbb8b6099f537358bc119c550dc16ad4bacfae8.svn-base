package com.diligroup.Home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.diligroup.Home.AddLunchActivity;
import com.diligroup.Home.adapter.HomeAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseFragment;
import com.diligroup.bean.BannerBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.DateUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.view.MyViewFilpper;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * Created by hjf on 2016/7/18.
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, RequestManager.ResultCallback {

    @Bind(R.id.home_flipper)
    MyViewFilpper homeFlipper;
    @Bind(R.id.banner_dot_ll)
    LinearLayout banner_dot_ll;
    @Bind(R.id.home_preDay)
    TextView homePreDay;//前一天
    @Bind(R.id.home_today)
    TextView homeToday;//今天日期
    @Bind(R.id.home_weekday)
    TextView homeWeekday;//今天是哪一天，周几
    @Bind(R.id.home_nextDay)
    TextView homeNextDay;//后一天
    //    @Bind(R.id.home_pst_tabstrip)
//    PagerSlidingTabStrip home_pst_tabstrip;
    @Bind(R.id.home_vPager)
    ViewPager home_vPager;
    @Bind(R.id.home_breakfast_food)
    LinearLayout homeBreakfastFood;//早餐
    @Bind(R.id.home_lunch)
    LinearLayout homeLunch;//午餐
    @Bind(R.id.home_dinner)
    LinearLayout homeDinner;//晚餐
    @Bind(R.id.home_header_rgroup)
    RadioGroup homeHeaderRgroup;//
    @Bind(R.id.home_tabline)
    View mTabLine;//底部指示器
    @Bind(R.id.home_thisService_evaluation)
    LinearLayout homeThisServiceEvaluation;//本次服务评价
    @Bind(R.id.breakfase_icon)
    ImageView breakfaseIcon;
    @Bind(R.id.breakfase_text)
    TextView breakfaseText;
    @Bind(R.id.lunch_icon)
    ImageView lunchIcon;
    @Bind(R.id.lunch_text)
    TextView lunchText;
    @Bind(R.id.dish_icon)
    ImageView dishIcon;
    @Bind(R.id.dish_text)
    TextView dishText;
    @Bind(R.id.home_rootView)
    LinearLayout home_rootView;
    @Bind(R.id.home_currend_date)
    RelativeLayout home_currend_date;

    private LinearLayout.LayoutParams thisService;//本次服务评价文本布局
    private Intent mIntent;
    private String currentDay;//今天日期字符串

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_home;
    }

    @Override
    public void setViews() {
        homeHeaderRgroup.check(R.id.home_breakfast_food);

        HomeAdapter homeAdapter = new HomeAdapter(getActivity());
        home_vPager.setAdapter(homeAdapter);

        currentDay = DateUtils.getCurrentDate();
        homeToday.setText(currentDay);
        homeWeekday.setText("(今天 " + DateUtils.getWeekDay() + ")");
        showWidgit();
        initDate();
    }

    /**
     * 展示控件初始位置
     */
    private void showWidgit() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();//获取控件的布局参数对象
        lp.width = CommonUtils.getScreenWidth(getActivity()) / 3;
        mTabLine.setLayoutParams(lp); //设置该控件的layoutParams参数

        thisService = (LinearLayout.LayoutParams) homeThisServiceEvaluation.getLayoutParams();
        thisService.width = CommonUtils.getScreenWidth(getActivity()) / 3;
//        thisService.leftMargin=(CommonUtils.getScreenHeight(getActivity())/3-width)/2;
        homeThisServiceEvaluation.setLayoutParams(thisService);
        breakfaseIcon.setImageResource(R.mipmap.breakfase_icon_pressed);
        breakfaseText.setTextColor(getResources().getColor(R.color.green));
    }

    @Override
    public void setListeners() {
        homeFlipper.setOnDisplayChagnedListener(new MyViewFilpper.OnDisplayChagnedListener() {
            @Override
            public void OnDisplayChildChanging(ViewFlipper view, int index) {
//             LogUtils.i("viewFliper当前index=="+index);
                switchDottor(index);
            }
        });
        homePreDay.setOnClickListener(this);
        homeNextDay.setOnClickListener(this);
        homeBreakfastFood.setOnClickListener(this);
        homeLunch.setOnClickListener(this);
        homeDinner.setOnClickListener(this);
        home_vPager.setOnPageChangeListener(this);
        home_currend_date.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initDate() {
        Api.getBanner("1","1",this);
        int image[] = new int[]
                {
                        R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3
                };
        for (int i = 0; i < image.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setBackgroundResource(image[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            homeFlipper.addView(iv);

            ImageView dotter = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
            dotter.setBackgroundResource(R.drawable.banner_dot_selector);
            if (i != 0) {
                layoutParams.leftMargin = 5;
                dotter.setSelected(false);
            } else {
                dotter.setSelected(true);
            }
            dotter.setLayoutParams(layoutParams);
            banner_dot_ll.addView(dotter);
        }

    }

    private void switchDottor(int index) {
        for (int i = 0; i < banner_dot_ll.getChildCount(); i++) {
            if (i == index) {
                banner_dot_ll.getChildAt(i).setSelected(true);
            } else {
                banner_dot_ll.getChildAt(i).setSelected(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_nextDay:
                String tempDay = DateUtils.getDate(homeToday.getText().toString().trim(), 1);
                homeToday.setText(tempDay.split(" ")[0]);
                if (tempDay.split(" ")[0].equals(currentDay)) {
                    homeWeekday.setText("（今天、" + tempDay.split(" ")[1]+")");
                } else {
                    homeWeekday.setText("（"+tempDay.split(" ")[1]+"）");
                }
                break;

            case R.id.home_preDay:
                String temp = DateUtils.getDate(homeToday.getText().toString().trim(), -1);
                homeToday.setText(temp.split(" ")[0]);
                if (temp.split(" ")[0].equals(currentDay)) {
                    homeWeekday.setText("（今天、" + temp.split(" ")[1]+"）");
                } else {
                    homeWeekday.setText("（"+temp.split(" ")[1]+"）");
                }
                break;
            case R.id.home_breakfast_food:
                homeHeaderRgroup.check(R.id.home_breakfast_food);
                home_vPager.setCurrentItem(0);
                break;
            case R.id.home_lunch:
                homeHeaderRgroup.check(R.id.home_lunch);
                home_vPager.setCurrentItem(1);
                break;
            case R.id.home_dinner:
                homeHeaderRgroup.check(R.id.home_dinner);
                home_vPager.setCurrentItem(2);
                break;
            case R.id.home_currend_date:
//                mIntent = new Intent(getActivity(), PhysiologicalPeriodActivity.class);
//                mIntent.putExtra("isFromHome", true);
//                mIntent.putExtra("currentDate",homeToday.getText().toString().trim());
//                startActivityForResult(mIntent, 10);//传递当前日期
                startActivity(new Intent(getActivity(), AddLunchActivity.class));
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        //获取组件距离左侧组件的距离
        lp.leftMargin = (int) ((positionOffset + position) * CommonUtils.getScreenWidth(getActivity()) / 3);
        mTabLine.setLayoutParams(lp);

        thisService.leftMargin = (int) ((position + positionOffset) * CommonUtils.getScreenWidth(getActivity()) / 3);
        homeThisServiceEvaluation.setLayoutParams(thisService);
        switch (position) {
            case 0:
                homeHeaderRgroup.check(R.id.home_breakfast_food);
                changeCheckState(R.id.home_breakfast_food);
                break;
            case 1:
                homeHeaderRgroup.check(R.id.home_lunch);
                changeCheckState(R.id.home_lunch);
                break;
            case 2:
                homeHeaderRgroup.check(R.id.home_dinner);
                changeCheckState(R.id.home_dinner);
                break;
        }
    }

    /**
     * 改变按钮的选中状态
     *
     * @param viewId
     */
    private void changeCheckState(int viewId) {
        switch (viewId) {
            case R.id.home_breakfast_food:
                breakfaseIcon.setImageResource(R.mipmap.breakfase_icon_pressed);
                breakfaseText.setTextColor(getResources().getColor(R.color.green));

                lunchIcon.setImageResource(R.mipmap.lunch_icon_normal);
                lunchText.setTextColor(getResources().getColor(R.color.black1));
                dishIcon.setImageResource(R.mipmap.dinner_icon_normal);
                dishText.setTextColor(getResources().getColor(R.color.black1));
                break;
            case R.id.home_lunch:
                lunchIcon.setImageResource(R.mipmap.lunch_icon_pressed);
                lunchText.setTextColor(getResources().getColor(R.color.green));

                breakfaseIcon.setImageResource(R.mipmap.breakfase_icon_normal);
                breakfaseText.setTextColor(getResources().getColor(R.color.black1));
                dishIcon.setImageResource(R.mipmap.dinner_icon_normal);
                dishText.setTextColor(getResources().getColor(R.color.black1));
                break;
            case R.id.home_dinner:
                dishIcon.setImageResource(R.mipmap.dinner_icon_pressed);
                dishText.setTextColor(getResources().getColor(R.color.green));

                lunchIcon.setImageResource(R.mipmap.lunch_icon_normal);
                lunchText.setTextColor(getResources().getColor(R.color.black1));
                breakfaseIcon.setImageResource(R.mipmap.breakfase_icon_normal);
                breakfaseText.setTextColor(getResources().getColor(R.color.black1));
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 20:
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
                break;
        }
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if(object!=null && action==Action.BANNER){
            BannerBean ben= (BannerBean) object;
            if(ben.getCode().equals("000000")){
                LogUtils.i("轮播图接口调用成功");
            }else{
                LogUtils.i("轮播图接口调失败");
            }
        }
    }
}
