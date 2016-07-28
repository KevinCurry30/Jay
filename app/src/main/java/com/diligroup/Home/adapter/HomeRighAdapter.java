package com.diligroup.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.Home.FeedbackActivity;
import com.diligroup.R;
import com.diligroup.bean.MyItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/19.
 */
public class HomeRighAdapter extends RecyclerView.Adapter{
    Context mContext;
    List<String> mList;

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    MyItemClickListener listener;
    public HomeRighAdapter(Context mContext, List<String> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.home_rightlist, null);
        return new MyViewHoder(view,listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder viewHoder = (MyViewHoder) holder;
//        String name=mList.get(position);
        Picasso.with(mContext).load(R.mipmap.banner_2).into(viewHoder.home_right_icon);
        viewHoder.homeDishesName.setText("大萝卜");
        viewHoder.homeDishesIngredients.setText("配料：" + "小葱+蘑菇");
        viewHoder.home_evaluate.setText("菜品评价");
        viewHoder.home_evaluate.setTextColor(mContext.getResources().getColor(R.color.common_orenge));
        viewHoder.home_evaluate.setOnClickListener(new MyOnClickListener(position));

    }

    @Override
    public int getItemCount() {
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
                case R.id.home_evaluate:
                    Intent mIntent = new Intent(mContext, FeedbackActivity.class);
//                    mIntent.putExtra("dishName", mList.get(position).get);
                    mContext.startActivity(mIntent);
                    break;
            }
        }
    }


    class MyViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.home_right_icon)
        ImageView home_right_icon;//右侧icon
        @Bind(R.id.home_dishes_name)
        TextView homeDishesName;//食物名称
        @Bind(R.id.home_dishes_ingredients)
        TextView homeDishesIngredients;//食品配料
        @Bind(R.id.home_evaluate)
        TextView home_evaluate;//菜品评价
        MyItemClickListener listener;
        public MyViewHoder(View itemView, MyItemClickListener listener) {
            super(itemView);
            this.listener=listener;
            ButterKnife.bind(this, itemView);
//            home_right_icon= (ImageView) itemView.findViewById(R.id.home_right_icon);
//            homeDishesName= (TextView) itemView.findViewById(R.id.home_dishes_name);
//            itemView.findViewById(R.id.ad)
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v,getPosition());
        }
    }
}
