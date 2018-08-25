package com.liucong.wisdombj.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.fragment.ContentFragment;
import com.liucong.wisdombj.fragment.LeftFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
//        initData();
        initFragment();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_main);

    }
    private void initData() {
        //设置toolbar navigation图标
       toolbar.setNavigationIcon(R.drawable.img_menu);
       //要设置menu 必须有以下一句：否则不能用menu 可能menu是actionbar的吧
       setSupportActionBar(toolbar);
       //toolbar去除actionbar默认title显示,就是actionbar带的项目的名字 比如这个是wisdombj
       //getSupportActionBar()获取actionbar实例
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    private void initFragment() {
        //获取Fragment管理器 注意是getSupportFragmentManager
        FragmentManager fragmentManager=getSupportFragmentManager();
        //开启fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //将布局中某个id对应的View替换成fragment；
        transaction.add(R.id.fl_content,new ContentFragment());
        transaction.add(R.id.fl_left,new LeftFragment());
        transaction.commit();

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home_menu,menu);
//        return true;
//    }

   /* @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if(menu!=null){
            if(menu.getClass()== MenuBuilder.class){
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        return super.onPrepareOptionsPanel(view, menu);
    }*/
}
