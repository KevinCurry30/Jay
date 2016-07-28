package com.diligroup.Home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.diligroup.Home.adapter.CustomerAddLunchAdapter;
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
public class CustomFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.custome_input_search_dishes)
    EditText customeInputSearchDishes;
    @Bind(R.id.addlunche_custome_delete)
    ImageView addluncheCustomeDelete;
    @Bind(R.id.cuostomer_recyclerView)
    RecyclerView cuostomerRecyclerView;
//@Bind(R.id.clear_hository_search)
//    LinearLayout clear_hository_search;
    List<String> mList=new ArrayList<String>();
    @Override
    public int getLayoutXml() {
        return R.layout.fragment_customer;
    }

    @Override
    public void setViews() {
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        mList.add("0000000000");
        DividerItemDecoration dividerItemDecoration=  new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST);
        cuostomerRecyclerView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        dividerItemDecoration.setWidth(CommonUtils.px2dip(getActivity(),1));

        cuostomerRecyclerView.setHasFixedSize(true);//保持固定大小，提高性能
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        cuostomerRecyclerView.setLayoutManager(layoutManager);
        CustomerAddLunchAdapter adapter=new CustomerAddLunchAdapter(getActivity(),mList);
        cuostomerRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setListeners() {
        addluncheCustomeDelete.setOnClickListener(this);
//        clear_hository_search.setOnClickListener(this);
    }

    public Fragment newInstance() {
        CustomFragment fragmnent = new CustomFragment();
        return fragmnent;
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
        switch (v.getId()){
            case R.id.addlunche_custome_delete:
                break;
            case R.id.clear_hository_search:
                break;
            default:
                break;
        }
    }
}
