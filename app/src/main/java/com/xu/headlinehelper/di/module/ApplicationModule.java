package com.xu.headlinehelper.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * @author 言吾許
 */
@Module
public class ApplicationModule {

    @Provides
    public Context provideContext(Application application) {
        return application;
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
