package com.liucong.wisdombj;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();

    }
    private void initView() {
        imageView = findViewById(R.id.iv_splash);

    }
    private void initData() {
        //设置首页动画
        setAnimation();
    }



    private void setAnimation() {
        /*方法一：（1）属性动画：
        ObjectAnimator animator =ObjectAnimator.ofFloat(imageView,"rotation",
                0,360);
        animator.setDuration(1000);
        ObjectAnimator animator1 =ObjectAnimator.ofFloat(imageView,"alpha",
                0.1f,1);
        animator1.setDuration(2000);
        //设置动画集合
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.setTarget(imageView);
        animatorSet.playTogether(animator,animator1);
        animatorSet.start();
        (2) 补间动画：
        RotateAnimation rotate=new RotateAnimation
                (0,360,0.5f,0.5f);
        rotate.setDuration(2000);
        AlphaAnimation  alpha=new AlphaAnimation(0.1f,1.0f);
        alpha.setDuration(1000);
        AnimationSet animationSet =new AnimationSet(false);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(alpha);
        imageView.setAnimation(animationSet);*/
       /* 方法二：
           (1)补间动画 通过AnimationUtils加载动画
             Animation animation = AnimationUtils.
                loadAnimation(this, R.anim.splash_animation);
                  imageView.setAnimation(animation);
        (2)属性动画 通过AnimatorInflater加载动画
        Animator animator = AnimatorInflater.
                loadAnimator(this, R.animator.splash_animator);
        animator.setTarget(imageView);
        animator.start();*/
        Animator animator = AnimatorInflater.
                loadAnimator(this, R.animator.splash_animator);
        animator.setTarget(imageView);
        animator.start();


    }

}
