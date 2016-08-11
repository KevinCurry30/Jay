package com.diligroup.Home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.diligroup.After.AddLunchActivity;
import com.diligroup.Home.adapter.HomeRighAdapter;
import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.R;
import com.diligroup.UserSet.activity.PhysiologicalPeriodActivity;
import com.diligroup.base.BaseFragment;
import com.diligroup.base.Constant;
import com.diligroup.bean.BannerBean;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.bean.MyStickyHeadChangeListener;
import com.diligroup.dialog.MealChoicePopwindow;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.shop.GetShopActivity;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.DateUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.view.DividerItemDecoration;
import com.diligroup.view.MyViewFilpper;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * Created by hjf on 2016/7/18.
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, RequestManager.ResultCallback, MyItemClickListener {

    @Bind(R.id.home_flipper)
    MyViewFilpper homeFlipper;
    @Bind(R.id.banner_dot_ll)
    LinearLayout banner_dot_ll;
    @Bind(R.id.home_today)
    TextView homeToday;//今天日期
    @Bind(R.id.home_weekday)
    TextView homeWeekday;//今天是哪一天，周几
    @Bind(R.id.home_thisService_evaluation)
    RelativeLayout homeThisServiceEvaluation;//本次服务评价
    @Bind(R.id.home_currend_date)
    RelativeLayout home_currend_date;
    @Bind(R.id.home_right_recycleView)
    RecyclerView home_right_recycleView;
    @Bind(R.id.home_left_listView)
    RecyclerView home_left_listView;
    @Bind(R.id.select_store)
    LinearLayout select_store;//选择门店
    @Bind(R.id.meal_choice)
    RelativeLayout meal_choice;//餐别选择
    @Bind(R.id.breakfase_icon)
    ImageView mealIcon;//餐别图标
    @Bind(R.id.breakfase_text)
    TextView mealText;//餐别文本
//    @Bind(R.id.home_frame)
//    FrameLayout home_frame;
//    @Bind(R.id.home_rootView)
//    LinearLayout home_rootView;

    @Bind(R.id.topView)
    LinearLayout topView;

    private LinearLayout.LayoutParams thisService;//本次服务评价文本布局
    private Intent mIntent;
    private String currentDay;//今天日期字符串
    private ArrayList<String> templateDateList = new ArrayList<>();//门店供应的菜品日期集合
    private int currentClickItem;
    private LeftAdapter adapter;
    HomeRighAdapter righAdapter;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> dishesTypeList;//左侧成品分类列表
    //    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList;//左侧成品分类列表
    private DividerItemDecoration dividerItemDecoration;

    int mealTypeCode;//餐别
    private int hour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_home;
    }

    @Override
    public void setViews() {
        dividerItemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setWidth(CommonUtils.px2dip(getActivity(), 1));
        currentDay = DateUtils.getCurrentDate();
        homeToday.setText(currentDay);
        homeWeekday.setText("(今天 " + DateUtils.getWeekDay() + ")");

        hour = DateUtils.getCurrentTime();
        if (hour >= 0 && hour <= 9) {
            select_meal(0);
        } else if (hour > 9 && hour <= 16) {
            select_meal(1);
        } else {
            select_meal(2);
        }
        initDate();
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
        home_currend_date.setOnClickListener(this);

        homeThisServiceEvaluation.setOnClickListener(this);
        select_store.setOnClickListener(this);
        meal_choice.setOnClickListener(this);
//        home_right_recycleView.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initDate() {
        Api.getBanner("1", "1", this);
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
        Api.homeStoreSupplyList(Constant.cusId,DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(currentDay)), "", "", "1", this);
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
            case R.id.home_currend_date:
                mIntent = new Intent(getActivity(), PhysiologicalPeriodActivity.class);
                mIntent.putExtra("isFromHome", true);
                mIntent.putExtra("currentDate", homeToday.getText().toString().trim());
                mIntent.putExtra("dateList", DateUtils.dateList(templateDateList));
                startActivityForResult(mIntent, 10);//传递当前日期
                break;
            case R.id.home_thisService_evaluation:
