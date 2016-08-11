package com.diligroup.After.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.bean.FindFoodByCategory;
import com.diligroup.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hjf on 2016/7/14.
 */
public class CustomeRighAdapter extends BaseAdapter {
    Context mContext;
    List<FindFoodByCategory.DishesLibListBean> mList;

    //    private List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean.DishesSupplyDtlListBean> rightDishesList=new ArrayList<>();//所有右侧成品分类列表
    public CustomeRighAdapter(Context mContext, List<FindFoodByCategory.DishesLibListBean> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }
//    @Override
//    public View getHeaderView(int position, View convertView, ViewGroup parent) {
//        HeadViewHolder headViewHolder;
//        if (convertView == null) {
//            convertView = View.inflate(mContext, R.layout.stickview_home_head, null);
//            headViewHolder = new HeadViewHolder();
//            headViewHolder.sticklist_headtext = (TextView) convertView.findViewById(R.id.sticklist_headtext);
//            convertView.setTag(headViewHolder);
//        } else {
//            headViewHolder = (HeadViewHolder) convertView.getTag();
//        }
//        headViewHolder.sticklist_headtext.setText(mList.get(position).getHeaderName());
//        return convertView;
//    }

//    @Override
//    public long getHeaderId(int position) {
//        return Long.parseLong(mList.get(position).getHeaderCode());
//    }

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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHoder myViewHoder;
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
            convertView.setTag(myViewHoder);
        } else {
            myViewHoder = (MyViewHoder) convertView.getTag();
        }
        Picasso.with(mContext).load(R.mipmap.banner_3).into(myViewHoder.addlunchRightIcon);
        myViewHoder.addlunchDishesName.setText(mList.get(position).getDishesName());
        String[] temp = mList.get(position).getAllergens().split("、");
        StringBuilder sb = new StringBuilder();
        if (temp[0].length() > 0) {
            for (int i = 0; i < temp.length; i++) {
                if (TextUtils.isEmpty(sb.toString())) {
                    sb.append(temp[i]);
                } else {
                    sb.append("+" + temp[i]);
                }
            }
        }
        myViewHoder.addlunchDishesIngredients.setText("配料：" + sb.toString());
        if (mList.get(position).getNum() > 0) {
            myViewHoder.addlunchDishesNum.setVisibility(View.VISIBLE);
            myViewHoder.addlunchReducedish.setVisibility(View.VISIBLE);
            myViewHoder.addlunchDishesNum.setText(mList.get(position).getNum() + "");
        } else {
            myViewHoder.addlunchDishesNum.setVisibility(View.INVISIBLE);
            myViewHoder.addlunchReducedish.setVisibility(View.INVISIBLE);
        }

        myViewHoder.addlunchGramsNum.setText("123kg");
        myViewHoder.addlunchAdddish.setOnClickListener(new MyOnClickListener(position, myViewHoder.addlunchDishesNum));
        myViewHoder.addlunchReducedish.setOnClickListener(new MyOnClickListener(position, null));
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
//
//
//        public MyViewHoder() {
//            ButterKnife.bind(this);
//        }
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
                    mList.get(position).setNum(mList.get(position).getNum()+1);
                    notifyDataSetChanged();
                    break;
                case R.id.addlunch_reducedish:
                    mList.get(position).setNum(mList.get(position).getNum()-1);
                    notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

}