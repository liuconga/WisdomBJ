package com.liucong.wisdombj.pager.newscenter;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.liucong.wisdombj.R;
import com.liucong.wisdombj.adapter.NewsCenterNewsViewPagerAdapter;
import com.liucong.wisdombj.bean.NewsCenterBean;
import com.liucong.wisdombj.bean.NewsCenterDataBean;
import com.liucong.wisdombj.bean.NewsCenterDataChildrenBean;
import com.liucong.wisdombj.global.Constants;
import com.liucong.wisdombj.pager.NewsCenterBase;
import com.liucong.wisdombj.pager.channelpager.ChannelBasePager;
import com.liucong.wisdombj.util.LogUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsCenterNews extends NewsCenterBase {

    private ViewPager viewPager;
    private ArrayList<ChannelBasePager> channels;
    private TabLayout tabLayout;
    private int[] mPositon;

    public interface RemoveHandler{

        void remove(android.os.Handler handler);
    }
    public interface JiLuBanner{

        void JiLU(int position);
    }
    public NewsCenterNews(AppCompatActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView(){
        //填充出一个view
        View view = View.inflate(mActivity, R.layout.newscenter_news, null);
        viewPager = view.findViewById(R.id.vp_newscenter_news);
        tabLayout = view.findViewById(R.id.tablayout_news_channel);
        return view;
    }
    @Override
    public void initData(){

        //设置TabLayout各个tab之间的分割线
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        //设置分割线的高度
        linearLayout.setDividerPadding(15);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(mActivity,R.drawable.divider_vertical));
        //初始化顶部导航标签,与viewpager进行关联
        tabLayout.setupWithViewPager(viewPager);
        channels=new ArrayList<>();
        //联网获取数据
        getDataFromNet();
    }
    /**
     * 从互联网获取数据
     */
    private void getDataFromNet() {

        //我们使用okhttp
        //首先创建okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建一个请求对象Request 默认是get请求；
        Request request = new Request.Builder().get().url(Constants.NEWS_CENTER).build();
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
                String data =response.body().string();
                parseJson(data);
            }
        });

    }
    public void parseJson(String data){
        //json解析 将json解析到相应的bean中
        Gson gson =new Gson();
        final NewsCenterBean newsCenterBean = gson.fromJson(data, NewsCenterBean.class);
        ArrayList<NewsCenterDataBean> dataBean = newsCenterBean.getData();
        NewsCenterDataBean dataBean1 = dataBean.get(0);
        final ArrayList<NewsCenterDataChildrenBean> childrenData = dataBean1.getChildren();
         mActivity.runOnUiThread(new Runnable() {
             @SuppressLint("ClickableViewAccessibility")
             @Override
             public void run() {
                 for (NewsCenterDataChildrenBean childrenBean : childrenData){
                     channels.add(new ChannelBasePager(mActivity,childrenBean));
                 }
                 mPositon=new int[channels.size()];
                 viewPager.setAdapter(new NewsCenterNewsViewPagerAdapter(channels));
                 //默认选中界面0
                 channels.get(0).initData(0,0);
                 viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                     @Override
                     public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

                     }

                     @Override
                     public void onPageSelected(final int position) {
                         //记录当前界面bannner轮播到的哪个页面
                         channels.get(position).JiBanner(new JiLuBanner() {
                             @Override
                             public void JiLU(int currentposition) {
                                 mPositon[position]=currentposition;
                                 Log.d("位置",position+"");
                             }
                         });
                         //当界面被选中时initData
                         channels.get(position).initData(mPositon[position],position);
                         //关闭循环
                         channels.get(position).setHandler(new RemoveHandler() {
                             @Override
//                             通过回调，先移除Handler的所有消息，然后在从新发送
                             public void remove(android.os.Handler handler) {
                                  handler.removeCallbacksAndMessages(null);
                                  handler.sendEmptyMessageDelayed(0,3000);
                             }
                         });
                         //停止其他界面的viewpager轮播清除他们的消息
                         if(position!=0){
                         channels.get(position-1).setHandler(new RemoveHandler() {
                             @Override
//                             通过回调，先移除Handler的所有消息，然后在从新发送
                             public void remove(android.os.Handler handler) {
                                 handler.removeCallbacksAndMessages(null);
//                                 handler.sendEmptyMessageDelayed(0,3000);
                             }
                         });}
                         if (position!=channels.size()-1){
                         channels.get(position+1).setHandler(new RemoveHandler() {
                             @Override
//                             通过回调，先移除Handler的所有消息，然后在从新发送
                             public void remove(android.os.Handler handler) {
                                 handler.removeCallbacksAndMessages(null);
//                                 handler.sendEmptyMessageDelayed(0,3000);
                             }
                         });}
                     }

                     @Override
                     public void onPageScrollStateChanged(int state) {

                     }
                 });

             }
         });


    }



}
