package com.liucong.wisdombj.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liucong.wisdombj.R;

public class LeftFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.left_fragment, container, false);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
