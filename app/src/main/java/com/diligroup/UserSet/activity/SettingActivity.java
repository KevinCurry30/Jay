package com.diligroup.UserSet.activity;


import com.diligroup.R;
import com.diligroup.base.AppManager;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.login.LoginActivity;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.SharedPreferenceUtil;

import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by Kevin on 2016/6/21.
 */
public class SettingActivity extends BaseActivity {

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
        isShowBack(true);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("设置");
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }

    @OnClick(R.id.bt_exit)
    public void exit() {
        Api.loginOut(Constant.userNumber, this);
        SharedPreferenceUtil spUtils = new SharedPreferenceUtil();
        spUtils.clear();
        readyGo(LoginActivity.class);
        AppManager.getAppManager().finishActivity(this);
    }
}
