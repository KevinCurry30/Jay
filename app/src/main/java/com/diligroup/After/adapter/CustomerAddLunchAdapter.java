package com.diligroup.After.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diligroup.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/14.
 */
public class CustomerAddLunchAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    Context mContext;
    List<String> mList;

    //item类型
  //  public static final int ITEM_TYPE_FOOTER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;

    private int mFooterCount = 0;//头部View个数

    public CustomerAddLunchAdapter(Context mContext, List<String> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        if (viewType == ITEM_TYPE_FOOTER) {
//            view = View.inflate(parent.getContext(), R.layout.addlunch_customer_footer, null);
//            return new BottomViewHolder(view);
//        } else {
            view = View.inflate(parent.getContext(), R.layout.addlunch_rightlist, null);
            return new MyViewHoder(view);
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position<getContentItemCount()){
        MyViewHoder viewHoder = (MyViewHoder) holder;
//        String name=mList.get(position);
        Picasso.with(mContext).load(R.mipmap.banner_3).into(viewHoder.addlunchRightIcon);
        viewHoder.addlunchDishesIngredients.setText("配料：" + "小葱+蘑菇");
        viewHoder.addlunchDishesNum.setText("5");
        viewHoder.addlunchGramsNum.setText("123kg");

        viewHoder.addlunchAdddish.setOnClickListener(this);
        viewHoder.addlunchReducedish.setOnClickListener(this);
        }else{

        }
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return mFooterCount+getContentItemCount();
    }
    //内容长度
    public int getContentItemCount(){
        return mList.size();
    }
    //判断当前item是否是footerView
    public boolean isBottomView(int position) {
        return mFooterCount != 0 && position > getContentItemCount();
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getItemCount();
//        if (mFooterCount != 0 && position >= getContentItemCount()) {//头部View
//            return ITEM_TYPE_FOOTER;
//        } else {//内容View
            return ITEM_TYPE_CONTENT;
//        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addlunch_adddish:
                break;
            case R.id.addlunch_reducedish:
                break;
            default:
                break;
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

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.clear_hository_search)
        LinearLayout  clear_hository_search;//
        public BottomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
