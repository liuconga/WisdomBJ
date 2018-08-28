package com.liucong.wisdombj.pager;

import android.app.Activity;
import android.view.View;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.util.LogUtils;

public class SmartPager extends BasePager{
    public SmartPager(Activity activity) {
        super(activity);
    }



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_smartserviece, null);
        return view;
    }


    public void initData(){
        LogUtils.d("初始化viewpager","Smart的数据加载了");
    }
}
