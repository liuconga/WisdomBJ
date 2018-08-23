package com.liucong.wisdombj.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liucong.wisdombj.MyApplication;
import com.liucong.wisdombj.R;
import com.liucong.wisdombj.global.Constants;
import com.liucong.wisdombj.util.SPUtils;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private  int[] vp_res =new int[]{R.drawable.guide_1,
            R.drawable.guide_2,R.drawable.guide_3};
    private ImageView imageView;
    private LinearLayout linearLayout;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initView() {
        viewPager = findViewById(R.id.vp_guide);
        imageView = findViewById(R.id.iv_guide);
        linearLayout = findViewById(R.id.ll_guide);
        button = findViewById(R.id.bt_guide);
        viewPager.addOnPageChangeListener(new MyPageChangeListener());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(MyApplication.getContext(), Constants.FIRST_RUN,false);
                //进入首页
                Intent intent = new Intent();
                intent.setClass(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void initData() {
        viewPager.setAdapter(new MyPagerAdapter());
    }

    /**
     * OnPageChangeListener的实现类
     */
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         *  红点应该随着ViewPager的滑动而移动, 怎样判断ViewPager滑动了多少?
         可以根据OnPageChangeListener中的onPageScrolled方法获取页面滑动的百分比
         // 页面滑动时
         // 参1: 当前滑动的是第几个页面, 从0开始
         // 参2: 滑动百分比 (0.0f - 1.0f)
         // 参3: 滑动像素数 (0-ViewPager宽度)
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /*    如何移动一个View? 我们学过的方法里, 修改View的margin, 就可以改变View的位置
            要移动红点, 可以给红点ImageView设置 android:layout_marginLeft, 改变它的左外边距,
            就可以让它在父布局中的位置发生变化*/
            //获取最左边小圆点的位置
            int mDotDistance= linearLayout.getChildAt(1).getLeft()-linearLayout.getChildAt(0).getLeft();
            // 获取红点ImageView的布局参数
          FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) imageView.getLayoutParams();
            // 根据百分比, 修改布局参数的leftMargin值
            layoutParams.leftMargin = (int) (mDotDistance * positionOffset + position * mDotDistance);
            //设置imageViw的LayoutParams，必须设置才能生效；
            imageView.setLayoutParams(layoutParams);
        }

        @Override
        public void onPageSelected(int position) {
         if(position==vp_res.length-1){
             button.setVisibility(View.VISIBLE);
         }else {
             button.setVisibility(View.GONE);
         }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * pagerAdapter实现类
     */
    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return vp_res.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView =new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(vp_res[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            // 将View从ViewPager中移除, 不能调用super
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
