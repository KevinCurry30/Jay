package com.diligroup.UserSet.activity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.diligroup.R;
import com.diligroup.base.BaseAcitvity;
import com.diligroup.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 上报 口味
 */
public class ReportTaste extends BaseAcitvity {
    @Bind(R.id.lv_taste)
    ListView lv_taste;
//    List<String> tasteDatas;
//
    @Bind(R.id.bt_ok_taste)
    Button bt_ok;
//    String sInfo;
//    private RadioAdapter adapter;
    String [] tasteArray;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_select_taste;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("口味");
    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
        getTasteData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, tasteArray);
        lv_taste.setAdapter(adapter);
        lv_taste.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @OnClick(R.id.bt_ok_taste)
    public void ReportTaste() {

        long[] authorsId = getListSelectededItemIds(lv_taste);
        String name = "";
        String message;
        if (authorsId.length > 0) {
            // 用户至少选择了一ge
            for (int i = 0; i < authorsId.length; i++) {
                name += "," + tasteArray[(int) authorsId[i]];
            }
            // 
            message = name.substring(1);
        } else {
            message = "请至少选择一个";
        }
        Toast.makeText(ReportTaste.this, message, Toast.LENGTH_LONG)
                .show();
    }
    // 避免使用getCheckItemIds()方法
    public long[] getListSelectededItemIds(ListView listView) {

        long[] ids = new long[listView.getCount()];//getCount()即获取到ListView所包含的item总个数
        //定义用户选中Item的总个数
        int checkedTotal = 0;
        for (int i = 0; i < listView.getCount(); i++) {
            //如果这个Item是被选中的
            if (listView.isItemChecked(i)) {
                ids[checkedTotal++] = i;
            }
        }

        if (checkedTotal < listView.getCount()) {
            //定义选中的Item的ID数组
            final long[] selectedIds = new long[checkedTotal];
            //数组复制 ids
            System.arraycopy(ids, 0, selectedIds, 0, checkedTotal);
            return selectedIds;
        } else {
            //用户将所有的Item都选了
            return ids;
        }
    }
    private void getTasteData() {

       tasteArray=new String[]{"酸","甜","苦","辣","咸","其他"};
    }
}
