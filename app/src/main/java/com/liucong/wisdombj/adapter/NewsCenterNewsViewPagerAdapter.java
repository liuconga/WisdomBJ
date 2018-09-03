package com.liucong.wisdombj.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.liucong.wisdombj.pager.channelpager.ChannelBasePager;
import com.liucong.wisdombj.util.LogUtils;

import java.util.ArrayList;

public class NewsCenterNewsViewPagerAdapter extends PagerAdapter {
    private ArrayList<ChannelBasePager> channelBasePagers;
    public NewsCenterNewsViewPagerAdapter(ArrayList<ChannelBasePager> channelBasePagers) {
        this.channelBasePagers=channelBasePagers;
    }

    @Override
    public int getCount() {
        return channelBasePagers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = channelBasePagers.get(position).getView();
        LogUtils.d("初始化","执行了");
        container.addView(view);
        LogUtils.d("初始化","执行了+1");
        channelBasePagers.get(position).initData();
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
