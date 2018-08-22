package com.liucong.wisdombj.util;

import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;

import com.liucong.wisdombj.BuildConfig;


/**
 * 1.根据系统运行模式(debug/relase)自动判断是否输出log(默认,可设置)
 * 2.自动使用类名作为TAG(默认,可自行指定tag)
 */
public class LogUtils {
    //BuildConfig.DEBUG 判断是否是 Debug 模式,这个值在 Debug 模式下为 true，Release 模式下为 false。
    private static boolean debug = BuildConfig.DEBUG;


    /**
     * 不自定义tag
     * @param message 传入要打印的信息
     */
    public static void v(String message){
        if (debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            String tag =stackTraceElement.getFileName().split("\\.")[0]+
                    "."+stackTraceElement.getMethodName()+"()";

            Log.v(tag,message);}
    }

    /**
     * 自定义tag
     * @param tag 传入你想要的tag
     * @param message 传入要打印的信息
     */
    public static void v(String tag,String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            if(TextUtils.isEmpty(tag.trim())){
                tag =stackTraceElement.getFileName().split("\\.")[0]+
                        "."+stackTraceElement.getMethodName()+"()";}
            Log.v(tag,message);}
    }


    /**
     * 不自定义tag
     * @param message 传入要打印的信息
     */
    public static void d(String message){
        if (debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            String tag =stackTraceElement.getFileName().split("\\.")[0]+
                    "."+stackTraceElement.getMethodName()+"()";
            Log.d(tag,message);}
    }

    /**
     * 自定义tag
     * @param tag 传入你想要的tag
     * @param message 传入要打印的信息
     */
    public static void d(String tag,String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            if(TextUtils.isEmpty(tag.trim())){
                tag =stackTraceElement.getFileName().split("\\.")[0]+
                        "."+stackTraceElement.getMethodName()+"()";}
            Log.d(tag,message);}
    }

    /**
     * 不自定义tag
     * @param message 传入要打印的信息
     */
    public static void i(String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            String tag =stackTraceElement.getFileName().split("\\.")[0]+
                    "."+stackTraceElement.getMethodName()+"()";
            Log.i(tag,message);}
    }

    /**
     * 自定义tag
     * @param tag 传入你想要的tag
     * @param message 传入要打印的信息
     */
    public static void i(String tag,String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            if(TextUtils.isEmpty(tag.trim())){
                tag =stackTraceElement.getFileName().split("\\.")[0]+
                        "."+stackTraceElement.getMethodName()+"()";}
            Log.i(tag,message);}
    }

    /**
     * 不自定义tag
     * @param message 传入要打印的信息
     */
    public static void w(String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            String tag =stackTraceElement.getFileName().split("\\.")[0]+
                    "."+stackTraceElement.getMethodName()+"()";
            Log.w(tag,message);}
    }

    /**
     * 自定义tag
     * @param tag 传入你想要的tag
     * @param message 传入要打印的信息
     */
    public static void w(String tag,String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            if(TextUtils.isEmpty(tag.trim())){
                tag =stackTraceElement.getFileName().split("\\.")[0]+
                        "."+stackTraceElement.getMethodName()+"()";}
            Log.w(tag,message);}
    }

    /**
     * 不自定义tag
     * @param message 传入要打印的信息
     */
    public static void e(String message){
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            String tag =stackTraceElement.getFileName().split("\\.")[0]+
                    "."+stackTraceElement.getMethodName()+"()";
            Log.e(tag,message);}
    }

    /**
     * 自定义tag
     * @param tag 传入你想要的tag
     * @param message 传入要打印的信息
     */
    public static void e(String tag,String message){
        Log.d("debug", debug+"");
        if(debug){
            //1.获得一个StackTraceElement的数组
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            //2.StackTraceElement这个类，它是由java.lang提供的，主要作用是获取方法的调用栈信息。
            //通过分析得到数组中第三个元素是我们的方法；
            StackTraceElement stackTraceElement=stackTrace[3];
            if(TextUtils.isEmpty(tag.trim())){
                tag =stackTraceElement.getFileName().split("\\.")[0]+
                        "."+stackTraceElement.getMethodName()+"()";}
            Log.e(tag,message);}
    }


}