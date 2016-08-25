package com.diligroup.UserSet.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diligroup.R;
import com.diligroup.UserSet.activity.ServiceCenter;
import com.diligroup.UserSet.activity.SettingActivity;
import com.diligroup.UserSet.activity.UserInfoActivity;
import com.diligroup.base.BaseFragment;
import com.diligroup.utils.LogUtils;
import com.diligroup.utils.UpLoadPhotoUtils;
import com.diligroup.utils.UserManager;
import com.diligroup.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 2016/7/4.
 */
public class UserSetFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.iv_user_header)
    CircleImageView iv_user_header;
    @Bind(R.id.tv_numb_phone)
    TextView tv_numb_phone;
    private Bundle savedState;


    @Override
    public int getLayoutXml() {
        return R.layout.fragment_user;
    }

    @Override
    public void setViews() {
        Picasso.with(getActivity()).load(UserManager.getInstance().getHeadUrl());
        tv_numb_phone.setText(UserManager.getInstance().getPhone());
    }

    @Override
    public void setListeners() {
        iv_user_header.setOnClickListener(this);
    }


    @OnClick(R.id.rl_go_userinfo)
    public void jumpUserInfo() {
        GoActivity(UserInfoActivity.class);

    }

    @OnClick(R.id.rl_go_setting)
    public void jumpSetView() {

        GoActivity(SettingActivity.class);
    }

    @OnClick(R.id.rl_go_service)
    public void jumpService() {
        GoActivity(ServiceCenter.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                new UpLoadPhotoUtils(getActivity()).pickImage();
                break;
        }
    }

    public void chageHeadIcon(String url) {
        if( null!=mRootView && mRootView.size()>0){
        LogUtils.i("homeActivity调用fragmeng中方法");
        Picasso.with(getActivity()).load(url).into(iv_user_header);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            View view=inflater.inflate(getLayoutXml(), null);
            mRootView = new ArrayList<View>();
            mRootView.add(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get(0).getParent();
            if (parent != null) {
                parent.removeView(mRootView.get(0));
            }
        }
        ButterKnife.bind(this,mRootView.get(0));
       setViews();
        setListeners();
        return mRootView.get(0);
    }
}
