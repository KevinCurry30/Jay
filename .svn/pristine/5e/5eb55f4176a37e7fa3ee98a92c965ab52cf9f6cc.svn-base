package com.diligroup.UserSet.activity;

import android.util.Log;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.WheelView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报身高
 */
public class ReportHeight extends BaseAcitvity {
    @Bind(R.id.wv_height)
    WheelView wheelView;
    private static final String[] HEIGHT_DATA = new String[]{"150 CM", "151 CM", "152 CM", "153 CM", "154 CM", "155 CM", "156 CM", "157 CM", "158 CM", "159 CM", "160 CM", "161 CM", "162 CM", "163 CM", "164 CM", "165 CM", "166 CM", "167 CM", "168 CM", "169 CM", "170 CM", "171 CM", "172 CM", "173 CM", "174 CM", "175 CM", "176 CM", "177 CM", "178 CM", "179 CM", "180 CM", "181 CM", "182 CM", "183 CM", "184 CM", "185 CM", "186 CM",};
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
        wheelView.setOffset(1);
        wheelView.setItems(Arrays.asList(HEIGHT_DATA));
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
        ToastUtil.showShort(this,selectHeight);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("身高");
        title_infos.setText("您的身高?");
    }
}
