package com.xu.headlinehelper.di.module;

import com.xu.headlinehelper.ui.activity.main.IMainPresenter;
import com.xu.headlinehelper.ui.activity.main.MainPresenter;
import com.xu.headlinehelper.ui.activity.newtask.INewTaskPresenter;
import com.xu.headlinehelper.ui.activity.newtask.NewTaskPresenter;
import com.xu.headlinehelper.ui.activity.settting.ISettingPresenter;
import com.xu.headlinehelper.ui.activity.settting.SettingPresenter;
import com.xu.headlinehelper.ui.fragment.downloading.DownloadingPresenter;
import com.xu.headlinehelper.ui.fragment.downloading.IDownloadingPresenter;
import com.xu.headlinehelper.ui.fragment.homelist.HomeListPresenter;
import com.xu.headlinehelper.ui.fragment.homelist.IHomeListPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 言吾許
 */
@Module
public class ActivityModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

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

    @Provides
    IDownloadingPresenter provideDownloadingPresenter(DownloadingPresenter downloadingPresenter) {
        return downloadingPresenter;
    }

    @Provides
    IHomeListPresenter provideHomeListPresenter(HomeListPresenter homeListPresenter) {
        return homeListPresenter;
    }
}
