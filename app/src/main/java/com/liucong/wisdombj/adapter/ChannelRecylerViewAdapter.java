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

import java.util.List;

public class ChannelRecylerViewAdapter
        extends RecyclerView.Adapter<ChannelRecylerViewAdapter.ViewHoder> {
    private static final int TYPE_HEADER =100000;
    private static final int TYPE_NORMAL =200000 ;
    private List<ListNews> listNews;
    private View mHeadView;
    public View getmHeadView() {
        return mHeadView;
    }
    public void setmHeadView(View mHeadView) {
        this.mHeadView = mHeadView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeadView==null)
            return TYPE_NORMAL;
        if(position==0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public ChannelRecylerViewAdapter(List<ListNews> listNews) {
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mHeadView!=null&&viewType==TYPE_HEADER)
        {
            return  new ViewHoder(mHeadView);}
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listnews, parent, false);
        ViewHoder viewHoder = new ViewHoder(view);
        LogUtils.d("新闻列表你", "我走了");
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER)
        { return;}
        if(mHeadView!=null){
            position=position-1;
        }
        ListNews mylistNews = listNews.get(position);
        Glide.with(MyApplication.getContext()).load(Constants.BASE + mylistNews
                .getListimage()).into(holder.imageView);
        holder.textView.setText(mylistNews.getTitle());
        holder.textView_time.setText(mylistNews.getPubdate());

    }

    @Override
    public int getItemCount() {
        return mHeadView==null?listNews.size():listNews.size()+1;
    }


    class ViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView_time;

        ViewHoder(View itemView) {
            super(itemView);
            if(itemView == mHeadView) return;
            imageView = itemView.findViewById(R.id.listnews_iv);
            textView = itemView.findViewById(R.id.listnews_tv);
            textView_time = itemView.findViewById(R.id.listnews_tv_time);
        }
    }
}
