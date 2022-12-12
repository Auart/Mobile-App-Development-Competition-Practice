package edu.eschina.mall;

import android.app.Application;
import edu.eschina.mall.utils.SuperToast;

/**
 * 全局context
 */
public class MyApplication extends Application{
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        SuperToast.init(getApplicationContext());
    }

    public static MyApplication getInstance(){
        return application;
    }
}
