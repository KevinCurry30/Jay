package com.diligroup.After.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
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

import com.diligroup.After.AddLunchActivity;
import com.diligroup.After.adapter.RighSearchAdapter;
import com.diligroup.After.adapter.StoreSupplyRighAdapter;
import com.diligroup.After.adapter.StoreSuppyLeftAdapter;
import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseFragment;
import com.diligroup.base.Constant;
import com.diligroup.bean.AddFoodCompleteBean;
import com.diligroup.bean.ButtonClickListener;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.bean.StoreSupplySearchBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.DateUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.RecordSQLiteOpenHelper;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.stickyListView.StickyListHeadersListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * Created by hjf on 2016/7/13.
 */
public class StoreSupplyFragment extends BaseFragment implements View.OnClickListener, StoreSuppyLeftAdapter.OnGetView, AdapterView.OnItemClickListener, RequestManager.ResultCallback, MyItemClickListener, ButtonClickListener {
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
    @Bind(R.id.near_search)
    TextView near_searchnear_search;//搜索文字布局
    //    @Bind(R.id.store_edit_parent)
//    LinearLayout store_edit_parent;//编辑框父布局
    @Bind(R.id.storesupply_search_list)
    RecyclerView storesupply_search_list;//门店供应搜索列表
    private int currentClickItem;
    private LeftAdapter adapter;
    RighSearchAdapter searchAdapter;//搜索适配器
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> dishesTypeList;//左侧成品分类列表
    private StoreSupplyRighAdapter rightAdapter;
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList;//左侧成品分类列表

    RecordSQLiteOpenHelper openHelper;
    List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> nearSearchList;//最近搜索列表
    private RecyclerView.LayoutManager searchLayyoutManager;
    private String whichDay;
    private String mealType;

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_storesupply;
    }

    @Override
    public void setViews() {
        CommonUtils.initRerecyelerView(getActivity(), leftListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        leftListView.setLayoutManager(layoutManager);

        searchLayyoutManager = new LinearLayoutManager(getActivity());
        Api.homeStoreSupplyList(Constant.cusId, whichDay, mealType, "", "1", this);
    }

    @Override
    public void setListeners() {
        store_supply_delete.setOnClickListener(this);
        complete_add.setOnClickListener(this);
        rightRecyclerView.setOnItemClickListener(this);
        input_search_dishes.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //获取焦点然后展示搜索最近的菜品
                if (hasFocus) {
                    openHelper = new RecordSQLiteOpenHelper(getActivity());
                    store_supply_normallayout.setVisibility(View.GONE);
                    store_supply_searchlayout.setVisibility(View.VISIBLE);
                    storesupply_line.setVisibility(View.GONE);
                    //展示最近搜索列表
                    nearSearchList = openHelper.getALllFoods(RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                    fillSearchDate(nearSearchList);
                } else {
////                    input_search_dishes.setFocusable(false);
//                    store_edit_parent.setFocusable(true);
//                    storesupply_search_list.setFocusableInTouchMode(true);
//                    input_search_dishes.setText("");
//                    store_supply_normallayout.setVisibility(View.VISIBLE);
//                    store_supply_searchlayout.setVisibility(View.GONE);
//                    storesupply_line.setVisibility(View.VISIBLE);
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
                if (!TextUtils.isEmpty(s.toString())) {
                    try {
                        Api.storeSupplySearch(Constant.cusId, whichDay, s.toString(), StoreSupplyFragment.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    nearSearchList = openHelper.getALllFoods(RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                    fillSearchDate(nearSearchList);
                    near_searchnear_search.setVisibility(View.VISIBLE);
                    if (nearSearchList.size() > 0) {
                        searchLayyoutManager.getChildAt(nearSearchList.size() - 1).setVisibility(View.VISIBLE);
                    }
                }
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

    public Fragment newInstance(String mealType, String whichDay) {
        Bundle args = new Bundle();
        args.putString("mealType", mealType);
        args.putString("whichDay", whichDay);
        StoreSupplyFragment fragment = new StoreSupplyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (getArguments() != null) {
            mealType = getArguments().getString("mealType");
            whichDay = DateUtils.dateFormatChanged_2(DateUtils.dateFormatChagee(getArguments().getString("whichDay")));
        }
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
                List<AddFoodCompleteBean> addMealList= ((AddLunchActivity)getActivity()).getAddMealList();
                String json= new Gson().toJson(addMealList);
                Api.addFoodComplete(Constant.userId+"",mealType,json,this);
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
        } else if (null != object && action == Action.STORESUPPLY_SEARCH) {
            StoreSupplySearchBean listBean = (StoreSupplySearchBean) object;
            if (listBean.getCode().equals(Constant.RESULT_SUCESS)) {
                CommonUtils.initRerecyelerView(getActivity(), storesupply_search_list);

                fillSearchDate(listBean.getJson().getDishesSupplyDtlList());
                //隐藏最近搜索和清空文字
                near_searchnear_search.setVisibility(View.GONE);
                if (searchLayyoutManager.getChildAt(nearSearchList.size()) != null) {
                    searchLayyoutManager.getChildAt(nearSearchList.size()).setVisibility(View.GONE);
                }

                if (listBean.getJson().getDishesSupplyDtlList().size() == 1) {
                    HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean foodBean = openHelper.selectFood(listBean.getJson().getDishesSupplyDtlList().get(0).getDishesName(), RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                    if (foodBean != null) {
                        openHelper.deleteItem(foodBean, listBean.getJson().getDishesSupplyDtlList().get(0).getDishesName(), RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                        openHelper.addFood(listBean.getJson().getDishesSupplyDtlList().get(0), RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                    } else {
                        openHelper.addFood(listBean.getJson().getDishesSupplyDtlList().get(0), RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                    }
                }
            } else {
                LogUtils.i("请求失败");
            }
        }else if(object!=null && action==Action.ADD_FOOD_COMPLETE){
            CommonBean commonBean= (CommonBean) object;
            if(commonBean.getCode().equals(Constant.RESULT_SUCESS)){
                //添加菜品成功，回传页面
                getActivity().setResult(10);
                getActivity().finish();
            }else{
                ToastUtil.showLong(getActivity(),"添加菜品失败");
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {//左侧点击右侧联动
        int count = 0;
//        rightRecyclerView.setSelection(0);
        for (int i = 0; i < dishesTypeList.size(); i++) {
            if (position == 0) {
                rightRecyclerView.setSelection(0);
                return;
            } else if (i < position && dishesTypeList.get(i).getDishesSupplyDtlList() == null) {
                continue;
            } else if (i < position && dishesTypeList.get(i).getDishesSupplyDtlList() != null) {
                count += dishesTypeList.get(i).getDishesSupplyDtlList().size();
            } else {
                break;
            }
        }
        rightRecyclerView.setSelection(count);
    }

    private void fillSearchDate(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList) {
        CommonUtils.initRerecyelerView(getActivity(), storesupply_search_list);

        storesupply_search_list.setLayoutManager(searchLayyoutManager);
        if (searchAdapter == null) {
            searchAdapter = new RighSearchAdapter(getActivity(), mList, this,true);
            storesupply_search_list.setAdapter(searchAdapter);
        } else {
            searchAdapter.setDate(mList);
        }
    }

    /**
     * 清空历史搜索点击
     */
    @Override
    public void clicked() {
        searchLayyoutManager.getChildAt(nearSearchList.size()).setVisibility(View.GONE);
        input_search_dishes.setText("");
    }
}
