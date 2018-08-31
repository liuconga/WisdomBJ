package com.liucong.wisdombj.fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.adapter.LeftMenuRecyclerAdapter;
import com.liucong.wisdombj.inter.OnMenuItemClickListener;
import com.liucong.wisdombj.inter.OnRecyclerviewItemClickListener;

import java.util.ArrayList;

public class LeftFragment extends BaseFragment {
    private DrawerLayout drawerLayout;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.left_fragment, container, false);
        return view;
    }
    @Override
    protected void initData() {
        super.initData();
        //找到drawerlayout对象
        drawerLayout = appCompatActivity.findViewById(R.id.drawer_main);
    }
    public void setData(int mPositon, ArrayList<String> menus, final OnMenuItemClickListener listener){
        //找到recylerview对象
        final RecyclerView recyclerView = appCompatActivity.findViewById(R.id.recyclerview_leftfragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(appCompatActivity));
        //让左侧边栏条目 默认点击成第一个 新闻 或者是公积金；
        listener.menuItemClick(mPositon);
        LeftMenuRecyclerAdapter adapter =new LeftMenuRecyclerAdapter(mPositon,menus,
                new OnRecyclerviewItemClickListener() {
                    @Override
                    //监听的回调
                    public void onItemClickListener(View v, int position) {
                        //将position传入对应的类，新闻中心或者智慧服务，对应的类中实现了menuItemClick方法,这就是回调吧
                        listener.menuItemClick(position);
                    }
                });
        recyclerView.setAdapter(adapter);
    }
}

