//package com.diligroup.UserSet.activity;
//
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.diligroup.R;
//import com.diligroup.base.BaseActivity;
//import com.diligroup.net.Action;
//import com.diligroup.utils.NetUtils;
//
//import butterknife.Bind;
//import butterknife.OnClick;
//import okhttp3.Request;
//
///**
// * 用户中心
// */
//public class UserCenterView extends BaseActivity {
//    @Bind(R.id.et_suggestion)
//    EditText et_suggest;
//    @Bind(R.id.bt_commit)
//    Button bt_commit;
//
//
//    @Override
//    protected int getContentViewLayoutID() {
//        return R.layout.activity_user_center;
//    }
//
//    @Override
//    protected void onNetworkConnected(NetUtils.NetType type) {
//
//    }
//
//    @Override
//    protected void onNetworkDisConnected() {
//
//    }
//
//    @Override
//    public void setTitle() {
//        super.setTitle();
////        tv_title.setText("我的");
//    }
//
//    @Override
//    protected void initViewAndData() {
//
//    }
//
//    @OnClick(R.id.rl_userinfo)
//    public void jumpUserInfo() {
//        readyGo(UserInfoActivity.class);
//
//    }
//
//    @OnClick(R.id.rl_user_set)
//    public void jumpSetView() {
//        readyGo(SettingActivity.class);
//    }
//
//    @OnClick(R.id.rl_service_center)
//    public void jumpService() {
//
//    }
//
//    @Override
//    public void onError(Request request, Action action, Exception e) {
//
//    }
//
//    @Override
//    public void onResponse(Request request, Action action, Object object) {
//
//    }
//}
