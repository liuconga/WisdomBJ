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
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.pager.BasePager;
import com.liucong.wisdombj.pager.HomePager;
import com.liucong.wisdombj.pager.NewsPager;
import com.liucong.wisdombj.pager.SettingPager;
import com.liucong.wisdombj.pager.SmartPager;
import com.liucong.wisdombj.pager.ZwPager;
import com.liucong.wisdombj.util.StatusTranslucent;

import java.util.ArrayList;

public class ContentFragment extends BaseFragment implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ArrayList<BasePager> arrayList=new ArrayList<>();
    private ViewPager viewPager;

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
        final Toolbar toolbar = appCompatActivity.findViewById(R.id.toolbar_main);
        appCompatActivity.setSupportActionBar(toolbar);
        //toolbar去除actionbar默认title显示,就是actionbar带的项目的名字 比如这个是wisdombj
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        //设置toolbar navigation图标
//        toolbar.setNavigationIcon(R.drawable.img_menu);
        //设置显示tool上的menu 与下面onCreateOptionsMenu关联；
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(this);
        //找到drawerlayout对象
        drawerLayout = appCompatActivity.findViewById(R.id.drawer_main);
        //设置drawerlayout不能手动滑出
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //设置状态栏透明；
        StatusTranslucent.setStatusBarFullTransparent(appCompatActivity);
        //设置toolbar的paddingtop为状态栏高度；注意toolbar控件的高度要是wrap_content
         toolbar.setPadding(0, StatusTranslucent.getStatusBarHeight(appCompatActivity),0,0);
         //创建viewpager5个界面的对象并加入集合
        arrayList.add(new HomePager(appCompatActivity));
        arrayList.add(new NewsPager(appCompatActivity));
        arrayList.add(new SmartPager(appCompatActivity));
        arrayList.add(new ZwPager(appCompatActivity));
        arrayList.add(new SettingPager(appCompatActivity));
         //设置主页面的viewpager
         setViewPager();
         //将viewpager与radiogroup进行关联，禁止滑动
          RadioGroup radioGroup=appCompatActivity.findViewById(R.id.rg_fm_content);
          //找到设置toolbar标题的Textview
          final TextView textView=appCompatActivity.findViewById(R.id.toolbar_title);
         //adiogroup默认选中首页
          radioGroup.check(R.id.home);
          //手动加载首页的数据
          arrayList.get(0).initData();
          radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home :
                        textView.setText("首页");
                        toolbar.setNavigationIcon(null);
                        //设置drawerlayout不能手动滑出
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        viewPager.setCurrentItem(0,false);

                        break;
                    case R.id.news :
                        textView.setText("新闻");
                        toolbar.setNavigationIcon(R.drawable.img_menu);
                        //设置drawerlayout能手动滑出
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.smart :
                        textView.setText("智慧服务");
                        toolbar.setNavigationIcon(R.drawable.img_menu);
                        //设置drawerlayout能手动滑出
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.zw :
                        textView.setText("政务");
                        toolbar.setNavigationIcon(null);
                        //设置drawerlayout不能手动滑出
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        viewPager.setCurrentItem(3,false);
                        break;
                    case R.id.setting :
                        textView.setText("设置");
                        toolbar.setNavigationIcon(null);
                        //设置drawerlayout不能手动滑出
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
        viewPager = appCompatActivity.findViewById(R.id.vp_content_fragment);
        final View view =null;
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
//                   因为加入了集合所以将其简化
//                 View view=null;
//                switch (position){
//                    case 0:
//                        HomePager homePager =new HomePager(appCompatActivity);
//                        view = homePager.getView();
//                        break;
//                    case 1:
//                        NewsPager newsPager = new NewsPager(appCompatActivity);
//                        view=newsPager.getView();
//                        break;
//                    case 2:
//                        SmartPager smartPager = new SmartPager(appCompatActivity);
//                        view=smartPager.getView();
//                        break;
//                    case 3:
//                        ZwPager zwPager = new ZwPager(appCompatActivity);
//                        view=zwPager.getView();
//                        break;
//                    case 4:
//                        SettingPager settingPager =new SettingPager(appCompatActivity);
//                        view=settingPager.getView();
//                        break;
//                }
                //通过集合获取到每个pager对象从而获取到相应的view
                BasePager pager =arrayList.get(position);
                View view=pager.getView();
                //加载各个页面的数据,但是为了避免加载两边的书，这里就不要调用它了
//                pager.initData();
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
               //   super.destroyItem(container, position, object); 去掉这一行
                container.removeView((View) object);
            }
        });
        //设置viewpager的切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
              //当页面切换时加载当前页面的数据；防止预加载左右两页
                arrayList.get(position).initData();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
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
