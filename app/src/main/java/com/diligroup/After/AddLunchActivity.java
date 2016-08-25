package com.diligroup.After;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.diligroup.After.adapter.AddLunchAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.AddFoodCompleteBean;
import com.diligroup.net.Action;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Request;

/**
 * Created by hjf on 2016/7/13.
 * 添加午餐
 */
public class AddLunchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.vPager)
    ViewPager vPager;
    @Bind(R.id.addlunch_storeSuppy)
    RadioButton addlunchStoreSuppy;
    @Bind(R.id.addlunch_customer)
    RadioButton addlunchCustomer;
    @Bind(R.id.addlunch_rgroup)
    RadioGroup addlunchRgroup;
    @Bind(R.id.addlunch_tabline)
    View addlunch_tabline;
    @Bind(R.id.comm_title)
    TextView tv_title;
    private String currentDay;
    private String mealType;

    public List<AddFoodCompleteBean> getAddMealList() {
        return addMealList;
    }

    private List<AddFoodCompleteBean> addMealList = new ArrayList<>();//已经添加的餐别菜品

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_add_lunch;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);// 默认软键盘不弹出
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void initViewAndData() {
        mealType = getIntent().getStringExtra("mealType");
        currentDay = getIntent().getStringExtra("currentDay");
        switch (mealType) {
            case "20001":
                tv_title.setText("添加早餐");
                break;
            case "20002":
                tv_title.setText("添加午餐");
                break;
            case "20003":
                tv_title.setText("添加晚餐");
                break;
        }

        addlunchRgroup.check(R.id.addlunch_storeSuppy);
        addlunchRgroup.setOnCheckedChangeListener(this);
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) addlunch_tabline.getLayoutParams();
        params.width = CommonUtils.getScreenWidth(this) / 2;
        addlunch_tabline.setLayoutParams(params);

        vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) addlunch_tabline.getLayoutParams();
                //获取组件距离左侧组件的距离
                lp.leftMargin = (int) ((positionOffset + position) * CommonUtils.getScreenWidth(AddLunchActivity.this) / 2);
                addlunch_tabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    addlunchStoreSuppy.setChecked(true);
                    addlunchCustomer.setChecked(false);
                } else if (position == 1) {
                    addlunchStoreSuppy.setChecked(false);
                    addlunchCustomer.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        AddLunchAdapter adapter = new AddLunchAdapter(getSupportFragmentManager(), this, mealType, currentDay);
        vPager.setAdapter(adapter);
        vPager.setCurrentItem(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.addlunch_customer:
                vPager.setCurrentItem(1);
                break;
            case R.id.addlunch_storeSuppy:
                vPager.setCurrentItem(0);
                break;
        }
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }

    /**
     * 添加菜品
     * wayType“:菜品来源（1自定义，2来自门店）
     *
     * @param bean
     */
    public void addFood(AddFoodCompleteBean bean) {
        if (addMealList.size() == 0) {
            addMealList.add(bean);
        } else {
            for (int i = 0; i < addMealList.size(); i++) {
                if (addMealList.get(i).getDishesCode().equals(bean.getDishesCode()) && addMealList.get(i).getWayType().equals(bean.getWayType())) {
                    addMealList.get(i).setWeight(Float.parseFloat(TextUtils.isEmpty(addMealList.get(i).getWeight()) ? "0.0f" : addMealList.get(i).getWeight()) + Float.parseFloat(TextUtils.isEmpty(bean.getWeight()) ? "0.0f" : bean.getWeight()) + "");
                    return;
                }
            }
            addMealList.add(bean);
        }
    }

    /**
     * 删除菜品
     *
     * @param bean
     */
    public void deleteFood(AddFoodCompleteBean bean) {
        if (addMealList.contains(bean)) {
            for (int i = 0; i < addMealList.size(); i++) {
                if (addMealList.get(i).getDishesCode().equals(bean.getDishesCode()) && addMealList.get(i).getWayType().equals(bean.getWayType())) {
                    addMealList.get(i).setWeight(Float.parseFloat(TextUtils.isEmpty(addMealList.get(i).getWeight()) ? "0.0f" : addMealList.get(i).getWeight()) - Float.parseFloat(TextUtils.isEmpty(bean.getWeight()) ? "0.0f" : bean.getWeight()) + "");
                }
                if (TextUtils.isEmpty(addMealList.get(i).getWeight())) {
                    addMealList.remove(addMealList.get(i));
                }
            }
        }
    }

}
