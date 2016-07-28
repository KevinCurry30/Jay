package com.diligroup.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/14.
 */
public class RighAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    Context mContext;
    List<String> mList;

    public RighAdapter(Context mContext, List<String> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.addlunch_rightlist, null);
        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder viewHoder = (MyViewHoder) holder;
//        String name=mList.get(position);
//        Picasso.with(mContext).load(R.drawable.dili_logo).into(viewHoder.addlunchRightIcon);
        viewHoder.addlunchDishesIngredients.setText("配料：" + "小葱+猪肝");
        viewHoder.addlunchDishesNum.setText("5");
        viewHoder.addlunchGramsNum.setText("123kg");

        viewHoder.addlunchAdddish.setOnClickListener(this);
        viewHoder.addlunchReducedish.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return mList.size();
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
}
