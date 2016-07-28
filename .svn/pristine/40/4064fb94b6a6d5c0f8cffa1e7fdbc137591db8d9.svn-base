package com.diligroup.UserSet.activity;

import android.widget.DatePicker;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报 生日
 */
public class ReportBirthday extends BaseAcitvity {

    @Bind(R.id.data_select)
    DatePicker data_select;

    @Override
    protected void onStart() {
        super.onStart();
        isShowBack(true);

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_birthday;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }


    @Override
    protected void initViewAndData() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        data_select.updateDate(year, month, day);
    }

    @OnClick(R.id.bt_getTime)
    public void getTime() {

        ToastUtil.showShort(ReportBirthday.this, "您选择的日期是：" + data_select.getYear() + "年" + (data_select.getMonth() + 1) + "月" + data_select.getDayOfMonth() + "日。");

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("您的生日");
        title_infos.setText("您的生日？");
    }
}
