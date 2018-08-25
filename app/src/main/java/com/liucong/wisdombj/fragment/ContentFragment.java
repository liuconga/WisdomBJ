package com.liucong.wisdombj.fragment;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.util.StatusTranslucent;

public class ContentFragment extends BaseFragment implements View.OnClickListener {
    private DrawerLayout drawerLayout;

/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //必须强转成AppCompatActivity 因为只有AppCompatActivity才有以下方法.例如setSupportActionBar()
        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        //findviewbyid获取toolbar对象 必须在onCreateView()方法之后,否则会空指针；
        Toolbar toolbar=appCompatActivity.findViewById(R.id.toolbar_main);
        appCompatActivity.setSupportActionBar(toolbar);
        //toolbar去除actionbar默认title显示,就是actionbar带的项目的名字 比如这个是wisdombj
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置toolbar navigation图标
        toolbar.setNavigationIcon(R.drawable.img_menu);
        //设置显示tool上的menu 与下面onCreateOptionsMenu关联；
        setHasOptionsMenu(true);
    }*/

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.content_fragment, container, false);
        return view;

    }

    @Override
    protected void initData() {
        //必须强转成AppCompatActivity 因为只有AppCompatActivity才有以下方法.例如setSupportActionBar()
        appCompatActivity = (AppCompatActivity) getActivity();
        //findviewbyid获取toolbar对象 必须在onCreateView()方法之后,否则会空指针；
        Toolbar toolbar = appCompatActivity.findViewById(R.id.toolbar_main);
        appCompatActivity.setSupportActionBar(toolbar);
        //toolbar去除actionbar默认title显示,就是actionbar带的项目的名字 比如这个是wisdombj
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置toolbar navigation图标
        toolbar.setNavigationIcon(R.drawable.img_menu);
        //设置显示tool上的menu 与下面onCreateOptionsMenu关联；
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(this);
        drawerLayout = appCompatActivity.findViewById(R.id.drawer_main);
        StatusTranslucent.setStatusBarFullTransparent(appCompatActivity);
        //设置toolbar的paddingtop为状态栏高度；注意toolbar控件的高度要是wrap_content
         toolbar.setPadding(0, StatusTranslucent.getStatusBarHeight(appCompatActivity),0,0);
    }

    /**
     * 创建toolbar上的菜单；要想让他调用 必须setHasOptionsMenu(true);
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.home_menu, menu);

    }

    /**
     * toolbar导航条的点击事件
     */
    @Override
    public void onClick(View v) {
        //打开抽屉
        drawerLayout.openDrawer(GravityCompat.START);

    }

}
