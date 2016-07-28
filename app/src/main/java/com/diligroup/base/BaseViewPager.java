package com.diligroup.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.Home.FoodDetailsActivity;
import com.diligroup.Home.adapter.HomeRighAdapter;
import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.R;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BaseViewPager implements LeftAdapter.OnGetView, AdapterView.OnItemClickListener {
    protected View rootView;
    public Context mContext;
    public RecyclerView home_right_recycleView;//其子类共享这一个listView
    public List<BaseViewPager> mList;

    protected RelativeLayout emptyView;
    protected TextView no_content;
    protected boolean isPull = true;
    protected LinearLayout ll_rootView;
    private ListView home_left_listView;

    private int currentClickItem;
    private LeftAdapter adapter;
    private TextView parent_textview;//餐别：成品分类

    //    protected HintDialog hintDialog;
//    protected CusProgress dialog;
    public BaseViewPager(Context mContext) {
        this.mContext = mContext;
        rootView = initView();
//        hintDialog=new HintDialog(mContext);
//        dialog=new CusProgress(mContext);
    }

    protected View initView() {
        rootView = View.inflate(mContext, R.layout.fragment_baseview, null);
        home_right_recycleView = (RecyclerView) rootView.findViewById(R.id.home_recycleView);
        home_left_listView = (ListView) rootView.findViewById(R.id.home_left_listView);
        home_left_listView.setOnItemClickListener(this);

        List<String> mmList = new ArrayList<String>();
        mmList.add("11111111");
        mmList.add("222222222");
        mmList.add("333333333333");
        adapter = new LeftAdapter(mContext, mmList, this);
        home_left_listView.setAdapter(adapter);
        View headerView=View.inflate(mContext,R.layout.addlunch_parent,null);
        parent_textview = (TextView) headerView.findViewById(R.id.parent_textview);
        home_left_listView.addHeaderView(headerView);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mContext, DividerItemDecoration.VERTICAL_LIST);
        home_right_recycleView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        dividerItemDecoration.setWidth(CommonUtils.px2dip(mContext, 1));
        home_right_recycleView.setHasFixedSize(true);//保持固定大小，提高性能
        RecyclerView.LayoutManager layoutManager_1 = new LinearLayoutManager(mContext);
        home_right_recycleView.setLayoutManager(layoutManager_1);

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
        HomeRighAdapter righAdapter = new HomeRighAdapter(mContext, mList);
        righAdapter.setListener(new MyItemClickListener() {

            private Intent mIntent;

            @Override
            public void onItemClick(View view, int position) {
                //跳转到详情页面
                mIntent = new Intent(mContext, FoodDetailsActivity.class);
                mIntent.putExtra("orderId", "");
                mContext.startActivity(mIntent);
                LogUtils.i("挑战到详情页面");
            }
        });
        home_right_recycleView.setAdapter(righAdapter);
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

    @Override
    public View getView(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.addlunch_child, null);
        }
        ImageView dishIcon = (ImageView) convertView.findViewById(R.id.dish_icon);
        TextView dishName = (TextView) convertView.findViewById(R.id.dish_name);

        if (position == currentClickItem) {
            dishName.setTextColor(mContext.getResources().getColor(R.color.green));
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            dishName.setTextColor(mContext.getResources().getColor(R.color.black1));
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.common_backgroud));
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentClickItem = position;
        adapter.notifyDataSetChanged();
    }
}
