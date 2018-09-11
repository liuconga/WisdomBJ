package com.liucong.wisdombj.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liucong.wisdombj.MyApplication;
import com.liucong.wisdombj.R;
import com.liucong.wisdombj.bean.ListNews;
import com.liucong.wisdombj.global.Constants;
import com.liucong.wisdombj.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ChannelRecylerViewAdapter
        extends RecyclerView.Adapter<ChannelRecylerViewAdapter.ViewHoder> {
    private List<View> mHeaderViews=new ArrayList<>();
    private List<View> mFooterViews=new ArrayList<>();
    private List<ListNews> listNews;
    public ChannelRecylerViewAdapter(List<ListNews> listNews) {
        this.listNews = listNews;
    }
    private boolean isHeaderViewPos(int position){
        return position<getHeadersCount();
    }
    private boolean isFooterViewsPos(int position){
        return position>=getHeadersCount()+listNews.size();
    }
    public int getHeadersCount() {
        return mHeaderViews.size();
    }
    public int getFootersCount() {
        return mFooterViews.size();
    }
    public void addHeaderView(View view){
        mHeaderViews.add(view);
    }
    public void addFooterView(View view){
        mFooterViews.add(view);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType<mHeaderViews.size()&&mHeaderViews.get(viewType)!=null){
            return new ViewHoder(mHeaderViews.get(viewType));
        }else if(viewType>=mHeaderViews.size()+listNews.size()
                &&mFooterViews.get(viewType-mHeaderViews.size()-listNews.size())!=null){
            return new ViewHoder(mFooterViews.get(viewType-mHeaderViews.size()-listNews.size()));
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listnews, parent, false);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        if(isHeaderViewPos(position)){
            return;
        }else if(isFooterViewsPos(position)){
            return;
        }
        position=position-mHeaderViews.size();
        ListNews mylistNews = listNews.get(position);
        Glide.with(MyApplication.getContext()).load(Constants.BASE + mylistNews
                .getListimage()).into(holder.imageView);
        holder.textView.setText(mylistNews.getTitle());
        holder.textView_time.setText(mylistNews.getPubdate());
    }
    @Override
    public int getItemCount() {
        return listNews.size()+getHeadersCount()+getFootersCount();
    }
    class ViewHoder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView_time;

        ViewHoder(View itemView) {
            super(itemView);
            LogUtils.d("条目","");
            imageView = itemView.findViewById(R.id.listnews_iv);
            textView = itemView.findViewById(R.id.listnews_tv);
            textView_time = itemView.findViewById(R.id.listnews_tv_time);
        }
    }
}

