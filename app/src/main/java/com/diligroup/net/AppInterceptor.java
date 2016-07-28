package com.diligroup.net;

import android.content.Context;

import com.diligroup.utils.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * 请求拦截器
 */
public class AppInterceptor implements Interceptor {
    private Context context;

    public AppInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //请求之前的拦截器
        //TODO
        //修改请求
        Request.Builder builder = request.newBuilder()
                .addHeader("Content-Type", "application/json");
//        SharedPreferenceUtil sp = new SharedPreferenceUtil("cache");
//        String url = request.url().toString();

        if(NetUtils.isNetworkAvailable(context)) {
            //有网络时，不使用缓存
            builder.addHeader("Cache-Control", "no-cache");
//            sp.putString(tag, url);
        }else{
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            builder.addHeader("Cache-Control",
                    "public, only-if-cached, max-stale=" + maxStale);
//            String cacheUrl = sp.getString(tag,url);
//            builder.url(cacheUrl);
        }
        Response response = chain.proceed(builder.build());
        //请求之后的拦截器
        //TODO
        return response;
    }
}
