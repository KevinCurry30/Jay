package com.diligroup.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Kevin on 2016/7/4.
 */
public abstract class BaseFragment extends Fragment {

    protected List<View> mRootView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutXml() != 0) {
            View view = inflater.inflate(getLayoutXml(), null);
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setViews();
        setListeners();
    }
    public abstract int getLayoutXml();

    public abstract void setViews();

    public abstract void setListeners();

    public void GoActivity(Class classes) {
        Intent intent = new Intent(this.getActivity(), classes);
        startActivity(intent);
    }

}
