package com.diligroup.shop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 用户手动 选择 门店地址
 * Created by Kevin on 2016/7/11.
 */
public class SelectShopByUser extends BaseActivity {
    @Bind(R.id.comm_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_info)
    TextView title_infos;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.select_shop;
    }
    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {
    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        tv_title.setText("选择门店");
        title_infos.setText("选择门店");
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @OnClick(R.id.bt_report_shop)
    public void reportShop(){

    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
