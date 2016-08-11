package com.diligroup.After.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.bean.CustomerSearchResultBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/14.
 */
public class RighSearchAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<CustomerSearchResultBean.SearchFoodBean> mList;

    public RighSearchAdapter(Context mContext, List<CustomerSearchResultBean.SearchFoodBean> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }
public void setDate(List<CustomerSearchResultBean.SearchFoodBean> mList){
    this.mList=mList;
    notifyDataSetChanged();
}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.addlunch_rightlist, null);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder viewHoder = (MyViewHoder) holder;
        StringBuilder tempStr=new StringBuilder();
        for(int i=0;i<mList.get(position).getIngredients().size();i++){
            if(!tempStr.toString().contains("+")){
                tempStr.append(mList.get(position).getAccessoriesList().get(i).getFoodName());
            }else{
                tempStr.append("+"+mList.get(position).getAccessoriesList().get(i).getFoodName());
            }
        }
        viewHoder.addlunchDishesIngredients.setText("配料：" +tempStr.toString());
        if (mList.get(position).getFoodNums() == 0) {
            viewHoder.addlunchDishesNum.setVisibility(View.GONE);
        } else {
            viewHoder.addlunchDishesNum.setText(mList.get(position).getFoodNums() + "");
        }
        viewHoder.addlunchAdddish.setImageResource(mList.get(position).getFoodNums() > 0 ? R.mipmap.add_dish_pressed : R.mipmap.add_dishes_normal);
        viewHoder.addlunchAdddish.setOnClickListener(new MyOnClickListener(position));
        viewHoder.addlunchReducedish.setOnClickListener(new MyOnClickListener(position));
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return mList.size();
    }

    class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addlunch_adddish:
                    if (mList.get(position).getFoodNums() == 0) {
                        notifyItemChanged(position);
                    } else {
                        mList.get(position).setFoodNums(mList.get(position).getFoodNums() + 1);
                    }
                    break;
                case R.id.addlunch_reducedish:
                    mList.get(position).setFoodNums(mList.get(position).getFoodNums() - 1);
                    if(mList.get(position).getFoodNums()==0){
                        notifyItemChanged(position);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        @Bind(R.id.addlunch_right_icon)
        ImageView addlunchRightIcon;//右侧icon
        @Bind(R.id.addlunch_dishes_name)
        TextView addlunchDishesName;//食物名称
        @Bind(R.id.addlunch_dishes_ingredients)
        TextView addlunchDishesIngredients;//食品配料
        @Bind(R.id.addlunch_grams_num)
        TextView addlunchGramsNum;//克数
        @Bind(R.id.addlunch_reducedish)
        ImageView addlunchReducedish;//“-”
        @Bind(R.id.addlunch_dishes_num)
        TextView addlunchDishesNum;//食物数量
        @Bind(R.id.addlunch_adddish)
        ImageView addlunchAdddish;//“+”


        public MyViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
