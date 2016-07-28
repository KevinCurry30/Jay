package com.diligroup.UserSet.activity;

import android.view.KeyEvent;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;

/**
 * 上报性别
 */
public class ReportSex extends BaseAcitvity {
//    @Bind(R.id.rb_boy)
//    RadioButton rb_boy;
//    @Bind(R.id.rb_girl)
//    RadioButton rb_girl;
//    @Bind(R.id.rg_sex)
//    RadioGroup  rg_sex;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_sex;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("性别");
        title_infos.setText("您的性别?");
    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
//        rb_boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    ToastUtil.showShort(ReportSex.this,buttonView.getText().toString());
//                }
//            }
//        });
//        rb_girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    ToastUtil.showShort(ReportSex.this,buttonView.getText().toString());
//                }
//            }
//        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            readyGo(UserInfoActivity.class);
//            this.finish();
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//      readyGo(UserInfoActivity.class);
//        this.finish();
//
//    }
}
