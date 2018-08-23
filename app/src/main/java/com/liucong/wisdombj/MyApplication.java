package com.liucong.wisdombj;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
    private static Context mCotext;
    @Override
    public void onCreate() {
        super.onCreate();
         mCotext=getApplicationContext();
    }
    
    public static Context getContext(){
        return mCotext;
    }
}
