package com.diligroup.UserSet.activity;

import android.widget.CheckBox;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报特殊人群
 * Created by Kevin on 2016/6/20.
 */
public class ReportSpecial extends BaseAcitvity {
    @Bind(R.id.cb_buru_stage)
    CheckBox cb_buru;
    @Bind(R.id.cb_early_stage)
    CheckBox cb_early;
    @Bind(R.id.cb_middle_stage)
    CheckBox cb_middle;
    @Bind(R.id.cb_later_stage)
    CheckBox cb_later;
//    @Bind(R.id.lv_special)
//    ListView lv_special;
//    String[] specialDatas;

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        tv_title.setText("特殊人群");
        isShowBack(true);

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_special;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }



    @Override
    protected void onNetworkDisConnected() {

    }


    @Override
    protected void initViewAndData() {

//        specialDatas = new String[]{"孕期", "哺乳期", "术后", "高温", "低温", "高龄"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_multiple_choice, specialDatas);
//        lv_special.setAdapter(adapter);
//        lv_special.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @OnClick(R.id.bt_report_special)
    public void reportSpecial() {
//        long[] authorsId = getListSelectededItemIds(lv_special);
//        String name = "";
//        String message;
//        if (authorsId.length > 0) {
//            // 用户至少选择了一ge
//            for (int i = 0; i < authorsId.length; i++) {
//                name += "," + specialDatas[(int) authorsId[i]];
//            }
//            //
//            message = name.substring(1);
//        } else {
//            message = "请至少选择一个";
//        }
//        Toast.makeText(ReportSpecial.this, message, Toast.LENGTH_LONG)
//                .show();
//    }
//
//    private long[] getListSelectededItemIds(ListView listView) {
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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
