package com.diligroup.UserSet.activity;

import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.utils.BirthdayUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.WheelView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报 生日
 */
public class ReportBirthday extends BaseActivity {

//    @Bind(R.id.data_select)
//    DatePicker data_select;
    @Bind(R.id.wv_height)
    WheelView wheelView;
    @Bind(R.id.wv_height2)
    WheelView wheelView2;
    @Bind(R.id.wv_height3)
    WheelView wheelView3;
//    int currentYear;

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
    protected void onNetworkDisConnected() {}

    String year;
    String month;
    String day;
    @Override
    protected void initViewAndData() {
      WindowManager  wm = this.getWindowManager();

//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        final int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);

        ViewGroup.LayoutParams  params =wheelView.getLayoutParams();
        params.height=wheelView.getHeight();
        params.width=wm.getDefaultDisplay().getWidth()/3;
        wheelView.setLayoutParams(params);
        wheelView2.setLayoutParams(params);
        wheelView3.setLayoutParams(params);
        wheelView.setOffset(3);
        wheelView2.setOffset(3);
        wheelView3.setOffset(3);

        wheelView.setItems(BirthdayUtils.getYears());

        wheelView2.setItems(BirthdayUtils.getMonth());
        wheelView3.setItems(BirthdayUtils.get31Day());
//        wheelView3.setItems(BirthdayUtils.get31Day());
//        currentYear = year;
//        data_select.updateDate(year, month, day);

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                year=item;
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });

        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {

                if (item.equals("1月")||item.equals("3月")||item.equals("5月")||item.equals("7月")||item.equals("8月")||item.equals("10月")||item.equals("12月")){
//                    wheelView3.setItems(BirthdayUtils.get31Day());
//                    wheelView3.notify();
                }
                if (item.equals("4月")||item.equals("6月")||item.equals("9月")||item.equals("11月")){
//                    wheelView3.setItems(BirthdayUtils.get30Day());
//                    wheelView3.notify();

                }
                if (item.equals("2月")){
//                    wheelView3.setItems(BirthdayUtils.get29Day());
//                    wheelView3.notify();

                }
                month=item;
//                wheelView3.notifyAll();
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                day=item;
                Log.d("====================", "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    @OnClick(R.id.bt_getTime)
    public void getBirthday() {

//        ToastUtil.showShort(ReportBirthday.this, "您选择的日期是：" + data_select.getYear() + "年" + (data_select.getMonth() + 1) + "月" + data_select.getDayOfMonth() + "日。");
//        int old = currentYear - data_select.getYear();
//        String brithday = data_select.getYear() + "-" + (data_select.getMonth() + 1) + "-" + data_select.getDayOfMonth();
//        ToastUtil.showShort(ReportBirthday.this,"你当前"+old+"岁");
//        UserInfoBean.getInstance().setBirthday(brithday);
        readyGo(ReportWork.class);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("基础信息");
        title_infos.setText("您的生日？");
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
