package com.diligroup.UserSet.activity;

import android.util.Log;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.WheelView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报身高
 */
public class ReportHeight extends BaseActivity {
    @Bind(R.id.wv_height)
    WheelView wheelView;
    String selectHeight;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_height;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        if (UserInfoBean.getInstance().getSex()!=null&&UserInfoBean.getInstance().getSex()==0){
            wheelView.setSeletion(13);
            selectHeight="163";
        }
        if (UserInfoBean.getInstance().getSex()!=null&&UserInfoBean.getInstance().getSex()==1){
            wheelView.setSeletion(25);
            selectHeight="175";
        }
        wheelView.setItems(Arrays.asList(getResources().getStringArray(R.array.height)));

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                selectHeight=item;
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    @OnClick(R.id.bt_ok_height)
    public void reprotHeight(){
        UserInfoBean.getInstance().setHeight(selectHeight);
        readyGo(ReportWeight.class);
//        ToastUtil.showShort(this,selectHeight);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("基础信息");
        title_infos.setText("您的身高?");
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
