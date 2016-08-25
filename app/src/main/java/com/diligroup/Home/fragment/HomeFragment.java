package com.diligroup.Home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.Home.ServiceActivity;
import com.diligroup.Home.adapter.HomeRighAdapter_1;
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
import com.diligroup.view.banner.BaseSliderView;
import com.diligroup.view.banner.DefaultSliderView;
import com.diligroup.view.banner.SliderLayout;
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
public class HomeFragment extends BaseFragment implements View.OnClickListener, RequestManager.ResultCallback, MyItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.slider)
    SliderLayout sliderLayout;
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
    @Bind(R.id.home_store_address)
    TextView home_store_address;//哪一个门店，地址
    @Bind(R.id.meal_choice)
    RelativeLayout meal_choice;//餐别选择
    @Bind(R.id.breakfase_icon)
    ImageView mealIcon;//餐别图标
    @Bind(R.id.breakfase_text)
    TextView mealText;//餐别文本
    @Bind(R.id.topView)
    LinearLayout topView;
//    @Bind(R.id.swipe_layout)
//    SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout.LayoutParams thisService;//本次服务评价文本布局
    private Intent mIntent;
    private String currentDay;//今天日期字符串
    private ArrayList<String> templateDateList = new ArrayList<>();//门店供应的菜品日期集合
    private int currentClickItem;
    private LeftAdapter adapter;
    HomeRighAdapter_1 righAdapter;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> dishesTypeList;//左侧成品分类列表
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList = new ArrayList<>();//左侧成品分类列表
    private DividerItemDecoration dividerItemDecoration;

    String currentMealTypeCode;//当前餐别
    private int hour;
    private LinearLayoutManager layoutManager_1;
    int currentLeftPosition;
    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }
    String currentHeadCode;//右侧滚动时候，当前sticky的headCode值
    private String address;//当前地址
    private String currenStoreId;//门店id

    public static HomeFragment newInstance(String currentStoreId,String storeAddress) {
        Bundle args = new Bundle();
        args.putString("currentStoreId", currentStoreId);
        args.putString("storeAddress", storeAddress);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments()!=null){
            currenStoreId = getArguments().getString("currentStoreId");
            address = getArguments().getString("storeAddress");
            LogUtils.i("哈哈哈哈，信息传递过来了");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_home;
    }

    @Override
    public void setViews() {
        currentDay = DateUtils.getCurrentDate();
        homeToday.setText(currentDay);
        homeWeekday.setText("(今天 " + DateUtils.getWeekDay() + ")");
        home_store_address.setText(address);

        hour = DateUtils.getCurrentTime();
        if (hour >= 0 && hour <= 9) {
            select_meal(0);
            currentMealTypeCode = "20001";
        } else if (hour > 9 && hour <= 16) {
            select_meal(1);
            currentMealTypeCode = "20002";
        } else {
            select_meal(2);
            currentMealTypeCode = "20003";
        }

        CommonUtils.initRerecyelerView(getActivity(), home_left_listView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        home_left_listView.setLayoutManager(layoutManager);
        layoutManager_1 = new LinearLayoutManager(getActivity());
        home_right_recycleView.setLayoutManager(layoutManager_1);
        CommonUtils.initRerecyelerView(getActivity(), home_right_recycleView);
        initDate();

//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        //设置样式刷新显示的位置
//        mSwipeRefreshLayout.setProgressViewOffset(true, 20, -100);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.greenyellow, R.color.orange);
    }

    @Override
    public void setListeners() {

        home_currend_date.setOnClickListener(this);

        homeThisServiceEvaluation.setOnClickListener(this);
        select_store.setOnClickListener(this);
        meal_choice.setOnClickListener(this);

        home_right_recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = ((LinearLayoutManager) home_right_recycleView.getLayoutManager()).findFirstVisibleItemPosition();
                String tempHeadCode = getRightList(dishesTypeList).get(position).getHeaderCode();
//                    if(position==0){
//                        home_left_listView.scrollToPosition(0);
//                        adapter.selectPosion(0);
//                        currentLeftPosition=0;
//                        return;
//                    }
                if (!tempHeadCode.equals(currentHeadCode)) {
                    for (int i = 0; i < dishesTypeList.size(); i++) {
                        if (dishesTypeList.get(i).getDishesTypeCode().equals(tempHeadCode)) {
                            home_left_listView.scrollToPosition(i);
                            adapter.selectPosion(i);
                            currentHeadCode = tempHeadCode;
                            currentLeftPosition = i;
                            break;
                        }
                    }
                }
            }
        });
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
//        for (int i = 0; i < image.length; i++) {
//            ImageView iv = new ImageView(getActivity());
//            iv.setBackgroundResource(image[i]);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            iv.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//            homeFlipper.addView(iv);
//
//            ImageView dotter = new ImageView(getActivity());
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
//            dotter.setBackgroundResource(R.drawable.banner_dot_selector);
//            if (i != 0) {
//                layoutParams.leftMargin = 5;
//                dotter.setSelected(false);
//            } else {
//                dotter.setSelected(true);
//            }
//            dotter.setLayoutParams(layoutParams);
//            banner_dot_ll.addView(dotter);
//        }
        for (int i = 0; i < image.length; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(
                    getActivity(), false);
            sliderView
                    .image(image[i])
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", "" + i);
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            sliderLayout.addSlider(sliderView);
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
                mIntent = new Intent(getActivity(), ServiceActivity.class);
                mIntent.putExtra("mealType", mealText.getText().toString());
                mIntent.putExtra("date", currentDay);
                startActivity(mIntent);
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
                currentMealTypeCode = "20001";
                Api.homeStoreSupplyList(Constant.cusId, DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), currentMealTypeCode, "", "1", this);
                break;
            case 1:
                mealIcon.setImageResource(R.mipmap.lunch_icon_normal);
                mealText.setText("午餐");
                currentMealTypeCode = "20002";
                Api.homeStoreSupplyList(Constant.cusId, DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), currentMealTypeCode, "", "1", this);
                break;
            case 2:
                mealIcon.setImageResource(R.mipmap.dinner_icon_normal);
                mealText.setText("晚餐");
                currentMealTypeCode = "20003";
                Api.homeStoreSupplyList(Constant.cusId, DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), currentMealTypeCode, "", "1", this);
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
                    Api.homeStoreSupplyList(Constant.cusId, DateUtils.dateFormatChanged_2(currenStr), currentMealTypeCode, "", "1", this);
                }
                break;
            case 0x111:
                currenStoreId = data.getStringExtra("storeId");
                address = data.getStringExtra("address");
                home_store_address.setText(address);
                Api.homeStoreSupplyList(currenStoreId, DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(homeToday.getText().toString())), currentMealTypeCode, "", "1", this);
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
                    adapter = new LeftAdapter(getActivity(), dishesTypeList, this);
                    home_left_listView.setAdapter(adapter);
                } else {
                    adapter.setDate(dishesTypeList);
                }
                if (righAdapter == null) {
                    righAdapter = new HomeRighAdapter_1(getActivity(), dishesTypeList, currentDay, mealText.getText().toString(), new MyStickyHeadChangeListener() {

                        @Override
                        public void headChange(int position, String headId) {
                            if (TextUtils.isEmpty(currentHeadCode)) {
                                currentHeadCode = dishesTypeList.get(0).getDishesSupplyDtlList().get(0).getHeaderCode();
                            }
                        }
                    });
                    final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(righAdapter);
                    home_right_recycleView.addItemDecoration(headersDecor);
                    home_right_recycleView.setAdapter(righAdapter);
                } else {
                    List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> temprightDishesList = getRightList(dishesTypeList);
                    righAdapter.setDate(temprightDishesList, currentDay, mealText.getText().toString());
                }
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
        if (righAdapter != null) {
            righAdapter.setDate(getRightList(dishesTypeList), currentDay, mealText.getText().toString());
        }
    }

    @Override
    public void onItemClick(View view, int position) {//左侧item点击事件
        int count = 0;

        for (int i = 0; i < dishesTypeList.size(); i++) {
            if (position == 0) {
                home_right_recycleView.scrollToPosition(0);
                return;
            } else if (i < position && dishesTypeList.get(i).getDishesSupplyDtlList() == null) {
                continue;
            } else if (i < position && dishesTypeList.get(i).getDishesSupplyDtlList() != null) {
                count = count + dishesTypeList.get(i).getDishesSupplyDtlList().size();
                currentLeftPosition = count;
            } else {
                break;
            }
        }
        home_right_recycleView.scrollToPosition(position);
        ((LinearLayoutManager) home_right_recycleView.getLayoutManager()).scrollToPositionWithOffset(count, 0);
    }

    /**
     * 获取右侧菜品列表
     *
     * @param mList
     * @return
     */
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> getRightList(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mList) {
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getDishesSupplyDtlList() != null) {
                    for (int j = 0; j < mList.get(i).getDishesSupplyDtlList().size(); j++) {
                        mList.get(i).getDishesSupplyDtlList().get(j).setHeaderCode(mList.get(i).getDishesTypeCode());
                        mList.get(i).getDishesSupplyDtlList().get(j).setHeaderName(mList.get(i).getDishesTypeName());
                    }
                    rightDishesList.addAll(mList.get(i).getDishesSupplyDtlList());
                }
            }
        }
        return rightDishesList;
    }

    @Override
    public void onRefresh() {

    }
}
