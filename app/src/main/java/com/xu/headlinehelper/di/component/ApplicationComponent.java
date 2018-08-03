package com.xu.headlinehelper.di.component;

import android.content.Context;

import com.xu.headlinehelper.di.module.ApplicationModule;

import dagger.Component;

/**
 * @author 言吾許
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    /**
     * 提供上下文对象
     *
     * @return 上下文
     */
    Context getContext();
}
