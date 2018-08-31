package com.liucong.wisdombj.pager;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liucong.wisdombj.MyApplication;
import com.liucong.wisdombj.R;
import com.liucong.wisdombj.bean.NewsCenterBean;
import com.liucong.wisdombj.bean.NewsCenterDataBean;
import com.liucong.wisdombj.fragment.LeftFragment;
import com.liucong.wisdombj.global.Constants;
import com.liucong.wisdombj.inter.OnMenuItemClickListener;
import com.liucong.wisdombj.pager.newscenter.NewsCenterHudong;
import com.liucong.wisdombj.pager.newscenter.NewsCenterNews;
import com.liucong.wisdombj.pager.newscenter.NewsCenterTopic;
import com.liucong.wisdombj.pager.newscenter.NewsCenterZutu;
import com.liucong.wisdombj.util.LogUtils;
import com.liucong.wisdombj.util.SPUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsPager extends BasePager {
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private TextView textView_toolbar_title;
    private int mPosition;
    ArrayList<String> menus=new ArrayList<>();
    private NewsCenterBean newsCenterBean;

    /**
     * retcode : 200
     * data : [{"id":10000,"title":"新闻","type":1,"children":[{"id":10007,"title":"北京","type":1,"url":"/10007/list_1.json"},{"id":10006,"title":"中国","type":1,"url":"/10006/list_1.json"},{"id":10008,"title":"国际","type":1,"url":"/10008/list_1.json"},{"id":10010,"title":"体育","type":1,"url":"/10010/list_1.json"},{"id":10091,"title":"生活","type":1,"url":"/10091/list_1.json"},{"id":10012,"title":"旅游","type":1,"url":"/10012/list_1.json"},{"id":10095,"title":"科技","type":1,"url":"/10095/list_1.json"},{"id":10009,"title":"军事","type":1,"url":"/10009/list_1.json"},{"id":10093,"title":"时尚","type":1,"url":"/10093/list_1.json"},{"id":10011,"title":"财经","type":1,"url":"/10011/list_1.json"},{"id":10094,"title":"育儿","type":1,"url":"/10094/list_1.json"},{"id":10105,"title":"汽车","type":1,"url":"/10105/list_1.json"}]},{"id":10002,"title":"专题","type":10,"url":"/10006/list_1.json","url1":"/10007/list1_1.json"},{"id":10003,"title":"组图","type":2,"url":"/10008/list_1.json"},{"id":10004,"title":"互动","type":3,"excurl":"","dayurl":"","weekurl":""}]
     * extend : [10007,10006,10008,10014,10012,10091,10009,10010,10095]
     */


    public NewsPager(AppCompatActivity activity) {
        super(activity);
        //连接网络获取服务端数据；
        LogUtils.d("联网获取数据","我联网了");


    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_news, null);
        return view;
    }


    /**
     * 1.初始化新闻中心数据，新闻中心分为四个专题：新闻，专题，组图，互动；通过侧滑菜单来进行切换
     */
    public void initData() {

        //每次切换RadioButton（切换Pager页面时） 清楚menus中内容 因为每次请求网络都往menus中添加了内容
        menus.clear();
        //获取缓存侧边栏的数据
        String leftmenucache = (String) SPUtils.get(MyApplication.getContext(), "leftmenucache", "");
        //如果缓存的左侧边栏数据不为空
        if(!TextUtils.isEmpty(leftmenucache)){
            //解析缓存的字符串
            parseJson(leftmenucache);
            LogUtils.d("缓存","我执行了缓存");
        }
        menus.clear();
        //请求网络
        getDataFromNet();

    }

    /**
     * 设置左侧边栏布局文件，因为每个界面的左侧边栏都不同
     */
    private void setLeftMenuData() {
        //找到LeftFragment 对象
        //1.获取Fragment管理器 注意是getSupportFragmentManager
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        //2.通过设置的tag找到fragment
        LeftFragment leftfragment = (LeftFragment) fragmentManager.findFragmentByTag("leftfragment");
        LogUtils.d("哈哈", "NewsPagerSetData()执行啦");
        //设置LeftFragment中数据
        leftfragment.setData(mPosition, menus, new OnMenuItemClickListener() {
            @Override
            public void menuItemClick(int position) {
                //记录上次点击的位置
                mPosition = position;
                switch (position) {
                    case 0:
                        //将toolbar标题设成新闻
                        textView_toolbar_title.setText("新闻");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成 新闻
                        //首先移除Framlayout这个viewgroup中所有子view

                        frameLayout.removeAllViews();
//                     //填充出一个view
//                     View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterNews newsCenterNews = new NewsCenterNews(mActivity);
                        //调用其getView方法得到view
                        View view = newsCenterNews.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterNews.initData();
                        break;
                    case 1:
                        //将toolbar标题设成专题
                        textView_toolbar_title.setText("专题");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成专题
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
                        //填充出一个view
                        //View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterTopic newsCenterTopic = new NewsCenterTopic(mActivity);
                        //调用其getView方法得到view
                        View view_topic = newsCenterTopic.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view_topic);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterTopic.initData();
                        break;
                    case 2:
                        //将toolbar标题设成组图
                        textView_toolbar_title.setText("组图");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成专题
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
                        //填充出一个view
                        //View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterZutu newsCenterZutu = new NewsCenterZutu(mActivity);
                        //调用其getView方法得到view
                        View view_zutu = newsCenterZutu.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view_zutu);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterZutu.initData();
                        break;
                    case 3:
                        //将toolbar标题设成互动
                        textView_toolbar_title.setText("互动");
                        //关闭侧边栏
                        drawerLayout.closeDrawers();
                        //将contentFragment 中新闻内容换成专题
                        //首先移除Framlayout这个viewgroup中所有子view
                        frameLayout.removeAllViews();
                        //填充出一个view
                        //View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                        //将其抽到一NewsCenterNews类中，省的这里代码太臃肿；得到newscenternews实例
                        NewsCenterHudong newsCenterHudong = new NewsCenterHudong(mActivity);
                        //调用其getView方法得到view
                        View view_hudong = newsCenterHudong.getView();
                        //将填充冲的一个view添加到Framelayout中
                        frameLayout.addView(view_hudong);
                        //要在initData()之前将view添加到framelayout中
                        //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                        newsCenterHudong.initData();
                        break;
                }
            }
        });

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
                SPUtils.put(MyApplication.getContext(),"leftmenucache",data);
            }
        });

    }
    public void parseJson(String data){

        //json解析 将json解析到相应的bean中
        Gson gson =new Gson();
        newsCenterBean = gson.fromJson(data, NewsCenterBean.class);

        //遍历NewsCenterDataBean
        for(NewsCenterDataBean dataBean :newsCenterBean.getData()){
            menus.add(dataBean.getTitle());
        }
        //设置左侧边栏布局必须在主线程中完成；
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                drawerLayout = mActivity.findViewById(R.id.drawer_main);
                frameLayout = mActivity.findViewById(R.id.fl_newscenter);
                textView_toolbar_title = mActivity.findViewById(R.id.toolbar_title);
                //设置左侧侧边栏布局文件 因为每个都不一样
                setLeftMenuData();
            }
        });
    }
}