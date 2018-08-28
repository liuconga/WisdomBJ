package com.liucong.wisdombj.pager;

import android.app.Activity;
import android.view.View;

import com.liucong.wisdombj.R;

public abstract class BasePager {
    protected Activity mActivity;

    private View view;


    public BasePager(Activity activity) {
        mActivity=activity;
        view=initView();

        mActivity.findViewById(R.id.toolbar_title);


    }


    public abstract View initView();
    protected void initData(){

    }
    public View getView(){
        return view;
    }

}
