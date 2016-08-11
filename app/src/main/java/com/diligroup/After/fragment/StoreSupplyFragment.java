package com.diligroup.After.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diligroup.After.adapter.StoreSupplyRighAdapter;
import com.diligroup.After.adapter.StoreSuppyLeftAdapter;
import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseFragment;
import com.diligroup.base.Constant;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.CommonUtils;
import com.diligroup.view.DividerItemDecoration;
import com.diligroup.view.stickyListView.StickyListHeadersListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * Created by hjf on 2016/7/13.
 */
public class StoreSupplyFragment extends BaseFragment implements View.OnClickListener, StoreSuppyLeftAdapter.OnGetView, AdapterView.OnItemClickListener, RequestManager.ResultCallback, MyItemClickListener {
    @Bind(R.id.left_listView)
    RecyclerView leftListView;
    @Bind(R.id.right_recyclerView)
    StickyListHeadersListView rightRecyclerView;
    @Bind(R.id.input_search_dishes)
    EditText input_search_dishes;//
    @Bind(R.id.complete_add)
    LinearLayout complete_add;
    @Bind(R.id.store_supply_delete)
    ImageButton store_supply_delete;
    @Bind(R.id.storesupply_line)
    View storesupply_line;//切换搜索时候的间隔线
    @Bind(R.id.store_supply_normallayout)
    LinearLayout store_supply_normallayout;//默认列表布局
    @Bind(R.id.store_supply_searchlayout)
    LinearLayout store_supply_searchlayout;//搜索布局
    private int currentClickItem;
    private LeftAdapter adapter;
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> dishesTypeList;//左侧成品分类列表
    private StoreSupplyRighAdapter rightAdapter;
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList;//左侧成品分类列表

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_storesupply;
    }

    @Override
    public void setViews() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setWidth(CommonUtils.px2dip(getActivity(), 1));
        leftListView.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        leftListView.setHasFixedSize(true);//保持固定大小，提高性能
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        leftListView.setLayoutManager(layoutManager);

        Api.homeStoreSupplyList(Constant.cusId, "2016-08-10", "", "", "1", this);
    }

    @Override
    public void setListeners() {
        store_supply_delete.setOnClickListener(this);
        complete_add.setOnClickListener(this);
        input_search_dishes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //获取焦点然后展示搜索最近的菜品
                if (hasFocus) {
                    store_supply_normallayout.setVisibility(View.GONE);
                    store_supply_searchlayout.setVisibility(View.VISIBLE);
                    storesupply_line.setVisibility(View.GONE);
                    //展示最近搜索列表
                } else {
                    store_supply_normallayout.setVisibility(View.VISIBLE);
                    store_supply_searchlayout.setVisibility(View.GONE);
                    storesupply_line.setVisibility(View.VISIBLE);

                }
            }
        });
        input_search_dishes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //实时根据文字改变，搜索菜品库中菜品
                Api.storeSupplySearch(Constant.cusId, "2016-08-10", "芝麻烧饼", StoreSupplyFragment.this);
            }
        });
        rightRecyclerView.setOnStickyHeaderChangedListener(new StickyListHeadersListView.OnStickyHeaderChangedListener() {
            @Override
            public void onStickyHeaderChanged(StickyListHeadersListView l, View header, int itemPosition, long headerId) {
                for (int i = 0; i < dishesTypeList.size(); i++) {
                    if (Long.parseLong(dishesTypeList.get(i).getDishesTypeCode()) == headerId) {
                        leftListView.scrollToPosition(i);
                        adapter.selectPosion(i);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
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
            case R.id.store_supply_delete:
                input_search_dishes.setText("");
                break;
        }
    }

    @Override
    public View getView(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(getActivity(), R.layout.addlunch_child, null);
        }
        ImageView dishIcon = (ImageView) convertView.findViewById(R.id.dish_icon);
        TextView dishName = (TextView) convertView.findViewById(R.id.dish_name);

        if (position == currentClickItem) {
            dishName.setTextColor(getActivity().getResources().getColor(R.color.green));
            convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        } else {
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

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (null != object && action == Action.GET_HOME_LIST) {
            HomeStoreSupplyList listBean = (HomeStoreSupplyList) object;
            if (listBean.getCode().equals(Constant.RESULT_SUCESS)) {
//                templateDateList = listBean.getResultId().getTemplateDateList();
                dishesTypeList = listBean.getJson().getDishesSupplyList();
//                rightDishesList = listBean.getJson().getDishesSupplyList().get(1).getDishesSupplyDtlList();

                adapter = new LeftAdapter(getActivity(), dishesTypeList, this);
                leftListView.setAdapter(adapter);

                rightAdapter = new StoreSupplyRighAdapter(getActivity(), dishesTypeList);
                rightRecyclerView.setAdapter(rightAdapter);
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        rightRecyclerView.setSelectionFromTop(position, 0);
//        rightRecyclerView.setItemChecked(rightAdapter.getHeaderId(position));
    }
}
