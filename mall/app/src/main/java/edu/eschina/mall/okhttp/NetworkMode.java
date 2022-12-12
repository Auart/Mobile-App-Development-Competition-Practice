package edu.eschina.mall.okhttp;

import java.util.concurrent.TimeUnit;

import edu.eschina.mall.MyApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class NetworkMode {
    /**
     * 提供OkHttpClient
     */
    public static OkHttpClient providerOkHttpClient() {
        //初始化OkHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //配置缓存
        Cache cache = new Cache(MyApplication.getInstance().getCacheDir(), 2131);
        builder.cache(cache);
        builder.connectTimeout(10, TimeUnit.SECONDS) //连接超时时间
                .writeTimeout(10, TimeUnit.SECONDS)  //写，就是将数据发送到服务器端超时时间
                .readTimeout(10, TimeUnit.SECONDS);//读，将服务器端的数据下载到本地
        return builder.build();
    }
}
