package edu.eschina.mall.utils;


import edu.eschina.mall.BuildConfig;

/**
 * 配置文件
 * 例如：Api地址，qq等第三方服务配置信息等
 */
public class Config {
    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final long SPLASH_DEFAULT_DELAY_TIME = 1000;
    /**
     * 端口
     */

    public static String ENDPOINT = "http://150.158.149.21:8081/at";

    public static String NETWORK_RESOURCE="http://150.158.149.21/at";


    /**
     * 网络缓存目录大小
     * 100M
     */

    public static final long NEtWORK_CACHE = 1024 * 1024 * 100;
}
