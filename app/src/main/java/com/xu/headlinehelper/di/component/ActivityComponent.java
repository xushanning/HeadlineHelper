package com.xu.headlinehelper.di.component;

import com.xu.headlinehelper.di.module.ActivityModule;
import com.xu.headlinehelper.di.scope.PerActivity;
import com.xu.headlinehelper.ui.activity.splash.SplashActivity;
import com.xu.headlinehelper.ui.activity.main.MainActivity;
import com.xu.headlinehelper.ui.activity.newtask.NewTaskActivity;
import com.xu.headlinehelper.ui.activity.settting.SettingActivity;
import com.xu.headlinehelper.ui.fragment.downloading.DownloadingFragment;
import com.xu.headlinehelper.ui.fragment.homelist.HomeListFragment;

import dagger.Component;

/**
 * @author 言吾許
 */
@PerActivity
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {
    /**
     * 注入到导航页
     *
     * @param splashActivity 目标activity
     */
    void inject(SplashActivity splashActivity);

    /**
     * 注入到main中
     *
     * @param mainActivity 目标activity
     */
    void inject(MainActivity mainActivity);

    /**
     * 注入到new task 中
     *
     * @param newTaskActivity 目标activity
     */
    void inject(NewTaskActivity newTaskActivity);

    /**
     * 注入到setting 中
     *
     * @param settingActivity 目标activity
     */
    void inject(SettingActivity settingActivity);

    /**
     * 注入到下载fragment中去
     *
     * @param downloadingFragment 目标fragment
     */
    void inject(DownloadingFragment downloadingFragment);

    /**
     * 注入到首页列表中去
     *
     * @param homeListFragment 目标fragment
     */
    void inject(HomeListFragment homeListFragment);
}
