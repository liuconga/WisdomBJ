package com.liucong.wisdombj.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liucong.wisdombj.MyApplication;
import com.liucong.wisdombj.bean.TopicNews;
import com.liucong.wisdombj.global.Constants;

import java.util.List;

public class NewChannelAdapter extends PagerAdapter {
    private List<TopicNews> topicNews;
    public NewChannelAdapter(List<TopicNews> topicNews) {
        this.topicNews=topicNews;
    }

    @Override
    public int getCount() {
        return topicNews.size()*10000;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position=position%topicNews.size();
        ImageView imageView=new ImageView(MyApplication.getContext());
        ViewGroup.LayoutParams layoutParams =container.getLayoutParams();
        layoutParams.height= 400;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //Glide加载图片
        Glide.with(MyApplication.getContext())
                .load(Constants.BASE+topicNews.get(position).getTopimage())
                .into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
