package com.diligroup.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.diligroup.R;
import com.diligroup.utils.CommonUtils;

/**
 * Created by hjf on 2016/8/18.
 */
public class SharePopwindow extends PopupWindow implements View.OnClickListener {
    Context mContext;
    PlantFormClickLinstener listener;
    public SharePopwindow(Context mContext, View v,PlantFormClickLinstener listener){
        this.mContext = mContext;
        this.listener=listener;
      View  view = View.inflate(mContext,R.layout.dialog_share, null);
        setContentView(view);

        View dialog_qq=view.findViewById(R.id.dialog_qq);
        View dialog_qqzone=view.findViewById(R.id.dialog_qqzone);
        View dialog_wx=view.findViewById(R.id.dialog_wx);
        View dialog_wx_circle=view.findViewById(R.id.dialog_wx_circle);

        dialog_qq.setOnClickListener(this);
        dialog_qqzone.setOnClickListener(this);
        dialog_wx.setOnClickListener(this);
        dialog_wx_circle.setOnClickListener(this);
        // 设置弹窗的宽度和高度
        setWidth(CommonUtils.getScreenWidth(mContext)/2);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_shaden));
        // 设置获取焦点
        setFocusable(true);
        // 设置弹窗内可点击
        setTouchable(true);
        // 设置弹窗外可点击
        setOutsideTouchable(true);

        // 放置到该控件的下方
        showAsDropDown(v,0,0);
        showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);

        // 设置动画效果
        setAnimationStyle(R.style.meal_choice);
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
//                if (view != null && view.isShown()) {
//                    dismiss();
//                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_qq:
                listener.onClickQQ();
                dismiss();
                break;
            case R.id.dialog_qqzone:
                listener.onClickQQZone();
                dismiss();
                break;
            case R.id.dialog_wx:
                listener.onClickWX();
                dismiss();
                break;
            case R.id.dialog_wx_circle:
                listener.onClickWXCircle();
                dismiss();
                break;
        }
    }

//
//    private void initAdapter() {
//        MealChoiceAdapter adapter=new MealChoiceAdapter(mContext,this);
//
//        CommonUtils.initRerecyelerView(mContext,pop_recyclerview);
//        LinearLayoutManager layoutManager_1 = new LinearLayoutManager(mContext);
//        pop_recyclerview.setLayoutManager(layoutManager_1);
//        pop_recyclerview.setAdapter(adapter);
//    }
public interface PlantFormClickLinstener{
    void onClickWX();
    void onClickWXCircle();
    void onClickQQ();
    void onClickQQZone();
}
}
