package com.liucong.wisdombj.pager.smartservice;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.pager.NewsCenterBase;

public class SmartServiceGongJiJin extends NewsCenterBase {

    public SmartServiceGongJiJin(AppCompatActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView(){
        //填充出一个view
        View view = View.inflate(mActivity, R.layout.smartservice_gongjijin, null);
        return view;
    }
    @Override
    public void initData(){
        TextView textView=mActivity.findViewById(R.id.smartservice_gongjijin_tv);
        textView.setText("公积金你好呀");
    }

}
