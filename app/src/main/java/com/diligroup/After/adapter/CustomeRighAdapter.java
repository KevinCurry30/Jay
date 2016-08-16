package com.diligroup.After.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.After.AddLunchActivity;
import com.diligroup.R;
import com.diligroup.bean.AddFoodCompleteBean;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.LogUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hjf on 2016/7/14.
 * 自定义菜品适配器
 */
public class CustomeRighAdapter extends BaseAdapter {
    Context mContext;
    List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList;
    AddFoodCompleteBean bean = new AddFoodCompleteBean();

    //    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList=new ArrayList<>();//所有右侧成品分类列表
    public CustomeRighAdapter(Context mContext, List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setDate(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final MyViewHoder myViewHoder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.addlunch_rightlist, null);
            myViewHoder = new MyViewHoder();
            myViewHoder.addlunchRightIcon = (ImageView) convertView.findViewById(R.id.addlunch_right_icon);
            myViewHoder.addlunchDishesName = (TextView) convertView.findViewById(R.id.addlunch_dishes_name);
            myViewHoder.addlunchDishesIngredients = (TextView) convertView.findViewById(R.id.addlunch_dishes_ingredients);

            myViewHoder.addlunchGramsNum = (TextView) convertView.findViewById(R.id.addlunch_grams_num);
            myViewHoder.addlunchReducedish = (ImageView) convertView.findViewById(R.id.addlunch_reducedish);
            myViewHoder.addlunchAdddish = (ImageView) convertView.findViewById(R.id.addlunch_adddish);
            myViewHoder.addlunchDishesNum = (TextView) convertView.findViewById(R.id.addlunch_dishes_num);

            myViewHoder.weightlayout = convertView.findViewById(R.id.weightlayout);
            myViewHoder.root_view = convertView.findViewById(R.id.root_view);
            myViewHoder.input_weight = (EditText) convertView.findViewById(R.id.input_weight);
            convertView.setTag(myViewHoder);
        } else {
            myViewHoder = (MyViewHoder) convertView.getTag();
        }
        Picasso.with(mContext).load(R.mipmap.banner_3).into(myViewHoder.addlunchRightIcon);
        myViewHoder.addlunchDishesName.setText(mList.get(position).getDishesName());
//        String[] temp = mList.get(position).getIngredients().getFoodName().split("、");
//        StringBuilder sb = new StringBuilder();
//        if (temp[0].length() > 0) {
//            for (int i = 0; i < temp.length; i++) {
//                if (TextUtils.isEmpty(sb.toString())) {
//                    sb.append(temp[i]);
//                } else {
//                    sb.append("+" + temp[i]);
//                }
//            }
//        }
        myViewHoder.addlunchDishesIngredients.setText("配料：" + mList.get(position).getIngredients().getFoodName());
        if (mList.get(position).getFoodNums() > 0) {
            myViewHoder.addlunchDishesNum.setVisibility(View.VISIBLE);
            myViewHoder.addlunchReducedish.setVisibility(View.VISIBLE);
            myViewHoder.addlunchDishesNum.setText(mList.get(position).getFoodNums() + "");
        } else {
            myViewHoder.addlunchDishesNum.setVisibility(View.INVISIBLE);
            myViewHoder.addlunchReducedish.setVisibility(View.INVISIBLE);
        }

        myViewHoder.addlunchGramsNum.setText("123kg");
        myViewHoder.addlunchAdddish.setOnClickListener(new MyOnClickListener(position, myViewHoder.addlunchDishesNum));
        myViewHoder.addlunchReducedish.setOnClickListener(new MyOnClickListener(position, myViewHoder.addlunchReducedish));
        myViewHoder.root_view.setOnClickListener(new MyOnClickListener(position, myViewHoder.root_view));
        myViewHoder.input_weight.setText(mList.get(position).getWeight());
        myViewHoder.input_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                LogUtils.i("输入的内容是==" + myViewHoder.input_weight.getText().toString());
                if (!hasFocus && !TextUtils.isEmpty(myViewHoder.input_weight.getText().toString())) {
                    bean.setWeight(myViewHoder.input_weight.getText().toString());
                    bean.setDishesCode(mList.get(position).getDishesCode());
                    bean.setDishesName(mList.get(position).getDishesName());
                    bean.setImageUrl(mList.get(position).getImagesURL());
                    bean.setWayType("1");
                    if (!TextUtils.isEmpty(myViewHoder.addlunchDishesNum.getText().toString())) {
                        ((AddLunchActivity) mContext).addFood(bean);
                    }
                }
                if (!TextUtils.isEmpty(myViewHoder.input_weight.getText().toString())) {
                    myViewHoder.input_weight.setText(myViewHoder.input_weight.getText().toString());
                    mList.get(position).setWeight(myViewHoder.input_weight.getText().toString());
                }
            }
        });
        if (mList.get(position).isShowWeight()) {
            myViewHoder.weightlayout.setVisibility(View.VISIBLE);
            myViewHoder.input_weight.setFocusable(true);
            myViewHoder.input_weight.requestFocus();
            mList.get(position).setWeight(myViewHoder.input_weight.getText().toString());

        } else {
            myViewHoder.weightlayout.setVisibility(View.GONE);
        }

        return convertView;
    }

    class MyViewHoder {
        ImageView addlunchRightIcon;//右侧icon
        TextView addlunchDishesName;//食物名称
        TextView addlunchDishesIngredients;//食品配料

        TextView addlunchGramsNum;//克数
        ImageView addlunchReducedish;//“-”
        TextView addlunchDishesNum;//食物数量

        ImageView addlunchAdddish;//“+”
        View root_view;
        View weightlayout;
        EditText input_weight;
    }

    class HeadViewHolder {
        TextView sticklist_headtext;
    }

    class MyOnClickListener implements View.OnClickListener {
        int position;
        View view;

        public MyOnClickListener(int position, View view) {
            this.position = position;
            this.view = view;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addlunch_adddish:
                    if (view != null)
                        CommonUtils.propertyValuesHolder(view);
                    if (!TextUtils.isEmpty(bean.getWeight())) {
                        ((AddLunchActivity) mContext).addFood(setBean(bean));
                    }
                    mList.get(position).setFoodNums(mList.get(position).getFoodNums() + 1);
//                    if (!TextUtils.isEmpty(conntent)) {
//                        mList.get(position).setWeight(conntent);
//                    }
                    notifyDataSetChanged();
                    break;
                case R.id.addlunch_reducedish:
                    mList.get(position).setFoodNums(mList.get(position).getFoodNums() - 1);
                    mList.get(position).setWeight(mList.get(position).getWeight());
//                    setBean(bean);
                    ((AddLunchActivity) mContext).deleteFood(setBean(bean));
                    notifyDataSetChanged();
                    break;
                case R.id.root_view://右侧item的点击事件，展示编辑框
                    for (int i = 0; i < mList.size(); i++) {
                        if (i == position) {
                            mList.get(i).setShowWeight(true);
                        } else {
                            mList.get(i).setShowWeight(false);

                        }
                    }
                    setDate(mList);
                    break;
//
                default:
                    break;
            }
        }

        private AddFoodCompleteBean setBean(AddFoodCompleteBean bean) {
            bean.setWeight(mList.get(position).getWeight());
            bean.setDishesCode(mList.get(position).getDishesCode());
            bean.setDishesName(mList.get(position).getDishesName());
            bean.setImageUrl(mList.get(position).getImagesURL());
            bean.setWayType("1");
            return bean;
        }
    }
}