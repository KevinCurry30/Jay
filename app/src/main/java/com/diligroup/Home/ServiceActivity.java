package com.diligroup.Home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.techery.properratingbar.ProperRatingBar;
import okhttp3.Request;

/**
 * 服务评价页面
 */
public class ServiceActivity extends BaseActivity {

    @Bind(R.id.service_evaluation)
    ProperRatingBar serviceEvaluation;
    @Bind(R.id.input_service_evaluation)
    EditText inputServiceEvaluation;
    @Bind(R.id.service_commit)
    Button serviceCommit;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_service;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {

    }
    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("服务评价");
        isShowBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        //1:菜品评价 2:服务评价)
//        Api.dishVarietyEvaluate(Constant.USER_ID,"1","2","")
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
