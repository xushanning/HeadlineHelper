package com.xu.headlinehelper.di.component;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.di.module.ApplicationModule;
import com.xu.headlinehelper.di.module.ClientModule;
import com.xu.headlinehelper.net.VideoApi;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @author 言吾許
 */
@Singleton
@Component(modules = {ApplicationModule.class, ClientModule.class})
public interface AppComponent {
    /**
     * 注入到 application中去
     *
     * @param application app
     */
    void inject(MyApplication application);

    /**
     * 提供上下文对象
     *
     * @return 上下文
     */
    Context getContext();

    /**
     * 提供application
     *
     * @return application
     */
    Application getApplication();

    /**
     * 提供setting dao
     *
     * @return DownLoadSettingDbBeanDao
     */
    DownLoadSettingDbBeanDao getSettingDao();

    /**
     * 提供video Api
     *
     * @return api
     */
    VideoApi getVideoApi();

    /**
     * 提供gson
     *
     * @return gson
     */
    Gson getGson();


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder clientModule(ClientModule clientModule);

        Builder appModule(ApplicationModule applicationModule);

        AppComponent build();
    }

}
