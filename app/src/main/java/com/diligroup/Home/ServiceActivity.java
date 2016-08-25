package com.diligroup.Home;

import android.os.Bundle;
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
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.utils.UserManager;

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
    private String mealType;
    private String date;
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;


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
        mealType = getIntent().getStringExtra("mealType");
        date = getIntent().getStringExtra("date");

        serviceCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(inputServiceEvaluation.getText().toString())){
                    ToastUtil.showLong(ServiceActivity.this,"亲：评价内容不能为空！");
                    return;
                }
                //1:菜品评价 2:服务评价)
                 String content=inputServiceEvaluation.getText().toString();
                int serviceStar=serviceEvaluation.getRating();
                Api.dishVarietyEvaluate(UserManager.getInstance().getUserId(),Constant.storeId,"2","",date,mealType,content,"","",serviceStar+"",ServiceActivity.this);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        tv_title.setText("服务评价");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if(action==Action.DISEVALUATE  && object!=null){
            CommonBean bean= (CommonBean) object;
            if(bean.getCode().equals(Constant.RESULT_SUCESS)){
                LogUtils.i("服务餐别评价接口调用成功");
            }
        }
    }
}
