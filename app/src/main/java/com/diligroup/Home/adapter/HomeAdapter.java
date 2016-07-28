package com.diligroup.Home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.diligroup.Home.viewpage.AddBreadFast;
import com.diligroup.base.BaseViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjf on 2016/7/19.
 * 首页s适配器
 */
public class HomeAdapter extends PagerAdapter {
	List<BaseViewPager> viewPagerList;
	Context mContext;
	public HomeAdapter(Context mContext) {
		this.mContext=mContext;
		viewPagerList=new ArrayList<>();
		viewPagerList.add(new AddBreadFast(mContext));
		viewPagerList.add(new AddBreadFast(mContext));
		viewPagerList.add(new AddBreadFast(mContext));
	}
	private  String[] TITLES;

//	public HomeAdapter(List<BaseViewPager> viewPager, Context mContext) {
//		this.viewPagerList =viewPager;
//		this.mContext=mContext;
//		TITLES = new String[]{ mContext.getResources().getString(R.string.breakfast), mContext.getResources().getString(R.string.lunch),mContext.getResources().getString(R.string.dinner) };
//	}
	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	@Override
	public int getCount() {
		return viewPagerList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		BaseViewPager orderPager = viewPagerList.get(position);
		View rootView = orderPager.getRootView();
		container.addView(rootView);
		return rootView;
	}
}
