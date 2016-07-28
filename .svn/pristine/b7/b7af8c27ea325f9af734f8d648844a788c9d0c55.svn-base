package com.diligroup.UserSet.activity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报种族
 */
public class ReportMinZu extends BaseAcitvity {
    @Bind(R.id.lv_mingzu)
    ListView lv_mingzu;
    List<String> minzuList;
//    @Bind(R.id.comm_title)
//    TextView tv_ttle;
    String select_minzu;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_min_zu;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    //    @Bind(R.id.bt_ok_mz)
//    Button bt_ok;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//        ButterKnife.bind(this);
//    }


    @Override
    public void setTitle() {
        super.setTitle();
//        tv_title.setText("请选择民族");
    }

    @Override
    protected void initViewAndData() {


        getMinZuData();
        lv_mingzu.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, minzuList));
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice);
//        adapter.addAll(minzuList);
//        lv_mingzu.setAdapter(adapter);
        lv_mingzu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @OnClick(R.id.bt_ok_mz)
    public void ReportMz() {

        int pos = lv_mingzu.getCheckedItemPosition();

        if (ListView.INVALID_POSITION != pos) {
            ToastUtil.showShort(this, lv_mingzu.getItemAtPosition(pos).toString());
        }
    }

    private void getMinZuData() {
        minzuList = new ArrayList<>();
        minzuList.add("汉族");
        minzuList.add("壮族 ");
        minzuList.add("藏族 ");
        minzuList.add("裕固族");
        minzuList.add("彝族 ");
        minzuList.add("瑶族");
        minzuList.add("锡伯族");
        minzuList.add("乌孜别克族");
        minzuList.add("维吾尔族");
        minzuList.add("佤族");
        minzuList.add("土家族 ");
        minzuList.add("汉族");
        minzuList.add("土族 ");

    }


}