//                mIntent = new Intent(getActivity(), ServiceActivity.class);
//                startActivity(mIntent);
                startActivity(new Intent(getActivity(), AddLunchActivity.class));
                break;
            case R.id.select_store:
                mIntent = new Intent(getActivity(), GetShopActivity.class);
                startActivity(mIntent);
                break;
            case R.id.meal_choice:
                final MealChoicePopwindow popwindow = new MealChoicePopwindow(getActivity(), meal_choice, meal_choice.getWidth(), new MyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        select_meal(position);
                    }
                });
                break;
        }
    }

    /**
     * 指定选中哪一个餐别
     *
     * @param position
     */
    private void select_meal(int position) {
        switch (position) {
            case 0:
                mealIcon.setImageResource(R.mipmap.breakfase_icon_normal);
                mealText.setText("早餐");
                Api.homeStoreSupplyList(Constant.cusId, DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), "", "", "1", this);
                break;
            case 1:
                mealIcon.setImageResource(R.mipmap.lunch_icon_normal);
                mealText.setText("午餐");
                Api.homeStoreSupplyList(Constant.cusId,DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), "", "", "1", this);
                break;
            case 2:
                mealIcon.setImageResource(R.mipmap.dinner_icon_normal);
                mealText.setText("晚餐");
                Api.homeStoreSupplyList(Constant.cusId,DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), "", "", "1", this);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    Api.homeStoreSupplyList(Constant.cusId, DateUtils.dateFormatChanged_2(currenStr), "", "", "1", this);
                }
                break;
        }
    }

    @Override
    public void onError(Request request, Action action, Exception e) {
        LogUtils.i("请求失败");
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

        if (object != null && action == Action.BANNER) {
            BannerBean ben = (BannerBean) object;
            if (ben.getCode().equals(Constant.RESULT_SUCESS)) {
            } else {
                LogUtils.i("轮播图接口调失败");
            }
        } else if (null != object && action == Action.GET_HOME_LIST) {
            HomeStoreSupplyList listBean = (HomeStoreSupplyList) object;
            if (listBean.getCode().equals(Constant.RESULT_SUCESS)) {
                templateDateList = listBean.getJson().getTmplDateList();
                dishesTypeList = listBean.getJson().getDishesSupplyList();
//                rightDishesList = listBean.getJson().getDishesSupplyList().get(1).getDishesSupplyDtlList();
                try {
                    if (DateUtils.compareDate(homeToday.getText().toString(), currentDay) < 0) {//早于现在时间
                        homeThisServiceEvaluation.setVisibility(View.VISIBLE);
                        listEvaluteInvisible(true);
                    } else {
                        if (homeWeekday.getText().toString().contains("今天") && mealText.getText().toString().equals("早餐") && hour > 9) {
                            homeThisServiceEvaluation.setVisibility(View.VISIBLE);
                            listEvaluteInvisible(true);
                        } else if (homeWeekday.getText().toString().contains("今天") && mealText.getText().toString().equals("午餐") && hour >= 16) {
                            homeThisServiceEvaluation.setVisibility(View.VISIBLE);
                            listEvaluteInvisible(true);
                        } else {
                            listEvaluteInvisible(false);
                            homeThisServiceEvaluation.setVisibility(View.GONE);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (adapter == null) {
                    adapter = new LeftAdapter(getActivity(), dishesTypeList,this);
                } else {
                    adapter.notifyDataSetChanged();
                }
                home_left_listView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
                home_left_listView.setHasFixedSize(true);//保持固定大小，提高性能
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                home_left_listView.setLayoutManager(layoutManager);
                home_left_listView.setAdapter(adapter);

                if (righAdapter == null) {
                    righAdapter = new HomeRighAdapter(getActivity(), dishesTypeList, new MyStickyHeadChangeListener() {
                        @Override
                        public void headChange(int position, String headId) {
                            for (int i = 0; i < dishesTypeList.size(); i++) {
                                if (dishesTypeList.get(i).getDishesTypeCode().equals(headId)) {
                                    home_left_listView.scrollToPosition(i);
                                    adapter.selectPosion(i);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
                } else {
                    righAdapter.setDate(dishesTypeList);
                }
                home_right_recycleView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
                home_right_recycleView.setHasFixedSize(true);//保持固定大小，提高性能

                LinearLayoutManager layoutManager_1 = new LinearLayoutManager(getActivity());
                home_right_recycleView.setLayoutManager(layoutManager_1);
                home_right_recycleView.setAdapter(righAdapter);
                // Add the sticky headers decoration
                final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(righAdapter);
                home_right_recycleView.addItemDecoration(headersDecor);
            }
        }
    }

    /**
     * 列表评价文字是否展示
     */
    private void listEvaluteInvisible(boolean isShow) {

        for (int i = 0; i < dishesTypeList.size(); i++) {
            if (dishesTypeList.get(i).getDishesSupplyDtlList() != null && dishesTypeList.get(i).getDishesSupplyDtlList().size() > 0) {
                for (int j = 0; j < dishesTypeList.get(i).getDishesSupplyDtlList().size(); j++) {
                    if (isShow) {
                        dishesTypeList.get(i).getDishesSupplyDtlList().get(j).setShow(true);
                    } else {
                        dishesTypeList.get(i).getDishesSupplyDtlList().get(j).setShow(false);
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
