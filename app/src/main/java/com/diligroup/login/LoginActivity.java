package com.diligroup.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.UserBeanFromService;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.DigestUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.SharedPreferenceUtil;
import com.diligroup.utils.StringUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.utils.UserManager;
import com.diligroup.utils.alipaylogin.AuthResult;
import com.diligroup.utils.alipaylogin.OrderInfoUtil2_0;
import com.diligroup.view.TogglePasswordVisibilityEditText;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 登录 Activity
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.input_username)
    EditText phoneNumber;
    @Bind(R.id.input_password)
    TogglePasswordVisibilityEditText et_password;
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.comm_title)
            TextView tv_title;
    boolean isFirst = true;
    UserBeanFromService userInfo;
    SharedPreferenceUtil spUtils;
    //    @Bind(R.id.bt_login)
//    Button bt_login;
    String phoneNum;
    String passdWord;
    @Bind(R.id.tv_notice_number)
    TextView tv_number;
    @Bind(R.id.tv_notice_password)
    TextView tv_psd;
    @Bind(R.id.login_qq)
    LinearLayout login_qq;
    @Bind(R.id.login_alipay)
    LinearLayout login_alipay;
    @Bind(R.id.login_wb)
    LinearLayout loginWb;
    @Bind(R.id.login_wx)
    LinearLayout loginWX;

    UMShareAPI mShareAPI;
    SHARE_MEDIA platform;
    private static final int SDK_AUTH_FLAG = 2;
    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016081601755137";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "2088221786995186";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "2016091125";

    private String openid;//第三方开放平台的openid
    private String userId;//自己服务器返回的用户id
    private String nickName;

    private String headImagUrl;
    private String sex;
    private String currentPlatFrom;
    /** 商户私钥，pkcs8格式 */
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMQyISfgimoFeGmDWz9YC7OomVa+58N22OfTJ4P+4xFc7/aqEzs8IuJjwqo+p+tsgNmcB+47oN4QCfi78Xhqe7mp4SK2O/S/s1WGP29DowK7AHH+tOVctJgMEQFogX0XrRIWrAuJcmG2C2Fo4diUmyk234VMhi4zfaO7CWZApZNrAgMBAAECgYBgKl4cCLBvlSzXMv53xvU9Y2d9oGdDZK6eut4EkdvEt/QayHRStYA3zUQuZDW0bGOfxh4RBIMuNVhd5elO54qqsiG9n5DwY2KCbZDCaG1KUG789c88UsfT+24Ni1PXSFxm1/exdQzsSEvzZptx6drobARN0h8gaAGz5KOKCyfvgQJBAP1dOdEflIw0hPltzMlymLi8LyHZRsCXEUeO1gE9UryGYRKyjK12HA/a6DeS64UnUz99Ei46reb3av7lz3wtjqsCQQDGPKZM7F5hrb1iFeylj5IGds7IU8XNlHqm8Rx/ZPp7gmCeGxlW7w5jSN673W4bHv3gpnAJ7Pbdua49kvSV1Q5BAkEAmIgqiaLIjIwFziBzXIf4N6dbfLZRKRsJlRoB7qcbi1IfWOFTXg6wID969BIoZnZhYOSMMHa1QUqNCL4T5r+KlwJBALunMe1jWzyv0LSG+IsIyzxfPwOXeYlP4oMhfs6BcjN0ia1hDa2jgkUt99pylAYMYltEco6SyGW/nVcgQ3OKSYECQQC1OFUG3eUB0u3M1VXscAEkd0FXsDnMBZ9EtUGFlVk8jXDafe4kz5Hg7/q8EivyJkbeXdeAuzCnEzO6uJxRDmrK";
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            mShareAPI.getPlatformInfo(LoginActivity.this, platform, umdelAuthListener);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * delauth callback interface
     **/
    private UMAuthListener umdelAuthListener = new UMAuthListener() {

        @Override
        //删除授权成功
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "删除授权成功", Toast.LENGTH_SHORT).show();

            String psd = data.get("openid");
            String userName = data.get("screen_name");
            switch (platform) {
                case WEIXIN:
//                    Api.threePartlogin(data.get("nickname"), data.get("unionid"), LoginActivity.this);
                    openid = data.get("unionid");
                    nickName = data.get("nickname");
                    headImagUrl = data.get("headimgurl");
                    sex = data.get("sex");
                    currentPlatFrom = "wx";
                    Api.selectUserInfo("wx",openid,LoginActivity.this);
                    break;
                case QQ:
//                    Api.login(data.get("screen_name"), data.get("openid"), LoginActivity.this);
                    openid= data.get("openid");
                    nickName=data.get("screen_name");
                    sex=data.get("gender");
                    headImagUrl =data.get("profile_image_url");
                    currentPlatFrom="qq";
                    Api.selectUserInfo("qq",openid,LoginActivity.this);
                    break;
                case SINA:
                    currentPlatFrom="microblog";

                    break;
            }
        }

        //授权失败
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        // 授权取消
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "delete Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(LoginActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(LoginActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };

            @Override
            protected int getContentViewLayoutID () {
                return R.layout.activity_login;
            }

            @Override
            protected void onNetworkConnected (NetUtils.NetType type){

            }

            @Override
            protected void onStart () {
                super.onStart();
            }

            @Override
            protected void onNetworkDisConnected () {

            }
            @Override
            protected void initViewAndData () {
                tv_title.setText("登录");
                mShareAPI = UMShareAPI.get(this);

                login_alipay.setOnClickListener(this);
                login_qq.setOnClickListener(this);
                loginWb.setOnClickListener(this);
                loginWX.setOnClickListener(this);

                spUtils = new SharedPreferenceUtil();
                if (!TextUtils.isEmpty(spUtils.getString("phoneNum", "")) && !TextUtils.isEmpty(spUtils.getString("passWord", ""))) {
                    phoneNumber.setText(spUtils.getString("phoneNum", ""));
                    et_password.setText(spUtils.getString("passWord", ""));
                    doLogin();
                }


            }
            @Override
            protected void onDestroy () {
                super.onDestroy();
                this.finish();
            }

            @OnClick(R.id.bt_login)
            public void doLogin () {
                phoneNum = phoneNumber.getText().toString();
                passdWord = et_password.getText().toString();
                if (!TextUtils.isEmpty(phoneNum)) {
                    tv_number.setVisibility(View.INVISIBLE);
                    if (StringUtils.isMobileNumber(phoneNum)) {
                        tv_number.setVisibility(View.INVISIBLE);
                        if (!TextUtils.isEmpty(passdWord)) {
                            tv_psd.setVisibility(View.INVISIBLE);
                            Api.login(phoneNum, DigestUtils.stringMD5(passdWord), this);
                            LogUtils.e(DigestUtils.stringMD5(passdWord));
                        } else {
                            tv_psd.setVisibility(View.VISIBLE);
                            tv_psd.setText("密码不能为空!");
//                    ToastUtil.showShort(this, "密码不能为空");
                        }
                    } else {
                        tv_number.setVisibility(View.VISIBLE);
                        tv_number.setText("手机号码格式不正确!");
//                ToastUtil.showShort(this, "手机号码格式不正确");
                    }

                } else {
                    tv_number.setVisibility(View.VISIBLE);
                    tv_number.setText("请输入手机号码!");
//            ToastUtil.showShort(this, "请输入手机号码");
                }

            }
