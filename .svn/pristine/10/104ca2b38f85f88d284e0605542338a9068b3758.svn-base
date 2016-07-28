package com.diligroup.UserSet.activity;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.WheelView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报体重
 */
public class ReportWeight extends BaseAcitvity {
    @Bind(R.id.wheel_weight)
    WheelView wheelView;
    private static final String[] WEIGHT_DATA = new String[]{"45 Kg", "46 Kg", "47 Kg", "48 Kg", "49 Kg", "50 Kg", "51 Kg", "52 Kg", "53 Kg", "54 Kg", "55 Kg", "56 Kg", "57 Kg", "58 Kg", "59 Kg", "60 Kg", "61 Kg", "62 Kg", "63 Kg", "64 Kg", "65 Kg", "66 Kg", "67 Kg", "68 Kg", "69 Kg", "70 Kg", "71 Kg", "72 Kg", "73 Kg", "74 Kg", "75 Kg", "76 Kg", "77 Kg", "78 Kg", "79 Kg", "80 Kg", "81 Kg", "82 Kg", "83 Kg", "84 Kg", "85 Kg", "86 Kg", "87 Kg", "88 Kg",};
    String select_where;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_weight;
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

        wheelView.setOffset(3);
        wheelView.setItems(Arrays.asList(WEIGHT_DATA));
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                select_where = item;
//                Log.d("===========WEIGHT=========", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    @OnClick(R.id.bt_ok_weight)
    public void reportWeight() {
        ToastUtil.showShort(this, select_where);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("体重");
        title_infos.setText("您的体重?");
    }
}
