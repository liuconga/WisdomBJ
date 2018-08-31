package com.liucong.wisdombj.pager;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liucong.wisdombj.R;
import com.liucong.wisdombj.bean.SmartServiceBean;
import com.liucong.wisdombj.bean.SmartServiceDataBean;
import com.liucong.wisdombj.fragment.LeftFragment;
import com.liucong.wisdombj.global.Constants;
import com.liucong.wisdombj.inter.OnMenuItemClickListener;
import com.liucong.wisdombj.pager.smartservice.SmartServiceGongJiJin;
import com.liucong.wisdombj.pager.smartservice.SmartServiceYiBao;
import com.liucong.wisdombj.util.LogUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SmartPager extends BasePager {
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private TextView textView_toolbar_title;
     private int mPosition;
    ArrayList<String> menus=new ArrayList<>();
    public SmartPager(AppCompatActivity activity) {
        super(activity);
        //连接网络获取服务端数据；
        getDataFromNet();
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.vp_item_smartserviece, null);
        return view;
    }


    public void initData() {

        drawerLayout = mActivity.findViewById(R.id.drawer_main);
        frameLayout = mActivity.findViewById(R.id.fl_smart);
        textView_toolbar_title = mActivity.findViewById(R.id.toolbar_title);
        //设置左侧侧边栏布局文件 因为每个都不一样
        setLeftMenuData();
    }

    private void setLeftMenuData() {
        //找到LeftFragment 对象
        //1.获取Fragment管理器 注意是getSupportFragmentManager
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        //2.通过设置的tag找到fragment
        LeftFragment leftfragment = (LeftFragment) fragmentManager.findFragmentByTag("leftfragment");
        LogUtils.d("哈哈","NewsPagerSetData()执行啦");
        //设置LeftFragment中数据
        leftfragment.setData(mPosition,menus, new OnMenuItemClickListener() {
            @Override
            public void menuItemClick(int position) {
                mPosition=position;
                switch (position){
                case 0:
                    //将toolbar标题设成公积金
                    textView_toolbar_title.setText("公积金");
                    //关闭侧边栏
                    drawerLayout.closeDrawers();
                    //将contentFragment 中新闻内容换成 新闻
                    //首先移除Framlayout这个viewgroup中所有子view
                    frameLayout.removeAllViews();
//                     //填充出一个view
//                     View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                    SmartServiceGongJiJin smartServiceGongJiJin = new SmartServiceGongJiJin(mActivity);
                    //调用其getView方法得到view
                    View view = smartServiceGongJiJin.getView();
                    //将填充冲的一个view添加到Framelayout中
                    frameLayout.addView(view);
                    //要在initData()之前将view添加到framelayout中
                    //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                    smartServiceGongJiJin.initData();
                    break;
                case 1:
                    //将toolbar标题设成医保
                    textView_toolbar_title.setText("医保");
                    //关闭侧边栏
                    drawerLayout.closeDrawers();
                    //将contentFragment 中新闻内容换成 新闻
                    //首先移除Framlayout这个viewgroup中所有子view
                    frameLayout.removeAllViews();
//                     //填充出一个view
//                     View view = View.inflate(mActivity, R.layout.newscenter_news, null);
                    SmartServiceYiBao smartServiceYiBao = new SmartServiceYiBao(mActivity);
                    //调用其getView方法得到view
                    View view_yibao = smartServiceYiBao.getView();
                    //将填充冲的一个view添加到Framelayout中
                    frameLayout.addView(view_yibao);
                    //要在initData()之前将view添加到framelayout中
                    //调用NewsCenterNews类中方法初始化newscenternews界面的数据
                    smartServiceYiBao.initData();
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
        Request request = new Request.Builder().get().url(Constants.SMART_SERVICE).build();
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
                //json解析 将json解析到相应的bean中
                Gson gson =new Gson();
                SmartServiceBean smartServiceBean = gson.fromJson(response.body().string(), SmartServiceBean.class);
//                LogUtils.d("标题",newsCenterBean.getData()+"");
                //遍历NewsCenterDataBean

                for(SmartServiceDataBean dataBean :smartServiceBean.getData()){
                    menus.add(dataBean.getTitle());
                }

            }
        });

    }
}
