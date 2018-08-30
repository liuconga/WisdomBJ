package com.liucong.wisdombj.pager.smartservice;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.pager.NewsCenterBase;

public class SmartServiceYiBao extends NewsCenterBase {

    public SmartServiceYiBao(AppCompatActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView(){
        //填充出一个view
        View view = View.inflate(mActivity, R.layout.smartservice_yibao, null);
        return view;
    }
    @Override
    public void initData(){
        TextView textView=mActivity.findViewById(R.id.smartservice_yibao_tv);
        textView.setText("医保你好呀");
    }

}
