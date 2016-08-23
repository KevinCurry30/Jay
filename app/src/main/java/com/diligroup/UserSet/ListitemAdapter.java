package com.diligroup.UserSet;

import java.util.HashMap;
import java.util.List;

import com.diligroup.R;
import com.diligroup.bean.GetFoodTypeBean;
import com.diligroup.bean.GetJiaoQinBean;
import com.diligroup.utils.GetCheckStateUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListitemAdapter extends BaseAdapter {

	private List<GetJiaoQinBean.ListBean> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	private static HashMap<Integer,Boolean> isSelected;//用来控制checkBox的选中情况

	public ListitemAdapter(Context context,List<GetJiaoQinBean.ListBean> list){
		mList = list;
		mContext = context;
		isSelected = new HashMap<>();
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
		ViewHolder holder ;
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
           
        holder.tvName.setText(mList.get(position).getDictName());
		holder.foodId=mList.get(position).getCode();
		if (mList.get(position).getIsShow().equals("1")){
			holder.cb.setChecked(true);
		}




		return convertView;
	}
//	public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
//		ListitemAdapter.isSelected = isSelected;
//	}
//	public static HashMap<Integer,Boolean> getIsSelected() {
//		return isSelected;
//	}
	public class ViewHolder{
		public String foodId;
		public  TextView tvName;
		public CheckBox cb;
	};
	
	
	

}


