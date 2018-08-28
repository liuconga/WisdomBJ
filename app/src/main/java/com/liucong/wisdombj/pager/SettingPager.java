package com.liucong.wisdombj.pager;

import android.app.Activity;
import android.view.View;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.util.LogUtils;

public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_setting, null);
        return view;
    }


    public void initData(){
        LogUtils.d("初始化viewpager","设置的数据加载了");
    }
}
