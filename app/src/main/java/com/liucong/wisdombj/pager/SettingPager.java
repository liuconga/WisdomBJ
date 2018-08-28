package com.liucong.wisdombj.pager;

import android.app.Activity;
import android.view.View;

import com.liucong.wisdombj.R;

public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }



    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_setting, null);
        return view;
    }


    protected void initData(){

    }
}
