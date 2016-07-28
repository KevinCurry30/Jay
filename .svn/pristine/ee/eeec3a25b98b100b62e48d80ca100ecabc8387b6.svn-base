package com.diligroup.login;

import android.text.TextUtils;
import android.widget.EditText;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.ProvingCodeBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
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
public class ModifyPSDActivity extends BaseAcitvity implements RequestManager.ResultCallback{

    @Bind(R.id.et_code2)
    EditText et_code;
    @Bind(R.id.input_phone2)
    EditText et_phone;
    @Bind(R.id.et_newpsd2)
    EditText et_psd;

    String phoneNumber;
    String codeNumber;
    String password;
    String server_code;
    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("找回密码");
    }

    //获取验证码
    @OnClick(R.id.bt_getcode2)
    public void getCode() {
        phoneNumber = et_phone.getText().toString();

        if (!TextUtils.isEmpty(phoneNumber) && StringUtils.isMobileNumber(phoneNumber)) {
            Api.getCode(phoneNumber,"2",this);
        } else {
            ToastUtil.showShort(this, "请检查手机号码");
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
        if (!TextUtils.isEmpty(phoneNumber) && StringUtils.isMobileNumber(phoneNumber)) {
            if (!TextUtils.isEmpty(codeNumber)) {
                if (server_code.equals(codeNumber)){
                    if (!password.isEmpty()&&password.length()>=6) {
                            Api.modifyPsd(phoneNumber, DigestUtils.stringMD5(password),this);
                    } else{
                        ToastUtil.showShort(this, "请输入密码");}
                }else{
                    ToastUtil.showShort(this, "验证码不正确");

                }

            } else {
                ToastUtil.showShort(this, "请输入验证码");
            }
        } else {
            ToastUtil.showShort(this, "请检查手机号码");
        }
    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);

    }


    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object!=null){
            switch (action){
                case MODIFY:
                    CommonBean commonBean= (CommonBean) object;
                    if (commonBean.equals("000000")){
                        ToastUtil.showShort(ModifyPSDActivity.this,"修改密码成功");
                        readyGo(LoginActivity.class);
                    }else{
                        ToastUtil.showShort(ModifyPSDActivity.this,"修改密码失败");
                    }
                    break;
                case SMSCODE:
                    ProvingCodeBean  codeBean= (ProvingCodeBean) object;
                    if (codeBean.getCode().equals("000000"))
                        server_code=codeBean.sendResponse.getSmsCode();
                    if (!TextUtils.isEmpty(codeBean.sendResponse.getErrCode())){
                        ToastUtil.showShort(ModifyPSDActivity.this,"获取验证码失败");
                    }
                    break;
            }
        }else{
            ToastUtil.showShort(ModifyPSDActivity.this,"服务器出问题了");
        }

    }
}