/* 去注册*/
            @OnClick(R.id.bt_regist)
            public void doRegist () {
                readyGo(RegistActivity.class);
            }
/* 忘记密码*/
            @OnClick(R.id.tv_forget)
            public void forgetPsd () {
                readyGo(ModifyPSDActivity.class);
            }

            @Override
            public void onError (Request request, Action action, Exception e){
                if (action == Action.LOGIN) {
                    ToastUtil.showShort(LoginActivity.this, "登录失败，服务器出问题了!");
                    LogUtils.e(e.getMessage());
                }
            }

            @Override
            public void onResponse (Request request, Action action, Object object){
//        userInfo=UserBeanFromService.getInstance();
                if (object != null && action == Action.LOGIN) {
                    userInfo = (UserBeanFromService) object;
                    if (userInfo.getCode().equals("000000")) {
                        tv_psd.setVisibility(View.INVISIBLE);
//                        spUtils.putString("phoneNum", phoneNum);
//                        spUtils.putString("passWord", passdWord);
//                        Constant.userNumber = userInfo.getUser().getMobileNum();
//                        Constant.userId = userInfo.getUser().getUserId();
                        UserManager.getInstance().saveUser(userInfo.getUser().getUserId()+"",DigestUtils.stringMD5(et_password.getText().toString()),phoneNumber.getText().toString().trim(),userInfo.getUserDetail().getHeadPhotoAdd(),"","");
//                UserInfoBean.getInstance().setBirthday(userInfo.getUser().getBirthday());
//                UserInfoBean.getInstance().setBirthday(userInfo.getUser().getBirthday());
                        //             如果是第一次登陆用户信息为kong则填写用户信息 否则进入首页面
//                        if (TextUtils.isEmpty(userInfo.getUser().getBirthday())) {
//                            Bundle bundle = new Bundle();
//                            bundle.putBoolean("isFrist", true);
//                            readyGo(ReportSex.class, bundle);
//                            AppManager.getAppManager().finishActivity(this);
//                        } else {
//                            UserInfoBean.getInstance().setBirthday(userInfo.getUser().getBirthday());
//                            UserInfoBean.getInstance().setSex(userInfo.getUser().getSex());
//                            UserInfoBean.getInstance().setHeight(userInfo.getUser().getHeight());
//                            UserInfoBean.getInstance().setWeight(userInfo.getUserDetail().getWeight());
//                            UserInfoBean.getInstance().setJob(userInfo.getUserDetail().getJobName());
//                            UserInfoBean.getInstance().setAllergyFood(userInfo.getUserDetail().getAllergyName());
//                            UserInfoBean.getInstance().setNoEatFood(userInfo.getUserDetail().getTabooNames());
//                            UserInfoBean.getInstance().setOtherReq(userInfo.getUserDetail().getOtherReqName());
//                            UserInfoBean.getInstance().setCurrentAddress(userInfo.getUserDetail().getCurrentAdd());
//                            UserInfoBean.getInstance().setNoEatFood(userInfo.getUserDetail().getAllergyName());
//                            UserInfoBean.getInstance().setTaste(userInfo.getUserDetail().getTasteNames());
//
//                            readyGo(HomeActivity.class);
//                            AppManager.getAppManager().finishActivity(this);
//                        }
//                        return;
                        finish();
                    }
                    if (userInfo.getCode().equals("APP_C010005")) {
                        tv_psd.setVisibility(View.VISIBLE);
                        tv_psd.setText("密码不正确!");
//                ToastUtil.showShort(this, "密码不正确");
                        return;
                    }
                    if (userInfo.getCode().equals("APP_C010001")) {
                        tv_psd.setVisibility(View.VISIBLE);
                        tv_psd.setText("密码不正确!");
//                ToastUtil.showShort(this, "密码不正确");

                    }
                }else if(object!=null && action==Action.THIRD_PART_LOGIN){//第三方注册
                    UserBeanFromService commonBean= (UserBeanFromService) object;
                    if(commonBean.getCode().equals(Constant.RESULT_SUCESS)){
                        //调用完善信息
                        userId=commonBean.getUser().getUserId()+"";
                        Api.perfectInfoAfterThirdLogin(userId,headImagUrl,nickName,sex,this);
                    }
                }else if(object!=null && action==Action.SET_INFOS){//第三方注册完成。上传同步信息
                    CommonBean commonBean= (CommonBean) object;
                    if(commonBean.getCode().equals(Constant.RESULT_SUCESS)){
                        //调用完善信息 存储openid，下次免登陆
                        UserManager.getInstance().saveUser(userId,"","",headImagUrl,nickName,sex);
                        ToastUtil.showLong(LoginActivity.this,"第三方登录后完善信息成功");
                        finish();
                    }
                }else if(object!=null && action==Action.SELECT_USER_INFO){//第三方注册之前查询数据库，是否是老用户
                    UserBeanFromService serviceBean= (UserBeanFromService) object;
                    if(serviceBean.getCode().equals("C010005")){//没有这个用户 ，去注册
                        Api.threePartlogin(currentPlatFrom,openid, LoginActivity.this);
                    }else if(serviceBean.getCode().equals(Constant.RESULT_SUCESS) && serviceBean.getUser()!=null){
                        spUtils.putString(Constant.USER_ID,serviceBean.getUser().getUserId()+"");
                      UserManager.getInstance().saveUser(serviceBean.getUser().getUserId()+"","","",headImagUrl,nickName,sex);
                        finish();
                    }
                }
            }

            @Override
            public void onClick (View view){
                switch (view.getId()) {
                    case R.id.login_alipay:
                        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID);
                        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
                        String sign = OrderInfoUtil2_0.getSign(authInfoMap, RSA_PRIVATE);
                        final String authInfo = info + "&" + sign;
                        Runnable authRunnable = new Runnable() {

                            @Override
                            public void run() {
                                // 构造AuthTask 对象
                                AuthTask authTask = new AuthTask(LoginActivity.this);
                                // 调用授权接口，获取授权结果
                                Map<String, String> result = authTask.authV2(authInfo, true);

                                Message msg = new Message();
                                msg.what = SDK_AUTH_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };

                        // 必须异步调用
                        Thread authThread = new Thread(authRunnable);
                        authThread.start();
//                        Api.alipaylogin("","",this);
                        break;
                    case R.id.login_qq:
                        platform = SHARE_MEDIA.QQ;
                        mShareAPI.doOauthVerify(this, platform, umAuthListener);
                        break;
                    case R.id.login_wx:
                        platform = SHARE_MEDIA.WEIXIN;
                        mShareAPI.doOauthVerify(this, platform, umAuthListener);
                        break;
                    case R.id.login_wb:
                        platform = SHARE_MEDIA.SINA;
                        mShareAPI.doOauthVerify(this, platform, umAuthListener);
                        break;
                }
            }
            @Override
            protected void onActivityResult ( int requestCode, int resultCode, Intent data){
                super.onActivityResult(requestCode, resultCode, data);
                mShareAPI.onActivityResult(requestCode, resultCode, data);
            }
        }
