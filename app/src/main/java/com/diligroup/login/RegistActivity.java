package com.diligroup.login;


import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.base.Constant;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.ProvingCodeBean;
import com.diligroup.bean.UserBeanFromService;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.DigestUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.StringUtils;
import com.diligroup.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;


/**
 * 注册
 */
public class RegistActivity extends BaseActivity {

    @Bind(R.id.input_phone)
    EditText et_phone;
    @Bind(R.id.et_code)
    EditText et_code;
    @Bind(R.id.et_psd)
    EditText et_psd;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    String smsCode;
    String phoneNum;
    String registCode;
    String psd;
    @Bind(R.id.bt_getcode)
    Button submit;
    private TimeCount time;
    @Bind(R.id.tv_notice1)
    TextView tv_notice_phone;
    @Bind(R.id.tv_notice2)
    TextView tv_notice_code;
    @Bind(R.id.tv_notice3)
    TextView tv_notice_psd;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_regist;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        tv_title.setText("注册");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        time = new TimeCount(60000, 1000);
    }

    //获取注册验证码
    @OnClick(R.id.bt_getcode)
    public void getRegistCode() {

        phoneNum = et_phone.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)) {
            if (StringUtils.isMobileNumber(phoneNum)) {
                time.start();// 开始计时
                Api.getCode(phoneNum, "1", this);
                tv_notice_phone.setVisibility(View.INVISIBLE);
            } else {
                tv_notice_phone.setVisibility(View.VISIBLE);
                tv_notice_phone.setText("手机号码格式不正确!");
//                ToastUtil.showShort(this, "手机号码格式不正确");
            }
        } else {
            tv_notice_phone.setVisibility(View.VISIBLE);
            tv_notice_phone.setText("请输入手机号码!");
//            ToastUtil.showShort(this, "请输入手机号码");
        }
    }

    @OnClick(R.id.bt_regist2)
    public void registUser() {
        phoneNum = et_phone.getText().toString();
        registCode = et_code.getText().toString();
        psd = et_psd.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)) {
            tv_notice_phone.setVisibility(View.INVISIBLE);
            if (StringUtils.isMobileNumber(phoneNum)) {
                tv_notice_phone.setVisibility(View.INVISIBLE);
                if (!TextUtils.isEmpty(registCode) && !TextUtils.isEmpty(smsCode)) {
                    tv_notice_code.setVisibility(View.INVISIBLE);
                    if (registCode.equals(smsCode)) {
                        tv_notice_code.setVisibility(View.INVISIBLE);
                        if (!TextUtils.isEmpty(psd)) {
                            tv_notice_psd.setVisibility(View.INVISIBLE);
                            if (psd.matches("(?=.*[0-9])(?=.*[a-z]).{6,16}")) {
                                tv_notice_psd.setVisibility(View.INVISIBLE);
                                Api.register(phoneNum, DigestUtils.stringMD5(psd), this);
                            } else {
                                tv_notice_psd.setVisibility(View.VISIBLE);
                                tv_notice_psd.setText("请输入6~16位数字和字母的组合!");
//                                ToastUtil.showShort(this, "请输入6~16数字和字母的组合");
                            }
                        } else {
                            tv_notice_psd.setVisibility(View.VISIBLE);
                            tv_notice_psd.setText("请输入密码!");
//                            ToastUtil.showShort(this, "请输入密码");
                        }
                    } else {
                        tv_notice_code.setVisibility(View.VISIBLE);
                        tv_notice_code.setText("验证码不正确!");
//                        ToastUtil.showShort(RegistActivity.this, "验证码不正确");
                    }

                } else {
                    tv_notice_code.setVisibility(View.VISIBLE);
                    tv_notice_code.setText("请输入验证码!");
//                    ToastUtil.showShort(this, "请输入验证码");
                }
            } else {
                tv_notice_phone.setVisibility(View.VISIBLE);
                tv_notice_phone.setText("手机号码格式不正确!");
//                ToastUtil.showShort(this, "手机号码格式不正确");
            }
        } else {
            tv_notice_phone.setVisibility(View.VISIBLE);
            tv_notice_phone.setText("请输入手机号码!");
//            ToastUtil.showShort(this, "请输入手机号码");

        }
    }


    @Override
    public void onError(Request request, Action action, Exception e) {
        switch (action) {
            case SMSCODE:
                ToastUtil.showShort(RegistActivity.this, "获取验证码失败");
                break;
            case REGISTER:
                ToastUtil.showShort(RegistActivity.this, "注册失败，服务器出问题了");
                break;
        }
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null) {
            switch (action) {
                case REGISTER:
                    CommonBean registBean = (CommonBean) object;
                    if (registBean.getCode().equals("000000")) {
                        ToastUtil.showShort(this, "注册成功");
                        readyGo(LoginActivity.class);
                        return;
                    }
                    if (registBean.getCode().equals("APP_C010002")) {
                        tv_notice_psd.setVisibility(View.VISIBLE);
                        tv_notice_psd.setText("此号码已经注册请直接登录");
//                        ToastUtil.showShort(this, "此号码已经注册请直接登录");
                        return;
                    }
                    break;
                case SMSCODE:
                    ProvingCodeBean smsBean = (ProvingCodeBean) object;
                    if (smsBean.getCode().equals("000000")) {
                        smsCode = smsBean.sendResponse.getSmsCode();
                        LogUtils.e("smsCode======" + smsCode);
                    }
                        break;
                case THIRD_PART_LOGIN:
                    UserBeanFromService thirdBean= (UserBeanFromService) object;
                    if(thirdBean.getCode().equals(Constant.RESULT_SUCESS)){

                    }

            }
        } else {
            ToastUtil.showShort(this, "服务器出问题了!");

        }

    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            submit.setText("获取验证码");
            submit.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            if (submit != null) {
                submit.setClickable(false);//防止重复点击
                submit.setText(millisUntilFinished / 1000 + "s");
            }
        }
    }
}
