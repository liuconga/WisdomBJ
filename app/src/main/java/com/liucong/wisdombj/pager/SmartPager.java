package com.liucong.wisdombj.pager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.fragment.LeftFragment;
import com.liucong.wisdombj.inter.OnMenuItemClickListener;
import com.liucong.wisdombj.util.LogUtils;

public class SmartPager extends BasePager {
    private String[] menus = new String[]{"公积金", "医保"};

    public SmartPager(AppCompatActivity activity) {
        super(activity);
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_smartserviece, null);
        return view;
    }


    public void initData() {
        //设置左侧侧边栏布局文件 因为每个都不一样
        setLeftMenuData();
    }

    private void setLeftMenuData() {
        //找到LeftFragment 对象
        //1.获取Fragment管理器 注意是getSupportFragmentManager
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        //2.通过设置的tag找到fragment
        LeftFragment leftfragment = (LeftFragment) fragmentManager.findFragmentByTag("leftfragment");
        //设置LeftFragment中数据
        leftfragment.setData(menus, new OnMenuItemClickListener() {
            @Override
            public void menuItemClick(int position) {
                switch (position){
                case 0:
                    LogUtils.d("条目点击","公积金");
                break;
                case 1:
                    LogUtils.d("条目点击","医保");
                break;
                }
            }
        });
    }

}
