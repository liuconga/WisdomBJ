package com.liucong.wisdombj.pager.newscenter;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.pager.NewsCenterBase;

public class NewsCenterHudong extends NewsCenterBase {

    public NewsCenterHudong(AppCompatActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView(){
        //填充出一个view
        View view = View.inflate(mActivity, R.layout.newscenter_hudong, null);
        return view;
    }
    @Override
    public void initData(){
        TextView textView=mActivity.findViewById(R.id.newscenter_hudong_tv);
        textView.setText("互动你好呀");
    }

}
