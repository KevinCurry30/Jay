package com.diligroup.UserSet.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.AppManager;
import com.diligroup.base.BaseActivity;
import com.diligroup.login.LoginActivity;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.SharedPreferenceUtil;
import com.diligroup.utils.UserManager;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by Kevin on 2016/6/21.
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.user_setting;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @OnClick(R.id.rl_clearcache)
    public void clearCache() {

    }

    @Override
    protected void initViewAndData() {
        tv_title.setText("设置");
       ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }

    @OnClick(R.id.bt_exit)
    public void exit() {
        Api.loginOut(UserManager.getInstance().getPhone(), this);
        SharedPreferenceUtil spUtils = new SharedPreferenceUtil();
        spUtils.clear();
        readyGo(LoginActivity.class);
        AppManager.getAppManager().finishActivity(this);
    }
}
