package com.liucong.wisdombj.pager.newscenter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    public NewsCenterNews(AppCompatActivity mActivity) {
        super(mActivity);
    }

    @Override
    protected View initView(){
        //填充出一个view
        View view = View.inflate(mActivity, R.layout.newscenter_news, null);
        viewPager = view.findViewById(R.id.vp_newscenter_news);
        return view;
    }
    @Override
    public void initData(){
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
        NewsCenterBean newsCenterBean = gson.fromJson(data, NewsCenterBean.class);
        ArrayList<NewsCenterDataBean> dataBean = newsCenterBean.getData();
        NewsCenterDataBean dataBean1 = dataBean.get(0);
        final ArrayList<NewsCenterDataChildrenBean> childrenData = dataBean1.getChildren();
         mActivity.runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 for (NewsCenterDataChildrenBean childrenBean : childrenData){
                     channels.add(new ChannelBasePager(mActivity,childrenBean));
                 }
                 viewPager.setAdapter(new NewsCenterNewsViewPagerAdapter(channels));

             }
         });


    }

}
