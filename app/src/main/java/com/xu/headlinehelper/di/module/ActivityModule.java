package com.xu.headlinehelper.di.module;

import com.xu.headlinehelper.ui.activity.main.IMainPresenter;
import com.xu.headlinehelper.ui.activity.main.MainPresenter;
import com.xu.headlinehelper.ui.activity.newtask.INewTaskPresenter;
import com.xu.headlinehelper.ui.activity.newtask.NewTaskPresenter;
import com.xu.headlinehelper.ui.activity.settting.ISettingPresenter;
import com.xu.headlinehelper.ui.activity.settting.SettingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author 言吾許
 */
@Module
public class ActivityModule {

    //presenter必须用provide提供，因为IMainPresenter 是一个接口，是抽象的,
    // 无法使用@Inject提供实例
    @Provides
    IMainPresenter provideMainPresenter(MainPresenter mainPresenter) {
        return mainPresenter;
    }

    @Provides
    INewTaskPresenter provideNewTaskPresenter(NewTaskPresenter newTaskPresenter) {
        return newTaskPresenter;
    }

    @Provides
    ISettingPresenter provideSettingPresenter(SettingPresenter settingPresenter) {
        return settingPresenter;
    }

}
