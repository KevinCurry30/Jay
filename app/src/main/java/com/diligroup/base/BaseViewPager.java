package com.diligroup.base;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.R;
import com.diligroup.view.stickyListView.StickyListHeadersListView;

import java.util.ArrayList;
import java.util.List;

public class BaseViewPager implements AdapterView.OnItemClickListener {
    protected View rootView;
    public Context mContext;
    public StickyListHeadersListView home_right_recycleView;//其子类共享这一个listView
    public List<BaseViewPager> mList;
    private ListView home_left_listView;

    private int currentClickItem;
    private LeftAdapter adapter;

    public BaseViewPager(Context mContext) {
        this.mContext = mContext;
        rootView = initView();
//        hintDialog=new HintDialog(mContext);
//        dialog=new CusProgress(mContext);
    }

    protected View initView() {
        rootView = View.inflate(mContext, R.layout.fragment_baseview, null);
        home_right_recycleView = (StickyListHeadersListView) rootView.findViewById(R.id.home_recycleView);
        home_left_listView = (ListView) rootView.findViewById(R.id.home_left_listView);
        home_left_listView.setOnItemClickListener(this);

//        List<String> mmList = new ArrayList<String>();
//        mmList.add("11111111");
//        mmList.add("222222222");
//        mmList.add("333333333333");
//        adapter = new LeftAdapter(mContext, mmList, this);
//        home_left_listView.setAdapter(adapter);

        final List<String> mList = new ArrayList<>();
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
//        HomeRighAdapter righAdapter = new HomeRighAdapter(mContext, mList);
//        righAdapter.setListener(new MyItemClickListener() {
//
//            private Intent mIntent;
//
//            @Override
//            public void onItemClick(View view, int position) {
//                //跳转到详情页面
//                mIntent = new Intent(mContext, FoodDetailsActivity.class);
//                mIntent.putExtra("orderId", "");
//                mContext.startActivity(mIntent);
//                LogUtils.i("挑战到详情页面");
//            }
//        });
//        home_right_recycleView.setAdapter(righAdapter);
        return rootView;


    }

    /**
     * 获得当前页面的布局对象
     *
     * @return
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * 子类需要覆盖此方法, 实现自己的初始化数据逻辑
     */
    public void initDate() {

//        dialog.show();
    }

//    @Override
//    public View getView(int position, View convertView) {
//        if (convertView == null) {
//            convertView = View.inflate(mContext, R.layout.addlunch_child, null);
//        }
//        ImageView dishIcon = (ImageView) convertView.findViewById(R.id.dish_icon);
//        TextView dishName = (TextView) convertView.findViewById(R.id.dish_name);
//
//        if (position == currentClickItem) {
//            dishName.setTextColor(mContext.getResources().getColor(R.color.green));
//            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
//        } else {
//            dishName.setTextColor(mContext.getResources().getColor(R.color.black1));
//            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.common_backgroud));
//        }
//        return convertView;
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentClickItem = position;
        adapter.notifyDataSetChanged();
    }
}
