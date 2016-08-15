package com.diligroup.After.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.diligroup.After.fragment.CustomFragment;
import com.diligroup.After.fragment.StoreSupplyFragment;
import com.diligroup.R;

public class AddLunchAdapter extends FragmentPagerAdapter{
	
	public AddLunchAdapter(FragmentManager fm) {
		super(fm);
	}
	private  String[] TITLES;

	String mealType;
	String whichDay;
	public AddLunchAdapter(FragmentManager fm,Context mContext,String mealType,String whichDay) {
		super(fm);
		this.mealType=mealType;
		this.whichDay=whichDay;
		TITLES = new String[]{ mContext.getResources().getString(R.string.store_supply), mContext.getResources().getString(R.string.custoom) };
	}

	@Override
	public Fragment getItem(int arg0) {
		if(arg0==0){
			return new StoreSupplyFragment().newInstance(mealType,whichDay);
		}else{
			return new CustomFragment().newInstance(mealType,whichDay);
		}
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

}
