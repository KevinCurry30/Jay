package com.diligroup.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.diligroup.R;


/**
 * 分享对话框
 * Created by hjf on 2016/8/17.
 */
public class ShareDialog extends Dialog implements View.OnClickListener {

    private LinearLayout dialog_qq;
    private LinearLayout dialog_wx;
    private LinearLayout dialog_qqzone;
    private LinearLayout dialog_wx_circle;
    PlantFormClickLinstener listener;
    Context context;
    public ShareDialog(Context context,PlantFormClickLinstener listener) {
        super(context, R.style.DialogStyle);
        this.listener=listener;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        dialog_qq = (LinearLayout) findViewById(R.id.dialog_qq);
        dialog_qqzone = (LinearLayout) findViewById(R.id.dialog_qqzone);
        dialog_wx = (LinearLayout) findViewById(R.id.dialog_wx);
        dialog_wx_circle = (LinearLayout) findViewById(R.id.dialog_wx_circle);

        dialog_qq.setOnClickListener(this);
        dialog_qqzone.setOnClickListener(this);
        dialog_wx.setOnClickListener(this);

        dialog_wx_circle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_qq:
                listener.onClickQQ();
                v.setBackgroundColor(context.getResources().getColor(R.color.dialog_bg));
                dismiss();
                break;
            case R.id.dialog_qqzone:
                listener.onClickQQZone();
                v.setBackgroundColor(context.getResources().getColor(R.color.dialog_bg));
                dismiss();
                break;
            case R.id.dialog_wx:
                listener.onClickWX();
                v.setBackgroundColor(context.getResources().getColor(R.color.dialog_bg));
                dismiss();
                break;
            case R.id.dialog_wx_circle:
                listener.onClickWXCircle();
                v.setBackgroundColor(context.getResources().getColor(R.color.dialog_bg));
                dismiss();
                break;
            default:
                break;
        }

    }

    public interface PlantFormClickLinstener{
        void onClickWX();
        void onClickWXCircle();
        void onClickQQ();
        void onClickQQZone();
    }
}
