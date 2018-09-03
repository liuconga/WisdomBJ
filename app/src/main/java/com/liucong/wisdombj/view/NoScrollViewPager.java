package com.liucong.wisdombj.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    //isScroll 是否可以滑动 默认是否；
    private boolean isScroll=false;

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否拦截
     * 拦截:会走到自己的onTouchEvent方法里面来
     * 不拦截:事件传递给子孩子
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 1.return false:不对事件进行拦截，放行该事件。事件会被传递到当前的View的子控件中，
        // 由子控件中的dispatchTouchEvent方法进行分发处理;
        // 2.return true:不行,孩子无法处理事件;拦截该事件，将该事件交给当前View的onTouchEvent方法进行处理
        // 3.return super.onInterceptTouchEvent(ev);//不行,会有细微的滑动:默认拦截方式，和return true一样，该事件会被拦截，
        // 将该事件交给当前view的onTouchEvent方法进行处理。
        if (isScroll){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }
    /**
     * 是否消费事件
     * 消费:事件就结束
     * 不消费:往父控件传
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return false;// 可行,不消费,传给父控件
        //return true;// 可行,消费,拦截事件
        //super.onTouchEvent(ev); //不行,
        //虽然onInterceptTouchEvent中拦截了,但是如果viewpage里面子控件不是viewgroup,还是会调用这个方法.
        if (isScroll){
            return super.onTouchEvent(ev);
        }else {
            return true;
        }
    }
    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
}
