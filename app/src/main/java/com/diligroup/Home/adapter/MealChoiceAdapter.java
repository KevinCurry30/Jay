package com.diligroup.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.bean.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hjf on 2016/8/4.
 */
public class MealChoiceAdapter extends RecyclerView.Adapter {
    Context mContext;
    private String[] meals;
    private int[] normalIcon;
    private int[] selectedIcon;
    private List<MealChoice> mList;
    MyItemClickListener listener;

    public MealChoiceAdapter(Context mContext,MyItemClickListener listener) {
        this.mContext = mContext;
        this.listener=listener;
        initDate();
    }

    private void initDate() {
        meals = mContext.getResources().getStringArray(R.array.mealchoice);
        normalIcon = new int[]{
                R.mipmap.breakfase_icon_normal,
                R.mipmap.lunch_icon_normal,
                R.mipmap.dinner_icon_normal
        };
        selectedIcon = new int[]{
                R.mipmap.breakfast_white,
                R.mipmap.lunch_white,
                R.mipmap.diner_white
        };
        mList = new ArrayList<>();
        for (int i = 0; i < normalIcon.length; i++) {
            MealChoice meal = new MealChoice(false);
            meal.setIcon(normalIcon[i]);
            meal.setIconName(meals[i]);
            mList.add(meal);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.layout_pop_mealchoice_item, null);
        RecyclerView.ViewHolder viewHolder = new MyViewHolder(view,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.popMealText.setText(mList.get(position).getIconName());
        myViewHolder.popMealIcon.setImageResource(mList.get(position).getIcon());

        if (mList.get(position).isSelected) {
            myViewHolder.pop_meal_root.setBackgroundColor(mContext.getResources().getColor(R.color.common_green));
            myViewHolder.popMealIcon.setImageResource(selectedIcon[position]);
            myViewHolder.popMealText.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            myViewHolder.pop_meal_root.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            myViewHolder.popMealIcon.setImageResource(normalIcon[position]);
            myViewHolder.popMealText.setTextColor(mContext.getResources().getColor(R.color.black1));
        }
    }

    @Override
    public int getItemCount() {
        return meals.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.pop_meal_icon)
        ImageView popMealIcon;
        @Bind(R.id.pop_meal_text)
        TextView popMealText;
        @Bind(R.id.pop_meal_root)
        LinearLayout pop_meal_root;
        MyItemClickListener listener;

        public MyViewHolder(View itemView, final MyItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
            pop_meal_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    listener.onItemClick(v, getPosition());
                    for(int i=0;i<mList.size();i++){
                        if(i==getPosition()){
                            mList.get(i).setSelected(true);
                        }else{
                            mList.get(i).setSelected(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    class MealChoice {
        boolean isSelected;
        int icon;

        public String getIconName() {
            return iconName;
        }

        public void setIconName(String iconName) {
            this.iconName = iconName;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        String iconName;

        public MealChoice(boolean isSelected) {
            this.isSelected = isSelected;
        }
    }
}
