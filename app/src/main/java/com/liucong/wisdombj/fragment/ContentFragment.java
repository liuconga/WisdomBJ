package com.liucong.wisdombj.fragment;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

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
        //设置主页面的viewpager
         setViewPager();
         //将viewpager与radiogroup进行关联，禁止滑动
          RadioGroup radioGroup=appCompatActivity.findViewById(R.id.rg_fm_content);
          final ViewPager viewPager=appCompatActivity.findViewById(R.id.vp_content_fragment);
          radioGroup.check(R.id.home);
          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home :
                        viewPager.setCurrentItem(0,false);
                        break;
                    case R.id.news :
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.smart :
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.zw :
                        viewPager.setCurrentItem(3,false);
                        break;
                    case R.id.setting :
                        viewPager.setCurrentItem(4,false);
                        break;
                }

              }
          });
    }

    /**
     *  //设置主页面的viewpager
     */
    private void setViewPager() {
        ViewPager viewPager = appCompatActivity.findViewById(R.id.vp_content_fragment);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view=null;
                switch (position){
                    case 0:
                        view = View.inflate(appCompatActivity, R.layout.vp_item_home, null);

                        break;
                    case 1:
                      view = View.inflate(appCompatActivity, R.layout.vp_item_news, null);

                        break;
                    case 2:
                         view = View.inflate(appCompatActivity, R.layout.vp_item_smartserviece, null);

                        break;
                    case 3:
                      view = View.inflate(appCompatActivity, R.layout.vp_item_zw, null);

                        break;
                    case 4:
                     view = View.inflate(appCompatActivity, R.layout.vp_item_setting, null);

                        break;
                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
               //   super.destroyItem(container, position, object); 去掉这一行
                container.removeView((View) object);
            }
        });
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
