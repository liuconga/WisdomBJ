package com.liucong.wisdombj.pager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.util.LogUtils;

public class HomePager extends BasePager{
    public HomePager(AppCompatActivity activity) {
       super(activity);
    }

    @Override
    public View initView() {
         View view = View.inflate(mActivity, R.layout.vp_item_home, null);
         return view;
    }


    public void initData(){
        LogUtils.d("初始化viewpager","首页的数据加载了");
    }


}
