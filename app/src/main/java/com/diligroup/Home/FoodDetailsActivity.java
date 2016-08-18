package com.diligroup.Home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.AppManager;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.GetFoodDetailsBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.NetUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;


/**
 * Created by Administrator on 2016/7/22
 */
public class FoodDetailsActivity extends AppCompatActivity implements RequestManager.ResultCallback {
    @Bind(R.id.iv_delish_food)
    ImageView iv_food;
    @Bind(R.id.tv_food_name)
    TextView tv_foodName;
    @Bind(R.id.tv_zhuliao)
    TextView tv_zhuliao;
    @Bind(R.id.tv_fuliao)
    TextView tv_fuliao;
    @Bind(R.id.tv_tiaoliao)
    TextView tv_tiaoliao;
    @Bind(R.id.tv_shiyongyou)
    TextView tv_shiyongyou;
    @Bind(R.id.tv_nengliang)
    TextView tv_nengliang;
    @Bind(R.id.tv_tshhw)
    TextView tv_tshhw;
    @Bind(R.id.tv_daibanzhis)
    TextView tv_daibanzhis;
    @Bind(R.id.tv_zhifangs)
    TextView tv_zhifangs;
//    @Bind(R.id.ib_goback)
//    ImageButton iv_back;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @OnClick(R.id.ib_goback)
    public void goBack() {
        this.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        String code=getIntent().getStringExtra("foodCode");
        Api.getFoodDetails(code, this);

    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (action == Action.GET_DETAILS && object != null) {
            GetFoodDetailsBean foodDetailsBean = (GetFoodDetailsBean) object;
            tv_foodName.setText(foodDetailsBean.getDishesName());
            tv_zhuliao.setText(foodDetailsBean.getMainFoodName());
            tv_fuliao.setText(foodDetailsBean.getAccessorysName());
            tv_tiaoliao.setText(foodDetailsBean.getSeasoningsName());
            tv_shiyongyou.setText(foodDetailsBean.getOilName());
            tv_nengliang.setText(String.valueOf(foodDetailsBean.getEnergyKC()));
            tv_tshhw.setText(String.valueOf(foodDetailsBean.getCarbohydrates()));
            tv_daibanzhis.setText(String.valueOf(foodDetailsBean.getProtein()));
            tv_zhifangs.setText(String.valueOf(foodDetailsBean.getFat()));

        }
    }
}
