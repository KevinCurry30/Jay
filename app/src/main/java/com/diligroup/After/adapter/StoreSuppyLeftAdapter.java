package com.diligroup.After.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.diligroup.bean.CostomerCategory;

import java.util.List;

/**
 * Created by hjf on 2016/7/14.
 * x
 */
public class StoreSuppyLeftAdapter extends BaseAdapter {
    Context mContext;
    List<CostomerCategory.ListBean> mlist;
    OnGetView onGetView;
    public StoreSuppyLeftAdapter(Context mContext, List<CostomerCategory.ListBean> mlist, OnGetView onGetView) {
        super();
        this.mContext = mContext;
        this.mlist = mlist;
        this.onGetView=onGetView;
    }
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
