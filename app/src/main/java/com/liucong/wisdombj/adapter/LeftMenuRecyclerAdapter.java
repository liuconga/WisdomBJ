package com.liucong.wisdombj.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liucong.wisdombj.R;
import com.liucong.wisdombj.inter.OnRecyclerviewItemClickListener;
import com.liucong.wisdombj.util.LogUtils;

public class LeftMenuRecyclerAdapter extends RecyclerView.Adapter<LeftMenuRecyclerAdapter.ViewHolder> {
    private int mSelection;
    private OnRecyclerviewItemClickListener listener;
    private String[] menus;
       //构造方法中接收传入的OnRecyclerviewItemClickListener
    public LeftMenuRecyclerAdapter(int mPosition,String[] menus,OnRecyclerviewItemClickListener listener) {
        this.listener = listener;
        this.menus=menus;
       mSelection=mPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.d("执行","onCreateViewHolder我执行了+1");
        View view = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.leftfragment_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        //这里 我们可以拿到点击的item的view 对象，所以在这里给view设置点击监听，
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将监听传递给自定义接口
                listener.onItemClickListener(v, (int) v.getTag());
                //一点击条目mSelection发生变化，通过notifyDataSetChanged();重新加载条目，刷新条目；
                mSelection=(int) v.getTag();
                notifyDataSetChanged();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogUtils.d("执行","onBindViewHolder我执行了+1");
        holder.textView.setText(menus[position]);
        //给view设置tag以作为参数传递到监听回调方法中
        holder.itemView.setTag(position);
        //一开始因为mSelection默认初始化为0 所以默认选中的是新闻；
        //enabled是可用的 在字体颜色选择器中用的就是enabled
        holder.textView.setEnabled(mSelection==position);
    }

    @Override
    public int getItemCount() {
        return menus.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            LogUtils.d("执行","ViewHOlder我执行了+1");
            textView = itemView.findViewById(R.id.tv_item1_leftfragment);

        }
    }
}
