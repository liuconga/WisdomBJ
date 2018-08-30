package com.liucong.wisdombj.pager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.fragment.LeftFragment;
import com.liucong.wisdombj.inter.OnMenuItemClickListener;
import com.liucong.wisdombj.util.LogUtils;

public class NewsPager extends BasePager {
    private String[] menus = new String[]{"新闻", "专题", "组图", "互动"};
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
    public void initData(){
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
         //设置LeftFragment中数据
        leftfragment.setData(menus, new OnMenuItemClickListener() {
            @Override
            public void menuItemClick(int position) {
             switch (position){
                 case 0:
                     LogUtils.d("条目点击","新闻");
                     break;
                 case 1:
                     LogUtils.d("条目点击","专题");
                     break;
                 case 2:
                     LogUtils.d("条目点击","组图");
                     break;
                 case 3:
                     LogUtils.d("条目点击","互动");
                     break;
             }
            }
        });

    }


}
