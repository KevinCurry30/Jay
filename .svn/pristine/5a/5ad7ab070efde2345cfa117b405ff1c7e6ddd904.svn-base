package com.diligroup.Home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.techery.properratingbar.ProperRatingBar;

/**
 * 服务评价页面
 */
public class ServiceActivity extends BaseAcitvity {

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
    public void setTitle() {
        super.setTitle();
        setTitle("服务评价");
        isShowBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
