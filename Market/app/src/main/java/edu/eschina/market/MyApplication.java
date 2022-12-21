package edu.eschina.market;

import android.app.Application;

/**
 * 全局上下文
 */
public class MyApplication extends Application {
    public static  MyApplication application;

    public float price=0;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    public static MyApplication getInstance(){
        return application;
    }
}
