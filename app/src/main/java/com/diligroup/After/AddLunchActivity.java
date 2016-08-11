package com.diligroup.After;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.diligroup.After.adapter.AddLunchAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.net.Action;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.NetUtils;

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
    public void setTitle() {
        super.setTitle();
        tv_title.setText("添加午餐");
        isShowBack(true);
    }

    @Override
    protected void initViewAndData() {
        addlunchRgroup.check(R.id.addlunch_storeSuppy);
        addlunchRgroup.setOnCheckedChangeListener(this);
        final LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) addlunch_tabline.getLayoutParams();
        params.width= CommonUtils.getScreenWidth(this)/2;
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
                if(position==0){
                    addlunchStoreSuppy.setChecked(true);
                    addlunchCustomer.setChecked(false);
                }else if(position==1){
                    addlunchStoreSuppy.setChecked(false);
                    addlunchCustomer.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        AddLunchAdapter adapter = new AddLunchAdapter(getSupportFragmentManager(), this);
        vPager.setAdapter(adapter);
        vPager.setCurrentItem(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
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
}
