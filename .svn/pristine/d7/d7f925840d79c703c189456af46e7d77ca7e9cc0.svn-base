package com.diligroup.UserSet.activity;

import android.widget.Button;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.CityPicker;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报住址
 */
public class ReportWhere extends BaseAcitvity {
    @Bind(R.id.select_where)
    CityPicker select_where;

    @Bind(R.id.bt_ok_where)
    Button bt_ok;
    String select_city;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_where;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }



    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("地址");
    }

    @Override
    protected void onNetworkDisConnected() {

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_select_where);
//        ButterKnife.bind(this);
//        tv_title.setText("籍贯");
//        select_where.setOnSelectingListener(new CityPicker.OnSelectingListener() {
//            @Override
//            public void selected(boolean selected) {
//            if (selected){
//                select_city= select_where.getCity_string();
//            }
//            }
//        });
//    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        select_where.setOnSelectingListener(new CityPicker.OnSelectingListener() {
            @Override
            public void selected(boolean selected) {
                if (selected){
                    select_city= select_where.getCity_string();
                }
            }
        });
    }

    @OnClick(R.id.bt_ok_where)
    public void reportWhere(){
        ToastUtil.showShort(this,select_city);
    }
}
