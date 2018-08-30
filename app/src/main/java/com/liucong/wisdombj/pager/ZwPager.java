package com.liucong.wisdombj.pager;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.util.LogUtils;

public class ZwPager extends BasePager{
    public ZwPager(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_zw, null);
        return view;
    }


    /**
     * 1.政务分为两个模块：公积金，社保，通过侧滑菜单来进行切换
     */
    public void initData(){
        LogUtils.d("初始化viewpager","ZW的数据加载了");

    }


}
