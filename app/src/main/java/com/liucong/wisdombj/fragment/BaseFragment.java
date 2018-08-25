package com.liucong.wisdombj.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected AppCompatActivity appCompatActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appCompatActivity= (AppCompatActivity) getActivity();
        View view =initView(inflater,container);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 创建Fragment的View, 让子类实现
     * @param inflater
     * @param container
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container);
    /**
     * 填充数据, 子类可以不重写
     */
    protected  void initData(){

    }
}
