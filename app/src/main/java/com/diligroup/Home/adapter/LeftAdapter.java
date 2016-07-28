package com.diligroup.Home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by hjf on 2016/7/14.
 * x
 */
public class LeftAdapter extends BaseAdapter {
    Context mContext;
    List<String> mlist;
    OnGetView onGetView;
    public LeftAdapter(Context mContext, List<String> mlist,OnGetView onGetView) {
        super();
        this.mContext = mContext;
        this.mlist = mlist;
        this.onGetView=onGetView;
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = View.inflate(mContext, R.layout.addlunch_child, null);
//        return new MyViewHolder(view,listener);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        LeftAdapter.MyViewHolder viewHoder = (MyViewHolder) holder;
//        if(position==0){
//            viewHoder.dishIcon.setSelected(true);
//        }
//        Picasso.with(mContext).load(R.drawable.test_drawable_selector).into(viewHoder.dishIcon);
//        viewHoder.dishName.setText("哈哈哈哈");
//    }


    @Override
    public int getCount() {
        return mlist == null ? 0 : mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return onGetView.getView(position,convertView);
    }
    public interface OnGetView {
        /**
         *
         * @param position
         *            下标
         * @param convertView
         *            视图
         * @return
         */
        public View getView(int position, View convertView);
    }
}
