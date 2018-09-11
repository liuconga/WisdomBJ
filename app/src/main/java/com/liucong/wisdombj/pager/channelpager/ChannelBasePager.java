package com.liucong.wisdombj.pager.channelpager;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liucong.wisdombj.MyApplication;
import com.liucong.wisdombj.R;
import com.liucong.wisdombj.adapter.ChannelRecylerViewAdapter;
import com.liucong.wisdombj.adapter.NewChannelAdapter;
import com.liucong.wisdombj.bean.ListNews;
import com.liucong.wisdombj.bean.NewsCenterDataChildrenBean;
import com.liucong.wisdombj.bean.NewsChannelData;
import com.liucong.wisdombj.bean.NewsChannelDataBean;
import com.liucong.wisdombj.bean.TopicNews;
import com.liucong.wisdombj.global.Constants;
import com.liucong.wisdombj.pager.newscenter.NewsCenterNews;
import com.liucong.wisdombj.util.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChannelBasePager {
    private int sign = 1000;
    protected AppCompatActivity mActivity;
    private View view;
    private NewsCenterDataChildrenBean dataChildrenBean;
    private TextView textView;
    private ViewPager viewPager;
    private ArrayList<ArrayList<ImageView>> arrayLists1 = new ArrayList<>();
    private ArrayList<ImageView>[] arrayLists = new ArrayList[12];
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(0, 3000);
                    break;
            }

        }
    };
    private List<TopicNews> topnews;
    private LinearLayout linearLayout;
    private int mPostion;
    private int currentPositon;
    private int positonWai;
    private RecyclerView recyclerView;
    private List<ListNews> listNews;
    private ChannelRecylerViewAdapter myAdapter;


    public ChannelBasePager(AppCompatActivity mActivity, NewsCenterDataChildrenBean dataChildrenBean) {
        this.mActivity = mActivity;
        this.dataChildrenBean = dataChildrenBean;
        view = initView();
    }

    /**
     * 让子类去实现initview
     */
    protected View initView() {
        view = View.inflate(mActivity, R.layout.news_channel, null);
//        viewPager = view.findViewById(R.id.vp_news_channel);
//        linearLayout = view.findViewById(R.id.ll_news_channel);
        recyclerView = view.findViewById(R.id.recyclerview_channel);
        return view;
    }

    /**
     * 回调方法，主要是将，handler传到NewsCenterNews中
     */
    public void setHandler(NewsCenterNews.RemoveHandler removeHandler) {
        removeHandler.remove(handler);
    }

    /**
     * 初始化数据 子类可实现可不实现
     */
    @SuppressLint("HandlerLeak")
    public void initData(int mPostion, int positionWai) {
        currentPositon = mPostion;
        this.positonWai = positionWai;
        //从网络获取数据
        getDataFromNet();
    }

    public View getView() {
        return view;
    }

    public NewsCenterDataChildrenBean getDataChildrenBean() {
        return dataChildrenBean;
    }

    /**
     * 从互联网获取数据
     */
    private void getDataFromNet() {
        String url = Constants.BASE + dataChildrenBean.getUrl();
        //我们使用okhttp
        //首先创建okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建一个请求对象Request 默认是get请求；
        Request request = new Request.Builder().get().url(url).build();
        //通过上述两个参数 构建call对象
        Call call = okHttpClient.newCall(request);
        //通过call的equeue方法提交异步请求 通过回调回去数据；
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.d("okhttp请求", "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.d("okhttp请求", "请求成功");
                String data = response.body().string();
                parseJson(data);
            }
        });

    }

    public void parseJson(String data) {
        //json解析 将json解析到相应的bean中
        Gson gson = new Gson();
        final NewsChannelData newsChannelData = gson.fromJson(data, NewsChannelData.class);
        final NewsChannelDataBean newsChannelDataBean = newsChannelData.getData();
        listNews = newsChannelDataBean.getNews();
        LogUtils.d("新闻列表", listNews.size() + "");
        topnews = newsChannelDataBean.getTopnews();
//        Log.d("我是TopNews", topnews.size() + " 执行了");
        mActivity.runOnUiThread(new Runnable() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void run() {
                recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
                //设置recylerview的adapter，初始化recylerview
                myAdapter = new ChannelRecylerViewAdapter(listNews);
                recyclerView.setAdapter(myAdapter);
                //添加头
                setHeader(recyclerView);

                viewPager.setAdapter(new NewChannelAdapter(topnews));
                if (currentPositon == 0) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() / 2);
                } else {
                    viewPager.setCurrentItem(currentPositon);
                }

                setBannerIndicator();
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        //传送当前的positon给上一层viewpager
                        mPostion = position;
                        handler.removeCallbacksAndMessages(null);
                        handler.sendEmptyMessageDelayed(0, 3000);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        //当轮播图切换时，切换小圆点选中状态；
                        position = position % (viewPager.getAdapter().getCount() / 10000);
                        for (int i = 0; i < arrayLists[positonWai].size(); i++) {
                            if (i == position) {
                                arrayLists[positonWai].get(i).setImageResource(R.drawable.banner_dot_select);
                            } else {
                                arrayLists[positonWai].get(i).setImageResource(R.drawable.banner_dot);
                            }
                        }

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

//                Log.d("孩子1",linearLayout.getChildCount()+" ");
                //让viewpager实现自动轮播
                handler.sendEmptyMessageDelayed(0, 3000);
                viewPager.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
//                                handler.removeMessages(0);
                                handler.removeCallbacksAndMessages(null);
                                break;

                            case MotionEvent.ACTION_UP:
                                handler.sendEmptyMessageDelayed(0, 3000);
                                break;
                        }
                        return false;
                    }
                });

                //t添加尾
//                setFooter(recyclerView);
            }

        });

    }

    //设置小圆点
    public void setBannerIndicator() {
//        linearLayout.removeAllViews();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        //如果相应数组位置为空则新创建imageview集合
        if (arrayLists[positonWai] == null) {
            for (int i = 0; i < topnews.size(); i++) {
                ImageView view = new ImageView(MyApplication.getContext());
                if (i == currentPositon) {
                    view.setImageResource(R.drawable.banner_dot_select);
                } else {
                    view.setImageResource(R.drawable.banner_dot);
                }
                view.setPadding(20, 0, 0, 0);
                imageViews.add(view);
                linearLayout.addView(view);
            }
            arrayLists[positonWai] = imageViews;
        }
        //否则从相应数组位置的集合中取出imageview
        else {
            for (int i = 0; i < arrayLists[positonWai].size(); i++) {
//                Log.d("长度", arrayLists[positonWai].get(i) + "");
                ImageView imageView = arrayLists[positonWai].get(i);
                if (i == currentPositon) {
                    imageView.setImageResource(R.drawable.banner_dot_select);
                }
                //判断子控件是否有父控件；移除父控件所有view；
                ViewGroup parent = (ViewGroup) imageView.getParent();
                if (parent != null) {
                    parent.removeAllViews();
                }
                linearLayout.addView(imageView);
            }
        }
    }

    /**
     * 添加recylerview头布局
     */
    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(mActivity).inflate(R.layout.header, view, false);
        viewPager = header.findViewById(R.id.vp_news_channel);
        linearLayout = header.findViewById(R.id.ll_news_channel);
        myAdapter.addHeaderView(header);
    }

    public void JiBanner(NewsCenterNews.JiLuBanner jiLuBanner) {
        jiLuBanner.JiLU(mPostion);
    }


    public void setFooter(RecyclerView view) {
        View footer = LayoutInflater.from(mActivity).inflate(R.layout.footer, view, false);
        myAdapter.addFooterView(footer);
    }
}


