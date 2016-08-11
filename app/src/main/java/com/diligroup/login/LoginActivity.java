package com.diligroup.login;

import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.diligroup.Home.HomeActivity;
import com.diligroup.R;
import com.diligroup.UserSet.activity.ReportSex;
import com.diligroup.base.AppManager;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.bean.UserBeanFromService;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.DigestUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.SharedPreferenceUtil;
import com.diligroup.utils.StringUtils;
import com.diligroup.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 登录 Activity
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.input_username)
    AutoCompleteTextView phoneNumber;
    @Bind(R.id.input_password)
    EditText et_password;
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
//    @Bind(R.id.bt_regist)
//    Button bt_regist;
//    @Bind(R.id.bt_login)
//    Button bt_login;
//    String phoneNum;
    boolean isFirst=true;
    UserBeanFromService  userInfo;
    SharedPreferenceUtil  spUtils;
//    @Bind(R.id.bt_login)
//    Button bt_login;
    String phoneNum;
    String passdWord;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNetworkDisConnected() {

    }
    @Override
    protected void initViewAndData() {
        tv_title.setText("登录");
        spUtils=new SharedPreferenceUtil("auto_login");
        if (!TextUtils.isEmpty(spUtils.getString("phoneNum",""))&&!TextUtils.isEmpty(spUtils.getString("passWord",""))){
            phoneNumber.setText(spUtils.getString("phoneNum",""));
            et_password.setText(spUtils.getString("passWord",""));
            doLogin();
        }



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    @OnClick(R.id.bt_login)
    public void doLogin() {
          phoneNum = phoneNumber.getText().toString();
         passdWord = et_password.getText().toString();
        if (!TextUtils.isEmpty(phoneNum) ) {
            if (StringUtils.isMobileNumber(phoneNum)){
                if (!TextUtils.isEmpty(passdWord)) {

                    Api.login(phoneNum, DigestUtils.stringMD5(passdWord), this);
                    LogUtils.e(DigestUtils.stringMD5(passdWord));
                } else {
                    ToastUtil.showShort(this, "密码不能为空");
                }
            }else{
                ToastUtil.showShort(this, "手机号码格式不正确");
            }

        } else {
            ToastUtil.showShort(this, "请输入手机号码");
        }

    }
/* 去注册*/
    @OnClick(R.id.bt_regist)
    public void doRegist() {
        readyGo(RegistActivity.class);
    }
/* 忘记密码*/
    @OnClick(R.id.tv_forget)
    public void forgetPsd() {
        readyGo(ModifyPSDActivity.class);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {
            if (action== Action.LOGIN){
                ToastUtil.showShort(LoginActivity.this,"登录失败，服务器出问题了");
                LogUtils.e(e.getMessage());
            }
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
//        userInfo=UserBeanFromService.getInstance();
        if (object != null&& action== Action.LOGIN) {
             userInfo= (UserBeanFromService) object;
            if (userInfo.getCode().equals("000000")) {
                spUtils.putString("phoneNum",phoneNum);
                spUtils.putString("passWord",passdWord);
                Constant.userNumber=userInfo.getUser().getMobileNum();
                Constant.userId=userInfo.getUser().getUserId();
//                UserInfoBean.getInstance().setBirthday(userInfo.getUser().getBirthday());
//                UserInfoBean.getInstance().setBirthday(userInfo.getUser().getBirthday());
 //             如果是第一次登陆用户信息为kong则填写用户信息 否则进入首页面
                if (TextUtils.isEmpty(userInfo.getUser().getBirthday())){
                    readyGo(ReportSex.class);
                    AppManager.getAppManager().finishActivity(this);
                }else{
                    UserInfoBean.getInstance().setBirthday(userInfo.getUser().getBirthday());
                    UserInfoBean.getInstance().setSex(userInfo.getUser().getSex());
                    UserInfoBean.getInstance().setHeight(userInfo.getUser().getHeight());
//                    UserInfoBean.getInstance().setWeight(userInfo.getUserDetail().getWeight());
                    readyGo(HomeActivity.class);
                    AppManager.getAppManager().finishActivity(this);
                }
                return;
            }
            if (userInfo.getCode().equals("APP_C010005")){
                ToastUtil.showShort(this, "密码不正确");
                return;
            }
            if (userInfo.getCode().equals("APP_C010001")){
                ToastUtil.showShort(this, "密码不正确");

            }


        }
    }
}
