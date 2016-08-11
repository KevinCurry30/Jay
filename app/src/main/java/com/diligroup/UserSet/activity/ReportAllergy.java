package com.diligroup.UserSet.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.UserSet.JiaoQinAdapter;
import com.diligroup.UserSet.SetItemSelector;
import com.diligroup.base.BaseActivity;
import com.diligroup.bean.GetAllergyDetailBean;
import com.diligroup.bean.GetFoodTypeBean;
import com.diligroup.bean.UserInfoBean;
import com.diligroup.net.Action;
import com.diligroup.net.Api;
import com.diligroup.utils.FoodTypeUtils;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.NetUtils;
import com.diligroup.utils.ToastUtil;
import com.diligroup.view.FlowLayout;
import com.diligroup.view.TagAdapter;
import com.diligroup.view.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 上报 过敏 食材
 */
public class ReportAllergy extends BaseActivity {

    @Bind(R.id.list_foods_detail)
    ListView lv_foodDetail;
    FoodAdapter adapter;
    @Bind(R.id.tag_allergy)
    TagFlowLayout taglayout;
    List<String> foodIdList;
    TagAdapter tagAdapter;
    LayoutInflater mInflater;
    List<GetAllergyDetailBean.ListBean> allergyList;
    List<String> foodNameList;
    ViewHolder foodHolder;
    @Bind(R.id.lv_left)
    ListView lv_foodType;
    //    @Bind(R.id.ll_guleis)
//    LinearLayout ll_gulei;
    List<GetFoodTypeBean> typeNameList;

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
        UserInfoBean.getInstance().setAllergyFood("");
        readyGo(ReportWhere.class);
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
        typeNameList = FoodTypeUtils.GetFoodTypeList();
        lv_foodType.setAdapter(new FoodTypeAdapter());
        mInflater = LayoutInflater.from(ReportAllergy.this);
        foodIdList = new ArrayList<>();
        foodNameList = new ArrayList<>();
//        ll_gulei.setPressed(true);
        Api.getAllergyDetails("", this);

        lv_foodDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodHolder = (ViewHolder) view.getTag();
                foodHolder.food_Check.toggle();
                adapter.getIsSelected().put(position, foodHolder.food_Check.isChecked());
                // 调整选定条目
                if (foodHolder.food_Check.isChecked()) {
//                    ToastUtil.showShort(ReportAllergy.this,"Checked==="+foodHolder.id);
                    foodNameList.add(foodHolder.title.getText().toString());
                } else {
//                    ToastUtil.showShort(ReportAllergy.this,"UnChecked==="+foodHolder.id);
                    removeUnChecked(foodHolder.title.getText().toString());
                }
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
        });

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

    @Override
    public void onError(Request request, Action action, Exception e) {

    }

    @Override
    public void onResponse(Request request, Action action, Object object) {
        if (object != null && action == Action.GET_ALLERGY_DETAILS) {
            GetAllergyDetailBean allergyDetailBean = (GetAllergyDetailBean) object;
            allergyList = allergyDetailBean.getList();
            adapter = new FoodAdapter(this, allergyList);
            lv_foodDetail.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    class FoodTypeAdapter extends BaseAdapter {

        public FoodTypeAdapter() {
            selectPosion(0);
        }

        @Override
        public int getCount() {
            return typeNameList.size();
        }

        @Override
        public Object getItem(int position) {
            return typeNameList.get(position);
        }

        /**
         * 指定哪一个被选中
         *
         * @param position
         */
        public void selectPosion(int position) {
            for (int i = 0; i < typeNameList.size(); i++) {
                if (i == position) {
                    typeNameList.get(i).setClicked(true);
                } else {
                    typeNameList.get(i).setClicked(false);
                }
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TypeHolder typeHolder;
            if (convertView == null) {
                typeHolder = new TypeHolder();
                convertView = LayoutInflater.from(ReportAllergy.this).inflate(R.layout.item_allergy, null);
                typeHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_allergy_name);
                typeHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_allergy_icon);
                typeHolder.ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item_type);
                convertView.setTag(typeHolder);
            }
            typeHolder = (TypeHolder) convertView.getTag();
            typeHolder.tv_name.setText(typeNameList.get(position).getFoodTypeName());
            if (typeNameList.get(position).isClicked) {
                SetItemSelector.setSelector(position, true, typeHolder.iv_icon);
            } else {
                SetItemSelector.setSelector(position, false, typeHolder.iv_icon);

            }
            typeHolder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosion(position);
                }
            });
            return convertView;
        }
    }

    class TypeHolder {
        TextView tv_name;
        ImageView iv_icon;
        LinearLayout ll_item;
    }

    //  小分类  adapter
    private class FoodAdapter extends BaseAdapter {
        List<GetAllergyDetailBean.ListBean> foodList;
        private LayoutInflater mInflater;
        //Iterator iterator;
        // 用来控制CheckBox的选中状况
        private HashMap<Integer, Boolean> isSelected;

        public FoodAdapter(Context context, List<GetAllergyDetailBean.ListBean> foodList) {
            this.foodList = foodList;
            isSelected = new HashMap<>();
            this.mInflater = LayoutInflater.from(context);
//            iterator = foodIdList.iterator();
            initDate();
        }

        // 初始化isSelected的数据
        private void initDate() {
            for (int i = 0; i < allergyList.size(); i++) {
                getIsSelected().put(i, false);
            }
        }

        public HashMap<Integer, Boolean> getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
            this.isSelected = isSelected;
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
            holder.food_Check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelected.get(position)) {
                        isSelected.put(position, false);
                        setIsSelected(isSelected);
                        LogUtils.e("unChecked");
                    } else {
                        isSelected.put(position, true);
                        setIsSelected(isSelected);
                        LogUtils.e("Checked");
                    }
                }
            });
            return convertView;
        }
    }

    //ViewHolder静态类
    static class ViewHolder {
        public TextView title;
        public CheckBox food_Check;
        public String id;
    }


//    @OnClick(R.id.ll_guleis)
//    public void clickGulei() {
//        Api.getAllergyDetails("", this);
//    }
//
//    @OnClick(R.id.ll_shucai)
//    public void clickShuCai() {
//        Api.getAllergyDetails("", this);
//    }
//
//    @OnClick(R.id.ll_doulei)
//    public void clickDoulei() {
//        Api.getAllergyDetails("", this);
//    }
//
//    @OnClick(R.id.ll_fruit)
//    public void clickFruit() {
//        Api.getAllergyDetails("", this);
//    }
//
//    @OnClick(R.id.ll_milk)
//    public void clickMilk() {
//        Api.getAllergyDetails("", this);
//    }
//
//    @OnClick(R.id.ll_jianguo)
//    public void clickJianGuo() {
//        Api.getAllergyDetails("", this);
//    }


}
