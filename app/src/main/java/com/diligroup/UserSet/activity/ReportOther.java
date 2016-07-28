package com.diligroup.UserSet.activity;

import com.diligroup.R;
import com.diligroup.base.BaseActivity;
import com.diligroup.net.Action;
import com.diligroup.utils.NetUtils;

import okhttp3.Request;

public class ReportOther extends BaseActivity {

//    @Bind(R.id.comm_title)
//    TextView tv_ttle;
//    @Bind(R.id.lv_other)
//    ListView lv_others;
//    String[] otherDatas;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_other;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }
    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("其他");
    }
    @Override
    protected void initViewAndData() {
        isShowBack(true);

//        otherDatas = new String[]{"减脂", "增肌"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_multiple_choice, otherDatas);
//        lv_others.setAdapter(adapter);
//        lv_others.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

//    @OnClick(R.id.bt_ok_other)
    public  void reportOther() {
//        long[] authorsId = getListSelectededItemIds(lv_others);
//        String name = "";
//        String message;
//        if (authorsId.length > 0) {
//            // 用户至少选择了一ge
//            for (int i = 0; i < authorsId.length; i++) {
//                name += "," + otherDatas[(int) authorsId[i]];
//            }
//            //
//            message = name.substring(1);
//        } else {
//            message = "请至少选择一个";
//        }
//        Toast.makeText(ReportOther.this, message, Toast.LENGTH_LONG)
//                .show();
//    }
//
//    // 避免使用getCheckItemIds()方法
//    public long[] getListSelectededItemIds(ListView listView) {
//
//        long[] ids = new long[listView.getCount()];//getCount()即获取到ListView所包含的item总个数
//        //定义用户选中Item的总个数
//        int checkedTotal = 0;
//        for (int i = 0; i < listView.getCount(); i++) {
//            //如果这个Item是被选中的
//            if (listView.isItemChecked(i)) {
//                ids[checkedTotal++] = i;
//            }
//        }
//
//        if (checkedTotal < listView.getCount()) {
//            //定义选中的Item的ID数组
//            final long[] selectedIds = new long[checkedTotal];
//            //数组复制 ids
//            System.arraycopy(ids, 0, selectedIds, 0, checkedTotal);
//            return selectedIds;
//        } else {
//            //用户将所有的Item都选了
//            return ids;
//        }
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {

    }
}
