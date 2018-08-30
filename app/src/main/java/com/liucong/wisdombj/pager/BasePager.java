package com.liucong.wisdombj.pager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liucong.wisdombj.R;

public abstract class BasePager {
    protected AppCompatActivity mActivity;

    private View view;


    public BasePager(AppCompatActivity activity) {
        mActivity=activity;
        view=initView();
        mActivity.findViewById(R.id.toolbar_title);
    }


    public abstract View initView();
    public void initData(){

    }
    public View getView(){
        return view;
    }

}
