package com.diligroup.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.Home.FeedbackActivity;
import com.diligroup.Home.FoodDetailsActivity;
import com.diligroup.R;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.bean.MyStickyHeadChangeListener;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/19.
 */
public class HomeRighAdapter_1 extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mList;
    String date;
    String mealType;
    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList=new ArrayList<>();//所有右侧成品分类列表
    MyStickyHeadChangeListener listener;
    public HomeRighAdapter_1(Context mContext, List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mList,String date,String mealType,MyStickyHeadChangeListener listener) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        this.listener=listener;
        this.date=date;
        this.mealType=mealType;
        initListDate();
    }
    public  void setDate(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList,String date,String mealType){
        this.rightDishesList=mList;
        this.date=date;
        this.mealType=mealType;
        notifyDataSetChanged();
    }
    private void initListDate() {
        rightDishesList.clear();
        if (mList!=null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if(mList.get(i).getDishesSupplyDtlList()!=null){
                    for(int j=0;j<mList.get(i).getDishesSupplyDtlList().size();j++){
                        mList.get(i).getDishesSupplyDtlList().get(j).setHeaderCode(mList.get(i).getDishesTypeCode());
                        mList.get(i).getDishesSupplyDtlList().get(j).setHeaderName(mList.get(i).getDishesTypeName());
                    }
                    rightDishesList.addAll(mList.get(i).getDishesSupplyDtlList());
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.home_rightlist, parent, false);
        MyItemViewHoder myViewHoder = new MyItemViewHoder(convertView);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHoder myViewHoder = (MyItemViewHoder) holder;
        Picasso.with(mContext).load(R.mipmap.banner_3).into(myViewHoder.home_right_icon);
        myViewHoder.homeDishesName.setText(rightDishesList.get(position).getDishesName());
        StringBuilder tempStr=new StringBuilder();
        for(int i=0;i<rightDishesList.get(position).getAccessoriesList().size();i++){
            if(TextUtils.isEmpty(tempStr.toString())){
                tempStr.append(rightDishesList.get(position).getAccessoriesList().get(i).getFoodName());
            }else{
                tempStr.append("+"+rightDishesList.get(position).getAccessoriesList().get(i).getFoodName());
            }
        }
        if(rightDishesList.get(position).isShow()){
            myViewHoder.home_evaluate.setVisibility(View.VISIBLE);
        }else{
            myViewHoder.home_evaluate.setVisibility(View.INVISIBLE);
        }
        myViewHoder.homeDishesIngredients.setText("配料：" +tempStr.toString());
        myViewHoder.home_evaluate.setOnClickListener(new MyOnClickListener(position));
        myViewHoder.home_right_view.setOnClickListener(new MyOnClickListener(position));
    }

    @Override
    public long getHeaderId(int position) {
        if( position>0 && !rightDishesList.get(position).getHeaderCode().equals(rightDishesList.get(position-1).getHeaderCode())){
            listener.headChange(position,rightDishesList.get(position).getHeaderCode());
        }
        return Long.parseLong(rightDishesList.get(position).getHeaderCode());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.stickview_home_head, parent, false);
        return new MyHeaderViewHolder(v);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHeaderViewHolder hvh = (MyHeaderViewHolder) holder;
        hvh.sticklistHeadtext.setText(rightDishesList.get(position).getHeaderName());
    }

    @Override
    public int getItemCount() {
        return rightDishesList==null?0:rightDishesList.size();
    }
    class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent mIntent;
            switch (v.getId()) {
                case R.id.home_evaluate:
                    mIntent = new Intent(mContext, FeedbackActivity.class);
                    mIntent.putExtra("foodCode",rightDishesList.get(position).getDishesCode());
                    mIntent.putExtra("mealType",mealType);
                    mIntent.putExtra("date",date);
                    mContext.startActivity(mIntent);
                    break;
                case R.id.home_right_view:
                    mIntent = new Intent(mContext, FoodDetailsActivity.class);
                    mIntent.putExtra("foodCode",rightDishesList.get(position).getDishesCode());
                    mContext.startActivity(mIntent);
                    break;
            }
        }
    }

    class MyItemViewHoder extends RecyclerView.ViewHolder {
        @Bind(R.id.home_right_icon)
        ImageView home_right_icon;//右侧icon
        @Bind(R.id.home_dishes_name)
        TextView homeDishesName;//食物名称
        @Bind(R.id.home_dishes_ingredients)
        TextView homeDishesIngredients;//食品配料
        @Bind(R.id.home_evaluate)
        TextView home_evaluate;//菜品评价
        @Bind(R.id.home_right_view)
        RelativeLayout home_right_view;

        public MyItemViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView sticklistHeadtext;

        public MyHeaderViewHolder(View headView) {
            super(headView);
            sticklistHeadtext = (TextView) headView.findViewById(R.id.sticklist_headtext);
        }

    }
}
