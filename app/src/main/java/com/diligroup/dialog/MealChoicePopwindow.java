package com.diligroup.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.diligroup.Home.adapter.MealChoiceAdapter;
import com.diligroup.R;
import com.diligroup.bean.MyItemClickListener;
import com.diligroup.utils.CommonUtils;
import com.diligroup.utils.LogUtils;

/**
 * Created by hjf on 2016/8/4.
 */
public class MealChoicePopwindow extends PopupWindow implements MyItemClickListener {
    Context mContext;
    private final RecyclerView pop_recyclerview;
    MyItemClickListener listener;

    public MealChoicePopwindow(Context mContext,View v,int width,MyItemClickListener listener){
        this.mContext = mContext;
        this.listener=listener;
      View  view = View.inflate(mContext,R.layout.meal_choice_pop, null);
        setContentView(view);
        // 设置弹窗的宽度和高度
        setWidth(v.getMeasuredWidth());
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rectangle_kuang));
        // 设置获取焦点
        setFocusable(true);
        // 设置弹窗内可点击
        setTouchable(true);
        // 设置弹窗外可点击
        setOutsideTouchable(true);

        // 放置到该控件的下方
        showAsDropDown(v, 100, 0);
        showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);

        // 设置动画效果
        setAnimationStyle(R.style.meal_choice);
        pop_recyclerview = (RecyclerView) view.findViewById(R.id.pop_recyclerview);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
                if (view != null && view.isShown()) {
                    dismiss();
                }
                return false;
            }
        });
        initAdapter();
       String i= android.os.Build.VERSION.RELEASE;
    }


    private void initAdapter() {
        MealChoiceAdapter adapter=new MealChoiceAdapter(mContext,this);

        CommonUtils.initRerecyelerView(mContext,pop_recyclerview);
        LinearLayoutManager layoutManager_1 = new LinearLayoutManager(mContext);
        pop_recyclerview.setLayoutManager(layoutManager_1);
        pop_recyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        LogUtils.i("当前点击的item=="+position);
        listener.onItemClick(view,position);
        this.dismiss();
    }
}
