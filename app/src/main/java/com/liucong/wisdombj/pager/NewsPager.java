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
import com.liucong.wisdombj.pager.newscenter.NewsCenterHudong;
import com.liucong.wisdombj.pager.newscenter.NewsCenterNews;
import com.liucong.wisdombj.pager.newscenter.NewsCenterTopic;
import com.liucong.wisdombj.pager.newscenter.NewsCenterZutu;
import com.liucong.wisdombj.util.LogUtils;

public class NewsPager extends BasePager {
    private String[] menus = new String[]{"新闻", "专题", "组图", "互动"};
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private TextView textView_toolbar_title;
   private int mPosition;
    public NewsPager(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_news, null);
        return view;
    }


    /**
     * 1.初始化新闻中心数据，新闻中心分为四个专题：新闻，专题，组图，互动；通过侧滑菜单来进行切换
     */
    public void initData() {
        LogUtils.d("哈哈","【initData】NewsPagerSetData()执行啦");
        drawerLayout = mActivity.findViewById(R.id.drawer_main);
        frameLayout = mActivity.findViewById(R.id.fl_newscenter);
        textView_toolbar_title = mActivity.findViewById(R.id.toolbar_title);
        //设置左侧侧边栏布局文件 因为每个都不一样
        setLeftMenuData();

    }

    /**
     * 设置左侧边栏布局文件，因为每个界面的左侧边栏都不同
     */
    private void setLeftMenuData() {
        //找到LeftFragment 对象
        //1.获取Fragment管理器 注意是getSupportFragmentManager
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        //2.通过设置的tag找到fragment
        LeftFragment leftfragment = (LeftFragment) fragmentManager.findFragmentByTag("leftfragment");
        LogUtils.d("哈哈","NewsPagerSetData()执行啦");
        //设置LeftFragment中数据
        leftfragment.setData(mPosition,menus, new OnMenuItemClickListener() {
            @Override
            public void menuItemClick(int position) {
                //记录上次点击的位置
                mPosition=position;
                switch (position) {
                    case 0:
                        //将toolbar标题设成新闻
                        textView_toolbar_title.setText("新闻");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成 新闻
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
//                     //填充出一个view
//                     View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterNews newsCenterNews = new NewsCenterNews(mActivity);
                        //调用其getView方法得到view
                        View view = newsCenterNews.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterNews.initData();
                        break;
                    case 1:
                        //将toolbar标题设成专题
                        textView_toolbar_title.setText("专题");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成专题
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
                        //填充出一个view
                        //View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterTopic newsCenterTopic = new NewsCenterTopic(mActivity);
                        //调用其getView方法得到view
                        View view_topic = newsCenterTopic.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view_topic);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterTopic.initData();
                        break;
                    case 2:
                        //将toolbar标题设成组图
                        textView_toolbar_title.setText("组图");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成专题
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
                        //填充出一个view
                        //View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterZutu newsCenterZutu = new NewsCenterZutu(mActivity);
                        //调用其getView方法得到view
                        View view_zutu = newsCenterZutu.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view_zutu);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterZutu.initData();
                        break;
                    case 3:
                        //将toolbar标题设成互动
                        textView_toolbar_title.setText("互动");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成专题
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
                        //填充出一个view
                        //View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterHudong newsCenterHudong = new NewsCenterHudong(mActivity);
                        //调用其getView方法得到view
                        View view_hudong = newsCenterHudong.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view_hudong);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterHudong.initData();
                        break;
                }
            }
        });

    }


}
