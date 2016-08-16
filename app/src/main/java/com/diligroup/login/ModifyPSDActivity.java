package com.diligroup.login;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.ProvingCodeBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.DigestUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.StringUtils;
import com.diligroup.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;


/**
 * 找回密码
 * 忘记密码
 * 修改密码
 */
public class ModifyPSDActivity extends BaseActivity {

    @Bind(R.id.et_code2)
    EditText et_code;
    @Bind(R.id.input_phone2)
    EditText et_phone;
    @Bind(R.id.et_newpsd2)
    EditText et_psd;
    @Bind(R.id.bt_getcode2)
    Button bt_getCode;
    String phoneNumber;
    String codeNumber;
    String password;
    String server_code;
    private TimeCount time;
    @Bind(R.id.tv_notice_a)
    TextView tv_notice_phone;
    @Bind(R.id.tv_notice_b)
    TextView tv_notice_code;
    @Bind(R.id.tv_notice_c)
    TextView tv_notice_psd;
    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("忘记密码");
    }

    //获取验证码
    @OnClick(R.id.bt_getcode2)
    public void getCode() {
        phoneNumber = et_phone.getText().toString();

        if (!TextUtils.isEmpty(phoneNumber)) {
            tv_notice_phone.setVisibility(View.INVISIBLE);
            if (StringUtils.isMobileNumber(phoneNumber)) {
                tv_notice_phone.setVisibility(View.INVISIBLE);
                Api.getCode(phoneNumber, "2", this);
                time.start();// 开始计时
            } else {
                tv_notice_phone.setVisibility(View.VISIBLE);
                tv_notice_phone.setText("手机号码格式不正确");

//                ToastUtil.showShort(this, "手机号码格式不正确");
            }
        } else {
            tv_notice_phone.setVisibility(View.VISIBLE);
            tv_notice_phone.setText("请输入手机号码");
//            ToastUtil.showShort(this, "请输入手机号码");
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_find_psd;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @OnClick(R.id.bt_ok2)
    public void reportModifyPsd() {
        phoneNumber = et_phone.getText().toString();
        codeNumber = et_code.getText().toString();
        password = et_psd.getText().toString();
        if (!TextUtils.isEmpty(phoneNumber)) {
            tv_notice_phone.setVisibility(View.INVISIBLE);
            if (StringUtils.isMobileNumber(phoneNumber)) {
                tv_notice_phone.setVisibility(View.INVISIBLE);
                if (!TextUtils.isEmpty(codeNumber)) {
                    tv_notice_code.setVisibility(View.INVISIBLE);

                    if (!TextUtils.isEmpty(server_code)&&server_code.equals(codeNumber)) {
                        tv_notice_code.setVisibility(View.INVISIBLE);
                        if (!password.isEmpty()) {
                            tv_notice_psd.setVisibility(View.INVISIBLE);

                            if (password.matches("(?=.*[0-9])(?=.*[a-z]).{6,16}")){
                                tv_notice_psd.setVisibility(View.INVISIBLE);
                                Api.modifyPsd(phoneNumber, DigestUtils.stringMD5(password), this);
                            }else{
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
//                        ToastUtil.showShort(this, "验证码不正确");
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
    protected void initViewAndData() {
        isShowBack(true);
        time = new TimeCount(60000, 1000);
    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null) {
            switch (action) {
                case MODIFY:
                    CommonBean commonBean = (CommonBean) object;
                    if (commonBean.getCode().equals("000000")) {
                        ToastUtil.showShort(ModifyPSDActivity.this, "修改密码成功!");
                        readyGo(LoginActivity.class);
                    }
                    if (commonBean.getCode().equals("APP_C010004")){
                        ToastUtil.showShort(ModifyPSDActivity.this, "手机号未注册!");
                    }

                    else {
                        ToastUtil.showShort(ModifyPSDActivity.this, "修改密码失败!");
                    }
                    break;
                case SMSCODE:
                    ProvingCodeBean codeBean = (ProvingCodeBean) object;
                    if (codeBean.getCode().equals("000000"))
                        server_code = codeBean.sendResponse.getSmsCode();
                    if (!TextUtils.isEmpty(codeBean.sendResponse.getErrCode())) {
                        ToastUtil.showShort(ModifyPSDActivity.this, "获取验证码失败!");
                    }
                    break;
            }
        } else {
            ToastUtil.showShort(ModifyPSDActivity.this, "服务器出问题了!");
        }

    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            bt_getCode.setText("获取验证码");
            bt_getCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            if (bt_getCode != null) {
                bt_getCode.setClickable(false);//防止重复点击
                bt_getCode.setText(millisUntilFinished / 1000 + "s");
            }

        }
    }
}
