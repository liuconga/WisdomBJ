package com.liucong.wisdombj.pager;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.fragment.LeftFragment;
import com.liucong.wisdombj.inter.OnMenuItemClickListener;
import com.liucong.wisdombj.pager.smartservice.SmartServiceGongJiJin;
import com.liucong.wisdombj.pager.smartservice.SmartServiceYiBao;

public class SmartPager extends BasePager {
    private String[] menus = new String[]{"公积金", "医保"};
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private TextView textView_toolbar_title;
     private int mPosition;
    public SmartPager(AppCompatActivity activity) {
        super(activity);
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_smartserviece, null);
        return view;
    }


    public void initData() {
        drawerLayout = mActivity.findViewById(R.id.drawer_main);
        frameLayout = mActivity.findViewById(R.id.fl_smart);
        textView_toolbar_title = mActivity.findViewById(R.id.toolbar_title);
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
        leftfragment.setData(mPosition,menus, new OnMenuItemClickListener() {
            @Override
            public void menuItemClick(int position) {
                mPosition=position;
                switch (position){
                case 0:
                    //将toolbar标题设成公积金
                    textView_toolbar_title.setText("公积金");
                    //关闭侧边栏
                    drawerLayout.closeDrawers();
                    //将contentFragment 中新闻内容换成 新闻
                    //首先移除Framlayout这个viewgroup中所有子view
                    frameLayout.removeAllViews();
//                     //填充出一个view
//                     View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                    SmartServiceGongJiJin smartServiceGongJiJin = new SmartServiceGongJiJin(mActivity);
                    //调用其getView方法得到view
                    View view = smartServiceGongJiJin.getView();
                    //将填充冲的一个view添加到Framelayout中
                    frameLayout.addView(view);
                    //要在initData()之前将view添加到framelayout中
                    //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                    smartServiceGongJiJin.initData();
                    break;
                case 1:
                    //将toolbar标题设成医保
                    textView_toolbar_title.setText("医保");
                    //关闭侧边栏
                    drawerLayout.closeDrawers();
                    //将contentFragment 中新闻内容换成 新闻
                    //首先移除Framlayout这个viewgroup中所有子view
                    frameLayout.removeAllViews();
//                     //填充出一个view
//                     View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                    SmartServiceYiBao smartServiceYiBao = new SmartServiceYiBao(mActivity);
                    //调用其getView方法得到view
                    View view_yibao = smartServiceYiBao.getView();
                    //将填充冲的一个view添加到Framelayout中
                    frameLayout.addView(view_yibao);
                    //要在initData()之前将view添加到framelayout中
                    //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                    smartServiceYiBao.initData();
                break;
                }
            }
        });
    }

}
