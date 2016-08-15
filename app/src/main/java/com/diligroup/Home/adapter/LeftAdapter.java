package com.diligroup.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.bean.HomeStoreSupplyList;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.utils.CommonUtils;

import java.util.List;

/**
 * Created by hjf on 2016/7/14.
 * x
 */
public class LeftAdapter extends RecyclerView.Adapter {
    Context mContext;
    List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mlist;
    OnGetView onGetView;
    MyItemClickListener listener;
    public LeftAdapter(Context mContext, List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mlist, MyItemClickListener listener) {
        super();
        this.mContext = mContext;
        this.mlist = mlist;
        this.listener=listener;
        initDate(mlist);
    }
    public void setDate(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mlist){
        this.mlist=mlist;
        notifyDataSetChanged();
    }

    private void initDate(List<HomeStoreSupplyList.JsonBean.DishesSupplyListBean> mlist) {
        if (mlist != null && mlist.size() > 0) {
            selectPosion(0);
        }
    }

    /**
     * 指定哪一个被选中
     *
     * @param position
     */
    public void selectPosion(int position) {
        for (int i = 0; i < mlist.size(); i++) {
            if (i == position) {
                mlist.get(i).setSelected(true);
            } else {
                mlist.get(i).setSelected(false);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.addlunch_child, null);
        return new MyHomeViewHoder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHomeViewHoder viewHoder = (MyHomeViewHoder) holder;
        viewHoder.dishName.setText(mlist.get(position).getDishesTypeName());

        if (mlist.get(position).isSelected()) {
            CommonUtils.showCategoryIcon(Integer.parseInt(mlist.get(position).getDishesTypeCode()),viewHoder.dishIcon,true);
            viewHoder.dishName.setTextColor(mContext.getResources().getColor(R.color.green));
            viewHoder.left.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHoder.rightLine.setVisibility(View.GONE);
        } else {
            CommonUtils.showCategoryIcon(Integer.parseInt(mlist.get(position).getDishesTypeCode()),viewHoder.dishIcon,false);
            viewHoder.dishName.setTextColor(mContext.getResources().getColor(R.color.black1));
            viewHoder.left.setBackgroundColor(mContext.getResources().getColor(R.color.gray_f0f0f0));
            viewHoder.rightLine.setVisibility(View.VISIBLE);
        }
        viewHoder.left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                selectPosion(position);
                notifyDataSetChanged();
                listener.onItemClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    public interface OnGetView {
        /**
         * @param position 下标
         * @param
         * @return
         */
        public void getView(int position, MyHomeViewHoder viewHoder);
    }

    public class MyHomeViewHoder extends RecyclerView.ViewHolder {
        public ImageView dishIcon;
        public TextView dishName;
        public View rightLine;
        public LinearLayout left;

        public MyHomeViewHoder(View itemView) {
            super(itemView);
            dishIcon = (ImageView) itemView.findViewById(R.id.dish_icon);
            dishName = (TextView) itemView.findViewById(R.id.dish_name);
            rightLine = itemView.findViewById(R.id.right_line);
            left = (LinearLayout) itemView.findViewById(R.id.left);
        }
    }
}
