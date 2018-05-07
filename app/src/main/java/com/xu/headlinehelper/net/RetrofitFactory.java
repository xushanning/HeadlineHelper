package com.xu.headlinehelper.net;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.jessyan.progressmanager.ProgressManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/1/18.
 *
 * @author xu
 */

public class RetrofitFactory {

    private static final long TIME_OUT = 3;

    private static OkHttpClient getOkHttpClient() {
        //缓存容量
//        long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB
//        //缓存路径
//        String cacheFile = context.getCacheDir() + "/http";
//        Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                //    .cache(cache)
                .addInterceptor(requestInterceptor)
                .addInterceptor(responseInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(headerInterceptor);
        return ProgressManager.getInstance().with(builder).build();

    }

    /**
     * 响应log拦截器
     */
    private static HttpLoggingInterceptor responseInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String s) {
            if (s.length() >= 1) {
                String begin = s.substring(0, 1);
                if ("{".equals(begin)) {
                    //只打印json
                    Logger.json(s);
                }
            }

        }
    });

    /**
     * 请求信息拦截器
     */
    private static Interceptor requestInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            //请求地址
            Logger.d("请求地址:" + request.url());
            return chain.proceed(request);
        }
    };
    /**
     * 统一的请求header
     */
    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();

            return chain.proceed(builder.build());
        }
    };


    /**
     * TouTiaoApi
     *
     * @return TouTiaoApi
     */
    public static VideoApi getVideoApi() {

        return new Retrofit
                .Builder()
                .baseUrl(HttpConstants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(VideoApi.class);
    }


}
