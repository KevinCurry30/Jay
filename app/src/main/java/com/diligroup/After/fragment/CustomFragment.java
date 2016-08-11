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
import android.widget.ListView;
import android.widget.TextView;

import com.diligroup.After.adapter.CustomeRighAdapter;
import com.diligroup.After.adapter.RighSearchAdapter;
import com.diligroup.After.adapter.StoreSuppyLeftAdapter;
import com.diligroup.R;
import com.diligroup.base.BaseFragment;
import com.diligroup.base.Constant;
import com.diligroup.bean.CostomerCategory;
import com.diligroup.bean.CustomerSearchResultBean;
import com.diligroup.bean.FindFoodByCategory;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.net.RequestManager;
import com.diligroup.utils.CommonUtils;
import com.diligroup.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * Created by hjf on 2016/7/13.
 */
public class CustomFragment extends BaseFragment implements View.OnClickListener, StoreSuppyLeftAdapter.OnGetView, AdapterView.OnItemClickListener, RequestManager.ResultCallback {
    @Bind(R.id.custome_input_search_dishes)
    EditText customeInputSearchDishes;
    @Bind(R.id.addlunche_custome_delete)
    ImageButton addluncheCustomeDelete;
    @Bind(R.id.cuostomer_recyclerView)
    ListView cuostomerRecyclerView;
    @Bind(R.id.customer_left_listView)
    ListView customer_left_listView;
    @Bind(R.id.costom_search_list)
    RecyclerView costom_search_list;
    @Bind(R.id.customer_normallayout)
    LinearLayout customer_normallayout;//默认展示自定义列表
    @Bind(R.id.costom_searchlayout)
    LinearLayout customer_search_layout;//自定义搜索列表
    @Bind(R.id.customer_margin)
    View custoer_margin;//自定义切换布局时候间隔
    //@Bind(R.id.clear_hository_search)
//    LinearLayout clear_hository_search;
    List<String> mList = new ArrayList<String>();
    List<CostomerCategory.ListBean> leftList = new ArrayList<CostomerCategory.ListBean>();
    private int currentClickItem;
    private StoreSuppyLeftAdapter leftAdapter;
    RighSearchAdapter searchAdapter;
    int currentIndex = 0;//当前左侧选中索引

    @Override
    public int getLayoutXml() {
        return R.layout.fragment_customer;
    }

    @Override
    public void setViews() {
        Api.getCustomerFoodList(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST);
        costom_search_list.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        costom_search_list.setHasFixedSize(true);//保持固定大小，提高性能
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        costom_search_list.setLayoutManager(layoutManager);


//        StoreSupplyRighAdapter adapter = new StoreSupplyRighAdapter(getActivity(), mList);
//        cuostomerRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setListeners() {
        addluncheCustomeDelete.setOnClickListener(this);
//        clear_hository_search.setOnClickListener(this);
        customer_left_listView.setOnItemClickListener(this);
        customeInputSearchDishes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                //获取焦点然后展示搜索最近的菜品
                if (hasFocus) {
                    customer_normallayout.setVisibility(View.GONE);
                    customer_search_layout.setVisibility(View.VISIBLE);
                    custoer_margin.setVisibility(View.GONE);
                    //展示最近搜索列表
                } else {
                    customer_normallayout.setVisibility(View.VISIBLE);
                    customer_search_layout.setVisibility(View.GONE);
                    custoer_margin.setVisibility(View.VISIBLE);
                }
            }
        });
        customeInputSearchDishes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Api.customer_search(Constant.cityCode, editable.toString(), CustomFragment.this);
            }
        });
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
        switch (v.getId()) {
            case R.id.addlunche_custome_delete:
                break;
            case R.id.clear_hository_search:
                break;
            default:
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
        View rightLine = convertView.findViewById(R.id.right_line);
        dishName.setText(leftList.get(position).getDictName());

        if (position == currentClickItem) {
            CommonUtils.showCategoryIcon(Integer.parseInt(leftList.get(position).getCode()), dishIcon, true);
            dishName.setTextColor(getActivity().getResources().getColor(R.color.green));
            convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            rightLine.setVisibility(View.GONE);
        } else {
            CommonUtils.showCategoryIcon(Integer.parseInt(leftList.get(position).getCode()), dishIcon, false);
            dishName.setTextColor(getActivity().getResources().getColor(R.color.black1));
            convertView.setBackgroundColor(getActivity().getResources().getColor(R.color.common_backgroud));
            rightLine.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentClickItem = position;
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.GET_COSTOMER_FOODLIST) {
            CostomerCategory bean = (CostomerCategory) object;
            if (bean.getCode().equals(Constant.RESULT_SUCESS)) {
                leftList = bean.getList();
//                Api.findFoodByCategoryId("20001", leftList.get(0).getCode(), this);
                Api.findFoodByCategoryId("20001", "10001", this);
                leftAdapter = new StoreSuppyLeftAdapter(getActivity(), leftList, this);
                customer_left_listView.setAdapter(leftAdapter);
            }
        } else if (object != null && action == Action.CUSTOMER_SEARCH) {
            CustomerSearchResultBean resultBean = (CustomerSearchResultBean) object;
            if (resultBean.getCode().equals(Constant.RESULT_SUCESS)) {
                List<CustomerSearchResultBean.SearchFoodBean> resultList = resultBean.getDishesLibList();
                if (resultList.size() > 0 && searchAdapter == null) {
                    searchAdapter = new RighSearchAdapter(getActivity(), resultList);
                    costom_search_list.setAdapter(searchAdapter);
                } else if (resultList.size() > 0 && searchAdapter != null) {
                    searchAdapter.setDate(resultList);
                }
            }
        } else if (object != null && action == Action.CUSTOMER_FIND_BY_CATEGORYID) {
            FindFoodByCategory rightFoodBean = (FindFoodByCategory) object;
            if (rightFoodBean.getCode().equals(Constant.RESULT_SUCESS)) {
//                if(rightFoodBean.getDishesLibList()!=null && rightFoodBean.getDishesLibList().size()>0){
//                for(int i=0;i<rightFoodBean.getDishesLibList().size();i++){
//                    rightFoodBean.getDishesLibList().get(i).setHeaderName(leftList.get(currentIndex).getDictName());
//                    rightFoodBean.getDishesLibList().get(i).setHeaderCode((leftList.get(currentIndex).getCode()));
//                }
//                }
            }
            CustomeRighAdapter rightAdapter = new CustomeRighAdapter(getActivity(), rightFoodBean.getDishesLibList());
            cuostomerRecyclerView.setAdapter(rightAdapter);
        }
    }
}
