package com.diligroup.login;


import android.text.TextUtils;
import android.widget.EditText;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.ProvingCodeBean;
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
    String smsCode;
    String phoneNum;
    String registCode;
    String psd;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_regist;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("注册");

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
    }

    //获取注册验证码
    @OnClick(R.id.bt_getcode)
    public void getRegistCode() {
        phoneNum = et_phone.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)&& StringUtils.isMobileNumber(phoneNum)){
            Api.getCode(phoneNum,"1",this);
        }else{
            ToastUtil.showShort(this,"请检查手机号码");
        }
    }

    @OnClick(R.id.bt_regist2)
    public void registUser() {
        phoneNum = et_phone.getText().toString();
        registCode = et_code.getText().toString();
        psd = et_psd.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)&& StringUtils.isMobileNumber(phoneNum)) {
            if (!TextUtils.isEmpty(registCode)&&!TextUtils.isEmpty(smsCode)) {
                if (registCode.equals(smsCode)){
                    if (!TextUtils.isEmpty(psd)) {
                        LogUtils.e("passwork=========="+ DigestUtils.stringMD5(psd));
                        Api.register(phoneNum, DigestUtils.stringMD5(psd), this);
                    } else {
                        ToastUtil.showShort(this, "请输入密码");
                    }
                }else{
                    ToastUtil.showShort(RegistActivity.this,"验证码不正确");
                }

            } else {
                ToastUtil.showShort(this, "请输入验证码");
            }


        } else {
            ToastUtil.showShort(this, "请输入正确的手机号码");

        }
    }


    @Override
    public void onError(Request request, Action action, Exception e) {
                    switch (action){
                        case SMSCODE:
                            ToastUtil.showShort(RegistActivity.this,"获取验证码失败");
                            break;
                        case REGISTER:
                            ToastUtil.showShort(RegistActivity.this,"注册失败，服务器出问题了");
                            break;
                    }
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object!=null){
            switch (action){
                case REGISTER:
                    CommonBean registBean = (CommonBean) object;
                    if (registBean.getCode().equals("000000")) {
                        ToastUtil.showShort(this, "注册成功");
                        readyGo(LoginActivity.class);
                        return;
                    }
                    if (registBean.getCode().equals("APP_C010002")) {
                        ToastUtil.showShort(this, "此号码已经注册请直接登录");
                        return;
                    }
                    break;
                case SMSCODE:
                    ProvingCodeBean smsBean= (ProvingCodeBean) object;
                    if (smsBean.getCode().equals("000000")){
                        smsCode=smsBean.sendResponse.getSmsCode();
                        LogUtils.e("smsCode======"+smsCode);
                        break;
                    }

            }
        }else{
            ToastUtil.showShort(this, "服务器出问题了");

        }

    }
}
