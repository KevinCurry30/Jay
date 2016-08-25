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
import com.diligroup.net.RequestManager;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.utils.UserManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.techery.properratingbar.ProperRatingBar;
import okhttp3.Request;

/**
 * 菜品和性价比评价页面
 */
public class FeedbackActivity extends BaseActivity implements View.OnClickListener, RequestManager.ResultCallback {


    @Bind(R.id.dishes_name)
    TextView dishesName;//菜品名称
    @Bind(R.id.taste_evaluation)
    ProperRatingBar tasteEvaluation;//口味评价
    @Bind(R.id.cost_performance_evaluation)
    ProperRatingBar costPerformanceEvaluation;//性价比评价
    @Bind(R.id.input_dishes_evaluation)
    EditText inputDishesEvaluation;//输入你的产品印象
    @Bind(R.id.feedback_commit)
    Button feedbackCommit;//提交评价
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    private String dishesCode;
    private String mealType;
    private String date;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }
    public void setTitle() {
        tv_title.setText("菜品评价");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void initViewAndData() {
        dishesCode = getIntent().getStringExtra("foodCode");
        mealType = getIntent().getStringExtra("mealType");
        date = getIntent().getStringExtra("date");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        feedbackCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String dishName=dishesName.getText().toString().trim();
      String content= inputDishesEvaluation.getText().toString().trim();
        int tast=tasteEvaluation.getRating();
        int cost=costPerformanceEvaluation.getRating();
        switch (view.getId()){
            case R.id.feedback_commit:
                if(TextUtils.isEmpty(inputDishesEvaluation.getText().toString().trim())){
                    ToastUtil.showLong(this,"亲！您还没有输入评价内容呢");
                    return;
                }
                Api.dishVarietyEvaluate(UserManager.getInstance().getUserId(),Constant.storeId,"1",dishesCode,date,mealType,content,tast+"",cost+"","",this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if(action==Action.DISEVALUATE  && object!=null){
            CommonBean bean= (CommonBean) object;
            if(bean.getCode().equals(Constant.RESULT_SUCESS)){
                LogUtils.i("菜品评价接口调用成功");
            }
        }
    }
}
