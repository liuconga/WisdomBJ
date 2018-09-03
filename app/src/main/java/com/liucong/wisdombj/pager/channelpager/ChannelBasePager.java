package com.liucong.wisdombj.pager.channelpager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.bean.NewsCenterDataChildrenBean;

public  class ChannelBasePager {
    protected AppCompatActivity mActivity;
    private View view;
    private NewsCenterDataChildrenBean dataChildrenBean;
    private TextView textView;

    public ChannelBasePager(AppCompatActivity mActivity, NewsCenterDataChildrenBean dataChildrenBean) {
        this.mActivity = mActivity;
        this.dataChildrenBean=dataChildrenBean;
        view=initView();
    }

    /**
     * 让子类去实现initview
     */
    protected  View initView(){
        view=View.inflate(mActivity, R.layout.news_channel,null);
        return view;
    }

    /**
     * 初始化数据 子类可实现可不实现
     */
    public void initData(){

    }
    public View getView(){
        return view;
    }

    public NewsCenterDataChildrenBean getDataChildrenBean() {
        return dataChildrenBean;
    }
}
