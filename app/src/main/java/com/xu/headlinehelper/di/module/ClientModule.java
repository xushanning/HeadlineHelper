package com.xu.headlinehelper.di.module;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.db.dao.DaoMaster;
import com.xu.headlinehelper.db.dao.DaoSession;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.net.HttpConstants;
import com.xu.headlinehelper.net.VideoApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
 * @author 言吾許
 * 提供一些第三方客户端实例
 */
@Module
public class ClientModule {
    private static final long TIME_OUT = 3;

    @Singleton
    @Provides
    public VideoApi provideVideoApi(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(HttpConstants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(VideoApi.class);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);
        //    .cache(cache)
        builder.addInterceptor(chain -> {
            final Request request = chain.request();
            //请求地址
            Logger.d("请求地址:" + request.url());
            return chain.proceed(request);
        });

        builder.addInterceptor(new HttpLoggingInterceptor(s -> {
            if (s.length() >= 1) {
                String begin = s.substring(0, 1);
                if ("{".equals(begin)) {
                    //只打印json
                    Logger.json(s);
                }
            }

        }));
        builder.addInterceptor(chain -> {
            Request.Builder builder1 = chain.request().newBuilder();
            return chain.proceed(builder1.build());
        });

        return builder.build();
    }

    /**
     * 提供daosession
     *
     * @param application 上下文
     * @return daoSession
     */
    @Provides
    @Singleton
    public DaoSession provideDaoSession(Application application) {
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(application, "video-helper-db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        return mDaoMaster.newSession();
    }

    /**
     * 提供SettingDao
     *
     * @param daoSession DaoSession
     * @return SettingDao
     */
    @Singleton
    @Provides
    public DownLoadSettingDbBeanDao provideSettingDao(DaoSession daoSession) {
        return daoSession.getDownLoadSettingDbBeanDao();
    }


}
