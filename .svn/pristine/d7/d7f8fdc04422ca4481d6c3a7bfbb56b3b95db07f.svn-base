package com.diligroup.Home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.Home.adapter.RighAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseFragment;
import com.diligroup.utils.CommonUtils;
import com.diligroup.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/13.
 */
public class StoreSupplyFragment extends BaseFragment implements View.OnClickListener, LeftAdapter.OnGetView, AdapterView.OnItemClickListener {
    @Bind(R.id.left_listView)
    ListView leftListView;
    @Bind(R.id.right_recyclerView)
    RecyclerView rightRecyclerView;
    @Bind(R.id.input_search_dishes)
    EditText input_search_dishes;//
    @Bind(R.id.complete_add)
    LinearLayout complete_add;
    private int currentClickItem;
    private LeftAdapter adapter;

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_storesupply;
    }

    @Override
    public void setViews() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST);
//        leftListView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
//        dividerItemDecoration.setWidth(CommonUtils.px2dip(getActivity(), 1));
//        leftListView.setHasFixedSize(true);//保持固定大小，提高性能
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        leftListView.setLayoutManager(layoutManager);
        List<String> mmList=new ArrayList<String>();
        mmList.add("11111111");
        mmList.add("222222222");
        mmList.add("333333333333");
        adapter = new LeftAdapter(getActivity(), mmList,this);
        leftListView.setAdapter(adapter);
        leftListView.setOnItemClickListener(this);

        rightRecyclerView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        dividerItemDecoration.setWidth(CommonUtils.px2dip(getActivity(), 1));
        rightRecyclerView.setHasFixedSize(true);//保持固定大小，提高性能
        RecyclerView.LayoutManager layoutManager_1 = new LinearLayoutManager(getActivity());
        rightRecyclerView.setLayoutManager(layoutManager_1);

        List<String> mList = new ArrayList<>();
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        RighAdapter righAdapter = new RighAdapter(getActivity(), mList);
        rightRecyclerView.setAdapter(righAdapter);
    }

    @Override
    public void setListeners() {
        input_search_dishes.setOnClickListener(this);
//        leftListView.setOnChildClickListener(this);//子item点击事件
        complete_add.setOnClickListener(this);
    }

    public Fragment newInstance() {
        StoreSupplyFragment fragment = new StoreSupplyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_search_dishes:
                input_search_dishes.setFocusable(true);
                break;
            case R.id.complete_add:
                break;
        }
    }

    @Override
    public View getView(int position, View convertView) {
        if(convertView==null){
            convertView=View.inflate(getActivity(), R.layout.addlunch_child, null);
        }
        ImageView dishIcon= (ImageView) convertView.findViewById(R.id.dish_icon);
        TextView dishName= (TextView) convertView.findViewById(R.id.dish_name);

        if(position==currentClickItem){
            dishName.setTextColor(getActivity().getResources().getColor(R.color.green));
            convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        }else{
            dishName.setTextColor(getActivity().getResources().getColor(R.color.black1));
            convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.common_backgroud));
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentClickItem = position;
        adapter.notifyDataSetChanged();
    }
}
