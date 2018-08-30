package com.liucong.wisdombj.pager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class NewsCenterBase {
    protected AppCompatActivity mActivity;
    private View view;
    public NewsCenterBase(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
        view=initView();
    }

    /**
     * 让子类去实现initview
     */
    protected abstract View initView();

    /**
     * 初始化数据 子类可实现可不实现
     */
    protected void initData(){

    }
    public View getView(){
        return view;
    }
}
