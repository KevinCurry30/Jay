package com.diligroup.After.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diligroup.Home.FoodDetailsActivity;
import com.diligroup.R;
import com.diligroup.bean.ButtonClickListener;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.utils.RecordSQLiteOpenHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/7/14.
 */
public class RighSearchAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList;

    private int mBottomCount = 1;//底部View个数
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    ButtonClickListener listener;

    public RighSearchAdapter(Context mContext, List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList, ButtonClickListener listener) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        this.listener = listener;
    }

    public void setDate(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_TYPE_BOTTOM) {
            view = View.inflate(mContext, R.layout.delete_hository, null);
            return new BottomViewHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.addlunch_rightlist, null);
            return new MyViewHoder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BottomViewHolder) {
            BottomViewHolder myViewHolder = (BottomViewHolder) holder;
        } else {
            MyViewHoder viewHoder = (MyViewHoder) holder;
//        StringBuilder tempStr = new StringBuilder();
//        for (int i = 0; i < mList.get(position).getAccessoriesList().size(); i++) {
//            if (!tempStr.toString().contains("+")) {
//                tempStr.append(mList.get(position).getAccessoriesList().get(i).getFoodName());
//            } else {
//                tempStr.append("+" + mList.get(position).getAccessoriesList().get(i).getFoodName());
//            }
//        }
         viewHoder.addlunchDishesIngredients.setText("配料：" + mList.get(position).getMainFood());
            if (mList.get(position).getFoodNums() == 0) {
                viewHoder.addlunchDishesNum.setVisibility(View.GONE);
            } else {
                viewHoder.addlunchDishesNum.setText(mList.get(position).getFoodNums() + "");
            }
            viewHoder.addlunchDishesName.setText(mList.get(position).getDishesName());
//        viewHoder.addlunchRightIcon.setImageURI(Uri.parse(mList.get(position).getImagesURL()));
            viewHoder.addlunchAdddish.setImageResource(mList.get(position).getFoodNums() > 0 ? R.mipmap.add_dish_pressed : R.mipmap.add_dishes_normal);
            viewHoder.addlunchAdddish.setOnClickListener(new MyOnClickListener(position));
            viewHoder.addlunchReducedish.setOnClickListener(new MyOnClickListener(position));
            viewHoder.rootView.setOnClickListener(new MyOnClickListener(position));
        }
    }

    @Override
    public int getItemCount() {
//        return mList == null ? 0 : mList.size();
        return getContentItemCount() + mBottomCount;
    }

    //内容长度
    public int getContentItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mBottomCount != 0 && position >= (dataItemCount)) {//底部View
            return ITEM_TYPE_BOTTOM;
        } else {//内容View
            return ITEM_TYPE_CONTENT;
        }
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
                    if (mList.get(position).getFoodNums() == 0) {
                        notifyItemChanged(position);
                    }
                    break;
                case R.id.root_view:
                  Intent  mIntent = new Intent(mContext, FoodDetailsActivity.class);
                    mIntent.putExtra("foodCode",mList.get(position).getDishesCode());
                    mContext.startActivity(mIntent);
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
        @Bind(R.id.root_view)
        RelativeLayout rootView;

        public MyViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //底部 ViewHolder
    class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.clear();
                    notifyDataSetChanged();
                    new RecordSQLiteOpenHelper(mContext).deleteAll(RecordSQLiteOpenHelper.STORESUPPLY_TABLE_NAME);
                    listener.clicked();
                }
            });
        }
    }

}

