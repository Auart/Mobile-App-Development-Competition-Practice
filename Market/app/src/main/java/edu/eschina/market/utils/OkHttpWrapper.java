package edu.eschina.market.utils;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class OkHttpWrapper {
    private OkHttpClient client;
    public OkHttpWrapper() {
        // 创建 OkHttpClient 实例
        this.client = new OkHttpClient();
    }

    public String get(String url) throws IOException {
        // 创建 Request 对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 发送请求并获取响应
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String post(String url, String json) throws IOException {
        // 创建 Request 对象
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json))
                .build();

        // 发送请求并获取响应
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String post(String url, FormBody formBody) throws IOException {
        // 创建 Request 对象
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        // 发送请求并获取响应
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}