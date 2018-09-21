package com.xu.headlinehelper.di.component;

import android.app.Application;
import android.content.Context;

import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.di.module.ApplicationModule;
import com.xu.headlinehelper.di.module.ClientModule;

import javax.inject.Singleton;

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
}
