package com.diligroup.UserSet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.diligroup.Home.adapter.LeftAdapter;
import com.diligroup.R;
import com.diligroup.UserSet.AllergyAdapter;
import com.diligroup.UserSet.SetItemSelector;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.CommonBean;
import com.diligroup.bean.GetAllergyDetailBean;
import com.diligroup.bean.GetFoodTypeBean;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.FoodTypeUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.DividerItemDecoration;
import com.diligroup.view.FlowLayout;
import com.diligroup.view.TagAdapter;
import com.diligroup.view.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报 过敏 食材
 */
public class ReportAllergy extends BaseActivity implements MyItemClickListener {

    @Bind(R.id.list_foods_detail)
    ListView lv_foodDetail;
    FoodAdapter adapter;
    @Bind(R.id.tag_allergy)
    TagFlowLayout taglayout;
    List<String> foodIdList;
    TagAdapter tagAdapter;
    List<GetAllergyDetailBean.ListBean> allergyList;
    List<String> foodNameList;
    ViewHolder foodHolder;
    @Bind(R.id.typwleft_listView)
    RecyclerView rv_left;
    AllergyAdapter allergyAdapter;
    List<GetFoodTypeBean> typeNameList;
    LayoutInflater mInflater;

    Boolean isFrist;
    Bundle bundle;
    @Bind(R.id.bt_commit_allergy)
    Button bt_allergy;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_allergy;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    /**
     * 上报按钮
     **/
    @OnClick(R.id.bt_commit_allergy)
    public void reportAllergy() {
        if (isFrist) {
            UserInfoBean.getInstance().setAllergyFood(foodNameList.toString());
            readyGo(ReportWhere.class, bundle);
            return;
        }
//        Map map =new HashMap();
//        map.put("allergyName",foodNameList.toArray());
//        Api.updataUserInfo(map,this);
//        readyGo(UserInfoActivity.class);
        this.finish();

    }

    @Override
    public void setTitle() {
        super.setTitle();
        tv_title.setText("过敏食材");
    }

    @Override
    protected void initViewAndData() {
        isShowBack(true);
//        Api.getAllergyFood(this);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        isFrist = bundle.getBoolean("isFrist");
        if (isFrist) {
            bt_allergy.setText("下一步");
        }
        mInflater = LayoutInflater.from(ReportAllergy.this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST);
        typeNameList = FoodTypeUtils.GetFoodTypeList();
        allergyAdapter = new AllergyAdapter(this, typeNameList, this);
        rv_left.addItemDecoration(dividerItemDecoration);//垂直列表的分割线
        rv_left.setHasFixedSize(true);//保持固定大小，提高性能
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_left.setLayoutManager(layoutManager);
        rv_left.setAdapter(allergyAdapter);
        Api.getAllergyDetails("谷类", this);
        foodIdList = new ArrayList<>();
        foodNameList = new ArrayList<>();

        lv_foodDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodHolder = (ViewHolder) view.getTag();
                foodHolder.food_Check.setEnabled(true);
                String food = foodHolder.title.getText().toString();

//                foodHolder.food_Check.toggle();
                if (foodHolder.food_Check.isChecked()) {
                    foodNameList.add(food);
//                    addTag(food);
//                    for (int i=0;i<foodNameList.size();i++){
//                        if (foodNameList.get(i).equals(food)){
//                            ToastUtil.showShort(ReportAllergy.this,"您已经添加过了");
//                        }else{
//                            foodNameList.add(food);
//                            setTagLayout();
//                            continue;
//                        }
//                        tagAdapter.notifyDataChanged();
//                    }
//                    if (foodNameList.get())
//                    foodNameList.add(foodHolder.title.getText().toString());
                } else {
                    removeUnChecked(food);
//                    tagAdapter.notifyDataChanged();
                }

                setTagLayout();
            }


        });

    }

    public void addTag(String name) {
        if (foodNameList != null) {
            for (int i = 0; i < foodNameList.size(); i++) {

                if (!foodNameList.get(i).equals(name)) {
                    foodNameList.add(name);
                }
            }
            setTagLayout();
            tagAdapter.notifyDataChanged();
        }
    }


    public void removeUnChecked(String foodName) {
        if (foodNameList.size() > 0) {
            for (int i = 0; i < foodNameList.size(); i++) {
                if (foodNameList.get(i).equals(foodName)) {
                    foodNameList.remove(foodName);
                }
            }
        }
    }

    private void setTagLayout() {
        tagAdapter = new TagAdapter(foodNameList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        taglayout, false);
                tv.setText(o.toString());
                return tv;
            }

            @Override
            public void setSelectedList(Set set) {
                super.setSelectedList(set);
            }
        };
        taglayout.setAdapter(tagAdapter);
    }

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.GET_ALLERGY_DETAILS) {
            GetAllergyDetailBean allergyDetailBean = (GetAllergyDetailBean) object;
            allergyList = allergyDetailBean.getList();
            adapter = new FoodAdapter(allergyList);
            lv_foodDetail.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if (action==Action.UPDATA_USERINFO&&object!=null){
            CommonBean commonBean= (CommonBean) object;
            if (commonBean.getCode().equals("000000")){
                Intent intent=new Intent();
                intent.putExtra("allergyList",String.valueOf(allergyList.size()));
                setResult(0x60,intent);
                this.finish();

            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                Api.getAllergyDetails("谷类", this);
                break;
            case 1:
                Api.getAllergyDetails("豆类", this);
                break;
            case 2:
                Api.getAllergyDetails("蔬菜类", this);
                break;
            case 3:
                Api.getAllergyDetails("水果类", this);
                break;
            case 4:
                Api.getAllergyDetails("坚果类", this);
                break;
            case 5:
                Api.getAllergyDetails("坚果类", this);
                break;
            case 6:
                Api.getAllergyDetails("坚果类", this);
                break;
            case 7:
                Api.getAllergyDetails("坚果类", this);
                break;
            case 8:
                Api.getAllergyDetails("坚果类", this);
                break;
            case 9:
                Api.getAllergyDetails("坚果类", this);
                break;
        }
    }

    //  小分类  adapter
    private class FoodAdapter extends BaseAdapter {
        List<GetAllergyDetailBean.ListBean> foodList;

        public FoodAdapter(List<GetAllergyDetailBean.ListBean> foodList) {
            this.foodList = foodList;
        }

        @Override
        public int getCount() {
            return foodList == null ? 0 : foodList.size();
        }

        @Override
        public Object getItem(int position) {
            return foodList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_food_detail, null);
                holder.title = (TextView) convertView.findViewById(R.id.tv_nzw_name);
                holder.food_Check = (CheckBox) convertView.findViewById(R.id.cb_item_check);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            holder.title.setText(foodList.get(position).getAllergyName());
            holder.id = String.valueOf(foodList.get(position).getId());
            if (foodList.get(position).getStatus().equals("1")){
                holder.food_Check.setChecked(false);
                removeUnChecked(foodList.get(position).getAllergyName());
            }else{
                holder.food_Check.setChecked(true);
                foodNameList.add(foodList.get(position).getAllergyName());
            }
//            if (foodList.get(position).getAllergyName().equals(foodNameList.get(position))){
//                holder.food_Check.setChecked(true);
//            }
            return convertView;
        }
    }

    //ViewHolder静态类
    static class ViewHolder {
        public TextView title;
        public CheckBox food_Check;
        public String id;
    }
}
