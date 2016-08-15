package com.diligroup.UserSet;

import java.util.List;

import com.diligroup.R;
import com.diligroup.bean.GetFoodTypeBean;
import com.diligroup.bean.GetJiaoQinBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListitemAdapter extends BaseAdapter {

	private List<GetJiaoQinBean.ListBean> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	public ListitemAdapter(Context context,List<GetJiaoQinBean.ListBean> list){
		mList = list;
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;  
        if (convertView == null) {  
              
            holder=new ViewHolder();    
              
            convertView = mInflater.inflate(R.layout.jiaoqing_item, null);
            holder.tvName = (TextView)convertView.findViewById(R.id.tv_noeat_item);
			holder.cb= (CheckBox) convertView.findViewById(R.id.cb_noeat_item);
//            holder.mSubTitile = (TextView)convertView.findViewById(R.id.subtitle);
            convertView.setTag(holder);  
              
        }else {  
              
            holder = (ViewHolder)convertView.getTag();  
        }  
           
        holder.tvName.setText((String)mList.get(position).getDictName());
//        holder.mSubTitile.setText((String)mList.get(position).subTitleStr);
		holder.foodId=mList.get(position).getCode();
        return convertView;  
	}
	
	public class ViewHolder{
		public String foodId;
		public  TextView tvName;
		public CheckBox cb;
	};
	
	
	

}


